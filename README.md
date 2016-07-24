# koreanQuizAndroid

**koreanQuizAndroid는 우리말 겨루기 앱을 구현하여 koreanQuizServer와 연동되는 안드로이드 프로젝트입니다.**  

## 특징
- 안드로이드 앱 형태로 구현하여 플레이스토어를 통해 여러 기기로 손쉽게 접근 가능
- 간단하고 명료한 디자인과 구성
- 어디서나 간단히 우리말을 공부할 수 있는 접근성 제공

## 사용법
``` bash
1. JDK, Android studio, FacebookSDK 설치 및 환경변수 설정
2. Git 설치후 Android studio에서 git 사용 설정
3. https://github.com/Cona19/koeranQuizAndroid.git에서 clone
4. Run을 누른 후 사용할 가상머신 또는 실제 디바이스 선택
5. 구공
```

## 프로그램 구조
1. 로그인
- 앱 구동시 가장 먼저 나오는 화면으로 페이스북 로그인 연동을 통해 인증이 되면 메뉴 화면으로 이동한다.
2. 메뉴
- 로그인 후 기본적인 메뉴를 나타내는 화면으로 초성문제, 십자말문제, 통계, 개발자정보의 이미지뷰를 클릭해 이동할수 있다.
