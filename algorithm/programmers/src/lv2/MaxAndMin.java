package lv2;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class MaxAndMin {
    public String solution(String s) {
        IntSummaryStatistics stats = Arrays.stream(s.split(" "))
            .mapToInt(Integer::parseInt)
            .summaryStatistics();

        return stats.getMin() + " " + stats.getMax();

    }
}
