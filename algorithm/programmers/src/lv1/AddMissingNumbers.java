package lv1;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddMissingNumbers {

//    public int solution(int[] numbers) {
//        return 45 - Arrays.stream(numbers).sum();
//    }

    public int solution(int[] numbers) {
        Set<Integer> fullSet = IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toSet());
        for (int num : numbers) {
            fullSet.remove(num);
        }
        return fullSet.stream().mapToInt(Integer::intValue).sum();
    }
}
