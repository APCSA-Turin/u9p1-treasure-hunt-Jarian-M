package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid; //instance Sprite[][] 2d array grid
    private int size; //int instance variable size

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size; //initializes size
        this.grid = new Sprite[size][size]; //initializes grid 2d array with size as the length of the 2d array

        for(int i = 0; i < size; i++) { //uses for loop to iterate from 0 to size - 1
            for(int j = 0; j < size; j++) { //uses for loop to iterate from 0 to size - 1
                Dot dot = new Dot(j, i); //initializes each object in the grid 2d array to a dot with (x,y) coordinates of (j, i) (column major)
                grid[i][j] = dot;
            }
        }
    }

 
    public Sprite[][] getGrid(){return grid;} //returns grid



    public void placeSprite(Sprite s){ //place sprite in new spot
        int y = s.getY(); //initializes y to the Sprite parameter's y coordinate
        int x = s.getX(); //initializes y to the Sprite parameter's x coordinate
        grid[size - 1 - y][x] = s; //sets the Sprite at grid[size - 1 -y][x] (size - 1 - y to account for the fact that the top row is 0) to the Sprite parameter (in turn moving the sprite to a new spot)
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        placeSprite(s); //places the sprite in a new spot
        if(direction.equals("a")) { //checks for which direction was inputted by the user in order to replace the user's old spot with a dot object
            placeSprite(new Dot(s.getX() + 1, s.getY())); //when direction is a (user moved left), a dot is placed in the user's old position (the x coordinate of the dot is the user's current x coordinate + 1 to account for the player moving left)
        } else if(direction.equals("d")) {
            placeSprite(new Dot(s.getX() - 1, s.getY())); //when direction is d (user moved right), a dot is placed in the user's old position (the x coordinate of the dot is the user's current x coordinate - 1 to account for the player moving right)
        } else if(direction.equals("s")) {
            placeSprite(new Dot(s.getX(), s.getY() + 1)); //when direction is s (user moved down), a dot is placed in the user's old position (the y coordinate of the dot is the user's current y coordinate - 1 to account for the player moving down)
        } else if(direction.equals("w")) {
            placeSprite(new Dot(s.getX(), s.getY() - 1)); //when direction is w (user moved up), a dot is placed in the user's old position (the y coordinate of the dot is the user's current y coordinate + 1 to account for the player moving up)
        }
    }


    public void display() { //print out the current grid to the screen 
        for(Sprite[] row : grid) { //uses enhanced for loop to iterate through each row in grid
            for(Sprite col : row) { //uses enhanced for loop to iterate through each column in each row
                if(col instanceof Trophy) { //checks what the object in the column is (either Trophy, Player, Enemy, Treasure, or Dot) and prints out their respective emoji
                    System.out.print("🌟");
                } else if(col instanceof Player) {
                    System.out.print("🕺");
                } else if(col instanceof Enemy) {
                    System.out.print("🐢");
                } else if(col instanceof Treasure) {
                    System.out.print("🍄");
                } else if(col instanceof Dot) {
                    System.out.print("🟥");
                }
            }
            System.out.println(); //skips to the next line to start a new row
        }
        
    }

    public void winDisplay() { //prints a grid specific to when a player wins
        for(Sprite[] row : grid) { //uses enhanced for loops to iterate through each row and column in grid
            for(Sprite col : row) {
                if(col instanceof Player) { //checks if the object at the column is a Player
                    System.out.print("🕺"); //prints the player emoji
                } else {
                    System.out.print("👑"); //prints a crown emoji for every other object that isn't a Player
                }
            }
            System.out.println(); //skips to the next line to start a new row
        }
    }

    public void loseDisplay() { //prints a grid specific to when a player loses
        for(Sprite[] row : grid) { //uses enhanced for loops to iterate through each row and column in grid
            for(Sprite col : row) {
                if(col instanceof Player) { //checks if the object at the column is a Player
                    System.out.print("🕺"); //prints the player emoji
                } else {
                    System.out.print("👾"); //prints a monster emoji for every other object that isn't a Player
                }
            }
            System.out.println(); //skips to the next line to start a new row
        }
    }
    
    public void gameover(){ //use this method to display a loss
        System.out.println("You Lost! GAME OVER!"); //prints a message when the Player loses
    }

    public void win(){ //use this method to display a win 
        System.out.println("You Won! GREAT JOB!"); //prints a message when the Player wins
    }


}