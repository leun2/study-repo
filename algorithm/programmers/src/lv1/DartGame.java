package lv1;

import java.util.Arrays;

public class DartGame {

    public int solution(String dartResult) {

        int[] scores = new int[3];
        int idx = -1;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(c);
            } else if (c == 'S' || c == 'D' || c == 'T') {
                idx++;
                int n = Integer.parseInt(sb.toString());
                sb.setLength(0);

                if (c == 'S') {
                    scores[idx] = n;
                } else if (c == 'D') {
                    scores[idx] = (int) Math.pow(n, 2);
                } else {
                    scores[idx] = (int) Math.pow(n, 3);
                }
            } else if (c == '*') {
                scores[idx] *= 2;
                if (idx > 0) {
                    scores[idx - 1] *= 2;
                }
            } else if (c == '#') {
                scores[idx] *= -1;
            }
        }
        return Arrays.stream(scores).sum();
    }
}