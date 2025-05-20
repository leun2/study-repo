package lv1;

import java.util.Arrays;

public class ArrayOfNumbersNoRemainder {
    public int[] solution(int[] arr, int divisor) {
        int[] result = Arrays.stream(arr)
            .filter(n -> n % divisor == 0)
            .sorted()
            .toArray();

        return result.length == 0 ? new int[]{-1} : result;
    }
}
