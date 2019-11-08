package dev.alexengrig.competition.interrupt;

import java.util.Random;

public class InterruptingThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting.");
        Thread thread = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 1e9; i++) {
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println("Interrupted!");
//                    break;
//                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    break;
                }
                Math.sin(random.nextDouble());
            }
        });
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
        thread.join();
        System.out.println("Finished.");
    }
}
