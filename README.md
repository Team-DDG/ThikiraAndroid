![LOGO](https://user-images.githubusercontent.com/48894871/72348358-9b75f680-371d-11ea-8d1a-4260a06d5468.png)

# 시키라 - 안드로이드

서비스, 시키라!는 더 좋은 배달 경험을 위해 만들어졌습니다.  
배달을 시키는 사용자 뿐만 아니라 주문, 메뉴를 관리하는 업체의 경험까지도 중요하게 생각합니다.  
아직 초기 개발 단계이기 때문에 새로운 아이디어가 빠르게 적용될 수 있습니다. 

## 컨트리뷰팅!
언제나 참신하고 멋진 아이디어를 기다리고 있습니다!   
상황과 여건이 충분하다면 언제든지 여러분의 멋진 아이디어를 적용할 수 있습니다.  
제 코드에 대해 궁금한 점이나 의문이 있다면 언제든지 Issue!

## 개발 가이드

### 기능 단위 모듈
![기능단위모듈](https://user-images.githubusercontent.com/36754680/75871089-5cc40900-5e4f-11ea-9965-ac1c5f492810.png)  
시키라는 기능 단위 모듈화가 이루어져 있습니다.  
개발 초기에는 단일(모놀리틱)구조로 이루어진 형태였습니다. 하지만 컴포넌트가 많아지면서 의존 관계가 복잡해졌습니다.  
그리고 배달 기능에 대해 [DynamicDelivery](https://developer.android.com/guide/app-bundle/dynamic-delivery)적용이 거의 확정되었습니다.  
하지만 단일 구조는 이에 어울리지 않다고 생각해 모듈화를 진행했습니다.  

### 아키텍처
아키텍처는 [AndroidJetpack](https://developer.android.com/jetpack/?gclid=Cj0KCQiAwP3yBRCkARIsAABGiPoQ4aLdFUSMcbfMnK9F39SH7PUfBiX9eUtjrwwH0w_oZPKtGnmGzfgaAq1FEALw_wcB)(ViewModel, DataBinding, LiveData)을 활용한 MVVM 패턴입니다.  

그리고 유연하게 모듈을 교체하고 독립적으로 테스트할 수 있는 구조를 사용합니다.  
이는 제 블로그에서 자세하게 다루었습니다. [분리 가능한 아키텍쳐](https://medium.com/@dikolight203/%EA%B5%90%EC%B2%B4-%EA%B0%80%EB%8A%A5%ED%95%9C-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-af1bff55715)  

### 데이터 흐름
![데이터흐름](https://user-images.githubusercontent.com/36754680/75874150-83387300-5e54-11ea-99db-ee8c127e8f4e.png)  
Room(로컬 데이터베이스)에 저장되었던 데이터를 Fragment가 LiveData 형태로 감시(observe)합니다.  
최신 데이터를 유지하기 위해 forceUpdate라는 Boolean 값을 사용합니다.  
만약 forceUpdate가 true라면 네트워크를 호출해 로컬에 저장된 값을 변경합니다. 


## 약속
### Git

##### 브랜치 전략

1. master - 피처 브랜치가 병합됩니다.  
2. feature - 각 기능단위로 브랜치를 생성해 master로 병합됩니다.

#### 커밋 메시지

1. [UPDATE] - 기능을 구현, 변경했을 때 사용
2. [TEST] - 테스트 코드에 수정이 필요한 경우 사용
3. [REFAC] - 기존 코드를 리팩토링 했을 때 사용
4. [FIX] - 예상하지 못했던 이슈를 해결할 때 사용

```
[헤더] 제목 (무엇을 했는가?)
본문 (왜?, 어떻게?, 고려 사항)  
```

## 디자인
[업체 디자인](https://www.figma.com/file/uKd5WlaWOaY9DmFg6OxSDq/Wireframe?node-id=2%3A2)  
[사용자 디자인](https://www.figma.com/file/uKd5WlaWOaY9DmFg6OxSDq/Wireframe?node-id=20%3A12)

## 팀
팀원은 가나다 순입니다.
- 김도훈: 안드로이드, 리드
- 박영진: 디자인
- 최승민: 안드로이드
- 홍성하: 안드로이드

## 사용 도구
- Github, Slack, Jira

## 라이브러리

* [Android Jetpack](https://developer.android.com/jetpack/?gclid=Cj0KCQiAwP3yBRCkARIsAABGiPqdj2dwHr5d0lsRM7dkP4c9A3Ih-e2C-CHnM26xGD89-tdQpWOGes8aAlzjEALw_wcB)  
   * 기초
      * Android KTX
      * AppCompat
      * 테스트
   * 아키텍처
      * 데이터 결합
      * 수명주기
      * LiveData
      * Navigation
      * Room
      * ViewModel
   * UI
      * Fragment
      * RecyclerView
      * ConstraintLayout
      * ...
* Kotlin 
   * Coroutine
* Firebase
   * Storage
* Networking
   * OkHttp logging
   * Retrofit
   * Retrofit Gson
* Test
   * mockito
   * live data testing
   * coroutine testing
* ETC
   * Koin
   * Groupie
   * Glide
