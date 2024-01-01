package com.example.newapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private LottieAnimationView startGameLottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startGameLottie = findViewById(R.id.startGameLottie);
        moveSplash(startGameLottie);
    }

    public void moveSplash(View view){
        view.setVisibility(View.VISIBLE);
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int h = displayMetrics.heightPixels;
        view.setY(-h);
        view.setScaleY(1.0f);
        view.setScaleX(1.0f);
        view.animate().scaleY(1.5f).scaleX(1.5f).translationY(0).setDuration(4500).setInterpolator(new AccelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                openMainScreen();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    private void openMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}