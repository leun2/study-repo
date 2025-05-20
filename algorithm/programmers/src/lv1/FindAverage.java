package lv1;

import java.util.Arrays;

public class FindAverage {
    public static double solution(int[] arr) {
        return Arrays.stream(arr).average().orElse(0.0);
    }
}
