package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game extends AppCompatActivity {
    private TicTacToe ticTacToe;
    private String[] playerNames;
    private TextView playerchance;
    private TextView playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        playAgain = (TextView)findViewById(R.id.PlayAgain);
        playerchance = (TextView) findViewById(R.id.playerdisplay);
        ticTacToe = findViewById(R.id.ticTacToe);
        playAgain.setVisibility(View.GONE);
        playerNames = getIntent().getStringArrayExtra("Player_Name");
        if (playerNames != null) {
            playerchance.setText((playerNames[0] + "'s Turn"));
        }
        ticTacToe.setUpGame(playAgain,playerchance,playerNames);




    }
    protected void exitbybackkey(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        alertDialog.setView(customLayout);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    @Override
    public void onBackPressed() {
        exitbybackkey();
    }
    public void playAgain(View view){
       ticTacToe.resetGame();
       ticTacToe.invalidate();

    }
}