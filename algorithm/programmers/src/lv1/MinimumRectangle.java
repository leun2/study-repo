package lv1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MinimumRectangle {
    public int solution(int[][] sizes) {
        int max1 = 0;
        int max2 = 0;
        int[][] arr = IntStream.range(0, sizes.length)
            .mapToObj(i -> Arrays.stream(sizes[i]).sorted().toArray())
            .toArray(int[][]::new);

        for(int[] array: arr) {
            if(array[0] > max1)
                max1 = array[0];
            if(array[1] > max2)
                max2 = array[1];
        }

        return max1 * max2;
    }

//    public int solution(int[][] sizes) {
//        int[] max = Arrays.stream(sizes)
//            .map(size -> Arrays.stream(size).sorted().toArray())
//            .reduce(new int[]{0, 0}, (acc, size) -> new int[]{
//                Math.max(acc[0], size[0]),
//                Math.max(acc[1], size[1])
//            });
//
//        return max[0] * max[1];
//    }
}
