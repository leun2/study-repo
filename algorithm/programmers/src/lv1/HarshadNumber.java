package lv1;

public class HarshadNumber {
    public static boolean solution(int x) {

        return x % String.valueOf(x).chars().map(ch -> ch - '0').sum() == 0;
    }
}
