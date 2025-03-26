package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite{
    private int treasureCount;
    private int numLives;
    private boolean win;
    private String player;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x,y);
        treasureCount = 0;
        numLives = 2;
        win = false;
        player = "🕺";
    }


    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

    public String getPlayer() {
        return player;
    }

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
        int row = size - 1 - super.getY();
        int col = super.getX();
        int objRow = size - 1 - obj.getY();
        int objCol = obj.getX();
        if(direction.equals("a")) {
            col--;
        } else if(direction.equals("d")) {
            col++;
        } else if(direction.equals("s")) {
            row--;
        } else if(direction.equals("w")) {
            row++;
        }
        if(row == objRow && col == objCol) {
            if(obj instanceof Enemy) {
                if(numLives > 0) {
                    numLives = numLives--;
                } else {
                    grid.gameover();
                }
            } else if(obj instanceof Treasure) {
                numTreasures--;
                treasureCount = treasureCount++;
            } else {
                if(numTreasures = 0) {
                    grid.win();
                } else {
                    if(direction.equals("a")) {
                        col++;
                    } else if(direction.equals("d")) {
                        col--;
                    } else if(direction.equals("s")) {
                        row++;
                    } else if(direction.equals("w")) {
                        row--;
                    }
                }
            }
        }
    }


    public boolean isValid(int size, String direction){ //check grid boundaries
        int row = size - 1 - super.getY();
        int col = super.getX();
        if(col >= 0 && col < size) {
            if(row >= 0 && row < size) {
                return true;
            }
        }
        return false;
    }
}



