package com.malykhnik.freelanceexchnge.controller.autentication;

import com.malykhnik.freelanceexchnge.model.EventCatcher;
import com.malykhnik.freelanceexchnge.service.EventCatcherService;
import com.malykhnik.freelanceexchnge.utils.DateFormatter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
class LoginController {

    private final EventCatcherService eventCatcherService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/customLogin")
    public String login() {
        return "login";
    }

    @PostMapping("/customLogin")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        @Autowired EventCatcher eventCatcher) {

        eventCatcher.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        eventCatcher.setDate(DateFormatter.formatCurrentDate(LocalDateTime.now()));

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication auth = authenticationManager.authenticate(authReq);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

            eventCatcher.setAction("Успешно залогинился");
            eventCatcherService.saveEventCatcher(eventCatcher);
            return "redirect:/getMainPage";
        } catch (AuthenticationException e) {
            eventCatcher.setAction("Ошибка при логине");
            eventCatcherService.saveEventCatcher(eventCatcher);
            return "login";
        }
    }
}