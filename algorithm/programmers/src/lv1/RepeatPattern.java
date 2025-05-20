package lv1;

public class RepeatPattern {
    public String solution(int n) {

        if(n == 1)
            return "수";

        if(n % 2 == 0)
            return "수박".repeat(n / 2);
        else
            return "수박".repeat(n / 2) + "수";

    }
}
