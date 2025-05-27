package optional;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OrElseExample {

    public static void main(String[] args) {

        Optional<String> optionalValue = Optional.of("value");

        String result1 = optionalValue.orElse(getDefault());

        System.out.println(result1);

        String result2 = optionalValue.orElseGet(OrElseExample::getDefault);

        System.out.println(result2);

        String result3 = optionalValue
            .orElseThrow(() -> new NoSuchElementException("Optional Value Is Empty"));

        System.out.println(result3);
    }

    public static String getDefault() {
        System.out.println("Creating Defaults...");
        return "default";
    }
}
