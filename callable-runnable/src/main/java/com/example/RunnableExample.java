package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableExample implements Runnable {
    @Override
    public void run() {
        System.out.println("Running in a separate thread: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        /*Thread thread = new Thread(new RunnableExample());
        thread.start();*/

        RunnableExample runnableExample = new RunnableExample();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(runnableExample);
    }
}
