package lv1;

import java.util.stream.IntStream;

public class SmallSubstring {
    public int solution(String t, String p) {
        if (t.length() < p.length()) return 0;

        long pLong = Long.parseLong(p);
        int len = p.length();

        return (int) IntStream.rangeClosed(0, t.length() - len)
            .filter(i -> Long.parseLong(t.substring(i, i + len)) <= pLong)
            .count();
    }
}
