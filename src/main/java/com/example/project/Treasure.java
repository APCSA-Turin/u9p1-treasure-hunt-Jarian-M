package com.example.project;

//only needs a constructor
public class Treasure extends Sprite{ //child of Sprite
    private String treasure;
    public Treasure(int x, int y) {
        super(x,y);
        treasure = "🍄";
    }

    public String getTreasure() {
        return treasure;
    }
}