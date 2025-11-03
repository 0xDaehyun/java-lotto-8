package lotto;

public enum Rank {
    FIRST(6, true,  false, 2_000_000_000L),
    SECOND(5, true,  true,  30_000_000L),
    THIRD(5, true,  false, 1_500_000L),
    FOURTH(4, false, false, 50_000L),
    FIFTH(3, false, false, 5_000L),
    NONE(0, false, false, 0L);

    private final int matchCount;
    private final boolean considerBonus;
    private final boolean needsBonus;
    private final long prize;

    Rank(int matchCount, boolean considerBonus, boolean needsBonus, long prize) {
        this.matchCount = matchCount;
        this.considerBonus = considerBonus;
        this.needsBonus = needsBonus;
        this.prize = prize;
    }

    public long getPrize() {
        return prize;
    }

    public static Rank from(int matchCount, boolean bonusMatched) {
        if (matchCount == 6) return FIRST;
        if (matchCount == 5 && bonusMatched) return SECOND;
        if (matchCount == 5) return THIRD;
        if (matchCount == 4) return FOURTH;
        if (matchCount == 3) return FIFTH;
        return NONE;
    }
}