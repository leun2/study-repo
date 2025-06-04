package lv1;

import java.util.stream.IntStream;

public class TemplarWeapon {
    public int solution(int number, int limit, int power) {
        int totalPower = IntStream.rangeClosed(1, number)
            .map(i -> {
                int divisorCount = countDivisors(i);
                if (divisorCount > limit) {
                    return power;
                } else {
                    return divisorCount;
                }
            })
            .sum();

        return totalPower;
    }

    public int countDivisors(int n) {
        if (n == 1) {
            return 1;
        }

        int count = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (i * i == n) {
                    count++;
                } else {
                    count += 2;
                }
            }
        }
        return count;
    }
}
