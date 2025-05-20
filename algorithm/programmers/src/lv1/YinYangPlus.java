package lv1;

public class YinYangPlus {
    public int solution(int[] absolutes, boolean[] signs) {
        int n = 0;
        for(int i=0;i<absolutes.length;i++){
            n += signs[i] ? absolutes[i] : -absolutes[i];
        }
        return n;
    }

//    public int solution(int[] absolutes, boolean[] signs) {
//        return IntStream.range(0, absolutes.length)
//            .map(i -> signs[i] ? absolutes[i] : -absolutes[i])
//            .sum();
//    }
}
