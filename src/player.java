public class player {

    private int lives;
    private int score;
    private int xPosition;
    private int yPosition;

    public player(int lives, int score, int xPosition, int yPosition) {
        this.lives = lives;
        this.score = score;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }
}
