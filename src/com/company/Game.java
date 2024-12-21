package com.company;

import java.io.IOException;
import java.util.*;

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
    LoggerService loggerService = new LoggerService();
    String[] level15 = {
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


    String[] level10 = {
            "--########--",
            "-##......##-",
            "##..#.b#..##",
            "#..#.r..#..#",
            "#....#..##.#",
            "#R#.##...#B#",
            "#...#......#",
            "############"
    };


    String [] level20 = {
            "---####--",
            "####Ry###",
            "#bPB...Y#",
            "#.p#rG#g#",
            "#########"
    };

    String[] level30 = {
                    "---######-######",
                    "#####..p###y##c#",
                    "#RBC..#........#",
                    "#YP.........#..#",
                    "#####@##b#######",
                    "----####0#------"
    };


    void  playGame(String[] Level){
        Scanner input = new Scanner(System.in);
        Cells[][] board = createCells(Level);
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

    Map<String , Integer> useBfs (String[] Level){
        Cells[][] board = createCells(Level);
        State state = new State(board);
        Map<String , Integer> result = new HashMap<>();

        BFS bfs = new BFS();
       Map<String,ArrayList<State>> resultOfBFS = bfs.bfsAlgorithm(state);
       ArrayList<State> visitedOfBfs = resultOfBFS.get("visited");
       int length_visited_bfs = visitedOfBfs.size();
      ArrayList<State> statesOfBfs = resultOfBFS.get("path");
      int length_path_bfs = statesOfBfs.size();

       result.put("path" , length_path_bfs);
       result.put("visited" , length_visited_bfs);

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
      ;
        System.out.println("The number of states to reach the goal :  " + length_path_bfs);
        System.out.println("The Game Is Finished By BFS ❤✔");

        return result ;

    }

    Map<String , Integer> useDfs (String[] Level){
        Cells[][] board = createCells(Level);
        State state = new State(board);
        Map<String , Integer> result = new HashMap<>();

        DFS dfs = new DFS();
        Map<String,ArrayList<State>> resultOfDFS = dfs.dfsAlgorithm(state);
        ArrayList<State> visitedOfBfs = resultOfDFS.get("visited");
        int length_visited_dfs = visitedOfBfs.size();
        ArrayList<State> statesOfDfs = resultOfDFS.get("path");
        int length_path_dfs = statesOfDfs.size();

        result.put("path" , length_path_dfs);
        result.put("visited" , length_visited_dfs);

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
        return result;

    }

    void useDfsRecursive (String[] Level){
        Cells[][] board = createCells(Level);
        State state = new State(board);

        DfsRecursive dfs = new DfsRecursive();
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
        int the_length= length_path_dfs-1;
        System.out.println("The number of states to reach the goal :  " + the_length);
        System.out.println("The Game Is Finished By DFS(Recursive) ❤✔");

    }

    Map<String , Integer> useUniformCost (String[] Level){
        Cells[][] board = createCells(Level);
        State state = new State(board);
        Map<String , Integer> result = new HashMap<>();

        UCS ucs = new UCS();
        Map<String, Object> resultOfUcs = ucs.ucsAlgorithm(state);

        PathWithCost pathWithCost = (PathWithCost) resultOfUcs.get("path");
        ArrayList<State> stateOfUcs = pathWithCost.path;
        int length_path_ucs = stateOfUcs.size();

        ArrayList<State> visitedOfUcs = (ArrayList<State>) resultOfUcs.get("visited");
        int length_visited_ucs = visitedOfUcs.size();

        int costOfPath = pathWithCost.totalCost;

        result.put("path" , length_path_ucs);
        result.put("visited" , length_visited_ucs);

        int i = 1;
        for (State sta: stateOfUcs) {
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
        System.out.println("The number of states to reach the goal :  " + length_path_ucs);
        System.out.println("the coast of this path : " + costOfPath);
        System.out.println("The Game Is Finished By UCS ❤✔");
          return result;
    }

    Map<String ,Integer> useHillClimbingSteepest (String[] Level) {
        Cells[][] board = createCells(Level);
        State state = new State(board);
        Map<String , Integer> result = new HashMap<>();

        HillClimbingSteepest hillClimbingSteepest = new HillClimbingSteepest();
        Map<String , ArrayList<State>> resultOfSteepest = hillClimbingSteepest.hillClimbingSteepest(state);

        ArrayList<State> stateOfSteepest = resultOfSteepest.get("path");
        int length_path_steepest = stateOfSteepest.size();

        ArrayList<State> visitedOfSteepest = resultOfSteepest.get("visited");
        int length_visited_steepest = visitedOfSteepest.size();

        result.put("path" , length_path_steepest);
        result.put("visited" , length_visited_steepest);

        int i = 1;
        for (State sta : stateOfSteepest) {
            if (i != 1) {
                if (sta.dir == "right")
                    System.out.println(i + ": " + sta.dir + " ➡");
                else if (sta.dir == "left")
                    System.out.println(i + ": " + sta.dir + " ⬅");
                else if (sta.dir == "down")
                    System.out.println(i + ": " + sta.dir + " ⬇");
                else if (sta.dir == "up")
                    System.out.println(i + ": " + sta.dir + " ⬆");
            } else System.out.println(i + ": " + "primary state ");
            i++;
            System.out.println(sta);
        }

        System.out.println("Path of States is :  " + length_path_steepest);
        System.out.println("Visited States is :  " + length_visited_steepest);
        System.out.println("The Game Is Finished By HillClimbingSimple ❤✔");
        return  result;
    }

    Map<String , Integer> useHillClimbingSimple (String[] Level) {
        Map<String , Integer> result = new HashMap<>();
        Cells[][] board = createCells(Level);
        State state = new State(board);

        HillClimbingSimple hillClimbingSimple = new HillClimbingSimple();
        Map<String , ArrayList<State>> resultOfSimple = hillClimbingSimple.hillClimbingSimple(state);

        ArrayList<State> stateOfSimple = resultOfSimple.get("path");
        int length_path_simple = stateOfSimple.size();

        ArrayList<State> visitedOfSimple = resultOfSimple.get("visited");
        int length_visited_simple = visitedOfSimple.size();

        result.put("path" , length_path_simple);
        result.put("visited" , length_visited_simple);

        int i = 1;
        for (State sta : stateOfSimple) {
            if (i != 1) {
                if (sta.dir == "right")
                    System.out.println(i + ": " + sta.dir + " ➡");
                else if (sta.dir == "left")
                    System.out.println(i + ": " + sta.dir + " ⬅");
                else if (sta.dir == "down")
                    System.out.println(i + ": " + sta.dir + " ⬇");
                else if (sta.dir == "up")
                    System.out.println(i + ": " + sta.dir + " ⬆");
            } else System.out.println(i + ": " + "primary state ");
            i++;
            System.out.println(sta);
        }

        System.out.println("Path of States is :  " + length_path_simple);
        System.out.println("Visited States is :  " + length_visited_simple);
        System.out.println("The Game Is Finished By HillClimbingSimple ❤✔");
        return result;
    }

    Map<String, Integer> useAStar(String[] Level){
            Cells[][] board = createCells(Level);
            State state = new State(board);
            Map<String , Integer> result = new HashMap<>();

            AStar aStar = new AStar();
            Map<String, Object> resultOfAStar = aStar.aStarAlgorithm(state);

            GeneralPath generalPath = (GeneralPath) resultOfAStar.get("path");
            ArrayList<State> stateOfAStar = generalPath.path;
            int length_path_AStar = stateOfAStar.size();

            ArrayList<State> visitedOfAStar = (ArrayList<State>) resultOfAStar.get("visited");
            int length_visited_AStar = visitedOfAStar.size();

            int costOfPath = generalPath.totalCost;
            int f_Cost = generalPath.f_cost;

            result.put("path" , length_path_AStar);
            result.put("visited" , length_visited_AStar);

            int i = 1;
            for (State sta: stateOfAStar) {
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
            System.out.println("The number of states to reach the goal :  " + length_path_AStar);
            System.out.println("The number of visitedStates to reach the goal : " + length_visited_AStar);
            System.out.println("the coast of this path : " + costOfPath);
            System.out.println("The Game Is Finished By AStar ❤✔");
            return result;
    }

    void runAllAlgorithm() throws IOException {
        ArrayList<String[]> levels = new ArrayList<>();
        levels.add(level10);
        levels.add(level20);
        levels.add(level30);

            int numLevel = 10;
            for (String[] level: levels) {

                // .......BFS..........
//                long beforeUsedMem1=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//                long startTime1 = System.nanoTime();
//                Map<String , Integer> resultBfs = useBfs(level);
//                long endTime1 = System.nanoTime();
//                long afterUsedMem1=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//                long executionMem1=afterUsedMem1-beforeUsedMem1;
//                long executionTime1 = (endTime1 - startTime1) / 1000000;
//                loggerService.createLogger("BFS Algorithm" ,numLevel , resultBfs, executionTime1 ,executionMem1 );

                //.......DFS........
//                long beforeUsedMem2=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//                long startTime2 = System.nanoTime();
//                Map<String , Integer> resultDfs = useDfs(level);
//                long endTime2 = System.nanoTime();
//                long afterUsedMem2=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//                long executionMem2=afterUsedMem2-beforeUsedMem2;
//                long executionTime2 = (endTime2 - startTime2) / 1000000;
//                loggerService.createLogger("DFS Algorithm" ,numLevel , resultDfs, executionTime2 ,executionMem2 );

                //......RDFS........


                //......UCS.........
//                long beforeUsedMem5=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//                long startTime5 = System.nanoTime();
//                Map<String , Integer> resultUcs = useUniformCost(level);
//                long endTime5 = System.nanoTime();
//                long afterUsedMem5=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//                long executionMem5=afterUsedMem5-beforeUsedMem5;
//                long executionTime5 = (endTime5 - startTime5) / 1000000;
//                loggerService.createLogger("UCS Algorithm" ,numLevel , resultUcs, executionTime5 ,executionMem5 );

                //......HillClimbingSteepest......
//                long beforeUsedMem3=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//                long startTime3 = System.nanoTime();
//                Map<String , Integer> resultSteepest = useHillClimbingSteepest(level);
//                long endTime3 = System.nanoTime();
//                long afterUsedMem3=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//                long executionMem3=afterUsedMem3-beforeUsedMem3;
//                long executionTime3 = (endTime3 - startTime3) / 1000000;
//                loggerService.createLogger("HillClimbingSteepest Algorithm" ,numLevel , resultSteepest, executionTime3 ,executionMem3 );


                //......HillClimbingSimple........
//                long beforeUsedMem4=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//                long startTime4 = System.nanoTime();
//                Map<String , Integer> resultSimple = useHillClimbingSimple(level);
//                long endTime4 = System.nanoTime();
//                long afterUsedMem4=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//                long executionMem4=afterUsedMem4-beforeUsedMem4;
//                long executionTime4 = (endTime4 - startTime4) / 1000000;
//                loggerService.createLogger("HillClimbingSimple Algorithm" ,numLevel , resultSimple, executionTime4 ,executionMem4 );

                //......AStar..........
                long beforeUsedMem6=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
                long startTime6 = System.nanoTime();
                Map<String , Integer> resultAStar = useAStar(level);
                long endTime6 = System.nanoTime();
                long afterUsedMem6=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
                long executionMem6=afterUsedMem6-beforeUsedMem6;
                long executionTime6 = (endTime6 - startTime6) / 1000000;
                loggerService.createLogger("AStar Algorithm" ,numLevel , resultAStar, executionTime6 ,executionMem6 );



                numLevel += 10 ;
            }

        }


    }



