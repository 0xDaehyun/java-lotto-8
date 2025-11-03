package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class WinningLotto {
    private final Set<Integer> winning;
    private final int bonus;

    WinningLotto(List<Integer> winning, int bonus) {
        new Lotto(winning); // 규칙 검증
        bonusNumValidation.validation(bonus, winning);
        this.winning = new HashSet<>(winning);
        this.bonus = bonus;
    }

    Rank evaluate(Lotto lotto) {
        int matched = 0;
        for (int n : lotto.getNumbers()) {
            if (winning.contains(n)) matched++;
        }
        boolean bonusMatched = winning.contains(bonus) ? false : contains(lotto, bonus);
        return Rank.from(matched, bonusMatched);
    }

    private boolean contains(Lotto lotto, int n) {
        for (int v : lotto.getNumbers()) {
            if (v == n) return true;
        }
        return false;
    }
}