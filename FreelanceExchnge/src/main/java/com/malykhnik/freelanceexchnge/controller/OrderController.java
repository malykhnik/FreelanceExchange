package com.malykhnik.freelanceexchnge.controller;

import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.service.impls.OrderServiceImpl;
import lombok.AllArgsConstructor;
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
        //передать в модель имя текущего авторизованного пользователя и передать
        model.addAttribute("username", "Nikita");
        model.addAttribute("user_role", "customer");
        return "all_orders";
    }

    @GetMapping("/newOrder")
    public String createNewOrder(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("user_role", "customer");
        return "new_order";
    }

    @PostMapping("/save")
    public String saveNewOrder(@ModelAttribute("order") Order order, Model model) {
        orderService.saveNewOrder(order);
        return "redirect:/getAllOrders";
    }
}
