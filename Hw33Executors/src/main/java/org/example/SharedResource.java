package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    private int count = 1;
    private boolean increasing = true;

    public synchronized void printNumbers() {
        while (count >= 1 && count <= 10) {
            if (increasing) {
                System.out.print("Поток 1:" + count + " ");
                count++;
                if (count > 10) {
                    increasing = false;
                    count = 9;
                }
            } else {
                System.out.print("Поток 2:" + count + " ");
                count--;
                if (count < 1) {
                    increasing = true;
                    count = 2;
                }
            }
            notify(); // Уведомляем другой поток, чтобы он мог выполнить свою часть
            try {
                if (count >= 1 && count <= 10) {
                    wait(); // Ожидаем, чтобы другой поток мог напечатать свое число
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
