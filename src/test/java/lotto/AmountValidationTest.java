package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class AmountValidationTest {

    private void assertInvalid(Long amount) {
        assertThatThrownBy(() -> AmountValidation.validation(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Nested
    @DisplayName("구입 금액 유효성")
    class PurchaseAmount {

        @Test
        @DisplayName("null이면 예외")
        void throwsIfNull() {
            assertInvalid(null);
        }

        @ParameterizedTest(name = "천원 미만(경계 포함): {0}")
        @CsvSource({"0", "-1", "-100"})
        void throwsIfBelowMin(long amount) {
            assertInvalid(amount);
        }

        @ParameterizedTest(name = "천원 단위 아님: {0}")
        @CsvSource({"1", "999", "1500", "12345"})
        void throwsIfNotThousandStep(long amount) {
            assertInvalid(amount);
        }

        @ParameterizedTest(name = "정상 입력: {0}")
        @CsvSource({"1000", "8000", "123000"})
        void passesIfValid(long amount) {
            assertThatCode(() -> AmountValidation.validation(amount))
                    .doesNotThrowAnyException();
        }
    }
}