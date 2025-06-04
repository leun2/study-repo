package lv1;

import java.util.HashMap;
import java.util.Map;

public class MemoriesScore {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int index = 0;
        Map<String,Integer> map = new HashMap<>();
        int[] result = new int[photo.length];

        for(int i=0;i<name.length;i++) {
            map.put(name[i], yearning[i]);
        }

        for(String[] arr: photo) {
            int score = 0;
            for(String s: arr) {
                score += map.getOrDefault(s, 0);
            }
            result[index] = score;
            index++;
        }

        return result;
    }
}
