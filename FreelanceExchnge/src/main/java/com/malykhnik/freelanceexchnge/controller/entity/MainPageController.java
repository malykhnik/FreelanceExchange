package com.malykhnik.freelanceexchnge.controller.entity;

import com.malykhnik.freelanceexchnge.model.*;
import com.malykhnik.freelanceexchnge.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final OrderService orderService;
    private final AnnouncementService announcementService;
    private final EventCatcherService eventCatcherService;
    private final PurchaseRequestService requestService;
    private final UserService userService;

    @GetMapping("/getMainPage")
    public String showAllOrders(Model model) {
        ArrayList<Order> ordersList = (ArrayList<Order>) orderService.getAllOrders();
        model.addAttribute("orders", ordersList);

//        ArrayList<PurchaseRequest> requests = (ArrayList<PurchaseRequest>) getRequestsByCurrentUser();
//        model.addAttribute("requests", requests);

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

        model.addAttribute("user_name", getUsernameFromContext());

        return "edit";
    }

    @GetMapping("/actions")
    public String showActions(Model model) {
        model.addAttribute("user_name", getUsernameFromContext());

        List<EventCatcher> actions = eventCatcherService.getAllActions();
        model.addAttribute("actions", actions);

        return "actions";
    }

    private String getUsernameFromContext() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private List<PurchaseRequest> getRequestsByCurrentUser() {
        User user = userService.findByUsername(getUsernameFromContext());
        return requestService.getAllRequestsByUserTo(user.getId());
    }
}
