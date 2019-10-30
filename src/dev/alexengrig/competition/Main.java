package dev.alexengrig.competition;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Thread[] threads = getRandomThreads(new Random().nextInt(6) + 2);
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static Thread[] getRandomThreads(int count) {
        Thread[] threads = new Thread[count];
        for (int i = 0, length = threads.length; i < length; i++) {
            threads[i] = getRandomThread();
        }
        return threads;
    }

    private static Thread getRandomThread() {
        return new Random().nextInt() % 2 == 0
                ? new Threader()
                : new Thread(new Runner());
    }
}
