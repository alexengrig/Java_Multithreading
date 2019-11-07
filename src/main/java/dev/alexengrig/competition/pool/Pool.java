package dev.alexengrig.competition.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Pool {
    public static void main(String[] args) {
        Function<Integer, Runnable> runnableById = id -> () -> {
            System.out.println("Started: " + id);
            try {
                Thread.sleep(id * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completed: " + id);
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 6; i++) {
            executorService.submit(runnableById.apply(i));
        }
        System.out.println("Run all");
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished all");
    }
}
