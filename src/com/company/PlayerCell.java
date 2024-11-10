package com.company;

public class PlayerCell extends Cells{

    int x;
    int y;
    boolean isVisible;

    public PlayerCell(char name, int x, int y) {
        super(name);
        this.x = x;
        this.y = y;
        this.isVisible = true;
    }
}
