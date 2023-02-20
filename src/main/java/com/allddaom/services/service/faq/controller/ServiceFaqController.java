package com.allddaom.services.service.faq.controller;

import com.allddaom.services.service.faq.service.ServiceFaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faq")
@RequiredArgsConstructor
public class ServiceFaqController {

    private final ServiceFaqService serviceFaqService;

    @GetMapping("/list")
    public String faq_all(Model model) {
        model.addAttribute("list", serviceFaqService.findAll());
        return "faq/faq_list";
    }

}
