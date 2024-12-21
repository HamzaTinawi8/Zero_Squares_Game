package com.company;

import java.util.ArrayList;
import java.util.Objects;

public class PathWithCost {
    ArrayList<State> path;
    int totalCost;



    public PathWithCost(ArrayList<State> path , int totalCost){
        this.path = path;
        this.totalCost = totalCost;
    }


    //Needed for HashMap's containsKey to work correctly
//    @Override
//    public boolean equals(Object obj){
//        if(this == obj) return true;
//        if(obj == null || getClass() != obj.getClass()) return false;
//        PathWithCost that = (PathWithCost) obj;
//        return this.totalCost == that.totalCost && this.path.equals(that.path);
//    }
//
//    @Override
//    public int hashCode(){
//        return this.totalCost + this.path.hashCode();
//    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathWithCost that = (PathWithCost) o;
        return this.totalCost == that.totalCost  && this.path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return this.totalCost + this.path.hashCode();
    }
}
