package com.company;

public class FreeGoalCell extends Cells{

    int x;
    int y;
    boolean hasChanged;

    public FreeGoalCell(int x, int y) {
        super('@');
        this.x = x;
        this.y = y;
        this.hasChanged = false;
    }

}
