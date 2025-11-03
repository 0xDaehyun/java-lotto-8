package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningLottoTest {

    @Test
    void 등수_판정() {
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        assertThat(winning.evaluate(new Lotto(List.of(1, 2, 3, 4, 5, 6))))
                .isEqualTo(Rank.FIRST);     // 6개

        assertThat(winning.evaluate(new Lotto(List.of(1, 2, 3, 4, 5, 7))))
                .isEqualTo(Rank.SECOND);    // 5개 + 보너스

        assertThat(winning.evaluate(new Lotto(List.of(1, 2, 3, 4, 5, 45))))
                .isEqualTo(Rank.THIRD);     // 5개

        assertThat(winning.evaluate(new Lotto(List.of(1, 2, 3, 4, 40, 41))))
                .isEqualTo(Rank.FOURTH);    // 4개

        assertThat(winning.evaluate(new Lotto(List.of(1, 2, 3, 40, 41, 42))))
                .isEqualTo(Rank.FIFTH);     // 3개

        assertThat(winning.evaluate(new Lotto(List.of(1, 2, 40, 41, 42, 43))))
                .isEqualTo(Rank.NONE);      // 2개 이하
    }
}