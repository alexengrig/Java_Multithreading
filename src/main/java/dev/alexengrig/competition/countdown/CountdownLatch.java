package dev.alexengrig.competition.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class CountdownLatch {
    public static void main(String[] args) {
        int count = 5;
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        Function<Integer, Runnable> runnableById = id -> () -> {
            System.out.println("Started: " + id);
            try {
                Thread.sleep(id * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("countdown before: " + countDownLatch.getCount());
            countDownLatch.countDown();
            System.out.println("countdown after: " + countDownLatch.getCount());
            System.out.println("Completed: " + id);
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < count; i++) {
            executorService.submit(runnableById.apply(i));
        }
        System.out.println("Run all");
        executorService.shutdown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished all");
    }
}
