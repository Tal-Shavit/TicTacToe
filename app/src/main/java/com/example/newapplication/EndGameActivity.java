package com.example.newapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class EndGameActivity extends AppCompatActivity {

    private TextView winOrLoseTxt;
    private Button homeButton;
    private Button startOverButton;
    public static String KEY_WIN = "KEY_WIN";
    public static String KEY_NAME1 = "KEY_NAME1";
    public static String KEY_NAME2 = "KEY_NAME2";
    public static String KEY_SCORE1 = "KEY_SCORE1";
    public static String KEY_SCORE2 = "KEY_SCORE2";
    private String name1;
    private String name2;
    private int score1;
    private int score2;
    private LottieAnimationView winLottie1;
    private LottieAnimationView winLottie2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        int idx = getIntent().getIntExtra(KEY_WIN, 0);
        name1 = getIntent().getStringExtra(KEY_NAME1);
        name2 = getIntent().getStringExtra(KEY_NAME2);
        score1 = getIntent().getIntExtra(KEY_SCORE1, 0);
        score2 = getIntent().getIntExtra(KEY_SCORE2, 0);

        findViews();
        initViews();

        if (idx == -1) {
            winOrLoseTxt.setText("YOU BOTH LOSE!");
        }
        else{
            winLottie1.playAnimation();
            winLottie2.playAnimation();
            if (idx == 1) {
                winOrLoseTxt.setText("THE WINNER IS " + name1.toUpperCase());
            } if(idx == 2) {
                winOrLoseTxt.setText("THE WINNER IS " + name2.toUpperCase());
            }
        }
    }

    private void findViews() {
        winOrLoseTxt = findViewById(R.id.winOrLoseTxt);
        startOverButton = findViewById(R.id.startOverButton);
        winLottie1 = findViewById(R.id.winLottie1);
        winLottie2 = findViewById(R.id.winLottie2);
        homeButton = findViewById(R.id.homeButton);
    }

    private void initViews() {
        startOverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGame();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeScreen();
            }
        });
    }

    private void openGame() {
        Intent myIntent = new Intent(this, GameActivity.class);
        myIntent.putExtra(GameActivity.KEY_NAME1,name1);
        myIntent.putExtra(GameActivity.KEY_NAME2,name2);
        myIntent.putExtra(GameActivity.KEY_SCORE1,score1);
        myIntent.putExtra(GameActivity.KEY_SCORE2,score2);
        startActivity(myIntent);
        finish();
    }

    private void openHomeScreen() {
        Intent myIntent = new Intent(this, SplashGameOverActivity.class);
        startActivity(myIntent);
        finish();
    }
}
