package com.allddaom.services.service.policy.controller;

import com.allddaom.services.service.policy.service.ServicePolicyService;
import com.allddaom.services.service.terms.service.ServiceTermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/policy")
@RequiredArgsConstructor
public class ServicPolicyController {

   private final ServicePolicyService servicePolicyService;

   @GetMapping("/info")
   public String info(Model model) {
      model.addAttribute("item", servicePolicyService.find());
      return "policy/info";
   }
}
