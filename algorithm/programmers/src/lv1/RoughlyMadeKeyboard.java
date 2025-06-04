package lv1;

import java.util.HashMap;
import java.util.Map;

public class RoughlyMadeKeyboard {

    public int[] solution(String[] keymap, String[] targets) {
        Map<String, Integer> map = new HashMap<>();
        for (String key : keymap) {

            String[] arr = key.split("");
            for (int i = 0; i < arr.length; i++) {
                int pressCount = i + 1;
                if (!map.containsKey(arr[i]) || pressCount < map.get(arr[i])) {
                    map.put(arr[i], pressCount);
                }
            }
        }

        int[] result = new int[targets.length];

        for (int index = 0; index < targets.length; index++) {
            String target = targets[index];
            String[] arr = target.split("");
            int sum = 0;
            boolean isInvalid = false;

            for (String ch : arr) {
                if (map.containsKey(ch)) {
                    sum += map.get(ch);
                } else {
                    isInvalid = true;
                    break;
                }
            }
            result[index] = isInvalid ? -1 : sum;
        }
        return result;
    }
}
