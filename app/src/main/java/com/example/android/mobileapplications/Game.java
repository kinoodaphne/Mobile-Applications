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

    //  Declare variables
    public static final Random RANDOM = new Random();
    public static final String text_winner = "com.example.android.mobileapplications.text_winner";

    Button btn_roll, btn_end;
    ImageView iv_dice1, iv_dice2, iv_dice3;

    String str_player1, str_player2;
    String str_winner;

    Integer counter = 0, round = 1, player1Turn;
    Boolean boolTurnPlayer1 = true;

    int dice1, dice2, dice3, score;

    CheckBox cb_dice1, cb_dice2, cb_dice3;

    TextView tv_player1, tv_player2, tv_turn, tv_counter, tv_round,
            tv_results, tv_scorePlayer1, tv_scorePlayer2,
            tv_roundsWinPlayer1, tv_roundsWinPlayer2;

    Integer roundP1, roundP2,
            TotalP1, TotalP2;

    int winsP1 = 0, winsP2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //  Call String value from Main for player names
        Intent intent = getIntent();
        str_player1 = intent.getStringExtra(Main.text_player1);
        str_player2 = intent.getStringExtra(Main.text_player2);

        //  Display players name & who is playing the round
        tv_player1 = findViewById(R.id.tv_player1);
        tv_player2 = findViewById(R.id.tv_player2);
        tv_turn = findViewById(R.id.tv_turn);

        tv_player1.setText(str_player1);
        tv_player2.setText(str_player2);
        tv_turn.setText(str_player1);

        //  Buttons
        btn_roll = findViewById(R.id.btn_roll);
        btn_end = findViewById(R.id.btn_end);

        //  ImageViews dices
        iv_dice1 = findViewById(R.id.iv_dice1);
        iv_dice2 = findViewById(R.id.iv_dice2);
        iv_dice3 = findViewById(R.id.iv_dice3);

        //  Checkboxes for ImageViews
        cb_dice1 = findViewById(R.id.cb_dice1);
        cb_dice2 = findViewById(R.id.cb_dice2);
        cb_dice3 = findViewById(R.id.cb_dice3);

        //  Display how many times the dices are thrown
        tv_counter = findViewById(R.id.tv_counter);

        //  Display rounds
        tv_round = findViewById(R.id.tv_rounds);

        // Display score each round & who won how many rounds
        tv_scorePlayer1 = findViewById(R.id.tv_scoreP1);
        tv_scorePlayer2 = findViewById(R.id.tv_scoreP2);
        tv_roundsWinPlayer1 = findViewById(R.id.tv_roundP1);
        tv_roundsWinPlayer2 = findViewById(R.id.tv_roundP2);

        // Display who won the round
        tv_results = findViewById(R.id.tv_result);


        //  Methods used for buttons
        rollDice();
        endTurn();
    }

    //  Generate a random number between 1 and 6
    public static int randomDiceValue() {
        return RANDOM.nextInt(6) + 1;
    }

    //  Roll the dices
    private void rollDice() {
        btn_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv_dice1.setVisibility(View.VISIBLE);
                iv_dice2.setVisibility(View.VISIBLE);
                iv_dice3.setVisibility(View.VISIBLE);

                // If checkbox is NOT checked, display new dice image
                if (!cb_dice1.isChecked()) {
                    dice1 = randomDiceValue();
                    int res1 = getResources().getIdentifier("ic_" + dice1,
                            "drawable", getPackageName());
                    iv_dice1.setImageResource(res1);
                }
                if (!cb_dice2.isChecked()) {
                    dice2 = randomDiceValue();
                    int res2 = getResources().getIdentifier("ic_" + dice2,
                            "drawable", getPackageName());
                    iv_dice2.setImageResource(res2);
                }
                if (!cb_dice3.isChecked()) {
                    dice3 = randomDiceValue();
                    int res3 = getResources().getIdentifier("ic_" + dice3,
                            "drawable", getPackageName());
                    iv_dice3.setImageResource(res3);
                }

                tv_results.setVisibility(View.INVISIBLE);

                btn_end.setVisibility(View.VISIBLE);
                cb_dice1.setVisibility(View.VISIBLE);
                cb_dice2.setVisibility(View.VISIBLE);
                cb_dice3.setVisibility(View.VISIBLE);
                //  How many times the dice is thrown
                counter++;
                //  If boolTurnPlayer 1 == true it's player1 his turn

                if (boolTurnPlayer1 == true) {
                    tv_counter.setText(counter + "/3");
                    if (counter <= 1) {
                        btn_end.setVisibility(View.VISIBLE);
                    } else if (counter == 3) {
                        Toast.makeText(Game.this, "Turns over, next player",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    tv_counter.setText(counter + "/" + player1Turn);
                    if (counter == player1Turn) {
                        Toast.makeText(Game.this, "Turns over, next player",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void endTurn() {
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculateScore();
                calculateSpecialScore();

                apen();

                if (cb_dice1.isChecked()) {
                    cb_dice1.toggle();
                }
                if (cb_dice2.isChecked()) {
                    cb_dice2.toggle();
                }
                if (cb_dice3.isChecked()) {
                    cb_dice3.toggle();
                }

                counter = 0;

                int image = getResources().getIdentifier("ic_",
                        "drawable", getPackageName());
                iv_dice1.setImageResource(image);
                iv_dice2.setImageResource(image);
                iv_dice3.setImageResource(image);
            }
        });
    }

    //  Maximum amount of points thrown
    private void apen() {
        if (dice1 == 100 && dice2 == 100 && dice3 == 100) {
            tv_results.setVisibility(View.VISIBLE);
            if (boolTurnPlayer1 == true) {
                tv_results.setText(str_player1 + " wins!");
            } else {
                tv_results.setText(str_player2 + " wins!");
            }
        } else {
            calculateRounds();
        }
    }

    private void calculateScore() {
        switch (dice1) {
            case 1:
                dice1 = 100;
                break;
            case 2:
                dice1 = 2;
                break;
            case 3:
                dice1 = 3;
                break;
            case 4:
                dice1 = 4;
                break;
            case 5:
                dice1 = 5;
                break;
            case 6:
                dice1 = 60;
                break;
        }

        switch (dice2) {
            case 1:
                dice2 = 100;
                break;
            case 2:
                dice2 = 2;
                break;
            case 3:
                dice2 = 3;
                break;
            case 4:
                dice2 = 4;
                break;
            case 5:
                dice2 = 5;
                break;
            case 6:
                dice2 = 60;
                break;
        }

        switch (dice3) {
            case 1:
                dice3 = 100;
                break;
            case 2:
                dice3 = 2;
                break;
            case 3:
                dice3 = 3;
                break;
            case 4:
                dice3 = 4;
                break;
            case 5:
                dice3 = 5;
                break;
            case 6:
                dice3 = 60;
                break;
        }
    }

    private void calculateSpecialScore() {
        if (dice1 == 60 && dice2 == 5 && dice3 == 4
                || dice1 == 60 && dice2 == 4 && dice3 == 5
                || dice1 == 5 && dice2 == 60 && dice3 == 4
                || dice1 == 5 && dice2 == 4 && dice3 == 60
                || dice1 == 4 && dice2 == 60 && dice3 == 5
                || dice1 == 4 && dice2 == 5 && dice3 == 60) {
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
    }

    private void calculateRounds() {
        if (boolTurnPlayer1 == true) {
            boolTurnPlayer1 = false;
            player1Turn = counter;
            tv_counter.setText("0/" + player1Turn);
            tv_turn.setText(str_player2);

            TotalP1 = dice1 + dice2 + dice3;
            tv_scorePlayer1.setText("" + TotalP1);
        } else {
            boolTurnPlayer1 = true;
            tv_turn.setText(str_player1);
            tv_counter.setText("0/3");
            round++;
            tv_round.setText("Round " + round);

            TotalP2 = dice1 + dice2 + dice3;
            tv_scorePlayer2.setText("" + TotalP2);

            tv_results.setVisibility(View.VISIBLE);
            String winner;
            if (roundP1 > roundP2) {
                winner = str_player1;
                winsP1++;
                tv_roundsWinPlayer1.setText("" + winsP1);
            } else if (roundP1.equals(roundP2)) {
                winner = "Nobody";
            } else {
                winner = str_player2;
                winsP2++;
                tv_roundsWinPlayer2.setText("" + winsP2);
            }
            tv_results.setText(winner + " wins!");
            roundWon();
        }
    }

    private void roundWon() {
        if (winsP1 == 5) {
            str_winner = str_player1;
            showResult();
        } else if (winsP2 == 5) {
            str_winner = str_player1;
            showResult();
        }
    }

    private void showResult() {
        Intent intent = new Intent(Game.this, Result.class);
        intent.putExtra(text_winner, str_winner);
        startActivity(intent);
    }
}