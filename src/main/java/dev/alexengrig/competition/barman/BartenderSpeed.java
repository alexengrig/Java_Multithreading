package dev.alexengrig.competition.barman;

public enum BartenderSpeed {
    LOW(3000),
    MEDIUM(2000),
    HIGH(1000);

    private final int speed;

    BartenderSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
