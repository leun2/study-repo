package lv1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DeckOfCards {

    public String solution(String[] cards1, String[] cards2, String[] goal) {
        Queue<String> deck1 = new LinkedList<>(Arrays.asList(cards1));
        Queue<String> deck2 = new LinkedList<>(Arrays.asList(cards2));


        for (String s : goal) {
            if (!deck1.isEmpty() && s.equals(deck1.peek())) {
                deck1.poll();
            } else if (!deck2.isEmpty() && s.equals(deck2.peek())) {
                deck2.poll();
            } else {
                return "No";
            }
        }
        return "Yes";
    }

//    public String solution(String[] cards1, String[] cards2, String[] goal) {
//        Queue<String> deck1 = new LinkedList<>(Arrays.asList(cards1));
//        Queue<String> deck2 = new LinkedList<>(Arrays.asList(cards2));
//
//        for (String s : goal) {
//            if (s.equals(deck1.peek())) {
//                deck1.poll();
//            } else if (s.equals(deck2.peek())) {
//                deck2.poll();
//            } else {
//                break;
//            }
//        }
//
//        return deck1.size() + deck2.size() != 0  ? "No" : "Yes";
//    }
}
