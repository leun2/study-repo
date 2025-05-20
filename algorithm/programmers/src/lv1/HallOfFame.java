package lv1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class HallOfFame {
    public int[] solution(int k, int[] score) {
        List<Integer> hallOfFame = new ArrayList<>();
        List<Integer> pScore = new ArrayList<>();

        for (int n : score) {
            if (hallOfFame.size() < k) {
                hallOfFame.add(n);
            } else {
                int min = hallOfFame.stream().min(Comparator.naturalOrder()).get();
                if (min < n) {
                    hallOfFame.remove(Integer.valueOf(min));
                    hallOfFame.add(n);
                }
            }
            int currentMin = hallOfFame.stream().min(Comparator.naturalOrder()).get();
            pScore.add(currentMin);
        }

        return pScore.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }

//    public static int[] solution(int k, int[] score) {
//        PriorityQueue<Integer> hallOfFame = new PriorityQueue<>();
//        List<Integer> pScore = new ArrayList<>();
//
//        for (int s : score) {
//            if (hallOfFame.size() < k) {
//                hallOfFame.offer(s);
//            } else {
//                if (hallOfFame.peek() < s) {
//                    hallOfFame.poll();
//                    hallOfFame.offer(s);
//                }
//            }
//            pScore.add(hallOfFame.peek());
//        }
//
//        return pScore.stream().mapToInt(Integer::intValue).toArray();
//    }
}
