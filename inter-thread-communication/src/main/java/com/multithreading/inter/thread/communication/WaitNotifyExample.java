package com.multithreading.inter.thread.communication;

class SharedResource {
    private boolean available = false;

    public synchronized void produce() throws InterruptedException {
        while (available) {
            wait(); // Wait until the resource is consumed
        }
        System.out.println("Produced item");
        available = true;
        notify(); // Notify consumer
    }

    public synchronized void consume() throws InterruptedException {
        while (!available) {
            wait(); // Wait until an item is produced
        }
        System.out.println("Consumed item");
        available = false;
        notify(); // Notify producer
    }

    public void waitWithoutLock(){
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void notifyWithoutLock(){
        notify();
    }
}

public class WaitNotifyExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producer = new Thread(() -> {
            try {
                resource.produce();
            } catch (InterruptedException ignored) {
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                resource.consume();
            } catch (InterruptedException ignored) {}
        });

        producer.start();
        consumer.start();

        //throws Illegal Monitor Exception, If we are calling the below methods without acquiring a lock.
        //resource.waitWithoutLock();
        //resource.notifyWithoutLock();
    }

}
