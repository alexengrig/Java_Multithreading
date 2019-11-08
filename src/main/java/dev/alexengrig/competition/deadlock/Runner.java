package dev.alexengrig.competition.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    void acquireLock(Lock left, Lock right) throws InterruptedException {
        while (true) {
            boolean gotLeftLock = false;
            boolean gotRightLock = false;
            try {
                gotLeftLock = left.tryLock();
                gotRightLock = right.tryLock();
            } finally {
                if (gotLeftLock && gotRightLock) {
                    return;
                }
                if (gotLeftLock) {
                    left.unlock();
                }
                if (gotRightLock) {
                    right.unlock();
                }
            }
            Thread.sleep(1);
        }
    }
    void firstThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            acquireLock(lock1, lock2);
            try {
                Account.transfer(account1, account2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    void secondThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            acquireLock(lock2, lock1);
            try {
                Account.transfer(account2, account1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    void finish() {
        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());
        System.out.println("Total balance: " + (account1.getBalance() + account2.getBalance()));
    }
}
