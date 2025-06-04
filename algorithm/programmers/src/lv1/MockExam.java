package lv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockExam {

    public int[] solution(int[] answers) {

        int[] pattern1 = {1, 2, 3, 4, 5};
        int[] pattern2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] pattern3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] scores = new int[3];

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == pattern1[i % pattern1.length]) {
                scores[0]++;
            }
            if (answers[i] == pattern2[i % pattern2.length]) {
                scores[1]++;
            }
            if (answers[i] == pattern3[i % pattern3.length]) {
                scores[2]++;
            }
        }

        int maxScore = Arrays.stream(scores).max().orElse(0);

        List<Integer> topScorers = new ArrayList<>();
        if (scores[0] == maxScore) {
            topScorers.add(1);
        }
        if (scores[1] == maxScore) {
            topScorers.add(2);
        }
        if (scores[2] == maxScore) {
            topScorers.add(3);
        }

        int[] answer = new int[topScorers.size()];
        for (int i = 0; i < topScorers.size(); i++) {
            answer[i] = topScorers.get(i);
        }

        return answer;
    }
}
