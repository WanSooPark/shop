package com.allddaom.services.service.mypage.controller;

import com.allddaom.commons.errors.exceptions.BusinessException;
import com.allddaom.commons.security.CurrentAccount;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.mypage.dto.MypageProfileResponse;
import com.allddaom.services.service.mypage.dto.form.ServiceProfileForm;
import com.allddaom.services.service.mypage.service.MypageProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class MypageProfileController {

    private final MypageProfileService mypageProfileService;

    @GetMapping("/form")
    public String profileFormView(@CurrentAccount Member member, Model model) {
        ServiceProfileForm profile = mypageProfileService.getProfileForm(member);
        model.addAttribute("profile", profile);
        return "mypage/profile_form";
    }

    @PostMapping
    public String updateProfile(@CurrentAccount Member member, @Valid ServiceProfileForm dto, RedirectAttributes attributes) {
        try {
            MypageProfileResponse response = mypageProfileService.update(dto, member);
        } catch (BusinessException businessException) {
            attributes.addAttribute("message", businessException.getMessage());
            attributes.addAttribute("exception", businessException);
            return "mypage/profile_form";
        }
        return "redirect:/mypage";
    }

    /**
     * 회원 탈퇴
     */
    @PostMapping("/withdraw")
    public String withdraw(@CurrentAccount Member member, HttpSession httpSession) {
        mypageProfileService.withdraw(member);
        httpSession.removeAttribute("sessionId");
        return "redirect:/logout";
    }

}
