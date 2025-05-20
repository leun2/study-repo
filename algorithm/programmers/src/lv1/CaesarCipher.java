package lv1;

public class CaesarCipher {
    public static String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch == ' ') {
                sb.append(' ');
            } else if (Character.isUpperCase(ch)) {
                int offset = (ch - 'A' + n) % 26;
                sb.append((char) ('A' + offset));
            } else if (Character.isLowerCase(ch)) {
                int offset = (ch - 'a' + n) % 26;
                sb.append((char) ('a' + offset));
            }
        }
        return sb.toString();
    }
}
