package com.java_final_project;

import java.util.Random;

/**
 * Created by mnpappo on 12/25/16.
 */

public class Players {

    public int TRIANGLE = 1;
    public int BOX = 2;
    public int CIRCLE = 3;

    int DRAW_STATUS = 1;
    int TRIANGLE_WON_STATUS = 2;
    int BOX_WON_STATUS = 3;
    int CIRCLE_WON_STATUS = 4;

    public static Random random = new Random();
    // get random wining cell
    public static final int winRow = random.nextInt(9);
    public static final int winCol = random.nextInt(9);



    /** Return true if the player with "theSeed" has won after placing at
     (currentRow, currentCol) */
    public static boolean HasWon(int currentRow, int currentCol) {
        if (winRow==currentRow && winCol==currentCol) {
            return true;
        } else return false;
    }

    // player won
    boolean PlayerWon(int currentState) {
        // Print message if game-over
        if (currentState == TRIANGLE_WON_STATUS) {
            System.out.println("'TRIANGLE' won!");
        } else if (currentState == BOX_WON_STATUS) {
            System.out.println("'BOX' won! Bye!");
        } else if (currentState == CIRCLE_WON_STATUS) {
            System.out.println("'CIRCLE' won!");
        }
        else if (currentState == DRAW_STATUS) {
            System.out.println("It's a Draw!");
        } else {
            return false;
        }
        return true;

    }


}
