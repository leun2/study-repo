package class_analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassAnalyzer {

    public static void main(String[] args) {
        PopupTypeInfo info = ClassAnalyzer.createPopupTypeInfoFromClass(ArrayList.class);
        System.out.println(info);
    }

    private static final List<String> JDK_PACKAGE_PREFIXES =
        Arrays.asList("com.sun.", "java", "javax", "jdk", "org.w3c", "org.xml");

    public static PopupTypeInfo createPopupTypeInfoFromClass(Class<?> inputClass) {
        PopupTypeInfo popupTypeInfo = new PopupTypeInfo();

        popupTypeInfo.setPrimitive(inputClass.isPrimitive())
            .setInterface(inputClass.isInterface())
            .setEnum(inputClass.isEnum())
            .setName(inputClass.getSimpleName())
            .setJdk(isJdkClass(inputClass))
            .addAllInheritedClassNames(getAllInheritedClassNames(inputClass));

        return popupTypeInfo;
    }

    /*********** Helper Methods ***************/

    public static boolean isJdkClass(Class<?> inputClass) {
        Package pkg = inputClass.getPackage();

        if (pkg == null) {
            return true;
        }

        String packageName = pkg.getName();
        return JDK_PACKAGE_PREFIXES.stream().anyMatch(packageName::startsWith);
    }

    public static String[] getAllInheritedClassNames(Class<?> inputClass) {
        List<String> result = new ArrayList<>();

        Class<?> current = inputClass.getSuperclass();
        while (current != null) {
            result.add(current.getSimpleName());
            current = current.getSuperclass();
        }

        return result.toArray(new String[0]);
    }
}