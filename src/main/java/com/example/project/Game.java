package com.example.project;

import java.util.Scanner;
import java.util.Random;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 
    private int numEnemies;
    private int numLives;
    private int numTreasures;

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;
        player = new Player(0, 0);
        numEnemies = 4;
        numLives = 2;
        numTreasures = 3;
        initialize();
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);

        while(true){
            grid.display();
            player.getCoords();
            player.getRowCol(size);
            System.out.println("Treasure Collected: " + player.getTreasureCount());
            System.out.println("Lives remaining: " + player.getLives());
            System.out.println("Enter a direction (w, a, s, d) or \"q\" to exit:");
            String direction = scanner.nextLine();

            if(direction.equals("q")) {
                System.out.println("Come on! Don't rage quit! You got this!");
                break;
            }

            if(!player.isValid(size, direction)) {
                System.out.println("Move is invalid, please try a different direction");
                continue;
            }

            int originalX = player.getX();
            int originalY = player.getY();

            player.move(direction);
            //grid.placeSprite(new Dot(originalX, originalY));
            grid.placeSprite(player, direction);

            if(player.getX() == trophy.getX() && player.getY() == trophy.getY()) {
                if(player.getTreasureCount() < treasures.length) {
                    System.out.println("You must collect all the treasures before you can collect the Trophy");
                    player.setX(originalX);
                    player.setY(originalY);
                    grid.placeSprite(player);
                    grid.placeSprite(trophy);
                    continue;
                } else {
                    player.interact(size, direction, treasures.length, trophy);
                    grid.placeSprite(player);
                    grid.placeSprite(new Dot(originalX, originalY));
                    grid.winDisplay();
                    player.getCoords();
                    player.getRowCol(size);
                    System.out.println("Treasure Collected: " + player.getTreasureCount());
                    System.out.println("Lives remaining: " + player.getLives());
                    grid.win();
                    System.out.println(player.getWin());
                    break;
                }
            }

            for(Treasure treasure : treasures) {
                if(player.getX() == treasure.getX() && player.getY() == treasure.getY()) {
                    player.interact(size, direction, treasures.length, treasure);
                }
            }

            for(Enemy enemy : enemies) {
                if(player.getX() == enemy.getX() && player.getY() == enemy.getY()) {
                    player.interact(size, direction, treasures.length, enemy);
                    grid.placeSprite(new Dot(originalX, originalY));
                    if(player.getLives() <= 0) {
                        clearScreen();
                        grid.loseDisplay();
                        player.getCoords();
                        player.getRowCol(size);
                        System.out.println("Treasure Collected: " + player.getTreasureCount());
                        System.out.println("Lives remaining: " + player.getLives());
                        grid.gameover();
                        return;
                    }
                }
            }
            
            try {
                Thread.sleep(100); // Wait for 1/100 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop

     
        }
     
    }

    public void difficulty(String difficulty) {
        if(difficulty.equals("easy")) {
            this.size = this.size * 2;
            numEnemies = numEnemies / 2;
            numLives = numLives * 2;
        } else if(difficulty.equals("medium")) {
            player.setLives(numLives);
        } else if(difficulty.equals("hard")) {
            this.size = this.size / 2;
            numEnemies = numEnemies + 1;
            numLives = numLives / 2;
        }

        grid = new Grid(this.size);
        enemies = new Enemy[numEnemies];
        player.setLives(numLives);
    }

    public void initialize(){

        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid

        Scanner scan = new Scanner(System.in);
        System.out.println("Please select a difficulty: \"easy\"  \"medium\"  \"hard\"");
        String difficulty = scan.nextLine();
        difficulty(difficulty);

        Random rand = new Random();

        treasures = new Treasure[numTreasures];

        for(int i = 0; i < numTreasures; i++) {
            treasures[i] = new Treasure(rand.nextInt(size), rand.nextInt(size));
            while(grid.getGrid()[size - 1 - treasures[i].getY()][treasures[i].getX()] instanceof Player || grid.getGrid()[size - 1 - treasures[i].getY()][treasures[i].getX()] instanceof Enemy || grid.getGrid()[size - 1 - treasures[i].getY()][treasures[i].getX()] instanceof Trophy) {
                treasures[i] = new Treasure(rand.nextInt(size), rand.nextInt(size));
            }
        }

        trophy = new Trophy(0, size - 1);

        grid.placeSprite(player);
        grid.placeSprite(trophy);

        for(Treasure t : treasures) {
            grid.placeSprite(t);
        }

        for(int i = 0; i < numEnemies; i++) {
            enemies[i] = new Enemy(rand.nextInt(size), rand.nextInt(size));
            while(grid.getGrid()[size - 1 - enemies[i].getY()][enemies[i].getX()] instanceof Player || grid.getGrid()[size - 1 - enemies[i].getY()][enemies[i].getX()] instanceof Treasure) {
                enemies[i] = new Enemy(rand.nextInt(size), rand.nextInt(size));
            }
        }
        
        for(Enemy e : enemies) {
            grid.placeSprite(e);
        }
    }

    public static void main(String[] args) {
        Game game = new Game(10);
    }
}