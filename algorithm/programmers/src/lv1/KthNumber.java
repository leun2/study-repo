package lv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KthNumber {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> result = new ArrayList<>();
        for(int[] command: commands){
            result.add(calc(array, command));
        }
        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }

    private int calc(int[] arr, int[] command) {
        int[] subArr = Arrays.stream(arr, command[0] - 1, command[1])
            .sorted()
            .toArray();

        return subArr[command[2] -1];
    }

//    public int[] solution(int[] array, int[][] commands) {
//        return Arrays.stream(commands)
//            .mapToInt(c -> Arrays.stream(array, c[0] - 1, c[1])
//                .sorted()
//                .toArray()[c[2] - 1])
//            .toArray();
//    }
}
