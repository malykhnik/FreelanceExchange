package com.malykhnik.freelanceexchnge.controller.request;

import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.model.PurchaseRequest;
import com.malykhnik.freelanceexchnge.model.User;
import com.malykhnik.freelanceexchnge.service.OrderService;
import com.malykhnik.freelanceexchnge.service.PurchaseRequestService;
import com.malykhnik.freelanceexchnge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PurchaseRequestController {
    private final OrderService orderService;
    private final PurchaseRequestService requestService;
    private final UserService userService;

    @GetMapping("/sendRequest/{id}")
    public String sendRequest(@PathVariable Long id) {
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
            PurchaseRequest request = new PurchaseRequest(userFrom.getId(), userTo.getId(), order, "Waiting");
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

        return "redirect:/getMainPage";
    }
}
