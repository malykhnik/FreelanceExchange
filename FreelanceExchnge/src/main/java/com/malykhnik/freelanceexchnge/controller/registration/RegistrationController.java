package com.malykhnik.freelanceexchnge.controller.registration;

import com.malykhnik.freelanceexchnge.model.EventCatcher;
import com.malykhnik.freelanceexchnge.model.User;
import com.malykhnik.freelanceexchnge.service.EventCatcherService;
import com.malykhnik.freelanceexchnge.service.UserService;
import com.malykhnik.freelanceexchnge.utils.DateFormatter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final EventCatcherService eventCatcherService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm,
                          BindingResult bindingResult,
                          Model model, @Autowired EventCatcher eventCatcher) {

        eventCatcher.setAction("registration");
        eventCatcher.setUsername(userForm.getUsername());
        eventCatcher.setDate(DateFormatter.formatCurrentDate(LocalDateTime.now()));

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            eventCatcher.setAction("Несовпадение паролей");
            return "registration";
        }
        if (userService.findByUsername(userForm.getUsername()) != null){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            eventCatcher.setAction("Некорректный username");
            return "registration";
        } else {
            userService.saveUser(userForm);
            eventCatcherService.saveEventCatcher(eventCatcher);
        }

        return "redirect:/login";
    }
}