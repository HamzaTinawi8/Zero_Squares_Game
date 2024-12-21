package com.company;


import java.util.*;


public class UCS {

    Map<String , Object > ucsAlgorithm(State state) {
        Map<String ,Object> result = new HashMap<>();
       ArrayList<State> visited = new ArrayList<>();
       ArrayList<State> initialStateList = new ArrayList<>();
       initialStateList.add(state);
       PathWithCost initialPath = new PathWithCost(initialStateList, 0);
       PriorityQueue<PathWithCost> myQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.totalCost));

        myQueue.offer(initialPath);


        while (!myQueue.isEmpty()) {
            PathWithCost  currentPathWithCost = myQueue.poll();
            ArrayList<State>  path = currentPathWithCost.path;
            State currentState = path.get(path.size() - 1);


            if (currentState.isFinalState){
                result.put("path" , currentPathWithCost);
                result.put("visited" , visited);
                return  result; }

            if (!visited.contains(currentState) ){
                visited.add(currentState);
                ArrayList<State> children = currentState.nextState();
                for (State child : children) {
                    ArrayList<State> copy_path = DeepCopy.copyPath(path);
                    copy_path.add(child);
                    int newCost = calculatePathCost(copy_path);
                    PathWithCost newPathWithCost = new PathWithCost(copy_path , newCost);
                    myQueue.offer(newPathWithCost);
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
}
