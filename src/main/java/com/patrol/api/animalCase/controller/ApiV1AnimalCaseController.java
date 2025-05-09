package com.patrol.api.animalCase.controller;

import com.patrol.api.animalCase.dto.AnimalCaseDetailDto;
import com.patrol.api.animalCase.dto.AnimalCaseListResponse;
import com.patrol.domain.animalCase.service.AnimalCaseService;
import com.patrol.global.rsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/animal-cases")
@Tag(name = "동물 케이스 관리 API", description = "동물 상태 변화 기록 추적")
public class ApiV1AnimalCaseController {

  private final AnimalCaseService animalCaseService;


  @GetMapping
  @Operation(summary = "동물 케이스 목록 (관리자)")
  public RsData<Page<AnimalCaseListResponse>> getAnimalCases(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
    Page<AnimalCaseListResponse> responses = animalCaseService.findAll(pageable);
    return new RsData<>("200", "모든 케이스를 성공적으로 호출했습니다.", responses);
  }


  @GetMapping("/{caseId}")
  @Operation(summary = "동물 케이스 상세조회 (관리자)")
  public RsData<AnimalCaseDetailDto> getAnimalCase(@PathVariable Long caseId) {
    AnimalCaseDetailDto response = animalCaseService.findByIdWithHistories(caseId);
    return new RsData<>("200", "%d번 케이스를 성공적으로 호출했습니다.".formatted(caseId), response);
  }


  @GetMapping("/animals/{animalId}")
  @Operation(summary = "동물 ID로 동물 케이스 ID 조회")
  public RsData<Long> getAnimalCaseIdByAnimalId(@PathVariable Long animalId) {
    Long animalCaseId = animalCaseService.findIdByAnimalId(animalId);
    return new RsData<>("200", "케이스를 성공적으로 조회했습니다.", animalCaseId);
  }

}
