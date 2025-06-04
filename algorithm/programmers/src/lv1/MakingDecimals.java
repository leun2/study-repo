package lv1;

public class MakingDecimals {

    public int solution(int[] nums) {
        return comb(nums, 0, 0, 0);
    }

    private int comb(int[] arr, int start, int depth, int sum) {
        if (depth == 3) {
            return isPrime(sum) ? 1 : 0;
        }

        int count = 0B
        for (int i = start; i < arr.length; i++) {
            count += comb(arr, i + 1, depth + 1, sum + arr[i]);
        }
        return count;
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

}
