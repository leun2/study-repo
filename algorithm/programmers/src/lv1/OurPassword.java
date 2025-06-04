package lv1;

import java.util.HashSet;
import java.util.Set;

public class OurPassword {

    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>();
        for (char ch : skip.toCharArray()) {
            set.add(ch);
        }
        for (char ch : s.toCharArray()) {
            int count = 0;
            char current = ch;

            while (count < index) {
                current++;

                if (current > 'z') {
                    current = 'a';
                }

                if (!set.contains(current)) {
                    count++;
                }
            }
            sb.append(current);
        }

        return sb.toString();
    }
}