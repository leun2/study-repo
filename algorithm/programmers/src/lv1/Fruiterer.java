package lv1;

import java.util.Arrays;

public class Fruiterer {

    public int solution(int k, int m, int[] score) {

        int result = 0;
        int[] arr = Arrays.stream(score).sorted().toArray();
        for(int i = score.length % m; i < score.length; i+=m){
            result += arr[i] * m;
        }

        return result;
    }
}
