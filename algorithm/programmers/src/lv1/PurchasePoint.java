package lv1;

public class PurchasePoint {
    public int solution(int p, int c, int n) {
        return n + calc(p, c, n * p);
    }

    private int calc(int p, int c, int point) {
        if(point < c) return 0;

        int free = point / c;
        int newPoint = point % c + free * p;

        return free + calc(p, c, newPoint);
    }
}
