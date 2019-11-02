package dev.alexengrig.competition.creation;

import org.junit.Test;

import java.util.Random;

public class CreationTest {
    @Test
    public void main() {
        Thread[] threads = Creation.getRandomThreads(new Random().nextInt(6) + 2);
        for (Thread thread : threads) {
            thread.start();
        }
    }
}