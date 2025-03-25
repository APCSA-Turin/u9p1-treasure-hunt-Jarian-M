package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite{
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x,y);
        treasureCount = 0;
        numLives = 2;
    }


    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

  
    //move method should override parent class, sprite
    @Override
    public void move(String direction) { //move the (x,y) coordinates of the player
        if(direction.equals("a") && super.getX() > 0) {
            super.setX(super.getX() - 1);
        } else if(direction.equals("d") && super.getX() < 9) {
            super.setX(super.getX() + 1);
        } else if(direction.equals("s") && super.getY() > 0) {
            super.setY(super.getX() - 1);
        } else if(direction.equals("w") && super.getY() < 9) {
            super.setY(super.getY() + 1);
        } else {
            System.out.println("Can't move out of bounds!");
        }
    }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
    }


    public boolean isValid(int size, String direction){ //check grid boundaries
        if(super.getX() >= 0 && super.getX() <= 9) {
            if(super.getY() >= 0 && super.getY() <= 9) {
                return true;
            }
        }
        return false;
    }


   
}



