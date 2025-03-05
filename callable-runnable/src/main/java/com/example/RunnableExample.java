package com.example;

public class RunnableExample implements Runnable {
    @Override
    public void run() {
        System.out.println("Running in a separate thread: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableExample());
        thread.start();
    }
}
