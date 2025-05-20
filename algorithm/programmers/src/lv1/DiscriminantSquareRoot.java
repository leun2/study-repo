package lv1;

public class DiscriminantSquareRoot {
//    public long solution(long n) {
//        if(Math.sqrt(n) % 1 == 0){
//            return (long) Math.pow(Math.sqrt(n) + 1, Math.sqrt(n) + 1);
//        }
//        return -1;
//    }

    public long solution(long n) {
        double sqrt = Math.sqrt(n);
        if (sqrt % 1 == 0) {
            long next = (long) sqrt + 1;
            return next * next;
        }
        return -1;
    }
}
