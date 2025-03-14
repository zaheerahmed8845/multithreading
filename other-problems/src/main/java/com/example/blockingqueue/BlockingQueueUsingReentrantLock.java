package com.example.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueUsingReentrantLock<T> {

    private final Queue<T> queue;
    private final int capacity;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();  // Condition for producers
    private final Condition notEmpty = lock.newCondition(); // Condition for consumers

    public BlockingQueueUsingReentrantLock(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public void put(T item) throws InterruptedException {
        lock.lock(); // Acquire lock
        try {
            while (queue.size() == capacity) {
                notFull.await(); // Wait if the queue is full
            }
            queue.add(item);
            System.out.println("Produced: " + item);
            notEmpty.signal(); // Signal consumers that an item is available
        } finally {
            lock.unlock(); // Release lock
        }
    }

    public T take() throws InterruptedException {
        lock.lock(); // Acquire lock
        try {
            while (queue.isEmpty()) {
                notEmpty.await(); // Wait if the queue is empty
            }
            T item = queue.poll();
            System.out.println("Consumed: " + item);
            notFull.signal(); // Signal producers that space is available
            return item;
        } finally {
            lock.unlock(); // Release lock
        }
    }

    /*public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }*/

    public static void main(String[] args) {
        BlockingQueueUsingReentrantLock<Integer> queue = new BlockingQueueUsingReentrantLock<>(5);

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    queue.put(i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    queue.take();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }


}
