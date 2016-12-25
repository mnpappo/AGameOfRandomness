package com.java_final_project;

/**
 * Created by mnpappo on 12/25/16.
 */

public class Board {

    public static int EMPTY = 0;

    public static final int ROWS = 10, COLS = 10;
    public static int[][] grid = new int[ROWS][COLS];

    //  containing (EMPTY, TRIANGLE, BOX)
    int cellObject;  // the current state of the game

    int currentPlayer; // the current player (TRIANGLE or BOX or CIRCLE)
    int currentRow, currentCol; // current seed's row and column


    /** Print the game board
     * @param player*/
    public static void PrintBoard(Players player) {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                PrintCell(player, grid[row][col]); // print each of the cells
                if (col != COLS - 1) {
                    System.out.print("|");   // print vertical partition
                }
            }
            System.out.println();
            if (row != ROWS - 1) {
                System.out.println("----------------------------------------"); // print horizontal partition
            }
        }
        System.out.println();
    }

    /** Print a cell with the specified "symbol" */
    public static void PrintCell(Players player, int symbol) {
        if (symbol == EMPTY) {
            System.out.print("   ");
        } else if (symbol == player.TRIANGLE) {
            System.out.print(" T ");
        } else if (symbol == player.BOX) {
            System.out.print(" B ");
        } else if (symbol == player.CIRCLE) {
            System.out.print(" C ");
        }
    }

}
