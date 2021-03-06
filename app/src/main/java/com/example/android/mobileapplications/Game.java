package com.example.android.mobileapplications;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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


                final Animation anim1 = AnimationUtils.loadAnimation(Game.this, R.anim.shake);
                final Animation anim2 = AnimationUtils.loadAnimation(Game.this, R.anim.shake);
                final Animation anim3 = AnimationUtils.loadAnimation(Game.this, R.anim.shake);
                final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // If checkbox is NOT checked, display new dice image
                        if (animation == anim1) {
                            if (!cb_dice1.isChecked()) {
                                dice1 = randomDiceValue();
                                int res1 = getResources().getIdentifier("ic_" + dice1,
                                        "drawable", getPackageName());
                                iv_dice1.setImageResource(res1);
                            }
                        } else if (animation == anim2) {
                            if (!cb_dice2.isChecked()) {
                                dice2 = randomDiceValue();
                                int res2 = getResources().getIdentifier("ic_" + dice2,
                                        "drawable", getPackageName());
                                iv_dice2.setImageResource(res2);
                            }
                        } else if (animation == anim3) {
                            if (!cb_dice3.isChecked()) {
                                dice3 = randomDiceValue();
                                int res3 = getResources().getIdentifier("ic_" + dice3,
                                        "drawable", getPackageName());
                                iv_dice3.setImageResource(res3);
                            }
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };

                anim1.setAnimationListener(animationListener);
                anim2.setAnimationListener(animationListener);
                anim3.setAnimationListener(animationListener);

                iv_dice1.startAnimation(anim1);
                iv_dice2.startAnimation(anim2);
                iv_dice3.startAnimation(anim3);

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

    // End turn of current player; gather score
    private void endTurn() {
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculateScore();
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

    //  Calculate score of current turn
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

        //  If dice is 69
        if (dice1 == 60 && dice2 == 5 && dice3 == 4
                || dice1 == 60 && dice2 == 4 && dice3 == 5
                || dice1 == 5 && dice2 == 60 && dice3 == 4
                || dice1 == 5 && dice2 == 4 && dice3 == 60
                || dice1 == 4 && dice2 == 60 && dice3 == 5
                || dice1 == 4 && dice2 == 5 && dice3 == 60) {
            //  Who gets the 69? huehue dirty mind
            if (boolTurnPlayer1 == true) {
                roundP1 = 69;
            } else {
                roundP2 = 69;
            }
        } else if ((dice1 == 3 && dice2 == 2 && dice3 == 2
                || dice1 == 2 && dice2 == 3 && dice3 == 2
                || dice1 == 2 && dice2 == 2 && dice3 == 3)) {
            if (boolTurnPlayer1 == true) {
                roundP1 = 7;
            } else {
                roundP2 = 7;
            }
        }
        // If dice is Zand
        else if (dice1 == dice2 && dice1 == dice3) {
            // Cases who gets Zand
            switch (dice1) {
                case 2:
                    if (boolTurnPlayer1 == true) {
                        roundP1 = 222;
                    } else {
                        roundP2 = 222;
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

    // Calculate who wins the round
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

            String winner;
            // If score player 1 is greater than score player 2
            if (roundP1 > roundP2) {
                //  Player 1 won
                winner = str_player1;
                winsP1++;
                tv_roundsWinPlayer1.setText("" + winsP1);
            } else if (roundP1.equals(roundP2)) {
                //  Sorry, it's a draw
                winner = "Nobody";
            } else {
                //  Player 2 won
                winner = str_player2;
                winsP2++;
                tv_roundsWinPlayer2.setText("" + winsP2);
            }
            tv_results.setVisibility(View.VISIBLE);
            tv_results.setText(winner + " wins!");
            roundWon();
        }
    }

    // Who ever gets to 5 rounds first wins
    private void roundWon() {
        if (winsP1 == 5) {
            str_winner = str_player1;
            showResult();
        } else if (winsP2 == 5) {
            str_winner = str_player1;
            showResult();
        }
    }

    // Go to next activity Result to celebrate the winner
    private void showResult() {
        Intent myIntent = new Intent(Game.this, Result.class);
        myIntent.putExtra(text_winner, str_winner);
        startActivity(myIntent);
    }
}