package optional;

import java.util.Optional;

public class IfPresentExample {

    public static void main(String[] args) {

        Optional<String> optionalValue = Optional.of("Hello");

        optionalValue.ifPresent(value -> System.out.println("value: " + value));
    }
}
