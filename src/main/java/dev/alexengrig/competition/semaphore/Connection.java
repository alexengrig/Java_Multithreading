package dev.alexengrig.competition.semaphore;

import java.util.concurrent.Semaphore;

class Connection {
    private static Connection INSTANCE = new Connection();

    private int count = 0;
    private Semaphore semaphore = new Semaphore(10, true);

    private Connection() {
    }

    static Connection getInstance() {
        return INSTANCE;
    }

    void connect() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            doConnect();
        } finally {
            semaphore.release();
        }
    }

    private void doConnect() {
        synchronized (this) {
            count++;
            System.out.println("Current connection count: " + count);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            count--;
        }
    }
}
