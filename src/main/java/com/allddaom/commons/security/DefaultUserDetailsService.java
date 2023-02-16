package com.allddaom.commons.security;

import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.findByUsername(username);

        if (ObjectUtils.isEmpty(member)) {
            throw new UsernameNotFoundException(username);
        }

        return PrincipalDetails.of(member);
    }

}
