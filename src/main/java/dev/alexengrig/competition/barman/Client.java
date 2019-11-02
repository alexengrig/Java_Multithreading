package dev.alexengrig.competition.barman;

public class Client extends Person {
    Client(String name) {
        super(name);
    }

    @Override
    public void run() {
        say("Where is my beer?");
        while (running) {
            say("I'm waiting my beer.");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void takeBearFromBarmen(Barman barman) {
        say(barman.getName() + ", thank you!");
        running = false;
    }
}
