package com.company;

public class GoalCell extends Cells{

    int x;
    int y;
    boolean isVisible;

    public GoalCell(char name, int x, int y){
        super(name);
        this.x = x;
        this.y = y;
        this.isVisible = true;
    }
}


