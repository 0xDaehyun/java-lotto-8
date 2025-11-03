package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class LottoMachine {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final int SIZE = 6;

    List<Lotto> issue(long amount) {
        int count = (int) (amount / 1_000);
        List<Lotto> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> nums = new ArrayList<>(Randoms.pickUniqueNumbersInRange(MIN, MAX, SIZE));
            Collections.sort(nums);
            list.add(new Lotto(nums));
        }
        return list;
    }

    static void printIssued(List<Lotto> list) {
        System.out.printf("%d개를 구매했습니다.%n", list.size());
        for (Lotto l : list) {
            System.out.println(l.getNumbers());
        }
    }
}