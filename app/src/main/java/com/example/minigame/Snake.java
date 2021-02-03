package com.example.minigame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minigame.Anim.Commencer;
import com.example.minigame.Views.ConstumViews;

import java.util.ArrayList;


public class Snake extends Activity implements View.OnClickListener {
    private ConstumViews cv;
    private Thread thread ;
    private Runnable runnable;
    private ArrayList<Integer> ancienneValeur = new ArrayList<Integer>();
    private  Snake activity;
    private Button button, btnReplay, btnQuit;
    private GestureDetectorCompat mGesture;

    private Commencer c;
    private int gauche;
    private int droite;
    private int haut;
    private int bas;
    private int commencer;
    private int compteur ;
    private int firstTime;
    private int stop ;
    private TextView t;
    private Button b;
    LinearLayout layout ;
    /** Called when the activity is first created. */
    public class MonHandler extends Handler {
        private TextView textView;
        public MonHandler(TextView textView) {
            this.textView = textView;
        }
        public void handleMessage(Message msg){
            textView.setText(msg.getData().getString("clef"));
        }

    }
    public class MonHandler2 extends Handler {
        private Button b;
        public MonHandler2(Button b) {
            this.b = b;
        }
        public void handleMessage(Message msg){
            b.setText(msg.getData().getString("clef2"));
        }

    }


    private MonHandler monHandler;
    private MonHandler2 monHandler2;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake);
        cv  = (ConstumViews)findViewById(R.id.maclasseamoi);
        b = (Button)findViewById(R.id.button);
        t = (TextView)findViewById(R.id.textView);
        t.setText("Appuyez sur commencer");
        t.setTextSize(30);
        layout = (LinearLayout)findViewById(R.id.linearLayout);
        monHandler = new MonHandler(t);
        monHandler2 = new MonHandler2(b);
        this.c=new Commencer(this, this.monHandler, this.monHandler2);
        runnable = c;
        thread = new Thread(runnable);
        thread.start();
        compteur=0;
        gauche =0;
        droite =0;
        haut =0;
        bas =0;
        this.activity=this;
        this.ancienneValeur.add(gauche);
        this.ancienneValeur.add(droite);
        this.ancienneValeur.add(haut);
        this.ancienneValeur.add(bas);
        commencer=0;
        btnReplay = (Button) findViewById(R.id.replay);
        btnQuit = (Button) findViewById(R.id.quit);
        b.setOnClickListener(this);
        mGesture = new GestureDetectorCompat(this, new GestureListener());
        AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
        myPopup.setTitle("Vous allez jouer au snake");
        myPopup.setMessage("Appuyez sur commencer pour debuter le jeu");
        myPopup.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"C'est parti !!! ", Toast.LENGTH_LONG).show(); }
        });
        myPopup.show();
        btnReplay.setVisibility(View.GONE);
        btnQuit.setVisibility(View.GONE);


    }


    public boolean onTouchEvent(MotionEvent e){
        mGesture.onTouchEvent(e);
        return super.onTouchEvent(e);

    }
    private void startAnimation() {
        c =new Commencer(this, monHandler,this.monHandler2);
        runnable = c;
        thread = new Thread(runnable);
        thread.start();

    }

    public void setCv(ConstumViews cv) {
        this.cv = cv;
    }

    public ConstumViews getCv() {
        return cv;
    }
    public LinearLayout getLayout(){
        return layout;
    }
    public int getGauche(){
        return gauche;
    }
    public int getDroite(){
        return droite;
    }
    public int getHaut(){
        return haut;
    }
    public int getBas(){
        return bas;
    }

    public void setT( TextView t) {
        this.t=t;

    }
    public  void ChangerScore(int i){
        this.t.setText("le score est de "+i);
    }

    public TextView getT() {
        return t;
    }

    public int getCommencer() {
        return commencer;
    }

    public void setCommencer(int commencer) {
        this.commencer = commencer;
    }
    public void setCompteur(int compteur){
        this.compteur=compteur;
    }
    public int getCompteur(){
        return compteur;
    }


    public ArrayList<Integer> getAncienneValeur() {
        return ancienneValeur;
    }
    public void setAncienneValeur(int gauche, int droite,int haut, int bas){
        this.gauche=gauche;
        this.droite=droite;
        this.haut=haut;
        this.bas=bas;
    }

    public int getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(int firstTime) {
        this.firstTime = firstTime;
    }
    public int getStop (){
        return  stop;
    }
    public void setStop( int stop){
        this.stop=stop;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button && commencer==0 ) {
            gauche=0;
            droite=0;
            haut =0;
            bas =1;
        commencer++;
        }else {
            if (v.getId() == R.id.button && commencer == 1) {
                stop++;
                commencer = 0;

            }
        }
    }


    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;


        @SuppressLint("ShowToast")
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                    result = true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public void onSwipeRight() {
        this.ancienneValeur.clear();
        this.ancienneValeur.add(gauche);
        this.ancienneValeur.add(droite);
        this.ancienneValeur.add(haut);
        this.ancienneValeur.add(bas);
        this.firstTime=1;
        compteur=1;
        gauche=0;
        droite=1;
        haut =0;
        bas =0;
    }

    public void onSwipeLeft() {
        this.ancienneValeur.clear();
        this.ancienneValeur.add(gauche);
        this.ancienneValeur.add(droite);
        this.ancienneValeur.add(haut);
        this.ancienneValeur.add(bas);
        this.firstTime=1;
        compteur=1;
        gauche=1;
        droite=0;
        haut =0;
        bas =0;

    }

    public void onSwipeTop() {
        this.ancienneValeur.clear();
        this.ancienneValeur.add(gauche);
        this.ancienneValeur.add(droite);
        this.ancienneValeur.add(haut);
        this.ancienneValeur.add(bas);
        compteur=1;
        gauche=0;
        droite=0;
        haut =1;
        bas =0;


    }

    public void onSwipeBottom() {
        this.ancienneValeur.clear();
        this.ancienneValeur.add(gauche);
        this.ancienneValeur.add(droite);
        this.ancienneValeur.add(haut);
        this.ancienneValeur.add(bas);
        compteur=1;
        gauche=0;
        droite=0;
        haut =0;
        bas =1;

    }
    public void perdu(){




    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {



        }
    }


}
