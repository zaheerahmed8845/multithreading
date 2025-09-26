package com.multithreading.inter.thread.communication.deadlock;

class Resource {
    String name;

    public Resource(String name) {
        this.name = name;
    }
}

public class DeadlockExample {
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
            synchronized (resourceB) {
                System.out.println(Thread.currentThread().getName() + " locked " + resourceB.name);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                synchronized (resourceA) {
                    System.out.println(Thread.currentThread().getName() + " locked " + resourceA.name);
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
