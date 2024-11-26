package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class UCS {

    ArrayList<State> ucsAlgorithm(State initialState) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.pathCost));
        ArrayList<State> visited = new ArrayList<>();

        queue.offer(new Node(initialState, new ArrayList<>(), 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            State currentState = current.state;

            if (currentState.isFinalState) {
                return current.path;
            }

            if (!visited.contains(currentState)) {
                visited.add(currentState);

                ArrayList<State> children = currentState.nextState();
                for (State child : children) {
                    ArrayList<State> newPath = new ArrayList<>(current.path);
                    newPath.add(child);
                    int newCost = current.pathCost + child.coastState;
                    queue.offer(new Node(child, newPath, newCost));
                }
            }
        }

        return null;
    }

    static class Node {
        State state;
        ArrayList<State> path;
        int pathCost;

        Node(State state, ArrayList<State> path, int pathCost) {
            this.state = state;
            this.path = path;
            this.pathCost = pathCost;
        }
    }

}

//
//    class Path_Cost{
//        ArrayList<State> path;
//        int cost ;
//
//        public Path_Cost( ArrayList<State> path , int cost){
//            this.path = path;
//            this.cost = cost;
//        }
//    }
//
//
//    void sortCells(ArrayList<PlayerCell> availablePlayers ) {
//            availablePlayers.sort( new Comparator<PlayerCell>() {
//                @Override
//                public int compare(PlayerCell p1, PlayerCell p2) {
//                    return Integer.compare(p1.x, p2.x);
//
//                }
//            }); }





