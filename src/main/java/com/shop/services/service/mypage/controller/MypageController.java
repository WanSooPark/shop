package com.shop.services.service.mypage.controller;

import com.shop.commons.security.CurrentAccount;
import com.shop.models.members.domain.Member;
import com.shop.services.service.mypage.dto.MypageProfileResponse;
import com.shop.services.service.mypage.service.MypageProfileService;
import com.shop.services.service.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;
    private final MypageProfileService mypageProfileService;

    @GetMapping
    public String mypageView(@CurrentAccount Member member, Model model) {
        MypageProfileResponse profile = mypageProfileService.getProfile(member);
        model.addAttribute("profile", profile);
        return "mypage/mypage";
    }

}
