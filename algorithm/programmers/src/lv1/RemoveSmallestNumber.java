package lv1;

import java.util.Arrays;

public class RemoveSmallestNumber {
    public int[] solution(int[] arr) {
        if(arr.length == 1) return new int[] {-1};

        int min = Arrays.stream(arr).min().orElse(-1);

        return Arrays.stream(arr)
                .filter(n -> n != min)
                .toArray();
    }
}
