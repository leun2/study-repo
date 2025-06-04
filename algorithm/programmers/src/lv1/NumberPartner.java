package lv1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class NumberPartner {

    public String solution(String X, String Y) {

        StringBuilder sb = new StringBuilder();
        Map<String, Integer> yMap = new HashMap<>();

        for(String s: Y.split("")){
            yMap.put(s, yMap.getOrDefault(s, 0) + 1);
        }

        String[] arr = Arrays.stream(X.split("")).sorted(Comparator.reverseOrder()).toArray(String[]::new);

        for(String s: arr){
            if(yMap.containsKey(s) && yMap.get(s) > 0) {
                sb.append(s);
                yMap.put(s, yMap.get(s) - 1);
            }
        }
        String result = sb.toString();
        if(result.isEmpty())
            return "-1";
        else if (result.replace("0", "").isEmpty())
            return "0";
        else
            return result;
    }
}
