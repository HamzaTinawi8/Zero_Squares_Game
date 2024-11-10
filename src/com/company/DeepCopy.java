package com.company;

import java.util.ArrayList;

public class DeepCopy {


   public static Cells[][] copyBoard(Cells[][] board) {
        Cells[][] copyBoard = new Cells[board.length][board[0].length];
        for (int i = 0; i <board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] instanceof OutCell) copyBoard[i][j] = new OutCell();
                else if(board[i][j] instanceof WallCell) copyBoard[i][j] = new WallCell();
                else if(board[i][j] instanceof FreeGoalCell) copyBoard[i][j] = new FreeGoalCell(i, j);
                else if(board[i][j] instanceof FreeWallCell) copyBoard[i][j] = new FreeWallCell();
                else if(board[i][j] instanceof EmptyCell) copyBoard[i][j] = new EmptyCell();
                else if(Character.isUpperCase(board[i][j].name)) copyBoard[i][j] = new PlayerCell(board[i][j].name, i, j);
                else if(Character.isLowerCase(board[i][j].name)) copyBoard[i][j] = new GoalCell(board[i][j].name, i, j);
            }
        }
        return copyBoard;
    }

    public  static ArrayList<PlayerCell> copyPlayers (ArrayList<PlayerCell> players) {
        ArrayList<PlayerCell> copyPlayers = new ArrayList<>();

        for (PlayerCell player: players) {
            copyPlayers.add(new PlayerCell(player.name , player.x, player.y));
        }
        return copyPlayers;
    }

    public  static ArrayList<GoalCell> copyGoals (ArrayList<GoalCell> goals) {
        ArrayList<GoalCell> copyGoals = new ArrayList<>();

        for (GoalCell goalCell: goals) {
            copyGoals.add(new GoalCell(goalCell.name,goalCell.x, goalCell.y));
        }
        return copyGoals;
    }


    public  static ArrayList<FreeGoalCell> copyFreeGoals (ArrayList<FreeGoalCell> freeGoals) {
        ArrayList<FreeGoalCell> copyFreeGoals = new ArrayList<>();

        for (FreeGoalCell freeGoal : freeGoals) {
            copyFreeGoals.add(new FreeGoalCell(freeGoal.x, freeGoal.y));
        }
        return copyFreeGoals;
    }
}
