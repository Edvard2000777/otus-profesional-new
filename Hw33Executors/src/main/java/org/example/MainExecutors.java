package org.example;


public class MainExecutors {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(() -> {
            synchronized (sharedResource) {
                sharedResource.printNumbers();
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (sharedResource) {
                sharedResource.printNumbers();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

