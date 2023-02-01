package com.shop.services.service.sign.dto;

import com.shop.models.members.domain.Member;
import com.shop.models.members.domain.MemberStatus;
import com.shop.models.members.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {
    private String name;
    private String email;
    private String password;
    //    private String username; // email
    //    private String nickname;
    //    private String tel;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(this.name);
        member.setEmail(this.email);
        member.setUsername(this.email);
        member.setPassword(passwordEncoder.encode(this.password));
//        member.setAddress(this.address);

        member.setNickname("");
        member.setAdministrativeNotes("");
        member.setStatus(MemberStatus.NORMAL);
        member.setRole(Role.USER);
        return member;
    }
}
