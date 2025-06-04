package lv1;

public class SplitString {

    public int solution(String s) {
        if(s.length() == 1) return 1;
        int c = 1;
        int o = 0;
        int index = 0;
        int result = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(index) == s.charAt(i)) {
                c++;
            } else {
                o++;
            }

            if (c == o) {
                result++;
                index = i + 1;
                c = 0;
                o = 0;
            }

        }

        if (c != 0 || o != 0) result++;

        return result;
    }

//    public int solution(String s) {
//        int result = 0;
//        int c = 0;
//        int o = 0;
//        char x = s.charAt(0);
//
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == x) {
//                c++;
//            } else {
//                o++;
//            }
//
//            if (c == o) {
//                result++;
//                if (i + 1 < s.length()) {
//                    x = s.charAt(i + 1);
//                }
//                c = 0;
//                o = 0;
//            }
//        }
//
//        if (c != 0 || o != 0) result++;
//
//        return result;
//    }
}
