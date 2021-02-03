package com.example.minigame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minigame.AnimatedView;
import com.example.minigame.R;

import java.util.Date;

public class Rollinball extends Activity implements SensorEventListener, View.OnClickListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView actual_score, lives, results;
    private Button button, btnReplay, btnQuit;
    private int width, height, score, livesCount;
    private FrameLayout fr;
    private boolean first, state;
    private Rollinball activity;

    AnimatedView animatedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rollinball);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        actual_score = (TextView) findViewById(R.id.score);
        lives = (TextView) findViewById(R.id.lives);
        results = (TextView) findViewById(R.id.result);
        this.activity=this;
        btnReplay = (Button) findViewById(R.id.replay);
        btnQuit = (Button) findViewById(R.id.quit);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        fr=(FrameLayout)findViewById(R.id.frameLayout);

        first = true;

        animatedView = new AnimatedView(this);
        animatedView  = (AnimatedView)findViewById(R.id.animatedView);
        AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
        myPopup.setTitle("Vous allez jouer à Rollin ball");
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

        animatedView.init();

        state = false;
        first=true;
    }

    public void lost(){
        if (livesCount == 3){
            lives.setText("♥ ♥ ♡");
            livesCount = 2;
            reposition();
        }
        else if (livesCount == 2){
            lives.setText("♥ ♡ ♡");
            livesCount = 1;
            reposition();
        }
        else {
            lives.setText("♡ ♡ ♡");

            results.setVisibility(View.VISIBLE);
            results.setText("Perdu ! Score : " + score + " points.");

            animatedView.setXPoint(-100);
            animatedView.setYPoint(-100);

            animatedView.setX(-1000);
            animatedView.setY(-1000);

            btnReplay.setVisibility(View.VISIBLE);
            btnReplay.setOnClickListener(this);
            btnQuit.setVisibility(View.VISIBLE);
            btnQuit.setOnClickListener(this);

            state=false;
        }
    }

    private void reposition() {
        if(animatedView.getX() < 10){
            animatedView.setX(-15);
        }
        else if(animatedView.getX() > width-85){
            animatedView.setX(15);
        }
        else if(animatedView.getY() < 10){
            animatedView.setY(15);
        }
        else if(animatedView.getY() > height-85){
            animatedView.setY(-15);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        height = fr.getMeasuredHeight();
        width = fr.getMeasuredWidth();

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if(state){
                animatedView.setX((int) event.values[0]);
                animatedView.setY((int) event.values[1]);
                if((animatedView.getX() < 0) || (animatedView.getX() > width-75) || (animatedView.getY() < 0) || (animatedView.getY() > height-75)){
                    lost();
                }
                if((animatedView.getX()> animatedView.getXPoint()-5) && (animatedView.getX()< animatedView.getXPoint()+5) && (animatedView.getY()> animatedView.getYPoint()-5) && (animatedView.getY()> animatedView.getYPoint()-5)){
                    animatedView.setXPoint((int) (Math.random() * (width-75)));
                    animatedView.setYPoint((int) (Math.random() * (height-75)));
                    score += 1;
                    actual_score.setText(Integer.toString(score));
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v == button) {
            if (first == true) {
                button.setVisibility(View.GONE);
                animatedView.setXPoint((int) (Math.random() * (width-75)));
                animatedView.setYPoint((int) (Math.random() * (height-75)));
                first = false;
                state=true;
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
}