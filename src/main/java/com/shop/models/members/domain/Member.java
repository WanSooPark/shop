package com.shop.models.members.domain;

import com.shop.commons.entity.BaseEntity;
import com.shop.commons.utils.Sha256;
import com.shop.models.addresses.domain.Address;
import com.shop.services.service.members.dto.MemberFormDto;
import lombok.*;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String name;

    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private String level;

    private String tel;

    private String administrativeNotes; // 관리자 메모

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto) throws NoSuchAlgorithmException {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        String password = Sha256.encrypt(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }

}
