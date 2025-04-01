package com.example.project;

//Enemy only need constructor and getCoords() getRowCol()
public class Enemy extends Sprite { //child  of Sprite
    public Enemy(int x, int y) {
        super(x, y); //initializes the x and y coordinates of the Enemy object using the Sprite SuperClass constuctor
    }

    //the methods below should override the super class 

    @Override
    public String getCoords(){ //returns "Enemy:" + coordinates in (x,y) format
        return "Enemy:" + super.getCoords();
    }


    public String getRowCol(int size){ //return "Enemy:" + row col in [row][col] format
        return "Enemy:" + super.getRowCol(size);
    }
}