package dev.alexengrig.competition;

public class Runner implements Runnable {
    private static int COUNTER = 0;

    private String name;

    Runner() {
        name = "Runner-" + COUNTER++;
    }

    @Override
    public void run() {
        System.out.println("Hello, my name is " + name);
    }
}
