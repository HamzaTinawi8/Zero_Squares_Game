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
//
//
//    Map<String, Object> solution = new HashMap<>();
//    Set<State> visited = new HashSet<>();
//    State startState = states.getFirst();
//        return RDFSHelper(startState, solution, visited);
//                }
//
//public Map<String, Object> RDFSHelper(State node, Map<String, Object> solution, Set<State> visited) {
//        if (visited.contains(node)) return null;
//        if (!node.isSolvable()) return null;
//        visited.add(node);
//        if (node.goalCheck()) {
//        ArrayList<State> path = new ArrayList<>();
//        path.add(node);
//        while (node.parent != null) {
//        node = node.parent;
//        path.add(node);
//        }
//        solution.put("visitedSize", visited.size());
//        solution.put("path", path);
//        return solution;
//        }
//        for (State nextState : node.nextStates()) {
//        if (!visited.contains(nextState)) {
//        Map<String, Object> result = RDFSHelper(nextState, solution, visited);
//        if (result != null) {
//        return result;
//        }
//        }
//        }
//        return null;