package com.example.project;

//only needs a constructor
public class Trophy extends Treasure{ //child of trophy
    private String trophy;
    public Trophy(int x, int y){
        super(x,y);
        trophy = "🌟";
    }

    public String getTrophy() {
        return trophy;
    }
}
