package com.example.android.mobileapplications;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Game extends AppCompatActivity {

    public static final Random RANDOM = new Random();

    //  Buttons
    Button btn_roll, btn_end;
    ImageView iv_dice1, iv_dice2, iv_dice3;

    String str_player1, str_player2;

    Integer counter = 0;
    Boolean boolTurnPlayer1 = true;
    Integer player1Turn;
    Integer round = 1;

    int dice1, dice2, dice3, score;

    CheckBox cb_dice1, cb_dice2, cb_dice3;

    TextView tv_player1, tv_player2, tv_turn;

    TextView  tv_counter, tv_round, tv_scorePlayer1, tv_scorePlayer2, tv_roundsWinPlayer1, tv_roundsWinPlayer2, tv_results;

    Integer roundP1, roundP2;
    String TotalP1, TotalP2;

    int winsP1 = 0;
    int winsP2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        str_player1 = intent.getStringExtra(Main.text_player1);
        str_player2 = intent.getStringExtra(Main.text_player2);

        tv_player1 = findViewById(R.id.tv_player1);
        tv_player2 = findViewById(R.id.tv_player2);
        tv_turn = findViewById(R.id.tv_turn);

        tv_player1.setText(str_player1);
        tv_player2.setText(str_player2);
        tv_turn.setText(str_player1);

        btn_roll = (Button) findViewById(R.id.btn_roll);
        iv_dice1 = (ImageView) findViewById(R.id.iv_dice1);
        iv_dice2 = (ImageView) findViewById(R.id.iv_dice2);
        iv_dice3 = (ImageView) findViewById(R.id.iv_dice3);

        btn_end = findViewById(R.id.btn_end);

        cb_dice1 = findViewById(R.id.cb_dice1);
        cb_dice2 = findViewById(R.id.cb_dice2);
        cb_dice3 = findViewById(R.id.cb_dice3);

        btn_end.setVisibility(View.INVISIBLE);
        cb_dice1.setVisibility(View.INVISIBLE);
        cb_dice2.setVisibility(View.INVISIBLE);
        cb_dice3.setVisibility(View.INVISIBLE);

        tv_counter = findViewById(R.id.tv_counter);
        tv_round = findViewById(R.id.tv_rounds);

        tv_scorePlayer1 = findViewById(R.id.tv_scoreP1);
        tv_scorePlayer2 = findViewById(R.id.tv_scoreP2);
        tv_roundsWinPlayer1 = findViewById(R.id.tv_roundP1);
        tv_roundsWinPlayer2 = findViewById(R.id.tv_roundP1);

        tv_results = findViewById(R.id.tv_result);

        rollDice();

        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dice1 == 1) {
                    dice1 = 100;
                } else if (dice1 == 2) {
                    dice1 = 2;
                } else if (dice1 == 3) {
                    dice1 = 3;
                } else if (dice1 == 4) {
                    dice1 = 4;
                } else if (dice1 == 5) {
                    dice1 = 5;
                } else if (dice1 == 6) {
                    dice1 = 60;
                }

                if (dice2 == 1) {
                    dice2 = 100;
                } else if (dice2 == 2) {
                    dice2 = 2;
                } else if (dice2 == 3) {
                    dice2 = 3;
                } else if (dice2 == 4) {
                    dice2 = 4;
                } else if (dice2 == 5) {
                    dice2 = 5;
                } else if (dice2 == 6) {
                    dice2 = 60;
                }

                if (dice3 == 1) {
                    dice3 = 100;
                } else if (dice3 == 2) {
                    dice3 = 2;
                } else if (dice3 == 3) {
                    dice3 = 3;
                } else if (dice3 == 4) {
                    dice3 = 4;
                } else if (dice3 == 5) {
                    dice3 = 5;
                } else if (dice3 == 6) {
                    dice3 = 60;
                }

                if (dice1 == 60 && dice2 == 5 && dice3 == 4 || dice1 == 60 && dice2 == 4 && dice3 == 5 || dice1 == 5 && dice2 == 60 && dice3 == 4 || dice1 == 5 && dice2 == 4 && dice3 == 60 || dice1 == 4 && dice2 == 60 && dice3 == 5 || dice1 == 4 && dice2 == 5 && dice3 == 60) {
                    if (boolTurnPlayer1 == true) {
                        roundP1 = 700;
                    } else {
                        roundP2 = 700;
                    }
                } else if (dice1 == dice2 && dice1 == dice3) {
                    switch (dice1) {
                        case 2:
                            if (boolTurnPlayer1 == true) {
                                roundP1 = 270;
                            } else {
                                roundP2 = 270;
                            }
                            break;

                        case 3:
                            if (boolTurnPlayer1 == true) {
                                roundP1 = 333;
                            } else {
                                roundP2 = 333;
                            }
                            break;

                        case 4:
                            if (boolTurnPlayer1 == true) {
                                roundP1 = 444;
                            } else {
                                roundP2 = 444;
                            }
                            break;

                        case 5:
                            if (boolTurnPlayer1 == true) {
                                roundP1 = 555;
                            } else {
                                roundP2 = 555;
                            }
                            break;

                        case 6:
                            if (boolTurnPlayer1 == true) {
                                roundP1 = 666;
                            } else {
                                roundP2 = 666;
                            }
                            break;
                    }
                } else {
                    if (boolTurnPlayer1 == true) {
                        score = dice1 + dice2 + dice3;
                        roundP1 = score;
                    } else {
                        score = dice1 + dice2 + dice3;
                        roundP2 = score;
                    }
                }

                if (dice1 == 100 && dice2 == 100 && dice3 == 100) {
                    //  Maximum points
                    tv_results.setVisibility(View.VISIBLE);
                    if (boolTurnPlayer1 == true) {
                        tv_results.setText(str_player1 + " wins!");
                    } else {
                        tv_results.setText(str_player2 + " wins!");
                    }
                } else {
                    if (boolTurnPlayer1 == true) {
                        boolTurnPlayer1 = false;
                        player1Turn = counter;
                        tv_counter.setText("0/" + player1Turn);
                        tv_turn.setText(str_player2);

                        TotalP1 = dice1 + " " + dice2 + " " + dice3;
                        tv_scorePlayer1.setText("" + TotalP1);
                    } else {
                        boolTurnPlayer1 = true;
                        tv_turn.setText(str_player1);
                        tv_counter.setText("0/3");
                        round++;
                        tv_round.setText("Round " + round);

                        TotalP2 = dice1 + " " + dice2 + " " + dice3;
                        tv_scorePlayer2.setText("" + TotalP2);

                        tv_results.setVisibility(View.VISIBLE);
                        String winner;
                        if (roundP1 > roundP2) {
                            winner = str_player1;
                            winsP1++;
                            tv_roundsWinPlayer1.setText("" + winsP1);
                        } else if (roundP1.equals(roundP2)) {
                            winner = "Draw";
                        } else {
                            winner = str_player2;
                            winsP2++;
                            tv_roundsWinPlayer1.setText("" + winsP2);
                        }
                        tv_results.setText(winner + " wins!");
                    }
                    btn_end.setVisibility(View.INVISIBLE);
                    btn_roll.setVisibility(View.VISIBLE);

                    if (cb_dice1.isChecked()) {
                        cb_dice1.toggle();
                    }
                    if (cb_dice2.isChecked()) {
                        cb_dice2.toggle();
                    }
                    if (cb_dice3.isChecked()) {
                        cb_dice3.toggle();
                    }
                    cb_dice1.setVisibility(View.INVISIBLE);
                    cb_dice2.setVisibility(View.INVISIBLE);
                    cb_dice3.setVisibility(View.INVISIBLE);

                    counter = 0;

                    int image = getResources().getIdentifier("ic_", "drawable", getPackageName());
                    iv_dice1.setImageResource(image);
                    iv_dice2.setImageResource(image);
                    iv_dice3.setImageResource(image);
                }
            }
        });
    }

    public static int randomDiceValue() {
        return RANDOM.nextInt(6) + 1;
    }

    private void rollDice() {
        btn_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cb_dice1.isChecked()) {
                    dice1 = randomDiceValue();
                    int res1 = getResources().getIdentifier("ic_" + dice1, "drawable", getPackageName());
                    iv_dice1.setImageResource(res1);
                }
                if (!cb_dice2.isChecked()) {
                    dice2 = randomDiceValue();
                    int res2 = getResources().getIdentifier("ic_" + dice2, "drawable", getPackageName());
                    iv_dice2.setImageResource(res2);
                }
                if (!cb_dice3.isChecked()) {
                    dice3 = randomDiceValue();
                    int res3 = getResources().getIdentifier("ic_" + dice3, "drawable", getPackageName());
                    iv_dice3.setImageResource(res3);
                }

                tv_results.setVisibility(View.INVISIBLE);

                btn_end.setVisibility(View.VISIBLE);
                cb_dice1.setVisibility(View.VISIBLE);
                cb_dice2.setVisibility(View.VISIBLE);
                cb_dice3.setVisibility(View.VISIBLE);
                counter++;
                if (boolTurnPlayer1 == true) {
                    tv_counter.setText(counter + "/3");
                    if (counter <= 1) {
                        btn_end.setVisibility(View.VISIBLE);
                    } else if (counter == 3) {
                        cb_dice1.setVisibility(View.INVISIBLE);
                        cb_dice2.setVisibility(View.INVISIBLE);
                        cb_dice3.setVisibility(View.INVISIBLE);
                        btn_roll.setVisibility(View.INVISIBLE);
                    }
                } else {
                    tv_counter.setText(counter + "/" + player1Turn);
                    if (counter == player1Turn) {
                        cb_dice1.setVisibility(View.INVISIBLE);
                        cb_dice2.setVisibility(View.INVISIBLE);
                        cb_dice3.setVisibility(View.INVISIBLE);
                        btn_roll.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    private void endTurn() {

    }
}
