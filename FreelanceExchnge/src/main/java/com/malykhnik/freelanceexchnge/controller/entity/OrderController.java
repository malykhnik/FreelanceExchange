package com.malykhnik.freelanceexchnge.controller.entity;

import com.malykhnik.freelanceexchnge.model.EventCatcher;
import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.service.EventCatcherService;
import com.malykhnik.freelanceexchnge.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final EventCatcherService eventCatcherService;

    @GetMapping("/newOrder")
    public String createNewOrder(Model model) {
        model.addAttribute("order", new Order());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("user_name", name);
        return "new_order";
    }

    @PostMapping("/saveOrder")
    public String saveNewOrder(@ModelAttribute("order") Order order,
                               @Autowired EventCatcher eventCatcher) {

        eventCatcher.setAction("Новый заказ");
        eventCatcher.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        eventCatcher.setDate(LocalDateTime.now());

        eventCatcherService.saveEventCatcher(eventCatcher);

        orderService.saveNewOrder(order);
        return "redirect:/getMainPage";
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id,
                              @Autowired EventCatcher eventCatcher) {
        orderService.deleteOrderById(id);

        eventCatcher.setAction("Удаление заказа");
        eventCatcher.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        eventCatcher.setDate(LocalDateTime.now());

        eventCatcherService.saveEventCatcher(eventCatcher);

        List<? extends GrantedAuthority> authorities = (List<? extends GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Set<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        if (userRoles.contains("ROLE_admin")) {
            return "redirect:/getMainPage";
        }

        return "redirect:/edit";
    }

    @GetMapping("/editOrder/{id}")
    public String editOrder(@PathVariable Long id,
                            Model model,
                            @Autowired EventCatcher eventCatcher) {
        Optional<Order> orderOptional = orderService.findOrderById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            model.addAttribute("order", order);

            model.addAttribute("order", order);
            model.addAttribute("user_name", getUsernameFromContext());
        } else {
            throw new RuntimeException("Такого заказа не существует");
        }

        eventCatcher.setAction("Редактирование заказа");
        eventCatcher.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        eventCatcher.setDate(LocalDateTime.now());

        eventCatcherService.saveEventCatcher(eventCatcher);

        List<? extends GrantedAuthority> authorities = (List<? extends GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Set<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        if (userRoles.contains("ROLE_admin")) {
            return "edit_my_order";
        }

        return "edit_my_order";
    }

    @PostMapping("/editOrder")
    public String editOrder(@ModelAttribute("order") Order order) {
        orderService.saveNewOrder(order);
        return "redirect:/getMainPage";
    }

    private String getUsernameFromContext() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
