package otus.study;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@CustomToString
public class TestRunner   {
    public static void runTests(Class<?> clazz) throws InvocationTargetException {
        int totalTests = 0;
        int passedTests = 0;
        int failedTests = 0;

        try {
            Object testObject = clazz.newInstance();

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Before.class)) {
                    method.invoke(testObject);
                }
            }

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    totalTests++;
                    try {
                        method.invoke(testObject);
                        passedTests++;
                    } catch (InvocationTargetException e) {
                        System.err.println("Test failed: " + method.getName());
                        failedTests++;
                    }
                }
            }

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(After.class)) {
                    method.invoke(testObject);
                }
            }

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("Total tests: " + totalTests);
        System.out.println("Tests passed: " + passedTests);
        System.out.println("Tests failed: " + failedTests);
    }

    public static void main(String[] args) throws InvocationTargetException {
        runTests(TestRunner.class); // Замените MyTestClass на класс, который хотите тестировать
    }
}