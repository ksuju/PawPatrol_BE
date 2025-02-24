package com.patrol.domain.member.member.service;

import com.patrol.api.member.member.dto.request.PetRegisterRequest;
import com.patrol.domain.animal.entity.Animal;
import com.patrol.domain.animal.repository.AnimalRepository;
import com.patrol.domain.member.member.entity.Member;
import com.patrol.domain.member.member.repository.V2MemberRepository;
import com.patrol.global.exceptions.ErrorCodes;
import com.patrol.global.exceptions.ServiceException;
import com.patrol.global.storage.FileStorageHandler;
import com.patrol.global.storage.FileUploadRequest;
import com.patrol.global.storage.FileUploadResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.patrol.domain.member.member.service
 * fileName       : V2MemberService
 * author         : sungjun
 * date           : 2025-02-19
 * description    : 자동 주석 생성
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-19        kyd54       최초 생성
 */
@Service
@RequiredArgsConstructor
@Transactional
public class V2MemberService {
    private final V2MemberRepository v2MemberRepository;
    private final AnimalRepository animalRepository;
    private final FileStorageHandler fileStorageHandler;

    // 회원 정보 가져오기
    @Transactional
    public Member getMember(String email) {
        return v2MemberRepository.findByEmail(email)
                // 이거 어떻게 바꿔야 하는지
                .orElseThrow(() -> new ServiceException(ErrorCodes.INVALID_EMAIL));
    }

    // 소셜 로그인 연동 시, 자체 계정 유무 확인
    @Transactional
    public boolean validateNewEmail(String email) {
        // 가입된 회원이 있으면 true 반환, 없으면 false 반환
        return v2MemberRepository.findByEmail(email).isPresent();
    }

    // 주인 있는 반려동물 등록
    @Transactional
    public void petRegister(Member member,
                            PetRegisterRequest petRegisterRequest) {

        // 이미지 업로드
        FileUploadResult uploadResult = fileStorageHandler.handleFileUpload(
                FileUploadRequest.builder()
                        .folderPath("petRegister/" + member.getId())
                        .file(petRegisterRequest.imageFile())
                        .build()
        );

        // 동물 등록
        if(uploadResult != null) {
            Animal animal = petRegisterRequest.buildAnimal(member, uploadResult.getFullPath());
            animalRepository.save(animal);
        }


    }
}
