package com.shop.models.members.domain;

import com.shop.commons.entity.BaseEntity;
import com.shop.commons.utils.Sha256;
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

    private String address;

    private String level;

    private String tel;

    private String administrativeNotes; // 관리자 메모

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    private Role role;

}
