package com.malykhnik.freelanceexchnge.controller.entity_controller;

import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.service.impls.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@AllArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderService;

    @GetMapping("/newOrder")
    public String createNewOrder(Model model) {
        model.addAttribute("order", new Order());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("user_name", name);
        return "new_order";
    }

    @PostMapping("/save")
    public String saveNewOrder(@ModelAttribute("order") Order order) {
        orderService.saveNewOrder(order);
        return "redirect:/getMainPage";
    }
}
