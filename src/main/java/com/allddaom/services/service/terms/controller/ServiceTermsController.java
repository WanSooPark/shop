package com.allddaom.services.service.terms.controller;

import com.allddaom.services.service.terms.service.ServiceTermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/terms")
@RequiredArgsConstructor
public class ServiceTermsController {

   private final ServiceTermsService termsService;

   @GetMapping("/info")
   public String info(Model model) {
      model.addAttribute("item", termsService.find());
      return "terms/info";
   }
}
