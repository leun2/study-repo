package lv1;

import java.util.Scanner;

public class RectangularStar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        String line = "*".repeat(a);
        String result = (line + "\n").repeat(b);
        System.out.print(result);
    }
}
