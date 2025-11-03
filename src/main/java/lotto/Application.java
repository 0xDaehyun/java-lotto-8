package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        String input = Console.readLine(); // 금액을 입력 받는다.
        AmountValidation.validation(Long.parseLong(input)); // 금액이 1000원 단위인지 확인.

        // String winNumber = Console.readLine(); // 6개의 당첨 번호를 입력받는다.
        // String bonusNumber = Console.readLine(); // 보너스 번호를 입력받는다.




    }
}
