package com.patrol.domain.protection.service;


import com.patrol.api.animalCase.dto.AnimalCaseDetailResponse;
import com.patrol.api.animalCase.dto.AnimalCaseListResponse;
import com.patrol.api.protection.dto.CreateAnimalCaseRequest;
import com.patrol.api.protection.dto.ProtectionResponse;
import com.patrol.domain.animal.entity.Animal;
import com.patrol.domain.animal.repository.AnimalRepository;
import com.patrol.domain.animalCase.entity.AnimalCase;
import com.patrol.domain.animalCase.enums.CaseStatus;
import com.patrol.domain.animalCase.service.AnimalCaseEventPublisher;
import com.patrol.domain.animalCase.service.AnimalCaseService;
import com.patrol.domain.member.member.entity.Member;
import com.patrol.domain.member.member.service.MemberService;
import com.patrol.domain.protection.entity.Protection;
import com.patrol.domain.protection.enums.ProtectionStatus;
import com.patrol.domain.protection.enums.ProtectionType;
import com.patrol.domain.protection.repository.ProtectionRepository;
import com.patrol.global.error.ErrorCode;
import com.patrol.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProtectionService {

  private final ProtectionRepository protectionRepository;
  private final AnimalCaseService animalCaseService;
  private final MemberService memberService;
  private final AnimalCaseEventPublisher animalCaseEventPublisher;
  private final AnimalRepository animalRepository;


  public AnimalCaseDetailResponse findPossibleAnimalCase(Long caseId) {
    Collection<CaseStatus> possibleStatuses = List.of(
        CaseStatus.PROTECT_WAITING,
        CaseStatus.TEMP_PROTECTING,
        CaseStatus.SHELTER_PROTECTING
    );
    AnimalCase animalCase = animalCaseService.findByIdAndStatusesWithHistories(caseId, possibleStatuses)
        .orElseThrow(() -> new CustomException(ErrorCode.ENTITY_NOT_FOUND));
    return AnimalCaseDetailResponse.of(animalCase);
  }

  public Page<AnimalCaseListResponse> findPossibleAnimalCases(Pageable pageable) {
    return animalCaseService.findAllByStatuses(
        List.of(
            CaseStatus.PROTECT_WAITING,
            CaseStatus.TEMP_PROTECTING,
            CaseStatus.SHELTER_PROTECTING
        ),
        pageable
    );
  }

  public Page<ProtectionResponse> findMyProtections(Long memberId, Pageable pageable) {
    return protectionRepository.findAllByApplicantIdAndDeletedAtIsNull(memberId, pageable)
        .map(ProtectionResponse::of);
  }

  public Optional<Protection> findById(Long protectionId) {
    return protectionRepository.findByIdWithFetchAll(protectionId);
  }

  public Page<AnimalCaseListResponse> findMyAnimalCases(Member currentFoster, Pageable pageable) {
    return animalCaseService.findAllByCurrentFoster(currentFoster, pageable);
  }


  @Transactional
  public ProtectionResponse applyProtection(Long caseId, Long memberId, String reason) {
    Member applicant = memberService.findById(memberId)
        .orElseThrow(() -> new CustomException(ErrorCode.ENTITY_NOT_FOUND));

    AnimalCase animalCase = animalCaseService.findByIdAndStatusesWithHistories(caseId,
            List.of(CaseStatus.PROTECT_WAITING, CaseStatus.TEMP_PROTECTING))
        .orElseThrow(() -> new CustomException(ErrorCode.ENTITY_NOT_FOUND));

    if (animalCase.getCurrentFoster() == null) {
      throw new CustomException(ErrorCode.NOT_ASSIGNED_PROTECTION);
    }

    if (applicant.getId().equals(animalCase.getCurrentFoster().getId())) {
      throw new CustomException(ErrorCode.ALREADY_FOSTER);
    }

    // 기존에 수락 대기 신청이 있는지 확인
    boolean hasPendingApplication = protectionRepository
        .existsByApplicantIdAndAnimalCaseIdAndProtectionStatusAndDeletedAtIsNull(
            memberId, caseId, ProtectionStatus.PENDING);
    if (hasPendingApplication) {
      throw new CustomException(ErrorCode.ALREADY_APPLIED);
    }

    Protection protection = Protection.builder()
        .applicant(applicant)
        .animalCase(animalCase)
        .reason(reason)
        .protectionType(ProtectionType.TEMP_PROTECTION)
        .protectionStatus(ProtectionStatus.PENDING)
        .build();

    protectionRepository.save(protection);
    animalCaseEventPublisher.applyProtection(protection.getId(), memberId, animalCase.getStatus());
    return ProtectionResponse.of(protection);
  }



  @Transactional
  public void cancelProtection(Long protectionId, Long memberId) {
    Protection protection = protectionRepository.findById(protectionId)
        .orElseThrow(() -> new CustomException(ErrorCode.ENTITY_NOT_FOUND));

    // 내가 작성한 것인지 확인
    if (!protection.getApplicant().getId().equals(memberId)) {
      throw new CustomException(ErrorCode.UNAUTHORIZED_ACCESS);
    }

    // 이미 취소할 수 없는 상태인지 확인
    if (protection.getProtectionStatus() != ProtectionStatus.PENDING) {
      throw new CustomException(ErrorCode.INVALID_STATUS_CHANGE);
    }

    protection.setProtectionStatus(ProtectionStatus.CANCELED);
    protection.cancel();
  }



  @Transactional
  public void acceptProtection(Long protectionId, Long memberId) {
    Protection protection = protectionRepository.findById(protectionId)
        .orElseThrow(() -> new CustomException(ErrorCode.ENTITY_NOT_FOUND));

    if (protection.getProtectionStatus() != ProtectionStatus.PENDING) {
      throw new CustomException(ErrorCode.INVALID_STATUS_CHANGE);
    }

    if (!protection.getAnimalCase().getCurrentFoster().getId().equals(memberId)) { // 권한 검사
      throw new CustomException(ErrorCode.UNAUTHORIZED_ACCESS);
    }

    protection.approve();
    protection.getAnimalCase().updateStatus(CaseStatus.TEMP_PROTECTING);
    animalCaseEventPublisher.acceptProtection(protection.getId(), memberId);
  }


  @Transactional
  public void rejectProtection(Long protectionId, Long memberId, String rejectReason) {
    Protection protection = protectionRepository.findById(protectionId)
        .orElseThrow(() -> new CustomException(ErrorCode.ENTITY_NOT_FOUND));

    if (protection.getProtectionStatus() != ProtectionStatus.PENDING) {  // 상태 검증
      throw new CustomException(ErrorCode.INVALID_STATUS_CHANGE);
    }

    if (protection.getAnimalCase().getStatus() != CaseStatus.PROTECT_WAITING) {  // 케이스 상태 검증
      throw new CustomException(ErrorCode.INVALID_STATUS_CHANGE);
    }

    if (!protection.getAnimalCase().getCurrentFoster().getId().equals(memberId)) { // 권한 검사
      throw new CustomException(ErrorCode.UNAUTHORIZED_ACCESS);
    }

    protection.reject(rejectReason);
    animalCaseEventPublisher.rejectProtection(protection.getId(), memberId);
    protection.cancel();
  }


  @Transactional
  public void createAnimalCase(CreateAnimalCaseRequest request, Member member) {
    Animal animal = request.toAnimal();
    animalRepository.save(animal);
    animalCaseEventPublisher.createAnimalCase(member, animal, request.title(), request.description());
  }
}
