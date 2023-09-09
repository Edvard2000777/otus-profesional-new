package org.example;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public double weight() {
        double totalWeight = 0.0;
        for (T fruit : fruits) {
            totalWeight += fruit.getWeight();
        }
        return totalWeight;
    }

    public boolean compare(Box<?> anotherBox) {
        return Math.abs(this.weight() - anotherBox.weight()) < 0.0001;
    }

    public void pourInto(Box<T> anotherBox) {
        if (this == anotherBox) {
            return; // Нельзя пересыпать в саму себя
        }

        for (T fruit : fruits) {
            anotherBox.addFruit(fruit);
        }

        fruits.clear(); // Очистить текущую коробку после пересыпки
    }

    public static void main(String[] args) {
        Box<Apple> appleBox1 = new Box<>();
        appleBox1.addFruit(new Apple(150));
        appleBox1.addFruit(new Apple(175));

        Box<Apple> appleBox2 = new Box<>();
        appleBox2.addFruit(new Apple(200));

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addFruit(new Orange(120));
        orangeBox.addFruit(new Orange(140));

        System.out.println("Вес коробки с яблоками 1: " + appleBox1.weight()); // Вес коробки с яблоками 1: 325.0
        System.out.println("Вес коробки с яблоками 2: " + appleBox2.weight()); // Вес коробки с яблоками 2: 200.0
        System.out.println("Вес коробки с апельсинами: " + orangeBox.weight()); // Вес коробки с апельсинами: 260.0

        System.out.println("Сравнение коробок с яблоками 1 и 2: " + appleBox1.compare(appleBox2)); // Сравнение коробок с яблоками 1 и 2: false
        System.out.println("Сравнение коробок с яблоками 1 и апельсинами: " + appleBox1.compare(orangeBox)); // Сравнение коробок с яблоками 1 и апельсинами: true

        appleBox1.pourInto(appleBox2);
        System.out.println("Вес коробки с яблоками 1 после пересыпки: " + appleBox1.weight()); // Вес коробки с яблоками 1 после пересыпки: 0.0
        System.out.println("Вес коробки с яблоками 2 после пересыпки: " + appleBox2.weight()); // Вес коробки с яблоками 2 после пересыпки: 525.0
    }
}

class Fruit {
    private double weight;

    public Fruit(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}

class Apple extends Fruit {
    public Apple(double weight) {
        super(weight);
    }
}

class Orange extends Fruit {
    public Orange(double weight) {
        super(weight);
    }
}