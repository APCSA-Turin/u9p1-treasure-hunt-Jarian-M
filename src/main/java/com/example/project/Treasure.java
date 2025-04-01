package com.example.project;

//only needs a constructor
public class Treasure extends Sprite{ //child of Sprite
    private boolean isCollected; //boolean instance variable isCollected

    public Treasure(int x, int y) {
        super(x, y); //initializes the x and y coordinates of the Treasure object using the Sprite SuperClass constuctor
        isCollected = false; //initializes isCollected to false;
    }

    public boolean getCollected() { //returns isCollected
        return isCollected;
    }

    public void setCollected(boolean setCollected) { //sets isCollected to a new boolean value in the parameter
        isCollected = setCollected;
    }

}