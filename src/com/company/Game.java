package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static Cells[][] createCells(String[] level){
        Cells[][] cells = new Cells[level.length][level[0].toCharArray().length];
        for (int i = 0; i < level.length; i++) {
            char[] charRow = level[i].toCharArray();
            for(int j = 0; j< charRow.length; j++){
                if(charRow[j] == '-') cells[i][j] = new OutCell();
                else if(charRow[j] == '#') cells[i][j] = new WallCell();
                else if(charRow[j] == '@') cells[i][j] = new FreeGoalCell(i, j);
                else if(charRow[j] == '0') cells[i][j] = new FreeWallCell();
                else if(charRow[j] == '.') cells[i][j] = new EmptyCell();
                else if(Character.isUpperCase(charRow[j])) cells[i][j] = new PlayerCell(charRow[j], i, j);
                else if(Character.isLowerCase(charRow[j])) cells[i][j] = new GoalCell(charRow[j], i, j);
            }
        }
        return cells;
    }


    String[] level = {
            "-#########--",
            "-0@b....R#--",
            "-#c......###",
            "-#.........#",
            "-#..BC...#.#",
            "-###.###.#.#",
            "---#.......#",
            "---#########",
            "------------",
            "------------",
            "------------",
            "------------",
            "------------",
    };

    String[] level1 = {
            "-###-",
            "#c..#",
            "#...#",
            "#..C#",
            "#####"
    };
    void  playGame(){
        Scanner input = new Scanner(System.in);
        Cells[][] board = createCells(level);
        State state = new State(board);

        while(!state.isFinalState){
           System.out.println(state);
           System.out.println("Enter Direction:");
           String direction = input.nextLine();
           state = state.move(direction);
       }
       System.out.println(state);
       System.out.println("The game was finished");

    }

    void useBfs (){
        Cells[][] board = createCells(level);
        State state = new State(board);

        BFS bfs = new BFS();
      ArrayList<State> statesOfBfs = bfs.bfsAlgorithm(state);
      int length_path_bfs = statesOfBfs.size();
        int i = 1;
        for (State sta: statesOfBfs) {
            if( i != 1){
                if (sta.dir == "right")
            System.out.println(i +": " + sta.dir +" ➡");
                else if (sta.dir == "left")
                    System.out.println(i +": " + sta.dir + " ⬅");
                else if (sta.dir == "down")
                    System.out.println(i +": " + sta.dir+" ⬇");
                else if (sta.dir == "up")
                    System.out.println(i +": " + sta.dir +" ⬆");
            }
            else System.out.println(i +": " + "primary state ");
            i ++;
            System.out.println(sta);
        }
        System.out.println("The number of states to reach the goal :  " + length_path_bfs);
        System.out.println("The Game Is Finished By BFS ❤✔");

    }


    void useDfs (){
        Cells[][] board = createCells(level);
        State state = new State(board);

        DFS dfs = new DFS();
        ArrayList<State> statesOfDfs = dfs.dfsAlgorithm(state);
        int length_path_dfs = statesOfDfs.size();
        int i = 1;
        for (State sta: statesOfDfs) {
            if( i != 1){
                if (sta.dir == "right")
                    System.out.println(i +": " + sta.dir +" ➡");
                else if (sta.dir == "left")
                    System.out.println(i +": " + sta.dir + " ⬅");
                else if (sta.dir == "down")
                    System.out.println(i +": " + sta.dir+" ⬇");
                else if (sta.dir == "up")
                    System.out.println(i +": " + sta.dir +" ⬆");
            }
            else System.out.println(i +": " + "primary state ");
            i ++;
            System.out.println(sta);
        }
        System.out.println("The number of states to reach the goal :  " + length_path_dfs);
        System.out.println("The Game Is Finished By DFS ❤✔");

    }
}
