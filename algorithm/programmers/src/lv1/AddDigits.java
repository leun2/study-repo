package lv1;

import java.util.Arrays;

public class AddDigits {
    public static int solution(int n) {

        return Arrays.stream(Integer.toString(n).split(""))
            .mapToInt(Integer::parseInt)
            .sum();
    }
}
