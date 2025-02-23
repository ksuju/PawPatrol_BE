package com.patrol.api.findPost.dto;

import com.patrol.domain.findPost.entity.FindPost;
import com.patrol.domain.findPost.entity.Gender;
import com.patrol.domain.findPost.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindPostResponseDto {
    private Long id;
    //private Long foundId;
    private String nickname;  // ✅ 추가된 필드
    private Long lostId;  // lostPost가 없을 경우 null 가능
    //private Long petId;
    private String title;
    private String content;
    private Double latitude;
    private Double longitude;
    private String findTime;
    private String tags;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String status;

    // 추가된 필드들
    private LocalDate birthDate;         // 출생일
    private String breed;                // 품종
    private String name;                 // 이름
    private String characteristics;      // 특징
    private String size;                   // 크기
    private String gender;               // 성별

    // 엔티티에서 DTO 변환 생성자
    public FindPostResponseDto(FindPost findPost) {
        this.id = findPost.getId();
        //this.foundId = findPost.getId();
        this.nickname = findPost.getAuthor().getNickname();  // ✅ Member에서 nickname 가져오기
        this.lostId = (findPost.getLostPost() != null) ? findPost.getLostPost().getId() : null;
        //this.lostId = findPost.getLostId();
        //this.petId = findPost.getPetId();
        this.status = findPost.getStatus().getDescription();
        this.title = findPost.getTitle();
        this.content = findPost.getContent();
        this.latitude = findPost.getLatitude();
        this.longitude = findPost.getLongitude();
        this.findTime = findPost.getFindTime();
        this.tags = findPost.getTags();
        this.createdAt = findPost.getCreatedAt();
        this.modifiedAt = findPost.getModifiedAt();

        // 추가된 필드들 세팅
        this.birthDate = findPost.getBirthDate();       // 출생일
        this.breed = findPost.getBreed();               // 품종
        this.name = findPost.getName();                 // 이름
        this.characteristics = findPost.getCharacteristics();  // 특징
        this.size = findPost.getSize().getDescription();                 // 크기
        this.gender = findPost.getGender().getDescription();             // 성별
    }

    public static FindPostResponseDto from(FindPost findPost) {
        return new FindPostResponseDto(findPost);
    }
}

