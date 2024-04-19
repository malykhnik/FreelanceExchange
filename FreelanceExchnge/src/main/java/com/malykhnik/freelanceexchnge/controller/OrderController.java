package com.malykhnik.freelanceexchnge.controller;

import com.malykhnik.freelanceexchnge.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class OrderController {
    @RequestMapping("/")
    public String hello(@RequestParam(required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/getAllOrders")
    public String showAllOrders(Model model) {
        model.addAttribute("user_role", "customer");
        return "all_orders";
    }

    @GetMapping("/newOrder")
    public String createNewOrder(Map<String, Object> model) {
        Order order = new Order();
        model.put("newOrder", order);
        return "new_order";
    }
}
