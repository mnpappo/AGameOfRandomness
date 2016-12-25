package com.java_final_project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by mnpappo on 12/25/16.
 */

public class PlayerMove {
    int EMPTY = 0;

    private int prev1Row=EMPTY;
    private int prev1Col=EMPTY;

    private int prev2Row=EMPTY;
    private int prev2Col=EMPTY;

    private int prev3Row=EMPTY;
    private int prev3Col=EMPTY;

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


    /** Player with the "theSeed" makes one move, with input validation.
     Update global variables "currentRow" and "currentCol". */
    public void Move(Board board, Players player, int currentPlayer) {
        boolean validInput = false;  // for input validation
        do {
            if (currentPlayer == player.TRIANGLE) {
                System.out.print("Player '(T)RIANGLE', enter your move (row[0-9] column[0-9]): ");
            } else if (currentPlayer == player.BOX) {
                System.out.print("Player '(B)OX', enter your move (row[0-9] column[0-9]): ");
            } else if (currentPlayer == player.CIRCLE){
                System.out.print("Player '(C)IRCLE', enter your move (row[0-9] column[0-9]): ");
            }

            // manual input
            // int row = input.nextInt();
            // int col = input.nextInt();

            // random input from 0 to 9 range
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            System.out.println("(" + row + "," + col + ")");



            // check if the move is valid and the cell is empty
            if (row >= 0 && row < board.ROWS && col >= 0 && col < board.COLS && board.grid[row][col] == EMPTY) {
                board.currentRow = row;
                board.currentCol = col;

                // update prevprev cell, to keep 3 players footstep only
                board.grid[prev3Row][prev3Col] = EMPTY;

                // current player moves to current cell
                board.grid[board.currentRow][board.currentCol] = currentPlayer;

                // create trace list
                tracedList_Player.add(listIndex, currentPlayer);
                tracedList_Row.add(listIndex, board.currentRow);
                tracedList_Col.add(listIndex, board.currentCol);
                listIndex += 1;

                prev3Row = prev2Row;
                prev3Col = prev2Col;

                prev2Row = prev1Row;
                prev2Col = prev1Col;

                prev1Row = board.currentRow;
                prev1Col = board.currentCol;

                validInput = true;  // input okay, exit loop

            } else { // cell is not empty
                System.out.println("This move at (" + (row) + "," + (col) + ") is not valid. Try again...");
            }
        } while (!validInput);  // repeat until input is valid
    }
}
