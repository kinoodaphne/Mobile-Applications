package com.example.android.mobileapplications;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    String str_winner;
    TextView tv_winner;
    Button btn_newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        str_winner = intent.getStringExtra(Game.text_winner);

        tv_winner = findViewById(R.id.tv_winnerPlayer);
        tv_winner.setText(str_winner);

        btn_newGame = findViewById(R.id.btn_new);

        newGame();
    }

    private void newGame() {
        btn_newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, Main.class);
                startActivity(intent);
            }
        });
    }
}
