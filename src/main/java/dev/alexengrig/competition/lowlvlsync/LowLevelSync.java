package dev.alexengrig.competition.lowlvlsync;

public class LowLevelSync {
    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();
        Thread producer = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
