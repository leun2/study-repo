package lv1;

import java.util.stream.LongStream;

public class NNumbersSpacedByX {
    public long[] solution(int x, int n) {
        return LongStream.rangeClosed(1, n)
            .map(i -> x * i)
            .toArray();
    }
}
