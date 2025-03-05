package com.multithreading.inter.thread.communication;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("All threads reached barrier!"));

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " is waiting...");
            try { barrier.await(); } catch (Exception ignored) {}
            System.out.println(Thread.currentThread().getName() + " continues execution.");
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }
}
