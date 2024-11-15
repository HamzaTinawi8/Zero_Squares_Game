package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class State {

    Cells[][] board;
    boolean isFinalState;
    ArrayList<PlayerCell> players;
    ArrayList<GoalCell> goals;
    ArrayList<FreeGoalCell> freeGoals;
    ArrayList<PlayerCell> availablePlayers = new ArrayList<>();
    String dir ="";

    public State(Cells[][] board) {
        this.board = board;
        this.isFinalState = false;
        stateInitialize();
    }

    public State(Cells[][] board,
                 boolean isFinalState,
                 ArrayList<PlayerCell> players,
                 ArrayList<GoalCell> goals,
                 ArrayList<FreeGoalCell> freeGoals , String dir){
        this.board = board;
        this.isFinalState = isFinalState;
        this.players = players;
        this.goals = goals;
        this.freeGoals = freeGoals;
        this.dir = dir;

    }

    public State(State state){
        this(
                DeepCopy.copyBoard(state.board),
                state.isFinalState,
                DeepCopy.copyPlayers(state.players),
                DeepCopy.copyGoals(state.goals),
                DeepCopy.copyFreeGoals(state.freeGoals),
                state.dir
        );
    }



    void stateInitialize(){
        players = new ArrayList<>();
        goals = new ArrayList<>();
        freeGoals = new ArrayList<>();
        for (Cells[] row: board) {
            for (Cells cell: row) {
                if(cell instanceof PlayerCell) players.add((PlayerCell) cell);
                else if(cell instanceof GoalCell) goals.add((GoalCell) cell);
                else if(cell instanceof FreeGoalCell) freeGoals.add((FreeGoalCell) cell);
            }
        }
    }

    void isFinalState() {
        this.isFinalState = (goals.isEmpty() && freeGoals.isEmpty()) || players.isEmpty();
    }


    ArrayList<State> nextState(){
        ArrayList<State> nextStates = new ArrayList<>();
        ArrayList<String> directions = new ArrayList<>();
        directions.add("right");
        directions.add("left");
        directions.add("down");
        directions.add("up");
        for (String direction: directions) {
            State newState = move(direction);
            if(!newState.equals(this)){
                newState.dir = direction;
                nextStates.add(newState);
//                System.out.println(newState);
            }
        }
        return nextStates;
    }

    State move(String direction){
        State newState = new State(this);
        for (PlayerCell player: newState.players) {
            boolean available = newState.check(direction, player.x, player.y);
            if(available) newState.availablePlayers.add(player);
        }
        if(newState.players.isEmpty()) return newState;

        // sort ///
       sortForDirection(newState.availablePlayers , direction);
        for (PlayerCell availablePlayer: newState.availablePlayers) {
            while(newState.check(direction, availablePlayer.x, availablePlayer.y)) {
                newState.updateBoardBeforeMoving(availablePlayer);
                if (direction.equals("right")) availablePlayer.y++;
                else if (direction.equals("left")) availablePlayer.y--;
                else if (direction.equals("up")) availablePlayer.x--;
                else if (direction.equals("down")) availablePlayer.x++;
                if(newState.updateBoardAfterMoving(availablePlayer)) break;
            }
        }
        newState.isFinalState();
        return newState;
    }

    boolean updateBoardAfterMoving(PlayerCell player) {
        if(board[player.x][player.y] instanceof EmptyCell) board[player.x][player.y] = player;
        else if(board[player.x][player.y] instanceof FreeWallCell) board[player.x][player.y] = player;
        else if(board[player.x][player.y] instanceof FreeGoalCell) board[player.x][player.y] = player;
        else if(board[player.x][player.y] instanceof GoalCell){
            if(Character.toUpperCase(board[player.x][player.y].name) == player.name){
                boolean a = goals.remove((GoalCell) board[player.x][player.y]);
                players.remove(player);
                board[player.x][player.y] = new EmptyCell();
                return true;
            }
            else board[player.x][player.y] = player;
        }else if(board[player.x][player.y] instanceof OutCell){
            players.remove(player);
            return true;
        }
        return false;
    }

    void updateBoardBeforeMoving(PlayerCell player){
        for (GoalCell goal: goals) {
            if(Character.toUpperCase(goal.name) != player.name && goal.x == player.x && goal.y == player.y){
                board[player.x][player.y] = goal;
                return;
            }
        }
        for(FreeGoalCell freeGoal: freeGoals) {
            if(freeGoal.x == player.x && freeGoal.y == player.y){
                char goalName = Character.toLowerCase(player.name);
                GoalCell newGoal = new GoalCell(goalName, player.x, player.y);
                freeGoals.remove(freeGoal);
                goals.add(newGoal);
                board[player.x][player.y] = newGoal;
                return;
            }
        }
        board[player.x][player.y] = new EmptyCell();
    }



    boolean check(String direction, int i,  int j) {
        if(direction.equals("right")) return checkRight(i,j);
        else if(direction.equals("left")) return checkLeft(i,j);
        else if(direction.equals("up")) return checkUp(i,j);
        else if(direction.equals("down")) return checkDown(i,j);
        else return checkRight(i, j);
    }

    boolean checkRight(int i, int j){
        if(board[i][j+1] instanceof WallCell || board[i][j+1] instanceof PlayerCell) return false;
        return true;
    }

    boolean checkLeft(int i, int j){
        if(board[i][j-1] instanceof WallCell || board[i][j-1] instanceof PlayerCell) return false;
        return true;
    }

    boolean checkUp(int i, int j){
        if(board[i-1][j] instanceof WallCell || board[i-1][j] instanceof PlayerCell) return false;
        return true;
    }

    boolean checkDown(int i, int j){
        if(board[i+1][j] instanceof WallCell || board[i+1][j] instanceof PlayerCell) return false;
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return isFinalState == state.isFinalState && Arrays.deepEquals(board, state.board) && players.equals(state.players) && goals.equals(state.goals) && freeGoals.equals(state.freeGoals);
    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder();
        for (Cells[] cells : board) {
            for (Cells cell : cells) {
//                if(cell instanceof OutCell) print.append(Main.ANSI_CYAN);
//                else if(cell instanceof WallCell) print.append(Main.ANSI_BLACK);
//                else if(cell instanceof FreeGoalCell) print.append(Main.ANSI_UNDERLINED_WHITE+);
//                else if(cell instanceof FreeWallCell) print.append(Main.ANSI_CYAN);
//                else if(cell instanceof EmptyCell) print.append(" ");
//                else if(Character.isUpperCase(cell.name)) print.append(Main.ANSI_RED);
//                else if(Character.isLowerCase(cell.name)) print.append(Main.ANSI_BLUE);
                print.append(cell.name).append(' ');
                print.append(Main.ANSI_RESET);
            }
            print.append('\n');
        }
        print.append("___________________________");
        return print.toString();
    }


    void sortForDirection(ArrayList<PlayerCell> availablePlayers, String direction){
        if(direction.equals("right")) sortCells(availablePlayers,false,"y");
        else if(direction.equals("left")) sortCells(availablePlayers,true,"y");
        else if(direction.equals("up")) sortCells(availablePlayers , true,"x");
        else if(direction.equals("down")) sortCells(availablePlayers, false,"x");
    }

    void sortCells(ArrayList<PlayerCell> availablePlayers, boolean ascending, String key) {
        if (key.equals("x")) {
            availablePlayers.sort( new Comparator<PlayerCell>() {
                @Override
                public int compare(PlayerCell p1, PlayerCell p2) {
                    return ascending ? Integer.compare(p1.x, p2.x) : Integer.compare(p2.x, p1.x);
                }
            });
        } else {
            availablePlayers.sort(new Comparator<PlayerCell>() {
                @Override
                public int compare(PlayerCell p1, PlayerCell p2) {
                    return ascending ? Integer.compare(p1.y, p2.y) : Integer.compare(p2.y, p1.y);
                }
            });
        }
    }


}


