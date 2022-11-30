public class Key {
    private int xPos;
    private int yPos;
    private boolean isFound;

    Key(int xpos, int ypos){
        xPos = xpos;
        yPos = ypos;
        isFound = false;
    }

    public void setisFound(boolean found){
         isFound = found;
    }

    public void setxPos(int xpos) {
        xPos = xpos;
    }

    public void setyPos(int ypos) {
        yPos = ypos;
    }

    public boolean getisFound(){
         return isFound;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}
