package lv1;

public class TernaryReversal {
    public static int solution(int n) {
        StringBuilder sb = new StringBuilder();
        int result = 0;
        int i =  1;
        while (n > 0) {
            sb.append(n % 3);
            n /= 3;
        }

        for(char c: sb.reverse().toString().toCharArray()){
            result += (c - '0') * i;
            i *= 3;
        }

        return result;
    }
}
