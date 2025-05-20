package lv1;

import java.util.HashMap;
import java.util.Map;

public class ClosestSameLetter {
    public int[] solution(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int[] result = new int[s.length()];

        for(int i = 0; i< s.length(); i++) {
            char ch = s.charAt(i);

            if(map.containsKey(ch)) {
                result[i] = i - map.get(ch);
            }else {
                result[i] = -1;
            }

            map.put(ch, i);
        }

        return result;
    }
}
