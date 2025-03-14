package com.example;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class RealWorldExample {

    record User(int id, String name){}

    record Order(int id, int userId){}

    public static void main(String[] args) throws InterruptedException {
        fetchUserOrders();
        TimeUnit.MILLISECONDS.sleep(100);
    }

    private  static void fetchUserOrders() {
        getUser(101)
                .thenCompose(RealWorldExample::getUserOrders)  // Fetch orders after fetching user
                .thenAccept(orders -> orders.forEach(order -> System.out.println("Order id : "+order.id())));
    }

    private static CompletableFuture<User> getUser(int userId) {
        return CompletableFuture.supplyAsync(() -> new User(userId, "Zaheer"));
    }

    private static CompletableFuture<List<Order>> getUserOrders(User user) {
        return CompletableFuture.supplyAsync(() -> List.of(new Order(1, user.id()), new Order(2, user.id())));
    }

}
