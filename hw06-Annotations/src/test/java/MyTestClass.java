import otus.study.After;
import otus.study.Before;
import otus.study.Test;

import java.util.ArrayList;
import java.util.List;

public class MyTestClass {
    private List<Integer> list;
    @Before
    public void setUp() {
        // Инициализация перед выполнением тестов
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
    }

    @Test
    public void testAddElement() {
        // Проверка добавления элемента
        int initialSize = list.size();
        list.add(3);
        int newSize = list.size();
        assert newSize == initialSize + 1 : "Failed to add element to the list";
    }

    @Test
    public void testRemoveElement() {
        // Проверка удаления элемента
        int initialSize = list.size();
        list.remove(Integer.valueOf(1));
        int newSize = list.size();
        assert newSize == initialSize - 1 : "Failed to remove element from the list";
    }

    @After
    public void tearDown() {
        // Логика завершения после выполнения тестов
        list.clear();
    }

    public static void main(String[] args) {
        MyTestClass testClass = new MyTestClass();
        testClass.setUp();
        testClass.testAddElement();
        testClass.tearDown();
        testClass.setUp();
        testClass.testRemoveElement();
        testClass.tearDown();
    }
}