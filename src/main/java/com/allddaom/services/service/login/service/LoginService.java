package com.allddaom.services.service.login.service;

import com.allddaom.commons.utils.Sha256;
import com.allddaom.models.members.domain.Member;
import com.allddaom.models.members.exception.ServiceException;
import com.allddaom.models.members.infra.repo.MemberRepository;
import com.allddaom.services.service.login.dto.LoginInfoReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member loginProcess(LoginInfoReq loginInfoReq, HttpSession session) throws ServiceException, NoSuchAlgorithmException {

        Member member = memberRepository.findByEmail(loginInfoReq.getEmail());

        if (member == null) {
            throw new ServiceException("이메일 계정이 존재하지 않습니다.");
        }

//      String password = passwordEncoder.encode(loginInfoReq.getPassword());
        String password = Sha256.encrypt(loginInfoReq.getPassword());
        if (!password.equals(member.getPassword())) {
            throw new ServiceException("비밀번호가 일치하지 않습니다.");
        }

        session.setAttribute("userId", member.getEmail());
        session.setAttribute("userName", member.getName());
        session.setAttribute("role", member.getRole());
        session.setAttribute("userInfo", member);

        return member;
    }

}
