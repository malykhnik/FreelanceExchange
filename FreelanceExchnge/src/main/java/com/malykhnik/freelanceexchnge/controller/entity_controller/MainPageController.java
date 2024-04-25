package com.malykhnik.freelanceexchnge.controller.entity_controller;

import com.malykhnik.freelanceexchnge.model.FreelanceAnnouncement;
import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.service.AnnouncementService;
import com.malykhnik.freelanceexchnge.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
@AllArgsConstructor
public class MainPageController {

    private final OrderService orderService;
    private final AnnouncementService announcementService;

    @GetMapping("/getMainPage")
    public String showAllOrders(Model model) {
        ArrayList<Order> ordersList = (ArrayList<Order>) orderService.getAllOrders();
        model.addAttribute("orders", ordersList);

        ArrayList<FreelanceAnnouncement> freelanceList = (ArrayList<FreelanceAnnouncement>) announcementService.getAllAnnouncements();
        model.addAttribute("announcements", freelanceList);

        model.addAttribute("user_name", getUsernameFromContext());

        return "main_page";
    }

    @GetMapping("/edit")
    public String editAccountInfo(Model model) {
        ArrayList<Order> ordersList = (ArrayList<Order>) orderService.getAllOrdersByUsername(getUsernameFromContext());
        model.addAttribute("orders", ordersList);

        ArrayList<FreelanceAnnouncement> freelanceList = (ArrayList<FreelanceAnnouncement>) announcementService.findAnnouncementByUsername(getUsernameFromContext());
        model.addAttribute("announcements", freelanceList);

        return "edit";
    }

    private String getUsernameFromContext() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
