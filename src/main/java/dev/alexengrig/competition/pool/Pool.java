package dev.alexengrig.competition.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Pool {
    public static void main(String[] args) {
        Function<Integer, Runnable> runnableById = id -> () -> {
            System.out.println("Starting " + id);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finishing " + id);
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            executorService.submit(runnableById.apply(i));
        }
        executorService.shutdown();
        System.out.println("All tasks submitted");
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks completed");
    }
}
