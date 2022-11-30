public class gameData {
    private int Score;
    private Object powerUps;

    public gameData() {
        Score = 0;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public Object getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(Object powerUps) {
        this.powerUps = powerUps;
    }
}
