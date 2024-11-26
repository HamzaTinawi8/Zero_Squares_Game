package com.company;
import java.util.*;

public class DfsRecursive {
        public ArrayList<State> dfsAlgorithm(State state) {
            ArrayList<State> path = new ArrayList<>();
            ArrayList<State> visited = new ArrayList<>();

            path.add(state);
            return dfsRecursive(state, path, visited);
        }

        private ArrayList<State> dfsRecursive(State currentState, ArrayList<State> currentPath, ArrayList<State> visited) {
            if (currentState.isFinalState) {
                return currentPath;
            }

            visited.add(currentState);

            for (State child : currentState.nextState()) {

                if (!visited.contains(child)) {
                    ArrayList<State> newPath = DeepCopy.copyPath(currentPath);
                    newPath.add(child);
                    ArrayList<State> result = dfsRecursive(child, newPath, visited);
                    if (result != null) {
                        return result;
                    }
                }
            }
            return null;
        }

}
