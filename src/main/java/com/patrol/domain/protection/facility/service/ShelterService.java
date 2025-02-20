package com.patrol.domain.protection.facility.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrol.api.protection.facility.dto.ShelterApiResponse;
import com.patrol.domain.protection.facility.entity.OperatingHours;
import com.patrol.domain.protection.facility.entity.Shelter;
import com.patrol.domain.protection.facility.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShelterService {

  private final ShelterRepository shelterRepository;
  private final ObjectMapper objectMapper;


  @Transactional
  public void saveApiResponse(String jsonResponse) {
    try {
      ShelterApiResponse response = objectMapper.readValue(jsonResponse, ShelterApiResponse.class);

      if (response.getResponse() != null
          && response.getResponse().getBody() != null
          && response.getResponse().getBody().getItems() != null
          && response.getResponse().getBody().getItems().getItem() != null) {

        List<Shelter> shelters = response.getResponse().getBody().getItems().getItem()
            .stream()
            .map(this::_convertToEntity)
            .collect(Collectors.toList());

        shelterRepository.saveAll(shelters);
        log.info("저장된 보호소 수: {}", shelters.size());
      }
    } catch (Exception e) {
      log.error("데이터 저장 중 에러 발생: {}", e.getMessage(), e);
      throw new RuntimeException("데이터 저장 실패", e);
    }
  }



  private Shelter _convertToEntity(ShelterApiResponse.Item item) {
    OperatingHours operatingHours = OperatingHours.builder()
        .weekdayTime(formatOperatingHours(item.getWeekOprStime(), item.getWeekOprEtime()))
        .weekendTime(formatOperatingHours(item.getWeekendOprStime(), item.getWeekendOprEtime()))
        .closedDays(item.getCloseDay())
        .build();

    return Shelter.builder()
        .name(item.getCareNm())
        .address(item.getCareAddr())
        .tel(item.getCareTel())
        .latitude(item.getLat())
        .longitude(item.getLng())
        .operatingHours(operatingHours)
        .vetPersonCount(item.getVetPersonCnt())
        .saveTargetAnimal(item.getSaveTrgtAnimal())
        .build();
  }

  private String formatOperatingHours(String startTime, String endTime) {
    if (startTime == null || endTime == null || startTime.isEmpty() || endTime.isEmpty()) {
      return null;
    }
    return startTime + " - " + endTime;
  }

}
