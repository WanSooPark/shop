package com.shop.models.members.domain;

import com.shop.commons.entity.BaseEntity;
import com.shop.models.addresses.domain.Address;
import lombok.*;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

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

    private String regionNumber; // 일반 전화 지역번호
    private String generalPhoneNumber1; // 일반 전화
    private String generalPhoneNumber2; // 일반 전화

    private String cellPhoneNumber; // 휴대 전화

    private boolean availableReceiveSms; // 문자 수신 여부

    private boolean availableReceiveEmail; // 메일 수신 여부

    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별

    private String birthday; // 생년월일

    @Enumerated(EnumType.STRING)
    private BirthCalendar birthCalendar; // 양력 음력

    private String administrativeNotes; // 관리자 메모

    private String recommenderUsername; // 추천인 아이디

    private boolean agreePrivacyTerms; // 개인정보 약관 동의 여부

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void updateAddress(Address address) {
        if (!ObjectUtils.isEmpty(this.address)) {
            if (this.address.toString()
                    .equals(this.address.toString())) {
                return;
            }
        }
        this.address = address;
        this.address.setMemberId(this.id);
    }
}
