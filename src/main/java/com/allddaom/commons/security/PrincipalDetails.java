package com.allddaom.commons.security;

import com.allddaom.models.members.domain.Member;
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

    public static PrincipalDetails of(Member member) {
        return new PrincipalDetails(member, Collections.singleton(new SimpleGrantedAuthority(member.getRole()
                .name())));
    }

}
