package lv1;

import java.util.stream.IntStream;

public class ANumberWhoseRemainderIsOne {
    public static int solution(int n) {
//        for (int i = 2; i < n; i++) {
//            if (n % i == 1) {
//                return i;
//            }
//        }
//        return -1;

        return IntStream.range(3, n)
            .filter(i -> n % i == 1)
            .findFirst()
            .orElseThrow();
    }
}
