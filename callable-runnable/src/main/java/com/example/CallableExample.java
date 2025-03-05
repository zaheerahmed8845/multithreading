package com.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 42; // Returning a value
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(new CallableExample());

        System.out.println("Result: " + future.get()); // Blocks until the result is available

        executor.shutdown();
    }
}
