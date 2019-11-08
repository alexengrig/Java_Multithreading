package dev.alexengrig.competition.callablefuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class CallableFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> task = executorService.submit((Callable<Integer>) () -> {
            Random random = new Random();
            int duration = random.nextInt(4000);
            if (duration > 1000) {
                throw new IOException("Sleeping for too long: " + duration);
            }
            System.out.println("Starting ...");
            Thread.sleep(duration);
            System.out.println("Finished.");
            return duration;
        });
        executorService.shutdown();
        try {
            System.out.println("Result: " + task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
