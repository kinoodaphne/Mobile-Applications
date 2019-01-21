package com.example.android.mobileapplications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {

    //    private static final Random RANDOM = new Random();
    final Random r = new Random();
    Button btn_roll, btn_skip;
    TextView tv_dice1, tv_dice2, tv_dice3;
    CheckBox cb_dice1, cb_dice2, cb_dice3;

    TextView tv_score;

    int dice1, dice2, dice3;

    int score, ogen, totaal;
    int aantalClicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btn_roll = findViewById(R.id.btn_roll);
        btn_skip = findViewById(R.id.btn_skip);
        tv_dice1 = findViewById(R.id.tv_dice1);
        tv_dice2 = findViewById(R.id.tv_dice2);
        tv_dice3 = findViewById(R.id.tv_dice3);
        tv_score = findViewById(R.id.tv_score);
        cb_dice1 = findViewById(R.id.cb_dice1);
        cb_dice2 = findViewById(R.id.cb_dice2);
        cb_dice3 = findViewById(R.id.cb_dice3);

        rollDices();
        skipTurn();
    }

    private void rollDices() {
        btn_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aantalClicks++;
                if (aantalClicks <= 3) {
                    if (cb_dice1.isChecked()){
                        tv_dice2.setText(Integer.toString(r.nextInt(6)+1));
                        tv_dice3.setText(Integer.toString(r.nextInt(6)+1));
                    }
                    else if (cb_dice2.isChecked()){
                        tv_dice1.setText(Integer.toString(r.nextInt(6)+1));
                        tv_dice3.setText(Integer.toString(r.nextInt(6)+1));

                    }
                    else if (cb_dice3.isChecked()){
                        tv_dice2.setText(Integer.toString(r.nextInt(6)+1));
                        tv_dice3.setText(Integer.toString(r.nextInt(6)+1));
                    }
//                    tv_dice1.setText(Integer.toString(r.nextInt(6)+1));
//                    tv_dice2.setText(Integer.toString(r.nextInt(6)+1));
//                    tv_dice3.setText(Integer.toString(r.nextInt(6)+1));
                    berekenScore();
                } else {
                    Toast.makeText(Game.this, "Turn over, next player", Toast.LENGTH_SHORT).show();
                    nextPlayer();
                }
            }
        });
    }

    private void skipTurn() {
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aantalClicks = 0;
                nextPlayer();
                tv_score.setText("Score: ");
                Toast.makeText(Game.this, "Skipped, next player", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void checkboxChecked() {
//        cb_dice1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (cb_dice1.isChecked()) {
//                    Toast.makeText(getApplicationContext(), "checked", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        cb_dice2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (cb_dice2.isChecked()) {
//                    Toast.makeText(getApplicationContext(), "checked", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        cb_dice3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (cb_dice3.isChecked()) {
//                    Toast.makeText(getApplicationContext(), "checked", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

    private void berekenScore() {
        dice1 = Integer.parseInt(tv_dice1.getText().toString());
        dice2 = Integer.parseInt(tv_dice2.getText().toString());
        dice3 = Integer.parseInt(tv_dice3.getText().toString());

        ogenDice();
        tv_score.setText("Score: " + String.valueOf(totaal));
    }

    private void nextPlayer() {

    }

    private void ogenDice() {
        switch (dice1) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 2;
                break;
            case 3:
                score += 3;
                break;
            case 4:
                score += 4;
                break;
            case 5:
                score += 5;
                break;
            case 6:
                score += 60;
                break;
        }

        switch (dice2) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 2;
                break;
            case 3:
                score += 3;
                break;
            case 4:
                score += 4;
                break;
            case 5:
                score += 5;
                break;
            case 6:
                score += 60;
                break;
        }

        switch (dice3) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 2;
                break;
            case 3:
                score += 3;
                break;
            case 4:
                score += 4;
                break;
            case 5:
                score += 5;
                break;
            case 6:
                score += 60;
                break;
        }
        totaal = dice1 + dice2 + dice3;
    }
}
