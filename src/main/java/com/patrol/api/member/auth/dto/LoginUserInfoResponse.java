package com.patrol.api.member.auth.dto;

import com.patrol.domain.member.member.entity.Member;
import com.patrol.domain.member.member.enums.MemberRole;
import com.patrol.domain.member.member.enums.MemberStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

/**
 * packageName    : com.patrol.api.member.auth.dto.requestV2
 * fileName       : LoginUserInfo
 * author         : sungjun
 * date           : 2025-02-21
 * description    : 자동 주석 생성
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-21        kyd54       최초 생성
 */
@Builder
public record LoginUserInfoResponse(
        Long id,
        @NotNull String email,
        @NotNull String nickname,
        String profileImage,
        MemberRole role
) {
    public static LoginUserInfoResponse of(Member member) {
        return LoginUserInfoResponse.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .profileImage(member.getProfileImageUrl())
                .build();
    }
}


