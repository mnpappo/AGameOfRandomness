package com.java_final_project;

import java.util.Scanner;

/**
 * Created by mnpappo on 12/23/16.
 */

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in); // the input Scanner

        Players player = new Players();
        PlayerMove playerMove = new PlayerMove();
        Board board = new Board();

        // Initialize the game-board and current status
        Game game = new Game(board, player, playerMove);

        // count iterations
        int iterations = 1;

        // Start playing game
        do {

            if (iterations <= 3) { // initial move of three player at (0,0) cell
                playerMove.Move(playerMove, board, 0, 0);
                // show the iteration number
                System.out.println("Initial Iteration number: " + (iterations) + " at cell (0,0) for player: " + board.currentPlayer);
                iterations += 1;
                // switch player
                game.SwitchPlayer(board, player);
            } else { //// take input for the move(until find valid move), move the player to the current cell
                playerMove.Move(board, player, playerMove);

                // update game status if win else keep playing
                game.UpdateStatus(board, player, playerMove, board.currentPlayer, board.currentRow, board.currentCol);

                // print the board after new update
                board.PrintBoard(player);

                // switch player
                game.SwitchPlayer(board, player);

                System.out.println("Winning cell is (" + player.winRow + "," + player.winCol + ")");

                // show the iteration number
                System.out.println("Iteration number: " + (iterations));
                iterations += 1;


                // after win, ask for trace
                if (player.PlayerWon(board.cellObject)) {
                    while (true) {
                        System.out.println("\n-----------------------------------------------------\n");
                        System.out.print("Show trace for: (1 for T, 2 for B, 3 for C, 0 for Exit Now.): ");
                        int traceCommand = input.nextInt();
                        if (traceCommand == 0) {
                            System.out.println("Thanks for playing.");
                            break;
                        } else {
                            System.out.println("Player " + traceCommand + " Looked for these cells: ");
                            for (int i = traceCommand-1; i < playerMove.tracedList_Player.size(); i+=3) {
                                System.out.print( "(" + playerMove.tracedList_Row.get(i) + "," + playerMove.tracedList_Col.get(i) + ") , ");
                            }
                        }
                    }
                }

                try { // just slow to see what happen
                    Thread.sleep(100);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

        } while (board.cellObject == game.KEEP_PLAYING); // repeat if not game-over
    }
}
