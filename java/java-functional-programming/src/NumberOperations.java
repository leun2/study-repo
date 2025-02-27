import java.util.List;

public class NumberOperations {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5, 7, 10, 9, 8);


        printAllNumbersInListStructured(numbers);
        printEvenNumbersInListStructured(numbers);

        printAllNumbersInListFunctional(numbers);
        printEvenNumbersInListFunctional(numbers);
        printSquaresOfEvenNumbersInListFunctional(numbers);
    }

    private static void printAllNumbersInListStructured(List<Integer> numbers) {

        for (int num : numbers) {
            System.out.println(num);
        }
    }

    private static void printEvenNumbersInListStructured(List<Integer> numbers) {

        for (int num : numbers) {
            if (num % 2 == 0) {
                System.out.println(num);
            }
        }
    }

    private static void printAllNumbersInListFunctional(List<Integer> numbers) {

        numbers
            .forEach(System.out::println);
    }

    private static void printEvenNumbersInListFunctional(List<Integer> numbers) {

        numbers.stream()
            .filter(num -> num % 2 == 0)
            .forEach(System.out::println);
    }

    private static void printSquaresOfEvenNumbersInListFunctional(List<Integer> numbers) {

        numbers.stream()
            .filter(num -> num % 2 == 0)
            .map(num -> num*num)
            .forEach(System.out::println);
    }
}
