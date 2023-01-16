package com.shop.commons.security;

import com.shop.models.members.domain.Member;
import com.shop.services.service.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.findByEmail(username);

        if (member == null) {
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                .username(member.getEmail())
//                .password(member.getPassword())
                .roles(member.getRole()
                        .toString())
                .build();
    }

}
