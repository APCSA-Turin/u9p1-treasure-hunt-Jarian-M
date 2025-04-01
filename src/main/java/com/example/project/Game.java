package com.example.project;

import java.util.Scanner;
import java.util.Random;

public class Game{
    private Grid grid; //instance Grid variable grid
    private Player player; //instance Player variable player
    private Enemy[] enemies; //instance Enemy[] array enemies
    private Treasure[] treasures; //instance Treasure[] array treasures
    private Trophy trophy; //instance Trophy variable trophy
    private int size; //instance int variable size
    private int numEnemies; //instance int variable numEnemies
    private int numLives; //instance int variable numLives
    private int numTreasures; //instance int variable numTreasures
    private int originalSize; //instance int variable originalSize

    public Game(int size){ //the constructor should call initialize() and play()
        originalSize = size; //initializes originalSize to be equal to the int size parameter
        player = new Player(0, 0); //initializes player to be a new Player with coordinates at (0,0)
        numEnemies = 4; //initializes numEnemies to be equal to 4
        numLives = 2; //initializes numLives to be equal to 2
        numTreasures = 3; //initializes numTreasures to be equal to 3
        initialize(); //calls initialize()
        play(); //calls play()
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
        Scanner scanner = new Scanner(System.in); //creates a Scanner object

        while(true){ //while loop that iterates until false is returned
            grid.display(); //displays grid
            System.out.println(player.getCoords()); //prints the player's coordinate
            System.out.println(player.getRowCol(size)); //uses size as a parameter to print the player's location on the grid in [row][col] format
            System.out.println("Treasure Collected: " + player.getTreasureCount()); //prints the number of Treasure collected by the player
            System.out.println("Lives remaining: " + player.getLives()); //prints the player's number of remaining lives
            System.out.println("Enter a direction (w, a, s, d) or \"q\" to exit:"); //asks the player to choose a direction to move in (w, a , s, or d)
            String direction = scanner.nextLine(); //initializes the String variable direction as the player's chosen direction

            if(direction.equals("q")) { //prints a encouraging message to the player before ending the program
                System.out.println("Come on! Don't rage quit! You got this!");
                break;
            }

            if(!player.isValid(size, direction)) { //checks if player's choice in direction for their move is valid, if invalid a message letting the player know is printed and the player is allowed to choose a different direction
                System.out.println("Move is invalid, please try a different direction");
                continue;
            }

            int originalX = player.getX(); //initializes originalX as the player's x coordinate
            int originalY = player.getY(); //initializes originalY as the player's y coordinate

            player.move(direction); //moves the player in their chosen direction
            grid.placeSprite(player, direction); //places the player on their new position in the grid after their move and replaces their old position with a dot object

            if(player.getX() == trophy.getX() && player.getY() == trophy.getY()) { //checks if the player is in the same position as the Trophy object
                if(player.getTreasureCount() < treasures.length) {  //checks if player hasn't collected all the Treasure
                    System.out.println("You must collect all the treasures before you can collect the Trophy"); //tells the player that all the Treasure must be collected first before collecting the Trophy
                    player.setX(originalX); //returns the player back to their original coordinate position (original (x,y))
                    player.setY(originalY);
                    grid.placeSprite(player); //places the player back on their original position in the grid
                    grid.placeSprite(trophy); //places the Trophy back on the grid
                    continue; //continues the loop so that the player can continue the game
                } else {
                    player.interact(size, direction, treasures.length, trophy); //if player has collected all the Treasure, the player interacts with the Trophy
                    grid.winDisplay(); //prints the win version of the grid since the player has won
                    System.out.println(player.getCoords()); //prints the player's coordinate
                    System.out.println(player.getRowCol(size)); //uses size as a parameter to print the player's location on the grid in [row][col] format
                    System.out.println("Treasure Collected: " + player.getTreasureCount()); //prints the number of Treasure collected by the player
                    System.out.println("Lives remaining: " + player.getLives()); //prints the player's number of remaining lives
                    grid.win(); //prints win message
                    System.out.println("Would you like to play again? yes or no?"); //asks if the player wants to play again
                    String again = scanner.nextLine(); //initializes String again to the player's input
                    if(again.equals("yes")) { //checks if again is equal to "yes"
                        clearScreen(); //clears screen
                        player = new Player(0, 0); //initializes the game again (restarts the game)
                        numEnemies = 4;
                        numLives = 2;
                        numTreasures = 3;
                        initialize();
                    } else if(again.equals("no")){ //checks if again is equal to "no"
                        break; //stops the program
                    }
                }
            }

            for(Treasure treasure : treasures) {
                if(player.getX() == treasure.getX() && player.getY() == treasure.getY()) { //checks if the player is in the same position as a Treasure object for each Treasure object in treasures
                    player.interact(size, direction, treasures.length, treasure); //player interacts with the Treasure object
                }
            }

            for(Enemy enemy : enemies) { 
                if(player.getX() == enemy.getX() && player.getY() == enemy.getY()) { //checks if the player is in the same position as a Enemy object for each Enemy object in enemies
                    player.interact(size, direction, treasures.length, enemy); //player interacts with the Enemy object
                    grid.placeSprite(new Dot(originalX, originalY)); //replaces the player's original position on the grid with a Dot
                    if(player.getLives() <= 0) { //checks if player has no more lives left
                        clearScreen(); //clears screen
                        grid.loseDisplay(); //prints the display specific to when a player loses
                        System.out.println(player.getCoords()); //prints the player's coordinate
                        System.out.println(player.getRowCol(size)); //uses size as a parameter to print the player's location on the grid in [row][col] format
                        System.out.println("Treasure Collected: " + player.getTreasureCount()); //prints the number of Treasure collected by the player
                        System.out.println("Lives remaining: " + player.getLives()); //prints the player's number of remaining lives
                        grid.gameover(); //prints loss message
                        System.out.println("Would you like to play again?"); //asks if the player wants to play again
                        String again = scanner.nextLine(); //initializes String again to the player's input
                        if(again.equals("yes")) { //checks if again is equal to "yes"
                            clearScreen(); //clears screen
                            player = new Player(0, 0); //initializes the game again (restarts the game)
                            numEnemies = 4;
                            numLives = 2;
                            numTreasures = 3;
                            initialize();
                        } else if(again.equals("no")){ //checks if again is equal to "no"
                            return; //stops the program
                        }
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

    public void difficulty(String difficulty) { //void method difficulty with a String parameter difficulty
        size = originalSize; //initializes size to originalSize
        if(difficulty.equals("easy")) { //checks what difficulty the player selects (easy, medium, or hard)
            size = originalSize * 2; //size is doubled
            numEnemies = numEnemies / 2; //number of enemies is halved
            numLives = numLives * 2; //number of lives is doubled
        } else if(difficulty.equals("medium")) {
            player.setLives(numLives); //nothing changes, default settings
        } else if(difficulty.equals("hard")) {
            size = originalSize / 2; //size is halved
            numEnemies = numEnemies + 1; //number of enemies increases by one
            numLives = numLives / 2; //number of lives is halved
        }

        grid = new Grid(size); //initializes grid as a new Grid with the updated size as a parameter
        enemies = new Enemy[numEnemies]; //initializes enemies as a new Enemy[] with the updated numEnemies as its length parameter
        player.setLives(numLives); //sets the player's numLives to the updated number of lives
    }

    public void initialize(){

        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid

        Scanner scan = new Scanner(System.in); //creates a Scanner object scan
        System.out.println("Please select a difficulty: \"easy\"  \"medium\"  \"hard\""); //asks the user to choose a difficulty (easy, medium, hard)
        String difficulty = scan.nextLine(); //String difficulty is set to the user's input
        difficulty(difficulty); //difficulty method is called with the variable difficulty as a parameter

        Random rand = new Random(); //creates a new Random object rand

        trophy = new Trophy(0, size - 1); //initializes trophy to a new Trophy object with coordinates (0, size - 1)

        grid.placeSprite(player); //places the player on the grid
        grid.placeSprite(trophy); //places trophy on the grid

        treasures = new Treasure[numTreasures]; //initializes treasures to a new Treasure[] with numTreasures as its length parameter

        for(int i = 0; i < numTreasures; i++) { //uses for loop to iterate from 0 to numTreasures - 1
            treasures[i] = new Treasure(rand.nextInt(size), rand.nextInt(size)); //initializes the Treasure at index i to a new Treasure with its coordinates for x and y set to random integers from 0 to size - 1
            while(grid.getGrid()[size - 1 - treasures[i].getY()][treasures[i].getX()] instanceof Player  //checks if the new Treasure object is overlapping with a different object (Player, Enemy, a different Treasure)//
            || grid.getGrid()[size - 1 - treasures[i].getY()][treasures[i].getX()] instanceof Enemy
            || grid.getGrid()[size - 1 - treasures[i].getY()][treasures[i].getX()] instanceof Treasure) {
                treasures[i] = new Treasure(rand.nextInt(size), rand.nextInt(size)); //initializes the Treasure object to new random coordinates at x and y from 0 to size - 1 until the Treasure object no longer overlaps with other objects
            }
            grid.placeSprite(treasures[i]); //places the Treasure object onto the grid
        }
        
        for(int i = 0; i < numEnemies; i++) { //uses for loop to iterate from 0 to numEnemies - 1
            enemies[i] = new Enemy(rand.nextInt(size), rand.nextInt(size));  //initializes the Enemy at index i to a new Enemy with its coordinates for x and y set to random integers from 0 to size - 1
            while(grid.getGrid()[size - 1 - enemies[i].getY()][enemies[i].getX()] instanceof Player //checks if the new Enemy object is overlapping with a different object (Player, Treasure, a different Enemy)//
            || grid.getGrid()[size - 1 - enemies[i].getY()][enemies[i].getX()] instanceof Treasure
            || grid.getGrid()[size - 1 - enemies[i].getY()][enemies[i].getX()] instanceof Enemy) {
                enemies[i] = new Enemy(rand.nextInt(size), rand.nextInt(size)); //initializes the Enemy object to new random coordinates at x and y from 0 to size - 1 until the Enemy object no longer overlaps with other objects
            }
            grid.placeSprite(enemies[i]); //places the Enemy object onto the grid
        }
    }

    public static void main(String[] args) { //main method
        Game game = new Game(10); //initializes Game game to a new Game with a size of 10 as its parameter (calls initialize() and play() in its constructor)
    }
}