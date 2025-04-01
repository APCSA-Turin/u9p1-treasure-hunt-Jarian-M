package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite{
    private int treasureCount; //int instance variable treasureCount
    private int numLives; //int instance variable numLives
    private boolean win; //boolean instance variable win

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y); //initializes the x and y coordinates of the Player object using the Sprite SuperClass constuctor
        treasureCount = 0; //initializes treasureCount to 0
        numLives = 2; //initializes numLives to 2
        win = false; //initializes win to false
    }


    public int getTreasureCount(){return treasureCount;} //returns treasureCount
    public int getLives(){return numLives;} //returns getLives
    public boolean getWin(){return win;} //returns getWin

    public void setTreasureCount(int newCount) { //sets the treasureCount variable to a new int value using the parameter
        treasureCount = newCount;
    }

    public void setLives(int newLives){numLives = newLives;} //sets the numLives variable to a new int value using the parameter

    //move method should override parent class, sprite
    @Override

    public String getCoords() { //returns "Player:" + coordinates in (x,y) format
        return "Player:" + super.getCoords();
    }

    public String getRowCol(int size) { //returns "Player:" + row col in [row][col] format
        return "Player:" + super.getRowCol(size);
    }

    public void move(String direction) { //move the (x,y) coordinates of the player
        if(direction.equals("a")) { //checks if which direction was inputted by the player and moves player coordinates accordingly (a decrements x, d increments x, s decrements y, w increments y)
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
        if(obj instanceof Trophy) { //checks if the object player is interacting with is a Trophy
            if(treasureCount == numTreasures) { //checks if player has collected all the treasures
                win = true; //sets win to true
            }
        } else if(obj instanceof Enemy) { //checks if the object player is interacting with is an Enemy
            if(numLives > 0) { //checks if player has at least 1 life left
                numLives--; //decrements numLives
            } 
        } else if(obj instanceof Treasure) { //checks if the object player is interacting with is a Treasure
            if(!((Treasure)obj).getCollected()) { //checks if the Treasure hasn't already been collected before
                treasureCount++; //increments treasureCount by 1
                ((Treasure)obj).setCollected(true); //sets isCollected to true, meaning that the chest can't be collected again
            }
        }
    }


    public boolean isValid(int size, String direction){ //check grid boundaries
        int row = size - 1 - super.getY(); //initializes the row that the player is in within grid
        int col = super.getX(); //initializes the column that the player is in within grid
        if(direction.equals("a")) { //checks which direction has been inputted by the player (w, a, s, or d) and checks if the move is valid or will go out of bounds
            if(col > 0) { //can't go past 0 when moving left
                return true;
            }
        } else if(direction.equals("d")) {
            if(col < size - 1) { //can't go past size - 1 (the last calumn to the right) when moving right
                return true;
            }
        } else if(direction.equals("s")) {
            if(row < size - 1) { //can't go past size - 1 (the last row from the bottom of grid) when moving down
                return true;
            }
        } else if(direction.equals("w")) {
            if(row > 0) { //can't go past 0 (the top of the grid) when moving up
                return true;
            }
        }
        return false; //returns false when the move is invalid
    }
}



