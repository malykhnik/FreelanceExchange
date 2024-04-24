package com.malykhnik.freelanceexchnge.controller.entity_controller;

import com.malykhnik.freelanceexchnge.model.FreelanceAnnouncement;
import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @GetMapping("/newAnnouncement")
    public String createNewAnnouncement(Model model) {
        model.addAttribute("announcement", new FreelanceAnnouncement());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("user_name", name);
        return "new_announcement";
    }

    @PostMapping("/save")
    public String saveNewAnnouncement(@ModelAttribute("announcement") FreelanceAnnouncement freelanceAnnouncement) {
        announcementService.saveNewAnnouncement(freelanceAnnouncement);
        return "redirect:/getMainPage";
    }
}
