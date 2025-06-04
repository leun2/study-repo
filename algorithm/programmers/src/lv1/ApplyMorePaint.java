package lv1;

public class ApplyMorePaint {

    public int solution(int n, int m, int[] section) {
        if(m == n) return 1;

        if(m == 1) return section.length;

        int start = section[0];
        int result = 1;
        for(int i: section){
            if(m <= i - start){
                result++;
                start = i;
            }
        }
        return result;
    }
}
