package com.allddaom.services.service.sign.service;

import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.members.service.MemberService;
import com.allddaom.services.service.sign.dto.SignUpForm;
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
        Member member = signUpForm.toEntity(passwordEncoder);
        member = memberService.signUp(member);
        return member;
    }
}
