package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HillClimbingSimple {

    Map<String , ArrayList<State>> hillClimbingSimple(State state){
        Map<String , ArrayList<State>> result = new HashMap<>();
        ArrayList<State> visited = new ArrayList<>();
        ArrayList<State> path = new ArrayList<>();

        path.add(state);
        visited.add(state);
        if (state.isFinalState){
            result.put("path" , path);
            result.put("visited" , visited);
            return result ;
        }

        State initialState = state ;
        int bestHeuristic = heuristic(state.players , state.goals);

        int x =0 ;
        while (!initialState.isFinalState && x < 10){
            ArrayList<State> children = initialState.nextState();
            for (State child: children) {
                visited.add(child);
                int childHeuristic = heuristic(child.players ,child.goals);
                if (childHeuristic < bestHeuristic){
                    bestHeuristic = childHeuristic;
                    initialState = child;
                    path.add(child);
                    break;
                }
            x +=1;
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
