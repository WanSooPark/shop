package com.shop.services.service.sign.service;

import com.shop.models.members.domain.Member;
import com.shop.services.service.members.service.MemberService;
import com.shop.services.service.sign.dto.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public Member signUp(SignUpForm signUpForm) {
        return null;
    }
}
