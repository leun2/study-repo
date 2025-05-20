package lv1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class StringsInDescendingOrder {
    public static String solution(String s) {
        return Arrays.stream(s.split(""))
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.joining());
    }
}
