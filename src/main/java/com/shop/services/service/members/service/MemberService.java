package com.shop.services.service.members.service;

import com.shop.commons.utils.Sha256;
import com.shop.models.members.domain.Member;
import com.shop.models.members.exception.ServiceException;
import com.shop.models.members.infra.repo.MemberRepository;
import com.shop.services.service.login.dto.LoginInfoReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole()
                        .toString())
                .build();
    }

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