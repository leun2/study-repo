package lv1;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CreateStrangeCharacters {
    public String solution(String s) {
        return Arrays.stream(s.split(" ", -1))
            .map(word ->
                IntStream.range(0, word.length())
                    .mapToObj(
                        i -> i % 2 == 0
                            ? Character.toUpperCase(word.charAt(i))
                            : Character.toLowerCase(word.charAt(i)))
                .map(String::valueOf)
                .collect(Collectors.joining())
            )
        .collect(Collectors.joining(" "));
    }

//    public String solution(String s) {
//        StringBuilder sb = new StringBuilder();
//        int index = 0;
//
//        for (char ch : s.toCharArray()) {
//            if (ch == ' ') {
//                sb.append(' ');
//                index = 0; // 단어 시작 시 인덱스 초기화
//            } else {
//                if (index % 2 == 0) {
//                    sb.append(Character.toUpperCase(ch));
//                } else {
//                    sb.append(Character.toLowerCase(ch));
//                }
//                index++;
//            }
//        }
//
//        return sb.toString();
//    }
}
