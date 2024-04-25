package com.malykhnik.freelanceexchnge.controller.entity_controller;

import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/newOrder")
    public String createNewOrder(Model model) {
        model.addAttribute("order", new Order());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("user_name", name);
        return "new_order";
    }

    @PostMapping("/saveOrder")
    public String saveNewOrder(@ModelAttribute("order") Order order) {
        orderService.saveNewOrder(order);
        return "redirect:/getMainPage";
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return "redirect:/edit";
    }

    @GetMapping("/editOrder/{id}")
    public String editOrder(@PathVariable Long id, Model model) {
        Optional<Order> order = orderService.findOrderById(id);
        model.addAttribute("order", order);
        model.addAttribute("user_name", getUsernameFromContext());

        return "edit_my_order";
    }

    private String getUsernameFromContext() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
