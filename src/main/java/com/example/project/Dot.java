package com.example.project;


//Dot only needs a constructor
public class Dot extends Sprite{
    private String dot;
    public Dot(int x, int y) {
        super(x, y);
        dot = "🟥";
    }

    public String getDot() {
        return dot;
    }
}
