package lv1;

import java.util.stream.IntStream;

public class CalculateYourShortfall {
    public long solution(int price, int money, int count) {
        long result = IntStream.rangeClosed(1, count)
            .mapToLong(n -> (long) n * price)
            .sum();
        return Math.max(result - money, 0);
    }

//    public long solution(int price, int money, int count) {
//        long total = (long) price * count * (count + 1) / 2;
//        return Math.max(0, total - money);
//    }
}
