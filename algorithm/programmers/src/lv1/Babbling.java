package lv1;

public class Babbling {

    public int solution(String[] babbling) {
        String[] words = {"aya", "ye", "woo", "ma"};
        int count = 0;

        for (String b : babbling) {
            String prev = "";
            String temp = b;

            boolean isValid = true;

            while (!temp.isEmpty()) {
                boolean matched = false;
                for (String w : words) {
                    if (temp.startsWith(w) && !w.equals(prev)) {
                        temp = temp.substring(w.length());
                        prev = w;
                        matched = true;
                        break;
                    }
                }

                if (!matched) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) count++;
        }

        return count;
    }
}
