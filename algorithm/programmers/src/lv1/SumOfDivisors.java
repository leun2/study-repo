package lv1;

import java.util.stream.IntStream;

public class SumOfDivisors {
    public static int solution(int n) {

        return IntStream.rangeClosed(1, n)
            .filter(i -> n % i == 0)
            .sum();
    }
}
