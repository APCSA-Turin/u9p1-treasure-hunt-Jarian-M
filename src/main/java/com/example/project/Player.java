package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite{
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y);
        treasureCount = 0;
        numLives = 2;
        win = false;
    }


    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

    public void setTreasureCount(int newCount) {
        treasureCount = newCount;
    }

    public void setLives(int newLives){numLives = newLives;}

    //move method should override parent class, sprite
    @Override

    public String getCoords() {
        return "Player:" + super.getCoords();
    }

    public String getRowCol(int size) {
        return "Player:" + super.getRowCol(size);
    }

    public void move(String direction) { //move the (x,y) coordinates of the player
        if(direction.equals("a")) {
            super.setX(super.getX() - 1);
        } else if(direction.equals("d")) {
            super.setX(super.getX() + 1);
        } else if(direction.equals("s")) {
            super.setY(super.getY() - 1);
        } else if(direction.equals("w")) {
            super.setY(super.getY() + 1);
        }
    }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
        if(obj instanceof Trophy) {
            if(treasureCount == numTreasures) {
                win = true;
            }
        } else if(obj instanceof Enemy) {
            if(numLives > 0) {
                numLives--;
            } 
        } else if(obj instanceof Treasure) {
            if(!((Treasure)obj).getCollected()) {
                treasureCount++;
                ((Treasure)obj).setCollected(true);
            }
        }
    }


    public boolean isValid(int size, String direction){ //check grid boundaries
        int row = size - 1 - super.getY();
        int col = super.getX();
        if(direction.equals("a")) {
            if(col > 0) {
                return true;
            }
        } else if(direction.equals("d")) {
            if(col < size - 1) {
                return true;
            }
        } else if(direction.equals("s")) {
            if(row < size - 1) {
                return true;
            }
        } else if(direction.equals("w")) {
            if(row > 0) {
                return true;
            }
        }
        return false;
    }
}



