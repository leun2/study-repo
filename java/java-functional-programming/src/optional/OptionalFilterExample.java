package optional;

import java.util.Optional;

public class OptionalFilterExample {

    public static void main(String[] args) {
        Optional<String> optionalValue = Optional.of("Java");

        Optional<String> filteredValue = optionalValue.filter(value -> value.startsWith("J"));
        System.out.println(filteredValue.orElse("Value Is Not Match"));

        Optional<String> notMatched = optionalValue.filter(value -> value.startsWith("P"));
        System.out.println(notMatched.orElse("Value Is Not Match"));
    }
}
