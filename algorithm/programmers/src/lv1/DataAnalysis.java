package lv1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataAnalysis {

    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {

        Map<String, Integer> map = Map.of(
            "code", 0,
            "date", 1,
            "maximum", 2,
            "remain", 3
        );

        int extIdx = map.get(ext);
        int sortIdx = map.get(sort_by);

        List<int[]> resultList = Arrays.stream(data)
            .filter(row -> row[extIdx] < val_ext)
            .sorted(Comparator.comparingInt(row -> row[sortIdx]))
            .collect(Collectors.toList());

        int[][] result = new int[resultList.size()][];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }
}
