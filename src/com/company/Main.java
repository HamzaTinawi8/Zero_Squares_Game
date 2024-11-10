package com.company;

import jdk.swing.interop.SwingInterOpUtils;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;

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
        game.playGame();

    }

}

