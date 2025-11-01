# Java Lotto (Precourse)

우아한테크코스 3주차 미션: **로또(자동) 발매기**  
사용자가 입력한 금액만큼 로또를 발행하고, 당첨 번호/보너스 번호와 비교하여 **당첨 통계**와 **총 수익률**을 출력합니다.

---

## 🔧 실행 환경
- **Java 21 (Temurin 21)**  
- **Gradle Wrapper** 사용 (`./gradlew`)  
- IDE: IntelliJ IDEA (권장)  
- OS: macOS / Windows / Linux (개발 환경 예시는 macOS aarch64)

> 채점 환경도 Java 21입니다. 로컬에서도 **Project SDK, Language level, Gradle JVM을 모두 21**로 맞추세요.

---

## ▶ 실행/테스트 방법

### 테스트
```bash
./gradlew clean test
```

### (선택) 애플리케이션 실행
미션 템플릿에 run task가 없는 경우 IDE에서 `Application.main()`을 실행하세요.

---

## 📦 의존/라이브러리
- `camp.nextstep.edu.missionutils`  
  - `Randoms.pickUniqueNumbersInRange(1, 45, 6)`  
  - `Console.readLine()`

외부 라이브러리 추가/변경 금지, `build.gradle` 수정 금지.

---

## 🧭 설계 원칙 (프로그래밍 요구 사항 반영)
- **역할 분리**: 입력/출력(UI) ↔ 파싱/검증 ↔ 도메인 로직 분리
- **상수화**: 매직 넘버/문자열은 `private static final`로 의미 부여
- **메서드 길이**: 15줄 이하 유지, 한 메서드 한 책임
- **분기 제한**: `else`, `switch/case` 사용 금지 → 조건 충족 시 **즉시 return**
- **Enum 활용**: 당첨 등수/상금은 `Rank` enum으로 모델링
- **UI 테스트 제외**: 단위 테스트는 **도메인 로직 중심**(UI 제외)

---

## 🧩 기능 목록 (기능/예외 중심 체크리스트)

### 1) 구매 금액 입력
- [ ] 사용자로부터 구매 금액을 입력받는다.
- [ ] 구매 금액은 **1,000원 단위**만 허용한다.
- [ ] 1,000으로 나누어 떨어지지 않으면 예외 발생 → `[ERROR]` 출력 후 **해당 입력부터 재시도**.

### 2) 로또 발행
- [ ] 로또 1장 가격은 **1,000원**이다.
- [ ] 구매 금액에 따라 N장의 로또를 자동 발행한다.
- [ ] 한 장은 **1~45 범위의 중복되지 않는 6개 정수**로 구성한다.
- [ ] 각 로또 번호는 **오름차순**으로 출력한다.
- [ ] `"N개를 구매했습니다."` 수량 출력 후 각 로또 번호를 한 줄씩 출력한다.

### 3) 당첨 번호/보너스 번호 입력
- [ ] 당첨 번호 6개를 `1,2,3,4,5,6` 형식으로 입력받는다(쉼표 기준).
- [ ] 각각 **1~45 범위**이며 **중복 불가**.
- [ ] **보너스 번호**를 별도로 입력받는다(1~45, **당첨 번호와 중복 불가**).
- [ ] 위반 시 예외 → `[ERROR]` 출력 후 **해당 입력부터 재시도**.

### 4) 결과 계산/출력
- [ ] 각 로또와 당첨 번호를 비교하여 **일치 개수** 및 **보너스 일치 여부**를 판단한다.
- [ ] 아래 기준으로 등수 판정하고 개수를 집계한다.  
  - 1등: 6개 일치 / 2,000,000,000원  
  - 2등: 5개 일치 + 보너스 / 30,000,000원  
  - 3등: 5개 일치 / 1,500,000원  
  - 4등: 4개 일치 / 50,000원  
  - 5등: 3개 일치 / 5,000원
- [ ] **당첨 통계** 섹션과 **총 수익률**을 형식에 맞춰 출력한다.  
  - 수익률(%) = (총 상금 / 구매 금액) × 100  
  - **소수점 둘째 자리**에서 반올림 (예: `62.5%`)

### 5) 예외 처리 공통 정책
- [ ] `IllegalArgumentException`(또는 `IllegalStateException`) 등 **명확한 유형** 사용
- [ ] 예외 메시지는 반드시 **`[ERROR]`로 시작**
- [ ] 예외가 난 **지점부터 입력을 다시 받음**
- [ ] `System.exit()` 사용 금지

---

## 📤 입출력 형식 (예시)

입력
```
구입금액을 입력해 주세요.
8000

당첨 번호를 입력해 주세요.
1,2,3,4,5,6

보너스 번호를 입력해 주세요.
7
```

출력
```
8개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[1, 3, 5, 14, 22, 45]

당첨 통계
---
3개 일치 (5,000원) - 1개
4개 일치 (50,000원) - 0개
5개 일치 (1,500,000원) - 0개
5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
6개 일치 (2,000,000,000원) - 0개
총 수익률은 62.5%입니다.
```

> **주의**: 공백/쉼표/줄바꿈 등 **형식을 정확히 일치**시켜야 합니다. 형식 불일치 시 0점 처리될 수 있습니다.

---

## 🧱 패키지 구조(제안)
```
lotto
 ├─ Application.java
 ├─ controller
 │   └─ LottoController.java
 ├─ domain
 │   ├─ Lotto.java
 │   ├─ WinningLotto.java
 │   ├─ Rank.java
 │   └─ ResultStatistics.java
 ├─ io
 │   ├─ InputView.java
 │   └─ OutputView.java
 └─ support (선택: 파서/밸리데이터/포매터)
```

---

## ✅ 테스트 전략
- **도메인 단위 테스트** 우선:  
  - `Lotto` 유효성(개수/범위/중복)  
  - `Rank.from(matchCount, bonusMatched)`  
  - `WinningLotto.evaluate(lotto)`  
  - `ResultStatistics` 집계/수익률(반올림)
- **UI 로직(System.in/out)**은 단위 테스트 제외

---

## 🌱 브랜치/커밋 컨벤션
- 브랜치: `feature/step-1`, `feature/validation`, `fix/…`  
- 커밋(Angular 규칙 예시)
  - `feat(lotto): 번호 유효성(범위/중복) 검증 추가`
  - `test(rank): 등수 매핑 테스트 추가`
  - `refactor(controller): 예외 재입력 루프 분리`
  - `docs(readme): 실행/테스트 방법 추가`

---

## 🔄 원본(upstream) 동기화
```bash
git remote add upstream https://github.com/woowacourse-precourse/java-lotto-8.git
git checkout main
git fetch upstream
git merge --ff-only upstream/main
git push origin main

git checkout feature/step-1
git rebase main
./gradlew clean test
git push -f origin feature/step-1
```

---

## 🔬 (선택) 학습 테스트
본 기능 전 `src/test/java/study`에 JUnit5/AssertJ 학습 테스트(문자열/컬렉션/예외 assertion/파라미터라이즈드)를 작성해 테스트 도구 사용감각을 먼저 확보합니다.

---

## 📓 주차 회고 포인트(README에 유지)
- 오래 고민했던 지점 / 실패했지만 의미 있었던 시도 / 다음에 다르게 할 점

---

## 체크리스트 요약
- [ ] Java 21/Gradle Wrapper로 테스트 GREEN
- [ ] 기능/예외 중심 README 갱신(살아있는 문서)
- [ ] UI ↔ 도메인 분리, 상수화, 15줄 이하, `else/switch` 미사용
- [ ] 예외는 `[ERROR]`로 시작, 재입력 흐름 준수
- [ ] PR 본문에 구현 기능/예외 정책/테스트 항목 요약