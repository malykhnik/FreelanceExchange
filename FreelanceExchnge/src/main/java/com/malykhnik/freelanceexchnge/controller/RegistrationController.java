package com.malykhnik.freelanceexchnge.controller;

import com.malykhnik.freelanceexchnge.model.User;
import com.malykhnik.freelanceexchnge.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (userService.findByUsername(userForm.getUsername()) != null){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        } else {
            userService.saveUser(userForm);
//            UsernamePasswordAuthenticationToken authToken =
//                    new UsernamePasswordAuthenticationToken(userForm.getUsername(), userForm.getPassword());
//            Authentication authentication = authenticationManager.authenticate(authToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        return "redirect:/getAllOrders";
    }
}