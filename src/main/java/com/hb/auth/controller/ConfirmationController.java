package com.hb.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("${apiPrefix}/confirmation")
public class ConfirmationController {
    @GetMapping(value = "/email")
    public String confirmEmail(@RequestParam String email, Model model) {
        model.addAttribute("email",email);
        return "confirm-email";
    }
}
