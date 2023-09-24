import otus.study.After;
import otus.study.Before;
import otus.study.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
    public static void runTests(String className) throws InvocationTargetException {
        int totalTests = 0;
        int passedTests = 0;
        int failedTests = 0;

        try {
            Class<?> clazz = Class.forName(className);
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

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("Total tests: " + totalTests);
        System.out.println("Tests passed: " + passedTests);
        System.out.println("Tests failed: " + failedTests);
    }

    public static void main(String[] args) throws InvocationTargetException {
        runTests("MyTestClass");
    }


}
