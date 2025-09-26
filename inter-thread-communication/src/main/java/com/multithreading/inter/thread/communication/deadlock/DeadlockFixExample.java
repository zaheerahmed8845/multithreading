package com.multithreading.inter.thread.communication.deadlock;


public class DeadlockFixExample {
    public static void main(String[] args) {
        Resource resourceA = new Resource("Resource A");
        Resource resourceB = new Resource("Resource B");

        Thread thread1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread().getName() + " locked " + resourceA.name);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                synchronized (resourceB) {
                    System.out.println(Thread.currentThread().getName() + " locked " + resourceB.name);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread().getName() + " locked " + resourceA.name);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                synchronized (resourceB) {
                    System.out.println(Thread.currentThread().getName() + " locked " + resourceB.name);
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
