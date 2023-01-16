package com.shop.services.service.members.service;

import com.shop.models.members.domain.Member;
import com.shop.models.members.infra.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return repository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = repository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public Member findByEmail(String email) {
        return repository.findByEmail(email);
    }

}