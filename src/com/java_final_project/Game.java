package com.java_final_project;

/**
 * Created by mnpappo on 12/25/16.
 */

public class Game {

    public int KEEP_PLAYING = 0;

    Game(Board board, Players player, PlayerMove playerMove) {
        for (int row = 0; row < board.ROWS; ++row) {
            for (int col = 0; col < board.COLS; ++col) {
                board.grid[row][col] = 0;  // all cells empty
            }
        }
        board.cellObject = KEEP_PLAYING; // ready to play
        board.currentPlayer = player.TRIANGLE;  // TRIANGLE plays first, next BOX, next CIRCLE

    }

    public void SwitchPlayer(Board board, Players player) {
        // Switch players
        if (board.currentPlayer == player.TRIANGLE) {
            System.out.println("Next Player's turn BOX");
            board.currentPlayer = player.BOX;
        } else if (board.currentPlayer == player.BOX) {
            System.out.println("Next Player's turn CIRCLE");
            board.currentPlayer = player.CIRCLE;
        } else if (board.currentPlayer == player.CIRCLE) {
            System.out.println("Next Player's turn TRIANGLE");
            board.currentPlayer = player.TRIANGLE;
        }
    }

    /**  */
    public void UpdateStatus(Board board, Players player, PlayerMove playerMove, int theSeed, int currentRow, int currentCol) {
        if (player.HasWon(currentRow, currentCol)) { //check if won
            // set cellObject that won
            if (theSeed == player.TRIANGLE) {
                board.cellObject = player.TRIANGLE_WON_STATUS;
            } else if (theSeed == player.BOX) {
                board.cellObject = player.BOX_WON_STATUS;
            } else if (theSeed == player.CIRCLE) {
                board.cellObject = player.CIRCLE_WON_STATUS;
            }

        } else if (playerMove.isDraw()) { // check if this move is draw
            board.cellObject = player.DRAW_STATUS;
        }
        // Otherwise, keep playing
    }
}
