package lv1;

import java.util.Arrays;

public class NumberOfPAndY {
    boolean solution(String s) {
        int count = 0;

        for (char c : s.toLowerCase().toCharArray()) {
            if (c == 'p') count++;
            else if (c == 'y') count--;
        }
        return count == 0;
    }
}
