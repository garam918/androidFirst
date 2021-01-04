# 노숙인들을 위한 복지 어플

## 서비스 소개

- 간단한 로그인을 통해 노숙인들이 생활에 필요한 각종 정보를 얻고 QR코드를 이용해 무료급식소에서 밥을 먹을 수 있다.
- 무료급식소에서는 QR코드를 활용해 식사 제공을 더 효율적으로 할 수 있다.

</br >

## 주요 기능

### 노숙인
 
 - 음성 또는 얼굴로 로그인을 하게 되면 필요한 복지시설, 정책, 귀향여비, 구인구직, 날씨 등의 정보를 얻을 수 있습니다.
   - 무료급식의 경우 오늘의 메뉴, 실시간 밥 양 등을 확인 할 수 있습니다.
   - 날씨의 경우 태풍이나 장마, 폭설, 폭염 등의 경우 미리 Push 알림으로 알려줍니다.
   - 복지시설의 경우 5가지 카테고리로 나누어 위치, 전화번호 등을 알려줍니다.
   - 정책의 경우 정부 24 API를 이용하여 노숙인에게 필요한 정책을 알려줍니다.
 - 휴대폰이 없는 노숙인의 경우 터치스크린을 통해 각종 시설의 위치 및 정보를 제공받을 수 있습니다.
 
### 무료급식소

- 실시간으로 밥의 양과 메뉴를 관리할 수 있습니다.
   - 노숙인의 QR코드를 인식해 현재 밥 양을 차감시킵니다

</br >

## 기술 스택

- Kotlin
- Retrofit2:2.7.0
- Firebase
- Kakao Map
- zxing:3.6.0
  - QR코드 생성 및 인식 라이브러리
- glide:4.10.0
  - 이미지 로딩 라이브러리

</br >


## 개발 기간

2019.09 ~ 2020.06

## 시연 영상

https://youtu.be/SEmdKrKMaSk