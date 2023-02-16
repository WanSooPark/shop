package com.allddaom.services.service.sign.controller;

import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.sign.dto.SignUpForm;
import com.allddaom.services.service.sign.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String signUpView(Model model) {
        SignUpForm signUpForm = new SignUpForm();
        model.addAttribute("signUpForm", signUpForm);
        return "sign/sign_up";
    }

    @PostMapping("/up")
    public String signUp(@Valid SignUpForm signUpForm, RedirectAttributes redirectAttributes) {
        Member member = signService.signUp(signUpForm);

        redirectAttributes.addFlashAttribute("username", member.getUsername());
        return "redirect:/login";
    }

}
