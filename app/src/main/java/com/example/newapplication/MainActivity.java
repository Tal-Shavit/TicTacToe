package com.example.newapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton button;
    private EditText editTextName1;
    private EditText editTextName2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();
    }

    private void initViews() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(editTextName1.getText().toString().equals("")) && !(editTextName2.getText().toString().equals(""))){
                    String namePlayer1 = editTextName1.getText().toString();
                    String namePlayer2 = editTextName2.getText().toString();
                    opeGame(namePlayer1, namePlayer2);
                }
            }
        });


    }

    private void opeGame(String namePlayer1, String namePlayer2) {
        Intent myIntent = new Intent(this, GameActivity.class);
        myIntent.putExtra(GameActivity.KEY_NAME1,namePlayer1);
        myIntent.putExtra(GameActivity.KEY_NAME2,namePlayer2);
        startActivity(myIntent);
        finish();
    }

    private void findViews() {
        button = findViewById(R.id.button);
        editTextName1 = findViewById(R.id.editTextName1);
        editTextName2 = findViewById(R.id.editTextName2);
    }
}