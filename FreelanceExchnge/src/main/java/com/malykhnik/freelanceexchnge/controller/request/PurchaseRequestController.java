package com.malykhnik.freelanceexchnge.controller.request;

import com.malykhnik.freelanceexchnge.model.FreelanceAnnouncement;
import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.model.PurchaseRequest;
import com.malykhnik.freelanceexchnge.model.User;
import com.malykhnik.freelanceexchnge.service.AnnouncementService;
import com.malykhnik.freelanceexchnge.service.OrderService;
import com.malykhnik.freelanceexchnge.service.PurchaseRequestService;
import com.malykhnik.freelanceexchnge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PurchaseRequestController {
    private final OrderService orderService;
    private final AnnouncementService announcementService;
    private final PurchaseRequestService requestService;
    private final UserService userService;

    @GetMapping("/sendRequest/{id}")
    public String sendRequest(@PathVariable Long id, Model model) {
        if (findRole() == -1) {
            Optional<Order> orderOptional = orderService.findOrderById(id);
            Order order;
            if (orderOptional.isPresent()) {
                order = orderOptional.get();
            } else {
                throw new RuntimeException("Такого order нет");
            }

            User userTo = order.getUser();

            Optional<User> freelancerOptional = Optional.ofNullable(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
            User userFrom;
            if (freelancerOptional.isPresent()) {
                userFrom = freelancerOptional.get();
                PurchaseRequest request = new PurchaseRequest(userFrom, userTo, order, "Waiting");
                if (requestService.findByOrderAndUsers(String.valueOf(order.getId()),
                        String.valueOf(userFrom.getId()),
                        String.valueOf(userTo.getId())).isEmpty()) {
                    requestService.saveRequest(request);
                } else {

                    ////////////ЗАМЕНИТЬ НА ВСПЫЛВАЮЩЕЕ ОКНО НА СТРАНИЦЕ!!!!!!!!!!!////////////
                    throw new RuntimeException("Такой запрос уже есть!");

                }
            } else {
                throw new RuntimeException("Пользователь с таким username нет");
            }
        } else if (findRole() == 1) {
            Optional<FreelanceAnnouncement> announcementOptional = announcementService.findAnnouncementById(id);
            if (announcementOptional.isPresent()) {
                FreelanceAnnouncement announcement = announcementOptional.get();
                User user = announcement.getUser();
                model.addAttribute("username", user.getUsername());
            }
            model.addAttribute("send", true);

            ArrayList<Order> ordersList = (ArrayList<Order>) orderService.getAllOrdersByUsername(getUsernameFromContext());
            model.addAttribute("orders", ordersList);

            return "edit";
        }

        return "redirect:/getMainPage";
    }

    @GetMapping("/sendRequestToFr")
    public String sendRequestToFr(@RequestParam("id") Long id,
                                  @RequestParam("username") String username) {
        Optional<Order> orderOptional = orderService.findOrderById(id);
        Optional<User> userOptional = Optional.ofNullable(userService.findByUsername(username));

        Order order;
        if (orderOptional.isPresent()) {
            order = orderOptional.get();
        } else  {
            throw new RuntimeException("Такого заказа не существует");
        }

        User userTo;
        if (userOptional.isPresent()) {
            userTo = userOptional.get();
        } else {
            throw new RuntimeException("Такого юзера нет");
        }

        User userFrom = userService.findByUsername(getUsernameFromContext());

        PurchaseRequest request = new PurchaseRequest(userFrom, userTo, order,"Waiting");
        if (requestService.findByOrderAndUsers(String.valueOf(order.getId()),
                String.valueOf(userFrom.getId()),
                String.valueOf(userTo.getId())).isEmpty()) {
            requestService.saveRequest(request);
        } else {

            ////////////ЗАМЕНИТЬ НА ВСПЫЛВАЮЩЕЕ ОКНО НА СТРАНИЦЕ!!!!!!!!!!!////////////
            throw new RuntimeException("Такой запрос уже есть!");

        }

        return "redirect:/getMainPage";
    }

    @GetMapping("/requestCustomer")
    public String getRequestCustomer(Model model) {
        ArrayList<PurchaseRequest> requests = (ArrayList<PurchaseRequest>) getRequestsByCurrentUser();
        model.addAttribute("requests", requests);

        return "customer_requests";
    }

    @GetMapping("/acceptRequest/{id}")
    public String acceptRequest(@PathVariable(name = "id") Long id) {
        Optional<PurchaseRequest> requestOptional = requestService.findById(id);
        if (findRole() == 1) {
            if (requestOptional.isPresent()) {
                PurchaseRequest request = requestOptional.get();
                request.setStatus("Accepted");
                requestService.saveRequest(request);
            }
        }
        return "redirect:/requestCustomer";
    }

    @GetMapping("/rejectRequest/{id}")
    public String rejectRequest(@PathVariable(name = "id") Long id)  {
        Optional<PurchaseRequest> requestOptional = requestService.findById(id);
        if (findRole() == 1) {
            if (requestOptional.isPresent()) {
                PurchaseRequest request = requestOptional.get();
                requestService.deleteRequest(request);
            }
        }
        return "redirect:/requestCustomer";
    }

    @GetMapping("/tasksFreelancer")
    public String tasksFreelancer(Model model) {
        List<PurchaseRequest> tasksFr = requestService.getAllRequestsByFreelancerWithStatus(getUsernameFromContext(), "Accepted");
        List<PurchaseRequest> tasksFromCustomer = requestService.getRequestsFromCustomer(getUsernameFromContext(), "Waiting");
        tasksFr.addAll(tasksFromCustomer);

        model.addAttribute("tasks_fr", tasksFr);

        return "tasks_freelancer";
    }

    private String getUsernameFromContext() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private List<PurchaseRequest> getRequestsByCurrentUser() {
        User user = userService.findByUsername(getUsernameFromContext());
        return requestService.getAllRequestsByUserTo(user.getId());
    }

    private int findRole() {
        List<? extends GrantedAuthority> authorities = (List<? extends GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Set<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        if (userRoles.contains("ROLE_admin")) {
            return 0;
        } else if (userRoles.contains("ROLE_customer")) {
            return 1;
        }
        return -1; //если фрилансер
    }

}
