public class Alien {
    private int xPosition;
    private int yPosition;
    private String type;

    public Alien(int xPosition, int yPosition, String type) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.type = type;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public String getType() {
        return type;
    }
}
