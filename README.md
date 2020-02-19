![LOGO](https://user-images.githubusercontent.com/48894871/72348358-9b75f680-371d-11ea-8d1a-4260a06d5468.png)

# 시키라 - 안드로이드

서비스, 시키라!는 더 좋은 배달 경험을 위해 만들어졌습니다.  

## 시키라 (소비자)

배달 서비스를 이용하는 일반 사용자입니다.

![USER_FLOW](https://user-images.githubusercontent.com/48894871/72348576-0aebe600-371e-11ea-849a-7308fe2d9186.png) -

## 시키라 (제공자)

배달 서비스를 제공하는 업체 관계자입니다.

![USER_FLOW](https://user-images.githubusercontent.com/48894871/72348664-3a025780-371e-11ea-88ea-57c5eb4ef821.png)  



## 이런 것들을 이용했어요!

### 기술

 * 아키텍처 
   시키라는 MVVM을 기반으로 동작합니다. 일반적으로 분리 가능한 모듈로 이루어져, 유연하게 모듈을 교체하고 독립적으로 테스트 할 수 있습니다. 더 자세한 설명을 원하신다면 다음 글을 참고해주세요. [분리 가능한 아키텍처](https://medium.com/@dikolight203/%EA%B5%90%EC%B2%B4-%EA%B0%80%EB%8A%A5%ED%95%9C-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-af1bff55715)  

* 멀티 모듈
  시키라는 소비자(유저), 제공자(업체) 2가지의 애플리케이션으로 이루어져 있습니다. 두 앱은 공통의 리소스(레이아웃, 아이콘 등)를 사용하므로 하나의 프로젝트에 여러개의 모듈을 사용하는 멀티 모듈 프로젝트로 이루어져 있습니다. 

  1. baseapp: baseapp 모듈은 공통적인 res파일 베이스 코드를 공유합니다.
  2. restaurant: 빌드 결과로 제공자 애플리케이션 apk을 얻을 수 있습니다
  3. user: 빌드 결과로 소비자 애플리케이션 apk를 얻을 수 있습니다.  

  모듈은 언제든지 추가/삭제될 가능성이 있습니다. 소비자, 제공자가 공통적으로 사용하는 CustomView, Activity 등의 크기가 크다면 언제든지 새로운 모듈을 고려할 것입니다.  

* 테스트 코드

  1. TDD - ViewModel 같은 경우에는 일반적으로 구현 전 테스트 코드를 먼저 작성하는 TDD를 실천합니다.
  2. Unit Test - Mockito를 활용한 테스팅으로, ViewModel, Repository, UseCase 테스팅을 진행합니다.
  3. UI Test - Espresso를 사용하여 UnitTest만으로 부족하다고 느낄 때 작성합니다.  

* 주요 라이브러리

  1. Retrofit + OkHttp - HTTP서버 연동과 Interceptor, Logging.
  2. Koin - 생산성이 높은 의존성 주입 라이브러리!
  3. Navigation - 프래그먼트간 이동을 손쉽게!
  4. Room - 쿼리문을 작성하여 로컬 DB에 저장!
  5. AAC ViewModel - 생명주기로 부터 자유로운 ViewModel!  



## 약속

### Git

##### 브랜치 전략

1. master - 최종적으로 버전업이 발생했을 때 master브랜치로 병합됩니다.
2. dev - master에서 파생된 개발 작업이 진행되는 브랜치입니다.
3. feature - 각 기능단위로 브랜치를 생성해 dev브랜치에 병합합니다.

#### 커밋 메시지

1. [UPDATE] - 기능을 구현, 변경했을 때 사용
2. [TEST] - 테스트 코드에 수정이 필요한 경우 사용
3. [REFAC] - 기존 코드를 리팩토링 했을 때 사용
4. [FIX] - 예상하지 못했던 이슈를 해결할 때 사용

```
[헤더] 제목 (무엇을 했는가?)
본문 (왜?, 어떻게?, 고려 사항)  
```

### 네이밍

네이밍은 다음글을 확인해주세요. [찾기 쉬운 리소스 네이밍]([https://medium.com/@dikolight203/%EC%B0%BE%EA%B8%B0-%EC%89%AC%EC%9A%B4-%EB%A6%AC%EC%86%8C%EC%8A%A4-%EB%84%A4%EC%9D%B4%EB%B0%8D-e4090bf55ff3](https://medium.com/@dikolight203/찾기-쉬운-리소스-네이밍-e4090bf55ff3))  




### 툴

- Figma for Design
- 추후 추가 예정
