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
        if(direction.equals("a")) {
            super.setX(super.getX() - 1);
        } else if(direction.equals("d")) {
            super.setX(super.getX() + 1);
        } else if(direction.equals("s")) {
            super.setY(super.getX() - 1);
        } else if(direction.equals("w")) {
            super.setY(super.getY() + 1);
        }
    }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
        if(getCoords().equals(obj.getCoords())) {
            if(obj instanceof Enemy) {
                numLives = numLives--;
            } else if(obj instanceof Treasure) {
                numTreasures--;
                treasureCount = treasureCount++;
            } else {
                if(numTreasures = 0) {
                    System.out.println("You ");
                }
            }
        }


    }


    public boolean isValid(int size, String direction){ //check grid boundaries
        move(direction);
        if(super.getX() >= 0 && super.getX() <= size) {
            if(super.getY() >= 0 && super.getY() <= size) {
                return true;
            }
        }
        return false;
    }


   
}



