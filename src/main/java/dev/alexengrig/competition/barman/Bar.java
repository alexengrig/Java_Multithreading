package dev.alexengrig.competition.barman;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bar extends Thread {
    private boolean working = false;

    public static void main(String[] args) {
        Bar bar = new Bar();
        bar.start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bar.close();
    }

    @Override
    public void run() {
        System.out.println("Bar is open.");
        working = true;
        Barman[] barmen = getBarmen();
        Queue<Client> clients = getClients();
        while (working) {
            if (!clients.isEmpty()) {
                for (Barman barman : barmen) {
                    if (barman.isFree()) {
                        barman.serve(clients.remove());
                        if (clients.isEmpty()) {
                            break;
                        }
                    }
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Finish barmen.");
        for (Barman barman : barmen) {
            barman.finish();
            try {
                barman.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Bar is closed.");
    }

    private void close() {
        System.out.println("Bar will close.");
        working = false;
    }

    private Barman[] getBarmen() {
        Barman bob = new Barman("Bob", BartenderSpeed.HIGH);
        Barman tod = new Barman("Tod", BartenderSpeed.LOW);
        bob.start();
        tod.start();
        return new Barman[]{bob, tod};
    }

    private Queue<Client> getClients() {
        Client tom = new Client("Tom");
        Client bill = new Client("Bill");
        tom.start();
        bill.start();
        return new LinkedList<>(List.of(tom, bill));
    }
}
