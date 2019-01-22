package com.example.android.mobileapplications;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    String str_winner;
    TextView tv_winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        str_winner = intent.getStringExtra(Game.text_winner);

        tv_winner = findViewById(R.id.tv_winnerPlayer);
        tv_winner.setText(str_winner);

        newGame();
        changeNames();
    }

    private void changeNames() {
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

    private void newGame() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}
