package lv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SortStringsHoweverYouWant {
    public static String[] solution(String[] strings, int n) {
        // Step 1: 사전순 정렬
        Arrays.sort(strings);

        // Step 2: 문자열 → 인덱스 n 문자 매핑
        Map<String, Character> map = new LinkedHashMap<>();
        for (String s : strings) {
            map.put(s, s.charAt(n));
        }

        // Step 3: 맵 entry를 인덱스 n의 문자 기준으로 정렬
        List<Map.Entry<String, Character>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue());

        // Step 4: 키만 뽑아서 결과 배열로 반환
        return entryList.stream()
            .map(Map.Entry::getKey)
            .toArray(String[]::new);
    }
//    public static String[] solution(String[] strings, int n) {
//        return Arrays.stream(strings)
//            .sorted((a, b) -> a.charAt(n) == b.charAt(n)
//                ? a.compareTo(b)
//                : a.charAt(n) - b.charAt(n))
//            .toArray(String[]::new);
//    }

}
