package lv1;

public class FlipNumIntoArray {
    public static int[] solution(long n) {

        return new StringBuilder(String.valueOf(n))
            .reverse()
            .chars()
            .map(c -> c - '0')
            .toArray();
    }
}
