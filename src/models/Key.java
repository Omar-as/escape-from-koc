package models;

import models.objects.Obj;
import utils.Position;

public class Key {
    private boolean isFound;
    private final Obj under;

    public Key(Obj under){
        this.under = under;
        this.isFound = false;
    }

    public boolean isFound() {
        return isFound;
    }

    public void setFound() {
        isFound = true;
    }

    public Obj getUnder() {
        return under;
    }
}