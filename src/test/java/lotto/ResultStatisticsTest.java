package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ResultStatisticsTest {

    @Test
    @DisplayName("등수별 개수 집계와 총상금 계산")
    void 집계_총상금() {
        // amount는 생성자에서 출력용으로만 쓰일 수 있어 검증 대상 아님
        ResultStatistics stats = new ResultStatistics(14_000L);

        stats.add(Rank.FIFTH);
        stats.add(Rank.FIRST);
        stats.add(Rank.NONE);
        stats.add(Rank.SECOND);
        stats.add(Rank.FOURTH);

        assertThat(stats.countOf(Rank.FIRST)).isEqualTo(1);
        assertThat(stats.countOf(Rank.SECOND)).isEqualTo(1);
        assertThat(stats.countOf(Rank.THIRD)).isEqualTo(0);
        assertThat(stats.countOf(Rank.FOURTH)).isEqualTo(1);
        assertThat(stats.countOf(Rank.FIFTH)).isEqualTo(1);

        long expected = Rank.FIRST.getPrize()
                + Rank.SECOND.getPrize()
                + Rank.FOURTH.getPrize()
                + Rank.FIFTH.getPrize();

        assertThat(stats.totalPrize()).isEqualTo(expected);
    }
}