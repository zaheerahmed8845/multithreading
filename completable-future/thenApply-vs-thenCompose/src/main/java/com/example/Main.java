package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {

        withoutThenCompose();
        withThenCompose();

    }

    private static void withoutThenCompose(){
        CompletableFuture<CompletableFuture<String>> future =
                CompletableFuture.supplyAsync(() -> "Hello")
                        .thenApply(result -> CompletableFuture.supplyAsync(() -> result + " World!"));

        try {
            System.out.println("Result without then Compose: "+future.get().get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void withThenCompose(){
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> "Hello")
                        .thenCompose(result -> CompletableFuture.supplyAsync(() -> result + " World!"));

        try {
            System.out.println("Result with then Compose: "+future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}