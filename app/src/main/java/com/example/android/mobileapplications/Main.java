package com.example.android.mobileapplications;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    Button btn_start, btn_validate;
    TextView tv_player;
    EditText et_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_player = findViewById(R.id.et_player);
        btn_start = findViewById(R.id.bn_start);
        btn_validate = findViewById(R.id.bn_validate);
        tv_player = findViewById(R.id.tv_player);

        validate();
        startGame();
    }

    private void validate() {
        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_player.setVisibility(View.VISIBLE);
                tv_player.setText("Player 1: " + et_player.getText().toString());
            }
        });
    }

    private void startGame() {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this, Game.class));
                Toast.makeText(Main.this, "Starting a new game", Toast.LENGTH_SHORT).show();

                Intent intentPlayer = new Intent(Main.this, Game.class);
                startActivity(intentPlayer);
            }
        });
    }
}
