package com.example.johan.tic_tac_toe;

import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class Game implements Serializable {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    // Setup game board
    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    // change selected tile to cross or circle in blank
    public TileState choose(int row, int column) {
        if(board[row][column] == TileState.BLANK)   {
            if(playerOneTurn)   {
                board[row][column] = TileState.CROSS;
                playerOneTurn = false;
                return TileState.CROSS;
            } else   {
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;
                return TileState.CIRCLE;
            }
        } else  {
            return TileState.INVALID;
        }

    }

    // Check if game is won
    public GameState won()   {
        if((board[0][0] == TileState.CROSS && board[1][1] == TileState.CROSS &&
                board[2][2] == TileState.CROSS) || (board[0][2] == TileState.CROSS &&
                board[1][1] == TileState.CROSS && board[2][0] == TileState.CROSS))  {
            return GameState.PLAYER_ONE;
        }
        for(int i=0; i<BOARD_SIZE; i++)
            if((board[i][0] == TileState.CROSS && board[i][1] == TileState.CROSS &&
                    board[i][2] == TileState.CROSS) || (board[0][i] == TileState.CROSS &&
                    board[1][i] == TileState.CROSS && board[2][i] == TileState.CROSS))  {
                return GameState.PLAYER_ONE;
            }
        if((board[0][0] == TileState.CIRCLE && board[1][1] == TileState.CIRCLE &&
                board[2][2] == TileState.CIRCLE) || (board[0][2] == TileState.CIRCLE &&
                board[1][1] == TileState.CIRCLE && board[2][0] == TileState.CIRCLE))    {
            return GameState.PLAYER_TWO;
        }
        for(int i=0; i<BOARD_SIZE; i++)
            if((board[i][0] == TileState.CIRCLE && board[i][1] == TileState.CIRCLE &&
                    board[i][2] == TileState.CIRCLE) || (board[0][i] == TileState.CIRCLE &&
                    board[1][i] == TileState.CIRCLE && board[2][i] == TileState.CIRCLE))    {
                return GameState.PLAYER_TWO;
            }
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                if(board[i][j] == TileState.BLANK)  {
                    return GameState.IN_PROGRESS;
                }
        return GameState.DRAW;
    }
}
