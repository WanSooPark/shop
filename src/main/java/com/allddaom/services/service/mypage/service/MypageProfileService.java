package com.allddaom.services.service.mypage.service;

import com.allddaom.models.addresses.domain.Address;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.members.service.MemberService;
import com.allddaom.services.service.mypage.dto.MypageProfileResponse;
import com.allddaom.services.service.mypage.dto.form.ServiceProfileForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class MypageProfileService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public MypageProfileResponse getProfile(Member member) {
        return MypageProfileResponse.of(member);
    }

    public ServiceProfileForm getProfileForm(Member member) {
        return ServiceProfileForm.of(member);
    }

    public MypageProfileResponse update(ServiceProfileForm dto, Member member) {
        member = dto.update(member);

        Address address = dto.getAddress();
        if (!ObjectUtils.isEmpty(address)) {
            member.updateAddress(address);
        }

        if (!dto.isEmptyPassword()) {
            member.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        member = memberService.update(member);
        return MypageProfileResponse.of(member);
    }

    public void withdraw(Member member) {
        memberService.withdraw(member);
    }

}
