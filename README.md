# PawPatrol_BE
### 개발 기간 : 2025년 02월 14일(수) ~ 03월 14일(금)
발바닥 구조대 방문 링크 : http://pawpatrols.shop/

[ 구경용 계정 ]
</br>
아이디 : test1@test.com
</br>
비밀번호 : 1234

* 저희 서비스는 로그인을 해야 볼 수 있습니다!

  <img width="1662" alt="스크린샷 2025-03-17 오후 2 57 45" src="https://github.com/user-attachments/assets/33329df6-511b-4329-835f-d43055b0c45c" />


[시연 영상]
https://www.youtube.com/watch?v=bnMnoca5ATc&t=11s



------------


## 💡프로젝트 개요
- 프로젝트명
  - 발바닥 구조대 (Paw Patrol)
- 목적
  - 잃어버린 반려동물을 다 같이 찾기 위한 애플리케이션
    

## 🙂 역할 분배

![역할 분배](https://github.com/user-attachments/assets/9646f688-dc69-4b84-ae9c-97fb73737abf)


## ⚙️ 개발 환경
> BE Repo : [백엔드 레포 바로가기](https://github.com/BackEndSchoolPlus3th/PawPatrol_BE)   
> FE Repo : [프론트 레포 바로가기](https://github.com/BackEndSchoolPlus3th/PawPatrol_FE)

- FE : Vite(React + Javascript), Axios
- BE : SpringBoot, JPA, WebSocket, Python, OAuth2
- Data & Messaging : MySQL, Redis, Kafka
- 버전 및 이슈관리 : Github
- 협업 툴 : Discord, Notion
- 서비스 배포 환경   
   - FE: Vercel   
   - BE: Terraform, GitAction, AWS
- 디자인 : Figma, Cursor


## 📂 프로젝트 구조

<details>
<summary>프로젝트 구조</summary>

```
PAWPATROL_BE
├─.github
│  └─workflows
├─.gradle
│  ├─8.12.1
│  │  ├─checksums
│  │  ├─executionHistory
│  │  ├─expanded
│  │  ├─fileChanges
│  │  ├─fileHashes
│  │  └─vcsMetadata
│  ├─buildOutputCleanup
│  └─vcs-1
├─.idea
│  └─modules
├─ai
│  └─models
├─build
│  └─reports
│      └─problems
├─gradle
│  └─wrapper
├─infraScript
├─out
│  ├─production
│  │  ├─classes
│  │  │  └─com
│  │  │      └─patrol
│  │  │          ├─api
│  │  │          │  ├─animal
│  │  │          │  │  ├─controller
│  │  │          │  │  └─dto
│  │  │          │  │      └─request
│  │  │          │  ├─animalCase
│  │  │          │  │  ├─controller
│  │  │          │  │  └─dto
│  │  │          │  ├─chatMessage
│  │  │          │  │  ├─controller
│  │  │          │  │  └─dto
│  │  │          │  ├─chatRoom
│  │  │          │  │  ├─controller
│  │  │          │  │  └─dto
│  │  │          │  ├─comment
│  │  │          │  │  ├─controller
│  │  │          │  │  └─dto
│  │  │          │  ├─facility
│  │  │          │  │  ├─controller
│  │  │          │  │  └─dto
│  │  │          │  ├─image
│  │  │          │  │  ├─controller
│  │  │          │  │  └─dto
│  │  │          │  ├─kakao
│  │  │          │  │  ├─controller
│  │  │          │  │  └─dto
│  │  │          │  ├─lostFoundPost
│  │  │          │  │  ├─controller
│  │  │          │  │  └─dto
│  │  │          │  ├─member
│  │  │          │  │  ├─auth
│  │  │          │  │  │  ├─controller
│  │  │          │  │  │  └─dto
│  │  │          │  │  │      ├─request
│  │  │          │  │  │      └─requestV2
│  │  │          │  │  └─member
│  │  │          │  │      ├─controller
│  │  │          │  │      └─dto
│  │  │          │  │          └─request
│  │  │          │  ├─notification
│  │  │          │  │  ├─controller
│  │  │          │  │  ├─dto
│  │  │          │  │  └─fcm
│  │  │          │  │      └─dto
│  │  │          │  ├─PostResponseDto
│  │  │          │  └─protection
│  │  │          │      ├─controller
│  │  │          │      └─dto
│  │  │          ├─domain
│  │  │          │  ├─ai
│  │  │          │  │  ├─config
│  │  │          │  │  ├─entity
│  │  │          │  │  ├─event
│  │  │          │  │  ├─repository
│  │  │          │  │  └─service
│  │  │          │  ├─animal
│  │  │          │  │  ├─entity
│  │  │          │  │  ├─enums
│  │  │          │  │  ├─repository
│  │  │          │  │  └─service
│  │  │          │  ├─animalCase
│  │  │          │  │  ├─entity
│  │  │          │  │  ├─enums
│  │  │          │  │  ├─events
│  │  │          │  │  ├─repository
│  │  │          │  │  └─service
│  │  │          │  ├─chatMessage
│  │  │          │  │  ├─entity
│  │  │          │  │  ├─repository
│  │  │          │  │  └─service
│  │  │          │  ├─chatRoom
│  │  │          │  │  ├─entity
│  │  │          │  │  ├─repository
│  │  │          │  │  └─service
│  │  │          │  ├─comment
│  │  │          │  │  ├─entity
│  │  │          │  │  ├─event
│  │  │          │  │  ├─repository
│  │  │          │  │  └─service
│  │  │          │  ├─facility
│  │  │          │  │  ├─config
│  │  │          │  │  ├─entity
│  │  │          │  │  ├─repository
│  │  │          │  │  ├─scheduler
│  │  │          │  │  └─service
│  │  │          │  ├─image
│  │  │          │  │  ├─entity
│  │  │          │  │  ├─repository
│  │  │          │  │  └─service
│  │  │          │  ├─kakao
│  │  │          │  │  └─service
│  │  │          │  ├─lostFoundPost
│  │  │          │  │  ├─entity
│  │  │          │  │  ├─repository
│  │  │          │  │  └─service
│  │  │          │  ├─member
│  │  │          │  │  ├─auth
│  │  │          │  │  │  ├─config
│  │  │          │  │  │  ├─entity
│  │  │          │  │  │  ├─repository
│  │  │          │  │  │  ├─service
│  │  │          │  │  │  └─strategy
│  │  │          │  │  └─member
│  │  │          │  │      ├─entity
│  │  │          │  │      ├─enums
│  │  │          │  │      ├─repository
│  │  │          │  │      └─service
│  │  │          │  ├─notification
│  │  │          │  │  ├─entity
│  │  │          │  │  ├─event
│  │  │          │  │  ├─repository
│  │  │          │  │  └─service
│  │  │          │  ├─Postable
│  │  │          │  └─protection
│  │  │          │      ├─entity
│  │  │          │      ├─enums
│  │  │          │      ├─event
│  │  │          │      ├─repository
│  │  │          │      └─service
│  │  │          ├─global
│  │  │          │  ├─app
│  │  │          │  ├─error
│  │  │          │  ├─exception
│  │  │          │  ├─exceptions
│  │  │          │  ├─fcm
│  │  │          │  ├─globalDto
│  │  │          │  ├─initData
│  │  │          │  ├─jpa
│  │  │          │  ├─oauth2
│  │  │          │  ├─redis
│  │  │          │  ├─rq
│  │  │          │  ├─rsData
│  │  │          │  ├─security
│  │  │          │  ├─storage
│  │  │          │  ├─swagger
│  │  │          │  ├─webMvc
│  │  │          │  └─webSocket
│  │  │          └─standard
│  │  │              ├─base
│  │  │              └─util
│  │  └─resources
│  │      ├─data
│  │      └─static
│  └─test
│      └─classes
│          ├─com
│          │  └─patrol
│          └─generated_tests
└─src
    ├─main
    │  ├─generated
    │  │  └─com
    │  │      └─patrol
    │  │          ├─domain
    │  │          │  ├─ai
    │  │          │  │  └─entity
    │  │          │  ├─animal
    │  │          │  │  └─entity
    │  │          │  ├─animalCase
    │  │          │  │  └─entity
    │  │          │  ├─chatMessage
    │  │          │  │  └─entity
    │  │          │  ├─chatRoom
    │  │          │  │  └─entity
    │  │          │  ├─comment
    │  │          │  │  └─entity
    │  │          │  ├─facility
    │  │          │  │  └─entity
    │  │          │  ├─image
    │  │          │  │  └─entity
    │  │          │  ├─lostFoundPost
    │  │          │  │  └─entity
    │  │          │  ├─member
    │  │          │  │  ├─auth
    │  │          │  │  │  └─entity
    │  │          │  │  └─member
    │  │          │  │      └─entity
    │  │          │  ├─notification
    │  │          │  │  └─entity
    │  │          │  └─protection
    │  │          │      └─entity
    │  │          └─global
    │  │              └─jpa
    │  ├─java
    │  │  └─com
    │  │      └─patrol
    │  │          ├─api
    │  │          │  ├─animal
    │  │          │  │  ├─controller
    │  │          │  │  └─dto
    │  │          │  │      └─request
    │  │          │  ├─animalCase
    │  │          │  │  ├─controller
    │  │          │  │  └─dto
    │  │          │  ├─chatMessage
    │  │          │  │  ├─controller
    │  │          │  │  └─dto
    │  │          │  ├─chatRoom
    │  │          │  │  ├─controller
    │  │          │  │  └─dto
    │  │          │  ├─comment
    │  │          │  │  ├─controller
    │  │          │  │  └─dto
    │  │          │  ├─facility
    │  │          │  │  ├─controller
    │  │          │  │  └─dto
    │  │          │  ├─image
    │  │          │  │  ├─controller
    │  │          │  │  └─dto
    │  │          │  ├─kakao
    │  │          │  │  ├─controller
    │  │          │  │  └─dto
    │  │          │  ├─lostFoundPost
    │  │          │  │  ├─controller
    │  │          │  │  └─dto
    │  │          │  ├─member
    │  │          │  │  ├─auth
    │  │          │  │  │  ├─controller
    │  │          │  │  │  └─dto
    │  │          │  │  │      ├─request
    │  │          │  │  │      └─requestV2
    │  │          │  │  └─member
    │  │          │  │      ├─controller
    │  │          │  │      └─dto
    │  │          │  │          └─request
    │  │          │  ├─notification
    │  │          │  │  ├─controller
    │  │          │  │  ├─dto
    │  │          │  │  └─fcm
    │  │          │  │      └─dto
    │  │          │  ├─PostResponseDto
    │  │          │  └─protection
    │  │          │      ├─controller
    │  │          │      └─dto
    │  │          ├─domain
    │  │          │  ├─ai
    │  │          │  │  ├─config
    │  │          │  │  ├─entity
    │  │          │  │  ├─event
    │  │          │  │  ├─repository
    │  │          │  │  └─service
    │  │          │  ├─animal
    │  │          │  │  ├─config
    │  │          │  │  ├─entity
    │  │          │  │  ├─enums
    │  │          │  │  ├─repository
    │  │          │  │  └─service
    │  │          │  ├─animalCase
    │  │          │  │  ├─entity
    │  │          │  │  ├─enums
    │  │          │  │  ├─events
    │  │          │  │  ├─repository
    │  │          │  │  └─service
    │  │          │  ├─chatMessage
    │  │          │  │  ├─entity
    │  │          │  │  ├─repository
    │  │          │  │  └─service
    │  │          │  ├─chatRoom
    │  │          │  │  ├─entity
    │  │          │  │  ├─repository
    │  │          │  │  └─service
    │  │          │  ├─comment
    │  │          │  │  ├─entity
    │  │          │  │  ├─event
    │  │          │  │  ├─repository
    │  │          │  │  └─service
    │  │          │  ├─facility
    │  │          │  │  ├─entity
    │  │          │  │  ├─repository
    │  │          │  │  ├─scheduler
    │  │          │  │  └─service
    │  │          │  ├─image
    │  │          │  │  ├─entity
    │  │          │  │  ├─repository
    │  │          │  │  └─service
    │  │          │  ├─kakao
    │  │          │  │  └─service
    │  │          │  ├─lostFoundPost
    │  │          │  │  ├─entity
    │  │          │  │  ├─repository
    │  │          │  │  └─service
    │  │          │  ├─member
    │  │          │  │  ├─auth
    │  │          │  │  │  ├─config
    │  │          │  │  │  ├─entity
    │  │          │  │  │  ├─repository
    │  │          │  │  │  ├─service
    │  │          │  │  │  └─strategy
    │  │          │  │  └─member
    │  │          │  │      ├─entity
    │  │          │  │      ├─enums
    │  │          │  │      ├─repository
    │  │          │  │      └─service
    │  │          │  ├─notification
    │  │          │  │  ├─entity
    │  │          │  │  ├─event
    │  │          │  │  ├─repository
    │  │          │  │  └─service
    │  │          │  ├─Postable
    │  │          │  └─protection
    │  │          │      ├─entity
    │  │          │      ├─enums
    │  │          │      ├─event
    │  │          │      ├─repository
    │  │          │      └─service
    │  │          ├─global
    │  │          │  ├─app
    │  │          │  ├─error
    │  │          │  ├─exception
    │  │          │  ├─exceptions
    │  │          │  ├─fcm
    │  │          │  ├─globalDto
    │  │          │  ├─initData
    │  │          │  ├─jpa
    │  │          │  ├─oauth2
    │  │          │  ├─redis
    │  │          │  ├─rq
    │  │          │  ├─rsData
    │  │          │  ├─security
    │  │          │  ├─storage
    │  │          │  ├─swagger
    │  │          │  ├─webMvc
    │  │          │  └─webSocket
    │  │          └─standard
    │  │              ├─base
    │  │              └─util
    │  └─resources
    │      ├─data
    │      └─static
    └─test
   
```
</details>


## 📋 ERD
![erd](https://github.com/user-attachments/assets/e68f7afb-4350-4722-bb60-8876b3bb4454)


## 📄 API 명세서
![명세](https://github.com/user-attachments/assets/31327d0a-539f-4626-98a8-42570c6dcd91)

## 📄 GitConvention
GitConvention에 시간을 너무 쏟지 않도록 모두에게 익숙한 깃컨벤션 채택   
```
main
├─ dev
│  └─ 개인 브랜치
```


## 🔧 기술 스택
![기술스택](https://github.com/user-attachments/assets/1c81d509-a46b-4d72-841f-d0e94daf553a)

### FE
React + TypeScript
   - 프론트 서버를 분리하면서 힘을 최대한 덜 들이기 위해 가장 유명하고 익숙한 **리액트**를 선정.
   - 간편한 API 요청을 위해 **axios** 사용

### BE
SpringBoot + JPA + QueryDSL
   - 빠른 프로젝트 시작을 위해 WAS가 내장되어있는 **SpringBoot** 선정
   - 데이터 매핑 자동화를 위한 **JPA** 도입 및 **QueryDSL** 사용

WebSocket STOMP
   - 채팅 기능 구현을 위한 **WebSocket STOMP** 라이브러리 사용

JWT
   - 회원 정보 토큰 인증, 인가 사용을 위한 **JWT** 채택

### DBMS
Mysql, Redis
   - 짧은 개발 기간을 고려하여 모두에게 익숙한 **Mysql** 채택
   - 이메일 인증 여부 확인을 위한 **Redis** 채택


## 🔧 아키텍쳐
![아키텍처](https://github.com/user-attachments/assets/4b0a4895-edf3-41a1-82a1-d160a3d4e5fa)

![cicd파이프라인](https://github.com/user-attachments/assets/2bf0d397-7d75-42c6-9a3b-2c85f9b8badf)


## 💁‍♂ 구현 기능 - 개인
### 인증/인가 및 소셜 로그인 (JWT, OAuth2)
- JWT를 이용한 인증/인가 구현
- 소셜 로그인 기능
- MimeMessage를 사용한 이메일 인증
- NCP Object Storage를 사용한 이미지 업로드, 삭제 기능 구현 (Gif, Png, Jpg, Jpeg)
### 회원 관리
- 일반 회원, 보호소 회원 구분 관리
- 반려동물 CRUD
### 검색 기능 (MySQL)
- 보호소 목록 검색
	- 동적 쿼리를 사용한 저장된 동물 보호소 목록 검색 (QueryDSL)


## 🎞️ 시연 영상

![시연1](https://github.com/user-attachments/assets/47fcf670-c716-4615-8757-b443bbf15594)

![시연2](https://github.com/user-attachments/assets/f7708f65-f7bf-42d7-9c73-2f010ac28afe)

![시연3](https://github.com/user-attachments/assets/d56f5a30-f3e5-49f5-98e3-bf677ee9e2fb)


![시연영상](https://github.com/user-attachments/assets/2f276aa6-6448-4fc3-a3b6-31ab42dddc38)


## 🔧 기술구현(팀)
![Video Label](http://img.youtube.com/vi/bnMnoca5ATc/0.jpg)<br>(https://www.youtube.com/watch?v=bnMnoca5ATc)


##  ✒️ 메모
- [OAuth2.0 자체 계정 연동 리팩토링](https://ksuju.tistory.com/156)<br>
	- 기존 단점 : 의도치 않은 계정과의 연동 문제
	- 변경 사항 : 자체 계정이 있을 때만 연동 페이지를 통해 명시적으로 연동
	- 개선 후 장/단점 : 의도치 않은 계정과의 연동 문제는 해결했으나, 사용자 경험이 복잡해짐
	- 배운 점 : 기존 코드의 장점을 살리며 단점을 최소화하는 방향으로 리팩토링을 진행해야 한다
- [Axios 메서드별 데이터 전송 방식](https://ksuju.tistory.com/161)


## ⁉️ 트러블 슈팅
- [@ConfigurationProperties를 사용할 때 "No setter found for property" 에러 발생](https://ksuju.tistory.com/157)
- [소셜 로그인시 브라우저에 accessToken 토큰이 발행되지 않음](https://ksuju.tistory.com/158)
- [Spring Boot에서 @ModelAttribute와 폼 데이터 바인딩 문제](https://ksuju.tistory.com/159)
- [JWT 토큰 갱신이 작동하지 않은 문제, ddl-auto=create](https://ksuju.tistory.com/160)
- [공공 API 사용 시 "등록되지 않은 인증키 입니다. " 에러 해결](https://ksuju.tistory.com/162)

