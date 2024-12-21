package com.company;

import java.io.IOException;
import java.util.*;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\033[0;30m";
    public static final String ANSI_WHITE = "\033[0;37m";
    public static final String ANSI_CYAN = "\033[0;36m";
    public static final String ANSI_RED = "\033[0;31m";
    public static final String ANSI_BLUE = "\033[0;34m";
    public static final String ANSI_UNDERLINED_WHITE = "\033[4;35m";

    public static void main(String[] args) throws IOException {


        Game game = new Game();

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome In Zero Squares Games ☺");
        System.out.println("Enter a number of process that are you want : \n 1 : PlayingGame \n 2 : Use BFS Algorithm \n 3 : Use DFS Algorithm \n 4 : Use DFS(Recursive) Algorithm \n 5 : Use UCS Algorithm \n " +
                "6 : Use HillClimbingSteepest Algorithm \n 7 : Use HillClimbingSimple Algorithm \n 8 : Use AStar Algorithm \n 9 : Run AllAlgorithm");
        System.out.println("number is : ");
        int inp = input.nextInt();
        System.out.println("___________________________");
        if (inp == 1)
            game.playGame(new String[] {
                    "-####-",
                    "#c...#",
                    "#....#",
                    "#...C#",
                    "######"
            });
        else if (inp == 2)
            game.useBfs(new String[] {"d"});
        else if (inp == 3)
            game.useDfs(new String[] {"d"});
        else if (inp == 4)
            game.useDfsRecursive(new String[] {"d"});
        else if (inp == 5)
           game.useUniformCost(new String[] {
                   "-####-",
                   "-###.#",
                   "#c...#",
                   "#...H#",
                   "#h..C#",
                   "######"
           });
        else if (inp == 6)
            game.useHillClimbingSteepest(new String[] {
                    "--########--",
                    "-##......##-",
                    "##..#.b#..##",
                    "#..#.r..#..#",
                    "#....#..##B#",
                    "#R#.##...#.#",
                    "#...#......#",
                    "############"
            });
        else if (inp == 7)
            game.useHillClimbingSimple(new String[] {"d"});
        else if(inp == 8)
            game.useAStar(new String[] {
                    "-####-",
                    "-###.#",
                    "#c...#",
                    "#...H#",
                    "#h..C#",
                    "######"
            });
        else if(inp == 9)
            game.runAllAlgorithm();

    }





}


