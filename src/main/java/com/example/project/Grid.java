package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size;
        this.grid = new Sprite[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                Dot dot = new Dot(j, i);
                grid[i][j] = dot;
            }
        }
    }

 
    public Sprite[][] getGrid(){return grid;}



    public void placeSprite(Sprite s){ //place sprite in new spot
        int y = s.getY();
        int x = s.getX();
        grid[size - 1 - y][x] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        int y = s.getY();
        int x = s.getX();
        s.move(direction);
        grid[size - 1 - y][x] = s;
    }


    public void display() { //print out the current grid to the screen 
        for(Sprite[] row : grid) {
            for(Sprite col : row) {
                if(col instanceof Trophy) {
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
            System.out.println();
        }
    }
    
    public void gameover(){ //use this method to display a loss
        System.out.println("You Lost! GAME OVER!");
    }

    public void win(){ //use this method to display a win 
        System.out.println("You Won! GREAT JOB!");
    }


}