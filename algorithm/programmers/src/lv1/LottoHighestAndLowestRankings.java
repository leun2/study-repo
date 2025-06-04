package lv1;

import java.util.HashSet;
import java.util.Set;

public class LottoHighestAndLowestRankings {
    public int[] solution(int[] lottos, int[] win_nums) {

        Set<Integer> set = new HashSet<>();
        int z = 0;
        int l = 0;
        int c = 0;

        for(int n: lottos) {
            if(n != 0)
                set.add(n);
        }
        l = set.size();
        z = 6 - l;

        for(int n: win_nums) {
            set.add(n);
        }
        c = set.size();

        int match = l + 6 - c;
        int maxRank = 7 - (match + z);
        int minRank = 7 - match;

        if (maxRank > 6) maxRank = 6;
        if (minRank > 6) minRank = 6;

        return new int[] {maxRank, minRank};
    }

//    public int[] solution(int[] lottos, int[] win_nums) {
//
//        Set<Integer> winSet = Arrays.stream(win_nums).boxed().collect(Collectors.toSet());
//
//        int zeroCount = 0;
//        int matchCount = 0;
//
//        for (int n : lottos) {
//            if (n == 0) zeroCount++;
//            else if (winSet.contains(n)) matchCount++;
//        }
//
//        int maxRank = 7 - (matchCount + zeroCount);
//        int minRank = 7 - matchCount;
//
//        maxRank = Math.min(maxRank, 6);
//        minRank = Math.min(minRank, 6);
//
//        return new int[] {maxRank, minRank};
//    }
}
