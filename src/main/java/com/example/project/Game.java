package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;
        grid = new Grid(size);
        player = new Player(0, 0);
        enemies = new Enemy[4];
        treasures = new Treasure[3];
        trophy = new Trophy(0, size - 1);
        initialize();
        grid.display();
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

        grid.display();
        while(true){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop

     
        }
            
     
    }

    public void initialize(){

        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        Player p1 = new Player(0, 0);
        Trophy trophy = new Trophy(0, 9);

        treasures[0] = new Treasure(0, 5);
        treasures[1] = new Treasure(3, 7);
        treasures[2] = new Treasure(8, 2);

        enemies[0] = new Enemy(2, 3);
        enemies[1] = new Enemy(9, 4);
        enemies[2] = new Enemy(1, 8);
        enemies[3] = new Enemy(5, 5);

        grid.placeSprite(p1);
        grid.placeSprite(trophy);

        for(Treasure t : treasures) {
            grid.placeSprite(t);
        }
        
        for(Enemy e : enemies) {
            grid.placeSprite(e);
        }
    }

    public static void main(String[] args) {
        Game game = new Game(10);
    }
}