package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NameOfPlayer extends AppCompatActivity {
    EditText player1;
    EditText player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_of_player);
        player1 = findViewById(R.id.player1edit);
        player2 = findViewById(R.id.player2edit);
        
    }
    public void submitbuttonclick(View view){
        String player1edit = player1.getText().toString();
        String player2edit = player2.getText().toString();
        Intent updatename = new Intent(this,Game.class);
        updatename.putExtra("Player_Name", new String[] {player1edit, player2edit});
        startActivity(updatename);
    }
}