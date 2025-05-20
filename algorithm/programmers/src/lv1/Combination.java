package lv1;

public class Combination {
    public int solution(int[] number) {
        return (int) comb(number, 0, 0, 0);
    }

    private long comb(int[] arr, int start, int depth, int sum) {
        if (depth == 4) return sum == 0 ? 1 : 0;

        long count = 0;
        for (int i = start; i < arr.length; i++) {
            count += comb(arr, i + 1, depth + 1, sum + arr[i]);
        }
        return count;
    }
}
