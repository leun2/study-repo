package lv1;

import java.util.HashSet;
import java.util.Set;

public class GymClothes {

    public int solution(int n, int[] lost, int[] reserve) {

        int result = n;

        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reserveSet = new HashSet<>();

        for (int l : lost) {
            lostSet.add(l);
        }

        for (int r : reserve) {
            if (lostSet.contains(r)) {
                lostSet.remove(r);
            } else {
                reserveSet.add(r);
            }
        }

        for (int r : reserveSet) {
            if (lostSet.contains(r - 1)) {
                lostSet.remove(r - 1);
            } else {
                lostSet.remove(r + 1);
            }
        }

        result -= lostSet.size();
        return result;
    }

}
