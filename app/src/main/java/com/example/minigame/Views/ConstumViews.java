/*package com.example.snake.Views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Random;

public class ConstumViews extends View {

    private int compteurDroit=0;
    private int compteurGauche=0;
   private int firstTime =0;
    private int x =650;
    private int y =400;
    private int tournerDroit=0;
    private int tournerGauche=0;
    private int descend=0;
    private int monter=0;
    private int xCercleAncienne=x;
    private int yCercleAncienne =y+40;
    private int xCercleAncienne2=xCercleAncienne;
    private int yCercleAncienne2 =yCercleAncienne;
    private int gagner =0;
    private int gagnerCompteur =0;
    private int compteurGagner=0;
    private Random rand = new Random();
    private int xCercle =rand.nextInt(500);
    private int yCercle=rand.nextInt(500);
    private Paint pCercle = new Paint();
    private Paint pPoint = new Paint();
    private Paint pCercle1 = new Paint();
    private Paint pRect2 = new Paint();
    private Paint pRect3 = new Paint();
    private Paint pRect4 = new Paint();
    private ArrayList<Paint> listPoint = new ArrayList<Paint>();
    private ArrayList<Integer> listeValeurX = new ArrayList<Integer>();
    private ArrayList<Integer> listeValeurY = new ArrayList<Integer>();



    private String score = "le score est de 0";


    public ConstumViews(Context context) {
        super(context);
        init(null);
    }

    public ConstumViews(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ConstumViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ConstumViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        pCercle.setColor(Color.GREEN);
        pCercle.setStyle(Paint.Style.FILL);
        pCercle.setAntiAlias(true);
        Random rand = new Random();
        this.xCercle= rand.nextInt(300);
        this.yCercle = rand.nextInt(300);
        this.listPoint.add(pCercle);
        this.listPoint.add(pCercle1);
        this.listPoint.add(pRect2);
        this.listPoint.add(pRect3);
        this.listPoint.add(pRect4);

        /*this.xRectleft=x-40;
        this.yRecttop= y-100;
        this.xRectright=x+40;
        this.yRectbotton=y;*/




/*
        init(attrs);
    }

    private void init( @Nullable AttributeSet set){

    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y){
        this.y=y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public void setxCercle(int xCercle) {
        this.xCercle = xCercle;
    }
    public void setyCercle(int yCercle){
        this.yCercle=yCercle;
    }

    public int getxCercle() {
        return xCercle;
    }

    public int getyCercle() {
        return yCercle;
    }

    public void setScore(String newscore) {
        this.score = newscore;
    }

    public String getScore() {
        return score;
    }

    public int getGagner() {
        return gagner;
    }
    public void setGagner(int gagner){
        this.gagner=gagner;
    }

    public int getCompteurGagner() {
        return compteurGagner;
    }
    public void setCompteurGagner(int compteurGagner){
        this.compteurGagner=compteurGagner;
    }

    public int getTournerDroit() {
        return tournerDroit;
    }

    public int getTournerGauche() {
        return tournerGauche;
    }

    public int getDescend() {
        return descend;
    }

    public int getMonter() {
        return monter;
    }

    public void setDescend(int descend) {
        this.descend = descend;
    }

    public void setMonter(int monter) {
        this.monter = monter;
    }

    public void setTournerDroit(int tournerDroit) {
        this.tournerDroit = tournerDroit;
    }

    public void setTournerGauche(int tournerGauche) {
        this.tournerGauche = tournerGauche;
    }

    protected void onDraw(Canvas c) {
        super.onDraw(c);
       if(firstTime==1){
           listeValeurX.add(x);
           listeValeurX.add(xCercleAncienne);
           listeValeurX.add(xCercleAncienne2);
           listeValeurY.add(y);
           listeValeurY.add(yCercleAncienne);
           listeValeurY.add(yCercleAncienne2);

       }
        pCercle.setColor(Color.RED);
        c.drawCircle(x , y, 40, pCercle);
        pPoint.setColor(Color.RED);
        c.drawCircle(xCercle , yCercle, 40, pPoint);
        c.drawCircle(xCercleAncienne,yCercleAncienne,40,pCercle1);

        //c.drawRect(this.xRectleft,this.yRecttop,this.xRectright,this.yRectbotton, pRect1);
       if(gagner==1){

           c.drawCircle(xCercleAncienne2,yCercleAncienne2,40,pCercle1);

        }


    }
    public void movehigh(){

        if(y-20>0) {
            y = y - 10;
            xCercleAncienne=x;
            yCercleAncienne =y+40;
            xCercleAncienne2=xCercleAncienne;
            yCercleAncienne2 =yCercleAncienne+40;

        }
    }
    public void movebas(int heigth){
        if(y+50<heigth) {
            y = y + 10;
           xCercleAncienne=x;
            yCercleAncienne =y-40;
            xCercleAncienne2=xCercleAncienne;
            yCercleAncienne2 =yCercleAncienne-40;

        }else {
            y=heigth-50;
        }
    }
    public void moveright(int width){
        if(x+50< width) {
            if(tournerDroit==0) {

                xCercleAncienne = x - 40;
                yCercleAncienne = y;
                xCercleAncienne2 = xCercleAncienne - 40;
                yCercleAncienne2 = yCercleAncienne;
                x = x + 10;
            }
            if(tournerDroit==1){
                xCercleAncienne2=xCercleAncienne;
                yCercleAncienne2 =yCercleAncienne;
                xCercleAncienne=x;
                yCercleAncienne =y;
               x = x + 10;
                Log.d("cration" ,"je rentre1: ");

            }
            if(tournerDroit==2){
               xCercleAncienne2=xCercleAncienne;
                yCercleAncienne2 =yCercleAncienne;
                xCercleAncienne=x-40;
                yCercleAncienne =y;
                x = x + 10;
                Log.d("cration" ,"je rentre2: ");
                tournerDroit=0;
            }


        }else {
            x=width-50;
        }
    }
    public void moveleft(){
        if (x-20>0) {
            x = x - 10;
            xCercleAncienne=x+40;
            yCercleAncienne =y;
            xCercleAncienne2=xCercleAncienne+40;
            yCercleAncienne2 =yCercleAncienne;

        }
    }



}*/
package com.example.minigame.Views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.sqrt;

public class ConstumViews extends View {

    private int compteurDroit=0;
    private int compteurGauche=0;
    private int firstTime =0;
    private int x =600;
    private int y =400;
    private boolean partiePerdu =false;
    private int gagner =0;
    private int gagnerCompteur =0;
    private int compteurGagner=0;
    private Random rand = new Random();
    private int xCercle =rand.nextInt(500);
    private int yCercle=rand.nextInt(500);
    private Paint pCercle = new Paint();
    private Paint pPoint = new Paint();
    private Paint pCercle1 = new Paint();
    private Paint pRect2 = new Paint();

    private ArrayList<Cercle> listCercle = new ArrayList<Cercle>();
    Cercle c1 = new Cercle(x,y,pCercle);
    Cercle c2 = new Cercle(0,0,pCercle1);


    private String score = "le score est de 0";


    public ConstumViews(Context context) {
        super(context);
        init(null);
    }

    public ConstumViews(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ConstumViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ConstumViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        pCercle.setColor(Color.GREEN);
        pCercle.setStyle(Paint.Style.FILL);
        pCercle.setAntiAlias(true);
        Random rand = new Random();
        this.xCercle= rand.nextInt(300);
        this.yCercle = rand.nextInt(300);

        init(attrs);
    }

    private void init( @Nullable AttributeSet set){

    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y){
        this.y=y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public void setxCercle(int xCercle) {
        this.xCercle = xCercle;
    }
    public void setyCercle(int yCercle){
        this.yCercle=yCercle;
    }

    public int getxCercle() {
        return xCercle;
    }

    public int getyCercle() {
        return yCercle;
    }

    public void setScore(String newscore) {
        this.score = newscore;
    }

    public String getScore() {
        return score;
    }

    public int getGagner() {
        return gagner;
    }
    public void setGagner(int gagner){
        this.gagner=gagner;
    }

    public int getCompteurGagner() {
        return compteurGagner;
    }
    public void setCompteurGagner(int compteurGagner){
        this.compteurGagner=compteurGagner;
    }

    public void setFirstTime(int firstTime) {
        this.firstTime = firstTime;
    }

    public int getFirstTime() {
        return firstTime;
    }

    public boolean isPerdu() {
        return partiePerdu;
    }

    public void setPerdu(boolean perdu) {
        this.partiePerdu = perdu;
    }

    public ArrayList<Cercle> getListCercle() {
        return listCercle;
    }
    public void setListCercle (ArrayList<Cercle> c ){
        this.listCercle=c;
    }
    protected void onDraw(Canvas c) {
        super.onDraw(c);
        if(firstTime==0){

            listCercle.add(c1);
            for (int i =0; i<1000; i++){
                listCercle.add(new Cercle(0,0,pRect2));
            }


            firstTime=1;


        }
        pPoint.setColor(Color.RED);
        c.drawCircle(xCercle , yCercle, 40, pPoint);
        if(gagner==0){
            c.drawCircle(listCercle.get(0).getX(), listCercle.get(0).getY(), 40, listCercle.get(0).getDessiner());
        }else {
            for( int i = gagner; i>0; i-- ){
                listCercle.get(i).setX(listCercle.get(i - 1).getX());
                listCercle.get(i).setY(listCercle.get(i - 1).getY());
            }
            for (int i =0; i<=gagner;i++){

                c.drawCircle(listCercle.get(i).getX(),listCercle.get(i).getY(),40, listCercle.get(0).getDessiner());
            }

        }



    }
    public void Ajouter(){
        listCercle.add(c2);
    }
    public void movehigh(){

        if(y-20>0) {
            listCercle.get(0).setY(listCercle.get(0).getY()-40);

        }
    }
    public void movebas(int heigth){
        if(y+50<heigth) {

            listCercle.get(0).setY(listCercle.get(0).getY() + 40);

            }else{
                y = heigth - 50;
            }
        }
    public void moveright( int width){
        if(x+50< width) {

            listCercle.get(0).setX(listCercle.get(0).getX()+40);

        }else {
            x=width-50;
        }
    }
    public void moveleft(){
        if (x-20>0) {
            listCercle.get(0).setX(listCercle.get(0).getX()-40);


        }
    }
    public void VousAvezperdu (){
       for (int i = 4; i<gagner;i++){

            if (sqrt((listCercle.get(0).getX()-listCercle.get(i).getX())*(listCercle.get(0).getX()-listCercle.get(i).getX())+((listCercle.get(0).getY()-listCercle.get(i).getY()))*(listCercle.get(0).getY()-listCercle.get(i).getY()))<80) {
                partiePerdu=true;


            }

        }

    }



}
