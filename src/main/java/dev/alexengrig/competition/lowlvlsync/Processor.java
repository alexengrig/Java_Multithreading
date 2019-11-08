package dev.alexengrig.competition.lowlvlsync;

import java.util.LinkedList;
import java.util.Random;

class Processor {
    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    void consume() throws InterruptedException {
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }
                System.out.print("List size: " + list.size());
                Integer value = list.removeFirst();
                System.out.println("; value: " + value);
                lock.notify();
            }
            Thread.sleep(random.nextInt(1000));
        }
    }
}
