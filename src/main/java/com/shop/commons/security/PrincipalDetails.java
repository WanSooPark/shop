package com.shop.commons.security;

import com.shop.models.members.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

@Getter
public class PrincipalDetails extends User {

    private final Member member;

    public PrincipalDetails(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.member = member;
    }

    public static PrincipalDetails of(Member account) {
        return new PrincipalDetails(account, Collections.singleton(new SimpleGrantedAuthority(account.getRole()
                .name())));
    }

}
