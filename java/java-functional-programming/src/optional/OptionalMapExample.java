package optional;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OptionalMapExample {

    public static void main(String[] args) {

        Optional<String> optionalString = Optional.of("hello");

        Optional<String> transformed = optionalString.map(sentence ->
            IntStream.range(0, sentence.length()) // [0, 1, 2, 3, 4]
                .mapToObj(i -> i % 2 == 1
                    ? Character.toUpperCase(sentence.charAt(i))
                    : sentence.charAt(i))
                .map(String::valueOf) // char -> string
                .collect(Collectors.joining())
        );

        System.out.println(transformed.orElse("Transform Is Failed"));
    }
}
