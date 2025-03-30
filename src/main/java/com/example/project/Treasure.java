package com.example.project;

//only needs a constructor
public class Treasure extends Sprite{ //child of Sprite
    private boolean isCollected;

    public Treasure(int x, int y) {
        super(x, y);
        isCollected = false;
    }

    public boolean getCollected() {
        return isCollected;
    }

    public void setCollected(boolean setCollected) {
        isCollected = setCollected;
    }

}