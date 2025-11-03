package lotto;

public class AmountValidation {
    private static final long UNIT = 1_000L;

    private AmountValidation() { };
    public static void validation(Long amount) {
        if(amount == null) {
            throw new IllegalArgumentException("[ERROR] 구입 금액을 입력해주세요.");
        }
        if(amount % UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
        if(amount < UNIT) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }
}
