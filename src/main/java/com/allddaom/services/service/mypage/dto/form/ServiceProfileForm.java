package com.allddaom.services.service.mypage.dto.form;

import com.allddaom.models.addresses.domain.Address;
import com.allddaom.models.members.domain.BirthCalendar;
import com.allddaom.models.members.domain.Gender;
import com.allddaom.models.members.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProfileForm {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String name;
    private String email;
    private String regionNumber; // 일반 전화 지역번호
    private String generalPhoneNumber1; // 일반 전화
    private String generalPhoneNumber2; // 일반 전화
    private String cellPhoneNumber; // 휴대 전화
    private boolean availableReceiveSms; // 문자 수신 여부
    private boolean availableReceiveEmail; // 메일 수신 여부
    private String birthday; // 생년월일
    private String administrativeNotes; // 관리자 메모
    private String recommenderUsername; // 추천인 아이디
    private boolean agreePrivacyTerms; // 개인정보 약관 동의 여부
    private String birthCalendar; // 양력 음력
    private String gender; // 성별
//    private Gender gender; // 성별
//    private BirthCalendar birthCalendar; // 양력 음력

    private String postcode; // 우편번호
    private String road; // 도로명
    private String detail; // 상세주소
//    private String jibun; // 지번
//    private String extra; // 참고항목
//    private Address address;

    public static ServiceProfileForm of(Member member) {
        String postcode = "";
        String road = "";
        String jibun = "";
        String detail = "";
        String extra = "";
        if (!ObjectUtils.isEmpty(member.getAddress())) {
            postcode = member.getAddress()
                    .getPostcode();
            road = member.getAddress()
                    .getRoad();
            jibun = member.getAddress()
                    .getJibun();
            detail = member.getAddress()
                    .getDetail();
            extra = member.getAddress()
                    .getExtra();
        }

        return ServiceProfileForm.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .name(member.getName())
                .email(member.getEmail())
                .regionNumber(member.getRegionNumber())
                .generalPhoneNumber1(member.getGeneralPhoneNumber1())
                .generalPhoneNumber2(member.getGeneralPhoneNumber2())
                .cellPhoneNumber(member.getCellPhoneNumber())
                .availableReceiveSms(member.isAvailableReceiveSms())
                .availableReceiveEmail(member.isAvailableReceiveEmail())
                .gender(ObjectUtils.isEmpty(member.getGender()) ? null : member.getGender()
                        .name())
                .birthday(member.getBirthday())
                .administrativeNotes(member.getAdministrativeNotes())
                .recommenderUsername(member.getRecommenderUsername())
                .agreePrivacyTerms(member.isAgreePrivacyTerms())
                .birthCalendar(ObjectUtils.isEmpty(member.getBirthCalendar()) ? null : member.getBirthCalendar()
                        .name())
                .postcode(postcode)
                .road(road)
                .detail(detail)
                .build();
    }

    public Address getAddress() {
        Address address = null;
        if (!ObjectUtils.isEmpty(this.postcode)) {
            address = new Address();
            address.setPostcode(this.postcode);
            address.setRoad(this.road);
            address.setDetail(this.detail);
        }

        return address;
    }

    public boolean isEmptyPassword() {
        return ObjectUtils.isEmpty(this.password);
    }

    public Member update(Member member) {
        member.setNickname(this.nickname);
        member.setName(this.name);
        member.setEmail(this.email);
        member.setRegionNumber(this.regionNumber);
        member.setGeneralPhoneNumber1(this.generalPhoneNumber1);
        member.setGeneralPhoneNumber2(this.generalPhoneNumber2);
        member.setCellPhoneNumber(this.cellPhoneNumber);
        member.setAvailableReceiveSms(this.availableReceiveSms);
        member.setAvailableReceiveEmail(this.availableReceiveEmail);
        member.setBirthday(this.birthday);
        member.setAdministrativeNotes(this.administrativeNotes);
        member.setRecommenderUsername(this.recommenderUsername);
        member.setAgreePrivacyTerms(this.agreePrivacyTerms);
        member.setBirthCalendar(BirthCalendar.getStringToEnum(this.birthCalendar));
        member.setGender(Gender.getStringToEnum(this.gender));
        return member;
    }

}
