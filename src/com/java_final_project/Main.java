package com.java_final_project;
import java.util.Scanner;

public class Main {

    // Name-constants to represent the seeds and cell contents
    public static final int EMPTY = 0;

    public static final int TRIANGLE = 1;
    public static final int BOX = 2;
    public static final int CIRCLE = 3;

    // Name-constants to represent the various states of the game
    public static final int PLAYING = 0;
    public static final int DRAW = 1;

    public static final int TRIANGLE_WON = 2;
    public static final int BOX_WON = 3;
    public static final int CIRCLE_WON = 4;

    // The game board and the game status
    public static final int ROWS = 10, COLS = 10;
    public static int[][] board = new int[ROWS][COLS];
    //  containing (EMPTY, TRIANGLE, BOX)
    public static int currentState;  // the current state of the game

    public static int currentPlayer; // the current player (TRIANGLE or BOX or CIRCLE)
    public static int currntRow, currentCol; // current seed's row and column

    public static Scanner in = new Scanner(System.in); // the input Scanner

    public static void main(String[] args) {
        // Initialize the game-board and current status
        initGame();
        // Play the game once
        do {
            playerMove(currentPlayer); // update currentRow and currentCol
            updateGame(currentPlayer, currntRow, currentCol); // update currentState
            printBoard();
            // Print message if game-over
            if (currentState == TRIANGLE_WON) {
                System.out.println("'TRIANGLE' won! Bye!");
            } else if (currentState == BOX_WON) {
                System.out.println("'BOX' won! Bye!");
            } else if (currentState == CIRCLE_WON) {
                System.out.println("'CIRCLE' won! Bye");
            }
            else if (currentState == DRAW) {
                System.out.println("It's a Draw! Bye!");
            }

            // Switch players
            if (currentPlayer == TRIANGLE) {
                System.out.println("Next Player's turn + BOX");
                currentPlayer = BOX;
            } else if (currentPlayer == BOX) {
                System.out.println("Next Player's turn CIRCLE");
                currentPlayer = CIRCLE;
            } else if (currentPlayer == CIRCLE) {
                System.out.println("Next Player's turn TRIANGLE");
                currentPlayer = TRIANGLE;
            }

        } while (currentState == PLAYING); // repeat if not game-over
    }

    /** Initialize the game-board contents and the current states */
    public static void initGame() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                board[row][col] = EMPTY;  // all cells empty
            }
        }
        currentState = PLAYING; // ready to play
        currentPlayer = TRIANGLE;  // TRIANGLE plays first, next BOX, next CIRCLE
    }

    /** Player with the "theSeed" makes one move, with input validation.
     Update global variables "currentRow" and "currentCol". */
    public static void playerMove(int theSeed) {
        boolean validInput = false;  // for input validation
        do {
            if (theSeed == TRIANGLE) {
                System.out.print("Player '(T)RIANGLE', enter your move (row[0-9] column[0-9]): ");
            } else if (theSeed == BOX) {
                System.out.print("Player '(B)OX', enter your move (row[0-9] column[0-9]): ");
            } else {
                System.out.print("Player '(C)IRCLE', enter your move (row[0-9] column[0-9]): ");
            }
            int row = in.nextInt();
            int col = in.nextInt();
            if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTY) {
                currntRow = row;
                currentCol = col;
                board[currntRow][currentCol] = theSeed;  // update game-board content
                validInput = true;  // input okay, exit loop
            } else {
                System.out.println("This move at (" + (row) + "," + (col) + ") is not valid. Try again...");
            }
        } while (!validInput);  // repeat until input is valid
    }

    /** Update the "currentState" after the player with "theSeed" has placed on
     (currentRow, currentCol). */
    public static void updateGame(int theSeed, int currentRow, int currentCol) {
        if (hasWon(theSeed, currentRow, currentCol)) {
            // check if winning move
            if (theSeed == TRIANGLE) {
                currentState = TRIANGLE_WON;
            } else if (theSeed == BOX) {
                currentState = BOX_WON;
            } else if (theSeed == CIRCLE) {
                currentState = CIRCLE_WON;
            }
        } else if (isDraw()) {
            // check for draw
            currentState = DRAW;
        }
        // Otherwise, no change to currentState (still PLAYING)
    }

    /** Return true if it is a draw (no more empty cell) */
    // TODO: Shall declare draw if no player can "possibly" win
    public static boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (board[row][col] == EMPTY) {
                    return false;  // an empty cell found, not draw, exit
                }
            }
        }
        return true;  // no empty cell, it's a draw
    }

    /** Return true if the player with "theSeed" has won after placing at
     (currentRow, currentCol) */
    public static boolean hasWon(int theSeed, int currentRow, int currentCol) {
        return false;
    }

    /** Print the game board */
    public static void printBoard() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                printCell(board[row][col]); // print each of the cells
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

    /** Print a cell with the specified "content" */
    public static void printCell(int content) {
        switch (content) {
            case EMPTY:  System.out.print("   "); break;
            case TRIANGLE: System.out.print(" T "); break;
            case BOX:  System.out.print(" B "); break;
            case CIRCLE:  System.out.print(" C "); break;
        }
    }
}
