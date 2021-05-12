package com.example.tictactoe;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.TextView;

public class     MainGame {
    private int[][] gameBoard;
    private TextView playAgain;
    private int player = 1;
    String [] playerNames ={"Player1", "Player2"};
    private TextView playerchance;
    private int[] winType = {-1,-1,-1};


    MainGame() {
        gameBoard = new int[3][3];
        for (int d = 0; d < 3; d++) {
            for (int c = 0; c < 3; c++) {
                gameBoard[d][c] = 0;
            }
        }
    }

    public boolean updateGameBoard(int row, int col) {
        if (gameBoard[row - 1][col - 1] == 0) {
            gameBoard[row - 1][col - 1] = player;
            if (player==1){
                playerchance.setText(playerNames[0] + "'s Turn");
            }
            else{
                playerchance.setText(playerNames[1] + "'s Turn");
            }

            return true;

        }
        else
            {
            return false;
        }
    }

    public boolean winnercheck() {
        boolean isWinner = false;
        for(int d=0;d<3;d++){
            if (gameBoard[d][0] == gameBoard[d][1] && gameBoard[d][0] == gameBoard[d][2] &&  gameBoard[d][0] != 0){
                winType = new int[]{d, 0, 1};
                isWinner = true;
            }
        }
        for(int c=0;c<3;c++){
            if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[2][c] == gameBoard[0][c] &&  gameBoard[0][c] != 0){
                winType = new int[]{0, c, 2};
                isWinner = true;
            }
        }
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] &&  gameBoard[0][0] != 0){
            winType = new int[]{0, 2, 3};
            isWinner = true;
        }
        if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[2][0] == gameBoard[0][2] &&  gameBoard[2][0] != 0){
            winType = new int[]{2, 2, 4};
            isWinner = true;
        }
        int boardFilled = 0;
        for (int d=0;d<3;d++){
            for (int c=0;c<3;c++){
                if (gameBoard[d][c] !=0){
                    boardFilled +=1;
                }

            }
        }
        if (isWinner){
            playAgain.setVisibility(View.VISIBLE);
            playerchance.setText((playerNames[player-1] + " Won!!!!"));
        }
        else if (boardFilled == 9){
            playAgain.setVisibility(View.VISIBLE);
            playerchance.setText("Game Draw!!!!");
            return false   ;
        }
        else {
            return false;
        }

        return isWinner;
    }


    public void resetGame(){
        for (int d = 0; d<3; d++){
            for (int c=0; c<3; c++){
                gameBoard[d][c] = 0;
            }
        }
        player = 1;
        playAgain.setVisibility(View.GONE);
        playerchance.setText(playerNames[0] + "'s turn");
    }
    public void setPlayAgain(TextView playAgain) {

        this.playAgain =playAgain;
    }
    public void setPlayturn(TextView playerchance){

        this.playerchance =playerchance;
    }
    public void setPlayerNames(String[] playerNames){
        this.playerNames = playerNames;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public int[] getWinType() {
        return winType;
    }
}
