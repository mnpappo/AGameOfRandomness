package com.java_final_project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by mnpappo on 12/25/16.
 */

public class PlayerMove {
    int EMPTY = 0;

    int prev1Row=EMPTY;
    int prev1Col=EMPTY;

    int prev2Row=EMPTY;
    int prev2Col=EMPTY;

    int prev3Row=EMPTY;
    int prev3Col=EMPTY;

    public static Scanner input = new Scanner(System.in); // the input Scanner
    public static Random random = new Random();

    public ArrayList tracedList_Player = new ArrayList();
    public ArrayList tracedList_Row = new ArrayList();
    public ArrayList tracedList_Col = new ArrayList();

    int listIndex = 0;

    /** Return true if it is a draw */
    public boolean isDraw() {
        return false;
    }

    public void Move(PlayerMove playerMove, Board board, int row, int col) {
        board.currentCol = row;
        board.currentRow = col;

        // update prevprev cell, to keep 3 players footstep only
        board.grid[playerMove.prev3Row][playerMove.prev3Col] = EMPTY;

        // current player moves to current cell
        board.grid[board.currentRow][board.currentCol] = board.currentPlayer;

        // create trace list
        tracedList_Player.add(listIndex, board.currentPlayer);
        tracedList_Row.add(listIndex, board.currentRow);
        tracedList_Col.add(listIndex, board.currentCol);
        listIndex += 1;

        playerMove.prev3Row = playerMove.prev2Row;
        playerMove.prev3Col = playerMove.prev2Col;

        playerMove.prev2Row = playerMove.prev1Row;
        playerMove.prev2Col = playerMove.prev1Col;

        playerMove.prev1Row = board.currentRow;
        playerMove.prev1Col = board.currentCol;

    }


    /** Player with the "theSeed" makes one move, with input validation.
     Update global variables "currentRow" and "currentCol". */
    public void Move(Board board, Players player, PlayerMove playerMove) {
        boolean validInput = false;  // for input validation
        do {
            if (board.currentPlayer == player.TRIANGLE) {
                System.out.print("Player '(T)RIANGLE', enter your move (row[0-9] column[0-9]): ");
            } else if (board.currentPlayer == player.BOX) {
                System.out.print("Player '(B)OX', enter your move (row[0-9] column[0-9]): ");
            } else if (board.currentPlayer == player.CIRCLE){
                System.out.print("Player '(C)IRCLE', enter your move (row[0-9] column[0-9]): ");
            }

            // manual input
//             int row = input.nextInt();
//             int col = input.nextInt();

            // random input from 0 to 9 range
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            System.out.println("(" + row + "," + col + ")");



            // check if the move is not outside boundary and the cell is empty
            if (row >= 0 && row < board.ROWS && col >= 0 && col < board.COLS && board.grid[row][col] == EMPTY) {
                board.currentRow = row;
                board.currentCol = col;

                if (checkMoveAbility(board, player, playerMove)) {

                    // update prevprev cell, to keep 3 players footstep only
                    board.grid[playerMove.prev3Row][playerMove.prev3Col] = EMPTY;

                    // current player moves to current cell
                    board.grid[board.currentRow][board.currentCol] = board.currentPlayer;

                    // create trace list
                    tracedList_Player.add(listIndex, board.currentPlayer);
                    tracedList_Row.add(listIndex, board.currentRow);
                    tracedList_Col.add(listIndex, board.currentCol);
                    listIndex += 1;

                    playerMove.prev3Row = playerMove.prev2Row;
                    playerMove.prev3Col = playerMove.prev2Col;

                    playerMove.prev2Row = playerMove.prev1Row;
                    playerMove.prev2Col = playerMove.prev1Col;

                    playerMove.prev1Row = board.currentRow;
                    playerMove.prev1Col = board.currentCol;

                    validInput = true;  // input okay, exit loop
                }

            } else { // cell is not empty
                System.out.println("This move at (" + (row) + "," + (col) + ") is not valid. Try again...");
            }
        } while (!validInput);  // repeat until input is valid
    }


    boolean checkMoveAbility(Board board, Players player, PlayerMove playerMove) {

        if (board.currentPlayer == player.TRIANGLE) {

//            System.out.println(playerMove.prev3Row + " --- i --- " + playerMove.prev3Col);
//
//            for (int i = playerMove.prev3Row+1; i <= 9; i++) { //for every next row
//                System.out.println("testing for i =" + i);
//                if (i == board.currentRow) {
//                    System.out.println("got next row " + i);
//
//                    for (int j = playerMove.prev3Col+1; j <= board.currentCol; j++) {
//                        System.out.println("testing every " + j + "for " + i);
//                        if (j == i-1) {
//                            System.out.println("got next col "+ j);
//                            return true;
//                        }
//                    }
//                } else return false;
//
//            }
//
//
//            System.out.println("number error!");
            return true;

        } else if (board.currentPlayer == player.BOX) {
            // only left, right, up, down
            return true;
        } else if (board.currentPlayer == player.CIRCLE) {
            // can move any way
            return true;
        } else {
            return false;
        }
    }
}
