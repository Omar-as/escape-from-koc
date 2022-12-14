package models;

import models.objects.Obj;

public class Key {
    private final Obj under;
    private boolean isFound;

    public Key(Obj under) {
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