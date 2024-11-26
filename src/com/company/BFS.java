package com.company;

import java.util.*;

public class BFS {

    ArrayList<State> bfsAlgorithm(State state) {
        ArrayList<State> visited = new ArrayList<>();
        ArrayList<State> path = new ArrayList<>();
        Queue<List<State>> myQueue = new ArrayDeque<>();

        List<State> initialStateList = new ArrayList<>();
        initialStateList.add(state);

        myQueue.offer(initialStateList);

        while (!myQueue.isEmpty()) {
             path = (ArrayList<State>) myQueue.poll();
            State currentState = path.get(path.size() - 1);

            if (currentState.isFinalState)
                return path;

            if (!visited.contains(currentState)) {
                visited.add(currentState);
                ArrayList<State> children = currentState.nextState();
                for (State child : children) {
                    ArrayList<State> copy_path = DeepCopy.copyPath(path);
                    copy_path.add(child);
                    myQueue.offer(copy_path);
                }
            }
        }
    return path;
    }
}


