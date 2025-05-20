package lv1;

import java.util.HashSet;
import java.util.Set;

public class TakeTwoAndAddThem {
    Set<Integer> set = new HashSet<>();

    public int[] solution(int[] numbers) {
        comb(numbers, 0, 0, 0);
        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    private void comb(int[] arr, int start, int depth, int sum) {
        if (depth == 2) {
            set.add(sum);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            comb(arr, i + 1, depth + 1, sum + arr[i]);
        }
    }
}
