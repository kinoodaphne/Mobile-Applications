package com.example.android.mobileapplications;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    Button btn_roll, btn_skip;
    ImageView iv_dice1, iv_dice2, iv_dice3;

    TextView tv_score;

    int score = 0;
    int aantalClicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btn_roll = findViewById(R.id.btn_roll);
        btn_skip = findViewById(R.id.btn_skip);
        iv_dice1 = findViewById(R.id.iv_dice1);
        iv_dice2 = findViewById(R.id.iv_dice2);
        iv_dice3 = findViewById(R.id.iv_dice3);

        btn_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aantalClicks++;
                if (aantalClicks <= 3) {
                    final Animation anim1 = AnimationUtils.loadAnimation(Game.this, R.anim.shake);
                    final Animation anim2 = AnimationUtils.loadAnimation(Game.this, R.anim.shake);
                    final Animation anim3 = AnimationUtils.loadAnimation(Game.this, R.anim.shake);
                    final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            int value = randomDiceValue();
                            int res = getResources().getIdentifier("ic_" + value, "drawable", "com.example.android.mobileapplications");

                            if (animation == anim1) {
                                iv_dice1.setImageResource(res);
                            } else if (animation == anim2) {
                                iv_dice2.setImageResource(res);
                            } else if (animation == anim3) {
                                iv_dice3.setImageResource(res);
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
                } else {
                    Toast.makeText(Game.this, "Turn over, next player", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aantalClicks = 0;
                Toast.makeText(Game.this, "Skipped, next player", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static int randomDiceValue() {
        return RANDOM.nextInt(6) + 1;
    }
}
