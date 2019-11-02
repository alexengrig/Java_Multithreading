package dev.alexengrig.competition.creation;

import java.util.Random;

public class Creation {
    static Thread[] getRandomThreads(int count) {
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
