package lv1;

import java.util.stream.IntStream;

public class NumberOfDivisorsAndAddition {

    public int solution(int left, int right) {

        return IntStream.rangeClosed(left, right)
            .map(n -> {
                long count = IntStream.rangeClosed(1, n)
                    .filter(i -> n % i == 0)
                    .count();
                return count % 2 == 0 ? n : -n;
            })
            .sum();
    }
}
