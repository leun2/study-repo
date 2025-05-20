package lv1;

import java.util.ArrayList;
import java.util.List;

public class HateSameNumbers {
    public int[] solution(int []arr) {
        if (arr.length == 0) return new int[0];

        List<Integer> result = new ArrayList<>();
        result.add(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                result.add(arr[i]);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
