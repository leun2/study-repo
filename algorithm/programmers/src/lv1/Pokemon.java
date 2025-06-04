package lv1;

import java.util.HashSet;
import java.util.Set;

public class Pokemon {

    public int solution(int[] nums) {

        Set<Integer> uniqueNumbers = new HashSet<>();
        for(int i : nums) {
            uniqueNumbers.add(i);
        }

        return Math.min(uniqueNumbers.size(), nums.length / 2);
    }
}
