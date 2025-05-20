package lv1;

import java.util.stream.IntStream;

public class DotProduct {
    public int solution(int[] a, int[] b) {
        return IntStream.range(0, b.length)
            .map(i -> a[i] * b[i])
            .sum();
    }
}
