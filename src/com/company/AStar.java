package com.company;
import java.util.*;

public class AStar {

    Map<String, Object> aStarAlgorithm(State state){
        Map<String ,Object> result = new HashMap<>();
        ArrayList<State> visited = new ArrayList<>();
        ArrayList<State> initialStateList = new ArrayList<>();
        initialStateList.add(state);
        int initialHeuristic = heuristic(state.players ,state.goals);
        GeneralPath initialPath = new GeneralPath(initialStateList, 0 , initialHeuristic , initialHeuristic);
        PriorityQueue<GeneralPath> myQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.f_cost));

        myQueue.offer(initialPath);


        while (!myQueue.isEmpty()) {
            GeneralPath  currentGeneralPath = myQueue.poll();
            ArrayList<State>  path = currentGeneralPath.path;
            State currentState = path.get(path.size() - 1);

            if (currentState.isFinalState){
                result.put("path" , currentGeneralPath);
                result.put("visited" , visited);
                return  result; }

            if (!visited.contains(currentState) ){
                visited.add(currentState);
                ArrayList<State> children = currentState.nextState();
                for (State child : children) {
                    ArrayList<State> copy_path = DeepCopy.copyPath(path);
                    copy_path.add(child);
                    int newCost = calculatePathCost(copy_path);
                    int newHeuristic = heuristic(child.players , child.goals);
                    int newF_Cost = newCost + newHeuristic ;
                    GeneralPath newGeneralPath = new GeneralPath(copy_path , newCost , newHeuristic , newF_Cost);
                    myQueue.offer(newGeneralPath);
                }
            }
        }
        return null;
    }

    private int calculatePathCost(ArrayList<State> path) {
        int total = 0;
        for (State state: path) {
            total +=  state.coastState;
        }
        return total;
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

//    Integer calculateF_Cost(PathWithCost pathWithCost){
//        int f_Cost = 0 ;
//        ArrayList<State> path = pathWithCost.path;
//        State lastState = path.get(path.size() - 1);
//
//        int heuristic = heuristic(lastState.players , lastState.goals);
//        int pathCost = calculatePathCost(path);
//        f_Cost = heuristic + pathCost;
//        return f_Cost;
//    }
}
