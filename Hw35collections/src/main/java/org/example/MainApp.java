package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class MainApp {
    private static MedianList<Integer> medianList = new MedianList<>();

    public static void main(String[] args) throws InterruptedException {
        threadsWork();
    }

    public static void threadsWork() throws InterruptedException {
        Runnable runnableAddItem = () -> {
            for (int i = 0; i <= 1000_000_000; i++) {
                medianList.add(ThreadLocalRandom.current().nextInt(0, 1000000));
            }
        };
        Runnable runnablePrintMedian = () -> {
            for (int i = 0; i <= 100; i++) {
                System.out.println(medianList.getMedian());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread producer1 = new Thread(runnableAddItem);
        Thread producer2 = new Thread(runnableAddItem);
        Thread consumer = new Thread(runnablePrintMedian);
        producer1.start();
        producer2.start();
        consumer.start();
        producer1.join();
        producer2.join();
        consumer.join();
    }
}
