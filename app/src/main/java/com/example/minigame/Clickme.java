package com.example.minigame;


import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Clickme extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private TextView actual_score, lives, results;
    private Button button, btnReplay, btnQuit;
    private int width, height, btnHeight, score, livesCount, compteurClics;
    private long temps1, temps2, tempsMoyen;
    private Date date1, date2;
    private FrameLayout fr;
    private boolean first;
    private  Clickme activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickme);
        this.activity=this;
        actual_score = (TextView) findViewById(R.id.score);
        lives = (TextView) findViewById(R.id.lives);
        results = (TextView) findViewById(R.id.result);

        btnReplay = (Button) findViewById(R.id.replay);
        btnQuit = (Button) findViewById(R.id.quit);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        fr=(FrameLayout)findViewById(R.id.frameLayout);
        fr.setOnTouchListener(this);

        first = true;
        AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
        myPopup.setTitle("Vous allez jouer à Click me");
        myPopup.setMessage("Appuyez sur Start pour commencer à jouer");
        myPopup.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"C'est parti !!! ", Toast.LENGTH_LONG).show(); }
        });
        myPopup.show();
        play();

    }

    public void play(){
        button.setVisibility(View.VISIBLE);
        results.setVisibility(View.GONE);
        btnReplay.setVisibility(View.GONE);
        btnQuit.setVisibility(View.GONE);

        lives.setText("♥ ♥ ♥");

        livesCount = 3;

        score = 0;
        actual_score.setText(Integer.toString(score));

        compteurClics = 0;
        tempsMoyen = 0;
    }

    public void lost(){
        lives.setText("♡ ♡ ♡");
        button.setVisibility(View.GONE);

        results.setVisibility(View.VISIBLE);
        results.setText("Perdu ! Score : " + score + " points. Temps de réaction moyen : " + Long.toString(tempsMoyen) + " mil.secondes");

        btnReplay.setVisibility(View.VISIBLE);
        btnReplay.setOnClickListener(this);
        btnQuit.setVisibility(View.VISIBLE);
        btnQuit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == button) {
            height = fr.getMeasuredHeight();
            width = fr.getMeasuredWidth();

            btnHeight = (int) (width * 0.15);

            if (first == true) {
                button.setTextColor(Color.parseColor("#FF6200EE"));
                button.setLayoutParams(new FrameLayout.LayoutParams(btnHeight, btnHeight));
                first = false;
                date1 = new Date();
                temps1 = date1.getTime();
            } else {
                button.setX((int) (Math.random() * (width - btnHeight)));
                button.setY((int) (Math.random() * (height - btnHeight)));

                score += 1;
                actual_score.setText(Integer.toString(score));

                date2 = new Date();
                temps2 = date2.getTime();
                compteurClics += 1;
                tempsMoyen = (tempsMoyen * (compteurClics - 1) + (temps2 - temps1)) / (compteurClics);
                temps1 = temps2;
            }
        }
        else if (v == btnReplay){
            play();
        }else if(v == btnQuit){
            Intent intent=new Intent();
            setResult(2,intent);
            finish();//finishing activity
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //Toast.makeText(getApplicationContext(), "♥ ♥ ♥", Toast.LENGTH_SHORT).show();
        if (livesCount == 3){
            lives.setText("♥ ♥ ♡");
            livesCount = 2;
        }
        else if (livesCount == 2){
            lives.setText("♥ ♡ ♡");
            livesCount = 1;
        }
        else {
            lost();
        }
        return false;
    }
}