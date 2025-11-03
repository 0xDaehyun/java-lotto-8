# Java Lotto (Precourse)

우테코 3주차 미션: **로또(자동)**

## ▶ 실행/테스트
```bash
./gradlew clean test
# (IDE 실행) Application.main()
```

## ✅ 기능 구현 체크리스트
- [o] **구매 금액 입력**
    - [o] 1,000원 단위만 허용(나누어떨어지지 않으면 예외)
- [o] **로또 발행**
    - [o] 가격: 1,000원/장
    - [o] `Randoms.pickUniqueNumbersInRange(1,45,6)` 사용
    - [o] 한 장은 1~45 **중복 없음 6개**, **오름차순 출력**
    - [o] `"N개를 구매했습니다."` + 각 로또 한 줄 출력
- [o] **당첨/보너스 입력**
    - [o] 당첨 번호: `1,2,3,4,5,6` (1~45, 중복 없음)
    - [o] 보너스 번호: 1~45, **당첨 번호와 중복 금지**
- [o] **등수/통계/수익률**
    - [o] 6=1등(2,000,000,000)
    - [o] 5+보너스=2등(30,000,000)
    - [o] 5=3등(1,500,000)
    - [o] 4=4등(50,000)
    - [o] 3=5등(5,000)
    - [o] 형식 그대로 **당첨 통계** 출력
    - [o] 수익률 = (총 상금/구매 금액)×100, **소수점 둘째 반올림**
- [o] **예외/규칙**
    - [o] 잘못된 입력 → `IllegalArgumentException`, 메시지는 **`[ERROR]`** 시작
    - [o] 예외 발생 **그 입력부터 재시도**
    - [o] `else/switch` 미사용, 메서드 15줄 이하, 상수화
    - [o] UI(System.in/out) 로직은 단위 테스트 제외

## 🧪 테스트(도메인 중심)
- [o] `Lotto` 유효성(개수/범위/중복) & 오름차순 보관
- [o] `Rank.from(match, bonus)` 등수 매핑
- [o] `WinningLotto.evaluate(lotto)` 판정
- [o] `ResultStatistics` 집계/수익률 반올림

## 참고
- Java 21, Gradle Wrapper, `camp.nextstep.edu.missionutils` 사용
- `build.gradle` 수정 금지, 외부 라이브러리 추가 금지