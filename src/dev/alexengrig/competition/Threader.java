package dev.alexengrig.competition;

public class Threader extends Thread {
    private static int COUNTER = 0;

    Threader() {
        super("Threader-" + COUNTER++);
    }

    @Override
    public void run() {
        System.out.println("Hello, my name is " + this.getName());
    }
}
