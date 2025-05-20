package lv1;

import java.util.stream.LongStream;

public class SumBetweenTwoIntegers {
    public long solution(int a, int b) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        return LongStream.range(a, b + 1)
            .sum();
    }
}
