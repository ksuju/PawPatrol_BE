package com.patrol.domain.member.auth.service;

import com.patrol.api.member.member.dto.GetAllMembersResponse;
import com.patrol.api.member.member.dto.request.ChangeMemberStatusRequest;
import com.patrol.domain.member.member.entity.Member;
import com.patrol.domain.member.member.enums.MemberRole;
import com.patrol.domain.member.member.repository.V2MemberRepository;
import com.patrol.global.error.ErrorCode;
import com.patrol.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final V2MemberRepository v2MemberRepository;
    private final Logger logger = LoggerFactory.getLogger(AdminService.class.getName());

    @Transactional
    public Page<GetAllMembersResponse> getAllMembers(Pageable pageable) {
        Page<Member> members = v2MemberRepository.findByRoleNot(MemberRole.ROLE_ADMIN, pageable);

        return members.map(member -> GetAllMembersResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .createdAt(member.getCreatedAt())
                .status(member.getStatus())
                .role(member.getRole())
                .build());
    }

    public void changeMemberStatus(ChangeMemberStatusRequest changeMemberStatusRequest) {
        Member member = v2MemberRepository.findById(changeMemberStatusRequest.userId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        member.setStatus(changeMemberStatusRequest.status());
    }
}
