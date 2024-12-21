package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HillClimbingSteepest {


   Map<String , ArrayList<State>> hillClimbingSteepest(State state ){
        Map<String , ArrayList<State>> result = new HashMap<>();
        ArrayList<ArrayList<State>> finish = new ArrayList<>();
         ArrayList<State> visited = new ArrayList<>();
//       HashSet<State> visited = new HashSet<>();
         ArrayList<State> path = new ArrayList<>();

         path.add(state);
         visited.add(state);
        if (state.isFinalState ){
            result.put("path" , path);
            result.put("visited" , visited);
            return result ;
        }

        int x =0 ;
        State initialState = state ;
        int bestHeuristic = heuristic(state.players , state.goals);

        while (!initialState.isFinalState && x < 10 ){
          ArrayList<State> children = initialState.nextState();
            for (State child: children) {
                visited.add(child);
               int childHeuristic = heuristic(child.players ,child.goals);
               if (childHeuristic < bestHeuristic){
                   bestHeuristic = childHeuristic;
                   initialState = child;
                   path.add(child);
               }
                   x += 1;
            }
        }

       result.put("path" , path);
       result.put("visited" , visited);
       return result ;
    }


    Integer heuristic (ArrayList<PlayerCell> players , ArrayList<GoalCell> goals){
        int total = 0;
        for (PlayerCell player: players) {
            for (GoalCell goal: goals) {
                if (Character.toUpperCase(goal.name) == player.name){
                    int x = player.x - goal.x ;
                    int y = player.y - goal.y ;
                    int result = Math.abs(x) + Math.abs(y);
                    total += result;
                }
            }
        }
        return  total;
    }
}



