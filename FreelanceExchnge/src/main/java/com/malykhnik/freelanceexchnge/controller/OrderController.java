package com.malykhnik.freelanceexchnge.controller;

import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.service.impls.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@Controller
@AllArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    @RequestMapping("/")
    public String hello(@RequestParam(required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/getAllOrders")
    public String showAllOrders(Model model) {
        ArrayList<Order> ordersList = (ArrayList<Order>) orderService.getAllOrders();
        model.addAttribute("orders", ordersList);
        model.addAttribute("user_role", "customer");
        return "all_orders";
    }

    @GetMapping("/newOrder")
    public String createNewOrder(Model model) {
        model.addAttribute("order", new Order());
        return "new_order";
    }

    @PostMapping("/save")
    public String saveNewOrder(@ModelAttribute("newOrder") Order order) {
        orderService.saveNewOrder(order);
        return "redirect:/getAllOrders";
    }
}
