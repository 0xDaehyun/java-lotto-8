package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        Long amount = readAmountOrNull();
        if (amount == null) return;

        List<Lotto> tickets = new LottoMachine().issue(amount);
        LottoMachine.printIssued(tickets);

        System.out.println();
        List<Integer> winning = readWinningOrNull();
        if (winning == null) return;

        System.out.println();
        Integer bonus = readBonusOrNull(winning);
        if (bonus == null) return;

        WinningLotto wl = new WinningLotto(winning, bonus);
        ResultStatistics stats = new ResultStatistics(amount);
        for (Lotto t : tickets) {
            Rank r = wl.evaluate(t);
            if (r != Rank.NONE) stats.add(r);
        }
        stats.print();
    }

    // 1) 금액
    private static Long readAmountOrNull() {
        System.out.println("구입금액을 입력해 주세요.");
        while (true) {
            try {
                String input = Console.readLine();
                long amount = Long.parseLong(input.trim());
                AmountValidation.validation(amount);
                return amount;
            } catch (NumberFormatException e) {              // 파싱 실패
                System.out.println("[ERROR] 구입 금액은 1,000원 단위의 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {           // 던진 도메인 예외
                System.out.println(e.getMessage());          // [ERROR]로 시작
            } catch (java.util.NoSuchElementException eof) { // 테스트 입력 소진
                return null;
            }
            // 재입력 루프 계속
        }
    }

    // 2) 당첨 번호
    private static List<Integer> readWinningOrNull() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        while (true) {
            try {
                String line = Console.readLine();
                List<Integer> nums = Arrays.stream(line.split("\\s*,\\s*"))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                new Lotto(nums);
                return nums;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자 6개를 쉼표로 구분해 입력해주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (java.util.NoSuchElementException eof) {
                return null;
            }
        }
    }

    // 3) 보너스 번호
    private static Integer readBonusOrNull(List<Integer> winning) {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
        while (true) {
            try {
                String s = Console.readLine();
                int bonus = Integer.parseInt(s.trim());
                bonusNumValidation.validation(bonus, winning);
                return bonus;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 보너스 번호는 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (java.util.NoSuchElementException eof) {
                return null;
            }
        }
    }
}