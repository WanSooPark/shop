package com.allddaom.services.service.mypage.dto;

import com.allddaom.models.members.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MypageProfileResponse {
    private Long id;
    private String name;

    public static MypageProfileResponse of(Member member) {
        return MypageProfileResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
    }
}
