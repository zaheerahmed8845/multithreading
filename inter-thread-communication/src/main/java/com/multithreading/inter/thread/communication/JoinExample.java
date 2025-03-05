package com.multithreading.inter.thread.communication;

class Task implements Runnable {
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Task Completed");
        } catch (InterruptedException ignored) {}
    }
}

public class JoinExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Task());
        t.start();

        t.join(); // Main thread waits for t to complete
        System.out.println("Main thread resumes");
    }
}
