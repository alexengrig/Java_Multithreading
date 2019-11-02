package dev.alexengrig.competition.barman;

public abstract class Person extends Thread {
    boolean running = true;

    Person(String name) {
        super(name);
    }

    public abstract void run();

    void say(String text) {
        System.out.println(getName() + ": - " + text);
    }
}
