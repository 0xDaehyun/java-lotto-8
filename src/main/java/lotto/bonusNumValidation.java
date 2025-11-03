package lotto;

import java.util.List;

public final class bonusNumValidation {
    private static final int MIN = 1;
    private static final int MAX = 45;

    private bonusNumValidation() {
    }

    public static void validation(int bonus, List<Integer> winning) {
        if (bonus < MIN || bonus > MAX) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        if (winning != null && winning.contains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}