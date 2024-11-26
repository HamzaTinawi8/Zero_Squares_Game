package com.company;

import java.util.*;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\033[0;30m";
    public static final String ANSI_WHITE = "\033[0;37m";
    public static final String ANSI_CYAN = "\033[0;36m";
    public static final String ANSI_RED = "\033[0;31m";
    public static final String ANSI_BLUE = "\033[0;34m";
    public static final String ANSI_UNDERLINED_WHITE = "\033[4;35m";

    public static void main(String[] args) {



        Game game = new Game();

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome In Zero Squares Games ☺");
        System.out.println("Enter a number of process that are you want : \n 1 : PlayingGame \n 2 : Use BFS Algorithm \n 3 : Use DFS Algorithm \n 4 : Use DFS(Recursive) Algorithm \n 5 : Use UCS Algorithm");
        System.out.println("number is : ");
        int inp = input.nextInt();
        System.out.println("___________________________");
        if (inp == 1)
            game.playGame();
        else if (inp == 2)
            game.useBfs();
        else if (inp == 3)
            game.useDfs();
        else if (inp == 4)
            game.useDfsRecursive();
        else if (inp == 5)
           game.useUniformCost();

    }
}


