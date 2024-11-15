package com.company;

import java.util.*;

public class DFS {
    ArrayList<State> dfsAlgorithm(State state) {
        ArrayList<State> visited = new ArrayList<>();
        ArrayList<State> path = new ArrayList<>();
        Stack<List<State>> myStack = new Stack<>();

        List<State> initialStateList = new ArrayList<>();
        initialStateList.add(state);

        myStack.push(initialStateList);

        while (!myStack.isEmpty()) {
            path = (ArrayList<State>) myStack.pop();
            State currentState = path.get(path.size() - 1);

            if (currentState.isFinalState)
                return path;

            if (!visited.contains(currentState)) {
                visited.add(currentState);
                ArrayList<State> children = currentState.nextState();
                for (State child : children) {
                    ArrayList<State> copy_path = DeepCopy.copyPath(path);
                    copy_path.add(child);
                    myStack.push(copy_path);
                }
            }
        }
        return path;
    }
}
