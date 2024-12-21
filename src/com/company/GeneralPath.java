package com.company;

import java.util.ArrayList;

public class GeneralPath {
    ArrayList<State> path;
    int totalCost;
    int heuristicForState;
    int f_cost;


    public GeneralPath(ArrayList<State> path , int totalCost , int heuristicForState , int f_cost){
        this.path = path;
        this.totalCost = totalCost;
        this.heuristicForState = heuristicForState;
        this.f_cost = f_cost;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralPath that = (GeneralPath) o;
        return this.totalCost == that.totalCost && this.heuristicForState == that.heuristicForState && this.f_cost == that.f_cost && this.path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return this.totalCost + this.path.hashCode() + this.heuristicForState + this.f_cost;
    }
}
