public class PowerUp {

    private int timer;
    private int xPosition;
    private int yPosition;
    private String type;

    public PowerUp(int timer, int xPosition, int yPosition, String type) {
        this.timer = timer;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.type = type;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
