package dev.alexengrig.competition.reentrantlock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Runner {
    private final int steps;
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    Runner(int steps) {
        this.steps = steps;
    }

    private void increment() {
        for (int i = 0; i < steps; i++) {
            count++;
        }
    }

    void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting ...");
        condition.await();
        System.out.println("Woken up!");
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();
        System.out.println("Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");
        condition.signal();
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    void finish() {
        System.out.println("Count: " + count + " / " + steps);
    }
}
