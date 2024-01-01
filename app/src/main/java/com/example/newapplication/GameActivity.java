package com.example.newapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    public static String KEY_NAME1 = "KEY_NAME1";
    public static String KEY_NAME2 = "KEY_NAME2";
    public static String KEY_SCORE1 = "KEY_SCORE1";
    public static String KEY_SCORE2 = "KEY_SCORE2";
    private TextView player1;
    private TextView player2;
    private int[] currentNumber = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int totalClicks = 0;
    private boolean playerX = true;
    int win = -1;
    private String namePlayer1;
    private String namePlayer2;
    private ImageButton[] buttons = new ImageButton[9];
    private Button homeButton;
    private TextView score1;
    private TextView score2;
    private int scorePlayer1;
    private int scorePlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        namePlayer1 = getIntent().getStringExtra(KEY_NAME1);
        namePlayer2 = getIntent().getStringExtra(KEY_NAME2);
        findViews();
        initView();
    }

    private void findViews() {
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        homeButton = findViewById(R.id.homeButton);
        score1 = findViewById(R.id.score1);
        score2 = findViewById(R.id.score2);
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);
    }

    private void initView() {
        player1.setText(namePlayer1.toUpperCase() + "\nX");
        player2.setText(namePlayer2.toUpperCase() + "\nO");
        scorePlayer1 = getIntent().getIntExtra(KEY_SCORE1, 0);
        scorePlayer2 = getIntent().getIntExtra(KEY_SCORE2, 0);
        score1.setText(scorePlayer1+"");
        score2.setText(scorePlayer2+"");
        clickButton();

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainScreen();
            }
        });
    }

    private void clickButton() {
        for (int i = 0; i < buttons.length; i++) {
            int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (playerX == true && currentNumber[finalI] == 0) {
                        buttons[finalI].setImageResource(R.drawable.x);
                        player1.setBackground(getResources().getDrawable(R.drawable.shape_player_name_with_color));
                        player2.setBackground(getResources().getDrawable(R.drawable.shape_player_name));
                        currentNumber[finalI] = 1;
                        totalClicks++;
                        playerX = false;
                        checkWinner();
                    } else if (playerX == false && currentNumber[finalI] == 0) {
                        buttons[finalI].setImageResource(R.drawable.o);
                        player2.setBackground(getResources().getDrawable(R.drawable.shape_player_name_with_color));
                        player1.setBackground(getResources().getDrawable(R.drawable.shape_player_name));
                        currentNumber[finalI] = 2;
                        totalClicks++;
                        playerX = true;
                        checkWinner();
                    }
                }
            });
        }
    }

    private void checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (currentNumber[i * 3] != 0 && currentNumber[i * 3] == currentNumber[i * 3 + 1] && currentNumber[i * 3] == currentNumber[i * 3 + 2]) {
                buttons[i * 3].setBackground(getResources().getDrawable(R.drawable.shapebutton_stroke));
                buttons[i * 3 + 1].setBackground(getResources().getDrawable(R.drawable.shapebutton_stroke));
                buttons[i * 3 + 2].setBackground(getResources().getDrawable(R.drawable.shapebutton_stroke));
                openEndGameScreen(i * 3);
                for(int j =0; j<buttons.length; j++){
                    buttons[j].setEnabled(false);
                }
            } else if (currentNumber[i] != 0 && currentNumber[i] == currentNumber[i + 3] && currentNumber[i] == currentNumber[i + 6]) {
                buttons[i].setBackground(getResources().getDrawable(R.drawable.shapebutton_stroke));
                buttons[i + 3].setBackground(getResources().getDrawable(R.drawable.shapebutton_stroke));
                buttons[i + 6].setBackground(getResources().getDrawable(R.drawable.shapebutton_stroke));
                openEndGameScreen(i);
                for(int j =0; j<buttons.length; j++){
                    buttons[j].setEnabled(false);
                }
            }
        }
        if (currentNumber[0] != 0 && currentNumber[0] == currentNumber[4] && currentNumber[0] == currentNumber[8]) {
            buttons[0].setBackground(getResources().getDrawable(R.drawable.shapebutton_stroke));
            buttons[4].setBackground(getResources().getDrawable(R.drawable.shapebutton_stroke));
            buttons[8].setBackground(getResources().getDrawable(R.drawable.shapebutton_stroke));
            openEndGameScreen(0);
            for(int j =0; j<buttons.length; j++){
                buttons[j].setEnabled(false);
            }
        } else if (currentNumber[2] != 0 && currentNumber[2] == currentNumber[4] && currentNumber[2] == currentNumber[6]) {
            buttons[2].setBackground(getResources().getDrawable(R.drawable.shapebutton_stroke));
            buttons[4].setBackground(getResources().getDrawable(R.drawable.shapebutton_stroke));
            buttons[6].setBackground(getResources().getDrawable(R.drawable.shapebutton_stroke));
            openEndGameScreen(2);
            for(int j =0; j<buttons.length; j++){
                buttons[j].setEnabled(false);
            }
        } else if (totalClicks == 9) {
            openEndGameScreen(-1);
        }
    }

    private void openEndGameScreen(int index) {
        if (index != -1) {
            if (currentNumber[index] == 1) {
                win = 1;
                scorePlayer1++;
                score1.setText(scorePlayer1+"");
            } else if (currentNumber[index] == 2) {
                win = 2;
                scorePlayer2++;
                score2.setText(scorePlayer2+"");
            }
        }

        Intent myIntent = new Intent(this, EndGameActivity.class);
        myIntent.putExtra(EndGameActivity.KEY_WIN, win);
        myIntent.putExtra(EndGameActivity.KEY_NAME1,namePlayer1);
        myIntent.putExtra(EndGameActivity.KEY_NAME2,namePlayer2);
        myIntent.putExtra(EndGameActivity.KEY_SCORE1,scorePlayer1);
        myIntent.putExtra(EndGameActivity.KEY_SCORE2,scorePlayer2);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(myIntent);
            }
        };

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(runnable,500);//delyed by 0.5 second
    }

    private void openMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}