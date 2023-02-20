package com.allddaom.services.admin.members.dto;

import com.allddaom.models.members.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminMemberResponse {
    private Long id;
    private String username;
    private String name;

    public static AdminMemberResponse of(Member member) {
        return AdminMemberResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .build();
    }
}
