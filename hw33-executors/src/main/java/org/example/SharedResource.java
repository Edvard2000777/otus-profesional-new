package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedResource {
    private int count = 1;
    private boolean increasing = true;
    private final Lock lock = new ReentrantLock();

    public void printNumbers() {
        while (count >= 1 && count <= 10) {
            lock.lock();
            try {
                if (increasing) {
                    System.out.print("Поток 1: " + count + " ");
                    count++;
                    if (count > 10) {
                        increasing = false;
                        count = 9;
                    }
                } else {
                    System.out.print("Поток 2: " + count + " ");
                    count--;
                    if (count < 1) {
                        increasing = true;
                        count = 2;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }
}