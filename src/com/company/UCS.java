package com.company;

import java.util.*;

public class UCS {

    ArrayList<State> ucsAlgorithm(State state) {

        ArrayList<State> visited = new ArrayList<>();
        ArrayList<State> path = new ArrayList<>();
        Queue<List<State>> myQueue = new PriorityQueue<>();

        List<State> initialStateList = new ArrayList<>();
        initialStateList.add(state);

        myQueue.offer(initialStateList);

        while (!myQueue.isEmpty()) {
            path = (ArrayList<State>) myQueue.poll();
            State currentState = path.get(path.size() - 1);

            if (currentState.isFinalState)
                return path;

            if (!visited.contains(currentState) && state.coastState!= currentState.coastState) {
                visited.add(currentState);
                ArrayList<State> children = currentState.nextState();
                for (State child : children) {
                    int s1 = child.coastState;
                    int s2 = currentState.coastState;
                    int sum = s1 + s2 ;
                    child.coastState = sum ;
                    ArrayList<State> copy_path = DeepCopy.copyPath(path);
                    copy_path.add(child);
                    myQueue.offer(copy_path);
                }
                for ( State child : children) {
                  //func for select child
                }
            }
        }
        return path;
    }


}



