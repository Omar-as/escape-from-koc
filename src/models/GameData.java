package models;

import models.powerUps.PowerUp;

public class GameData {
    private final int score;
    private final PowerUp[] powerUps;

    public GameData(int score, PowerUp[] powerUps) {
        this.score = score;
        this.powerUps = powerUps;
    }

    public int getScore() {
        return score;
    }

    public PowerUp[] getPowerUps() {
        return powerUps;
    }
}
