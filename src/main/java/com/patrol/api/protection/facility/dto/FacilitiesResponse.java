package com.patrol.api.protection.facility.dto;

import com.patrol.domain.protection.facility.entity.Facility;
import com.patrol.domain.protection.facility.entity.OperatingHours;
import lombok.Builder;

@Builder
public record FacilitiesResponse(
    String name, String address, String tel, Double latitude, Double longitude,
    OperatingHours operatingHours
) {

  public static FacilitiesResponse of(Facility facility) {
    return FacilitiesResponse.builder()
        .name(facility.getName())
        .address(facility.getAddress())
        .tel(facility.getTel())
        .latitude(facility.getLatitude())
        .longitude(facility.getLongitude())
        .build();
  }
}
