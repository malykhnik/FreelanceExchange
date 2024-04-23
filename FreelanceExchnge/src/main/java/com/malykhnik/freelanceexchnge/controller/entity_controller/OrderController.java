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

    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("user_role", "Nikita");
        return "index";
    }

    @GetMapping("/getAllOrders")
    public String showAllOrders(Model model) {
        ArrayList<Order> ordersList = (ArrayList<Order>) orderService.getAllOrders();
        model.addAttribute("orders", ordersList);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        model.addAttribute("user_role", role);

        return "all_orders";
    }

    @GetMapping("/newOrder")
    public String createNewOrder(Model model) {
        model.addAttribute("order", new Order());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        model.addAttribute("user_role", role);
        return "new_order";
    }

    @PostMapping("/save")
    public String saveNewOrder(@ModelAttribute("order") Order order, Model model) {
        orderService.saveNewOrder(order);
        return "redirect:/getAllOrders";
    }
}
