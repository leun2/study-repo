package optional;

import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {

        Optional<String> optionalValue = Optional.of("value");
        System.out.println(optionalValue.get());

        Optional<String> optionalNullable = Optional.ofNullable(null);
        System.out.println(optionalNullable.isPresent());

        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.orElse("default"));
    }
}
