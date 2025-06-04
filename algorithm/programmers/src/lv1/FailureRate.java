package lv1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FailureRate {

    public int[] solution(int N, int[] stages) {
        List<Stage> stageList = IntStream.rangeClosed(1, N)
            .mapToObj(i -> {
                long challenger = Arrays.stream(stages).filter(n -> i == n).count();
                long arrival = Arrays.stream(stages).filter(n -> i <= n).count();
                double failRate = arrival == 0 ? 0.0 : (double) challenger / arrival;
                return new Stage(i, failRate);
            })
            .sorted(Comparator.comparingDouble(Stage::getFailRate).reversed()
                .thenComparingInt(Stage::getStage))
            .collect(Collectors.toList());

        return stageList.stream().mapToInt(Stage::getStage).toArray();
    }

    static class Stage {
        private final int stage;
        private final double failRate;

        public Stage(int stage, double failRate) {
            this.stage = stage;
            this.failRate = failRate;
        }

        public int getStage() {
            return stage;
        }

        public double getFailRate() {
            return failRate;
        }
    }
}
