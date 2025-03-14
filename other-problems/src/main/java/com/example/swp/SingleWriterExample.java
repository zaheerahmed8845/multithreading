package com.example.swp;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SingleWriterExample {

    private final Queue<String> queue = new ConcurrentLinkedQueue<>();

    public void write(String data) {
        queue.add(data); // Only one thread writes
    }

    public String read() {
        return queue.poll(); // Multiple threads can read
    }

    public static void main(String[] args) {
        SingleWriterExample example = new SingleWriterExample();

        // Single writer thread
        Thread writer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                example.write("Data " + i);
                System.out.println("Written: Data " + i);
            }
        });

        // Multiple reader threads
        Thread reader1 = new Thread(() -> {
            while (true) {
                String data = example.read();
                if (data != null) System.out.println("Reader1 Read: " + data);
            }
        });

        Thread reader2 = new Thread(() -> {
            while (true) {
                String data = example.read();
                if (data != null) System.out.println("Reader2 Read: " + data);
            }
        });

        writer.start();
        reader1.start();
        reader2.start();
    }
}
