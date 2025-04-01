package com.example.project;

public class Sprite {
    private int x, y; //int instance variables x and y

    public Sprite(int x, int y) { //initializes the instance variables x and y
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;} //returns x
    public int getY(){return y;} //returns y

    public void setX(int newX){ //sets x to a new int value in the parameter
        x = newX;
    }
    public void setY(int newY){ //set y to a new int value in the parameter
        y = newY;
    }

    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)" format
        return "(" + x + "," + y + ")";
    }

    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]" format
        int newY = size - 1 - y;
        return "[" + newY + "][" + x + "]";
    }
    
    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
