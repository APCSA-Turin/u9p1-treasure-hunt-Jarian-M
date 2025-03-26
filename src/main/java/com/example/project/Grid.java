package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        Sprite[][] grid = new Sprite[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                Dot dot = new Dot(i, j);
                grid[j][i] = dot;
            }
        }
    }

 
    public Sprite[][] getGrid(){return grid;}



    public void placeSprite(Sprite s){ //place sprite in new spot
        
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        s.move(direction);
        grid[9 - s.getY()][9 - s.getX()] = s;
    }


    public void display() { //print out the current grid to the screen 
        for(Sprite[] row : grid) {
            for(Sprite col : row) {
                System.out.println(col);
            }
        }
    }
    
    public void gameover(){ //use this method to display a loss
        System.out.println("You Lost! GAME OVER!");
    }

    public void win(){ //use this method to display a win 
        System.out.println("You Won! GREAT JOB!");
    }


}