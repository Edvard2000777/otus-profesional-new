package org.example;
import java.util.concurrent.Semaphore;


public class SharedResource {
    private int count = 1;
    private Semaphore semaphore = new Semaphore(1);

    public void printNumbers() {
        try {
            while (count >= 1 && count <= 10) {
                semaphore.acquire(); // Захватываем разрешение

                System.out.print(Thread.currentThread().getName() + ":" + count + " ");

                if (Thread.currentThread().getName().equals("Поток 1")) {
                    count++;
                    if (count > 10) {
                        count = 9;
                    }
                } else {
                    count--;
                    if (count < 1) {
                        count = 2;
                    }
                }

                semaphore.release(); // Освобождаем разрешение
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
