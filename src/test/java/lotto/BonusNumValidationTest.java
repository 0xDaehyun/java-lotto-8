package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BonusNumValidationTest {

    @Test
    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외")
    void 범위_벗어나면_예외() {
        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> bonusNumValidation.validation(0, winning))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");

        assertThatThrownBy(() -> bonusNumValidation.validation(46, winning))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복이면 예외")
    void 당첨과_중복이면_예외() {
        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> bonusNumValidation.validation(6, winning))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("정상 보너스 번호는 통과")
    void 정상값_통과() {
        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);

        assertThatCode(() -> bonusNumValidation.validation(7, winning))
                .doesNotThrowAnyException();
    }
}