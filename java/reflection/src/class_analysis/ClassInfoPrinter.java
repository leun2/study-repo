package class_analysis;

import java.util.HashMap;
import java.util.Map;

public class ClassInfoPrinter {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringClass = String.class;
        Map<String, Integer> mapObject = new HashMap <>();
        Class<?> hashMapClass = mapObject. getClass();
        Class<?> squareClass = Class.forName("ClassInfoPrinter$Square");

        PaintClassInfo(squareClass, hashMapClass, stringClass);
    }

    private static void PaintClassInfo(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            System.out.printf("class name: %s class package name: %s%n",
                clazz.getSimpleName(),
                clazz.getPackageName());

            Class<?> [] implementedInterfaces = clazz.getInterfaces();
            for (Class<?> implementedInterface : implementedInterfaces) {
                System.out.printf("class: %s implements: %s%n",
                    clazz.getSimpleName(),
                    implementedInterface.getSimpleName());
            }

            System.out.println();
            System.out.println();
        }
    }

    private static class Square implements Drawable {

        @Override
        public int getNumberOfCorners() {
            return 4;
        }
    }

    private static interface Drawable {

        int getNumberOfCorners();
    }

    private enum Color {
        RED,
        GREEN,
        BLUE,
        YELLOW
    }
}