package com.example.project;

public class Sprite {
    private int x, y;
    private String emoji;

    public Sprite(int x, int y, String emoji) {
        this.x = x;
        this.y = y;
        this.emoji = emoji;
    }

    public int getX(){return x;}//placeholder
    public int getY(){return y;}

    public void setX(int newX){
        x = newX;
    }
    public void setY(int newY){
        y = newY;
    }

    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "(" + x + "," + y + ")";
    }

    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        int newX = size - 1 - x;
        return "[" + newX + "][" + y + "]";
    }

    public String getEmoji() {
        return emoji;
    }
    
    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
