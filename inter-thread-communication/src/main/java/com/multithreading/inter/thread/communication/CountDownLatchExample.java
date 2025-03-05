package com.multithreading.inter.thread.communication;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " completed");
            latch.countDown(); // Decrement latch count
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();

        latch.await(); // Main thread waits
        System.out.println("All tasks completed. Main thread continues.");
    }
}
