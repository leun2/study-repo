package lv1;

public class CokeProblem {

    public static int solution(int a, int b, int n) {
        return calc(a, b, n);
    }

    private static int calc(int a, int b, int n) {
        if(n < a) return 0;
        int exchanged = (n / a) * b;
        int remaining = (n % a) + exchanged;
        return exchanged + calc(a, b, remaining);
    }
}
