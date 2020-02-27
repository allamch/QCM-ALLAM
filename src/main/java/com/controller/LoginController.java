
package com.controller;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.entity.User;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(@AuthenticationPrincipal User user) {
        if (user != null) {
            return "redirect:/";
        }
        return "login";
    }
}
