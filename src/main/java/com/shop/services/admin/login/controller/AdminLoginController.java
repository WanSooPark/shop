package com.shop.services.admin.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/login")
@RequiredArgsConstructor
public class AdminLoginController {

    @GetMapping
    public String adminLoginView() {
        return "admin/login";
    }

}
