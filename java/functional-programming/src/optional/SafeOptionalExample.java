package optional;

import java.util.Optional;

public class SafeOptionalExample {

    public static void main(String[] args) {
        String value = null;

        Optional<String> optionalValue = Optional.ofNullable(value);

        String result1 = optionalValue.orElse("value");
        System.out.println(result1);

        String result2 = optionalValue.orElseGet(() -> "generated value");
        System.out.println(result2);
    }
}
