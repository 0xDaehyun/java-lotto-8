package lotto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.Map;

public class ResultStatistics {

    private final long amount;
    private final Map<Rank, Integer> counts = new EnumMap<>(Rank.class);

    public ResultStatistics(long amount) {
        this.amount = amount;
        for (Rank r : Rank.values()) counts.put(r, 0);
    }

    public void add(Rank rank) {
        if (rank == null) return;
        counts.put(rank, counts.get(rank) + 1);
    }

    public int countOf(Rank rank) {
        return counts.getOrDefault(rank, 0);
    }

    public long totalPrize() {
        long sum = 0L;
        for (Rank r : Rank.values()) {
            sum += (long) countOf(r) * r.getPrize();
        }
        return sum;
    }

    public double yieldPercent() {
        if (amount == 0L) return 0.0;
        return (totalPrize() * 100.0) / amount;
    }


    public void print() {
        System.out.println("당첨 통계");
        System.out.println("---");
        printLine("3개 일치 (5,000원) - ", countOf(Rank.FIFTH));
        printLine("4개 일치 (50,000원) - ", countOf(Rank.FOURTH));
        printLine("5개 일치 (1,500,000원) - ", countOf(Rank.THIRD));
        printLine("5개 일치, 보너스 볼 일치 (30,000,000원) - ", countOf(Rank.SECOND));
        printLine("6개 일치 (2,000,000,000원) - ", countOf(Rank.FIRST));
        System.out.printf("총 수익률은 %s입니다.%n", formatPercent(yieldPercent()));
    }

    private void printLine(String label, int count) {
        System.out.println(label + count + "개");
    }

    private String formatPercent(double percent) {
        BigDecimal bd = BigDecimal.valueOf(percent).setScale(1, RoundingMode.HALF_UP);
        DecimalFormat df = new DecimalFormat("#,##0.0");
        return df.format(bd) + "%";
    }
}