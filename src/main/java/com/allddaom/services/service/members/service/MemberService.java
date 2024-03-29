package com.allddaom.services.service.members.service;

import com.allddaom.models.members.domain.Member;
import com.allddaom.models.members.infra.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    /**
     * 회원가입
     * 서비스 - 사용자 가입
     */
    public Member signUp(Member member) {
        return repository.save(member);
    }

    /**
     * 회원추가
     * 어드민 - 관리자 추가
     */
    public Member add(Member member) {
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

    public Member findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Member update(Member member) {
        return repository.save(member);
    }

    public void withdraw(Member member) {
        member.withdraw();
        repository.save(member);
    }
}
