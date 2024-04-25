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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @GetMapping("/newService")
    public String createNewAnnouncement(Model model) {
        model.addAttribute("announcement", new FreelanceAnnouncement());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("user_name", name);
        return "new_announcement";
    }

    @PostMapping("/saveAnnouncement")
    public String saveNewAnnouncement(@ModelAttribute("announcement") FreelanceAnnouncement freelanceAnnouncement) {
        announcementService.saveNewAnnouncement(freelanceAnnouncement);
        return "redirect:/getMainPage";
    }

    @GetMapping("/deleteAnnouncement/{id}")
    public String deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncementById(id);
        return "redirect:/edit";
    }

    @GetMapping("/editAnnouncement/{id}")
    public String editAnnouncement(@PathVariable Long id, Model model) {
        Optional<FreelanceAnnouncement> announcement = announcementService.findAnnouncementById(id);
        model.addAttribute("announcement", announcement);
        return "edit_my_announcement";
    }
}
