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
    public static final String text_player1 = "com.example.android.mobileapplications.text_player1";
    public static final String text_player2 = "com.example.android.mobileapplications.text_player2";

    Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.bn_start);
        startGame();
    }

    private void startGame() {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText player1 = findViewById(R.id.et_player1);
                EditText player2 = findViewById(R.id.et_player2);

                String str_player1 = player1.getText().toString();
                String str_player2 = player2.getText().toString();

                TextView tv_validate = findViewById(R.id.tv_validate);

                if (str_player1.isEmpty() && str_player2.isEmpty()) {
                    tv_validate.setText("Please enter names for both players");
                } else {
                    Intent intent = new Intent(Main.this, Game.class);

                    intent.putExtra(text_player1, str_player1);
                    intent.putExtra(text_player2, str_player2);
                    startActivity(intent);
                }
            }
        });
    }
}
