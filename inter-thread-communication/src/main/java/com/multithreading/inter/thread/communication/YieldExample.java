package com.multithreading.inter.thread.communication;

public class YieldExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 1 executing");
                Thread.yield(); // Yield execution to another thread
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 2 executing");
            }
        });

        t1.start();
        t2.start();
    }
}
