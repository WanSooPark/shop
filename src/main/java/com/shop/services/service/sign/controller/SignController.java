package com.shop.services.service.sign.controller;

import com.shop.models.members.domain.Member;
import com.shop.services.service.sign.dto.SignUpForm;
import com.shop.services.service.sign.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/sign")
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @GetMapping("/up")
    public String signUpView() {
        return "sign/sign_up";
    }

    @PostMapping("/up")
    public String signUp(@Valid SignUpForm signUpForm, RedirectAttributes redirectAttributes) {
        Member member = signService.signUp(signUpForm);

        redirectAttributes.addAttribute("username", member.getUsername());
        return "redirect:/login";
    }

}
