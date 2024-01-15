package org.example;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainExecutors {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(() -> sharedResource.printNumbers());
        Thread thread2 = new Thread(() -> sharedResource.printNumbers());

        thread1.start();
        thread2.start();
    }
}