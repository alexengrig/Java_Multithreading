package dev.alexengrig.competition.barman;

public class Barman extends Person {
    private final int timeout;
    private Client client;

    Barman(String name, BartenderSpeed speed) {
        super("Barman " + name);
        this.timeout = speed.getSpeed();
    }

    @Override
    public void run() {
        say("I'm working.");
        while (needToWork()) {
            if (isFree()) {
                rest();
            } else {
                serve();
            }
        }
        say("I'm finishing work.");
    }

    boolean isFree() {
        return client == null;
    }

    void serve(Client client) {
        if (isFree()) {
            this.client = client;
        } else {
            throw new IllegalStateException("I already have a client: " + client.getName());
        }
    }

    void finish() {
        say("I will finish.");
        running = false;
    }

    private boolean needToWork() {
        return running || !isFree();
    }

    private void rest() {
        say("I'm resting.");
        while (running && isFree()) {
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void serve() {
        say("I'm serving " + client.getName() + ".");
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        say(client.getName() + ", here your beer.");
        client.takeBearFromBarmen(this);
        client = null;
    }
}
