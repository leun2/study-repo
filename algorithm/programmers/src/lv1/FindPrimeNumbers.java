package lv1;

import java.util.stream.IntStream;

public class FindPrimeNumbers {

    public int solution(int n) {

        return (int) IntStream.rangeClosed(1, n)
            .filter(i -> isPrime(i))
            .count();
    }

    public boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
