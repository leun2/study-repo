package lv1;

import java.util.stream.IntStream;

public class GreatestCommonDivisorAndLeastCommonMultiple {
    public int[] solution(int n, int m) {

        int gcd = IntStream.rangeClosed(1, Math.min(n, m))
            .filter(i -> n % i == 0 && m % i == 0)
            .max()
            .orElse(1);

        int lcm = n * m / gcd;

        return new int[]{gcd, lcm};
    }
}
