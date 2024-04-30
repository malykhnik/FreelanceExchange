package com.malykhnik.freelanceexchnge.controller.entity_controller;

import com.malykhnik.freelanceexchnge.model.EventCatcher;
import com.malykhnik.freelanceexchnge.model.FreelanceAnnouncement;
import com.malykhnik.freelanceexchnge.model.Order;
import com.malykhnik.freelanceexchnge.service.AnnouncementService;
import com.malykhnik.freelanceexchnge.service.EventCatcherService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;
    private final EventCatcherService eventCatcherService;

    @GetMapping("/newService")
    public String createNewAnnouncement(Model model) {
        model.addAttribute("announcement", new FreelanceAnnouncement());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("user_name", name);
        return "new_announcement";
    }

    @PostMapping("/saveAnnouncement")
    public String saveNewAnnouncement(@ModelAttribute("announcement") FreelanceAnnouncement freelanceAnnouncement,
                                      @Autowired EventCatcher eventCatcher) {
        announcementService.saveNewAnnouncement(freelanceAnnouncement);

        eventCatcher.setAction("Новая услуга");
        eventCatcher.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        eventCatcher.setDate(LocalDateTime.now());

        eventCatcherService.saveEventCatcher(eventCatcher);

        return "redirect:/getMainPage";
    }

    @GetMapping("/deleteAnnouncement/{id}")
    public String deleteAnnouncement(@PathVariable Long id,
                                     @Autowired EventCatcher eventCatcher) {
        announcementService.deleteAnnouncementById(id);

        eventCatcher.setAction("Удаление услуги");
        eventCatcher.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        eventCatcher.setDate(LocalDateTime.now());

        eventCatcherService.saveEventCatcher(eventCatcher);

        List<? extends GrantedAuthority> authorities = (List<? extends GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Set<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        if (userRoles.contains("ROLE_admin")) {
            return "redirect:/getMainPage";
        }
        return "redirect:/edit";
    }

    @GetMapping("/editAnnouncement/{id}")
    public String editAnnouncement(@PathVariable Long id,
                                   Model model,
                                   @Autowired EventCatcher eventCatcher) {
        Optional<FreelanceAnnouncement> announcement = announcementService.findAnnouncementById(id);
        model.addAttribute("announcement", announcement);

        eventCatcher.setAction("Редактирование услуги");
        eventCatcher.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        eventCatcher.setDate(LocalDateTime.now());

        eventCatcherService.saveEventCatcher(eventCatcher);

        model.addAttribute("user_name", getUsernameFromContext());

        List<? extends GrantedAuthority> authorities = (List<? extends GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Set<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        if (userRoles.contains("ROLE_admin")) {
            return "redirect:/getMainPage";
        }
        return "edit_my_announcement";
    }

    private String getUsernameFromContext() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
