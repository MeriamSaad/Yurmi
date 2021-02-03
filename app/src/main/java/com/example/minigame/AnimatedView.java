package com.example.minigame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class AnimatedView extends View {

    private int width=75;
    private int x,y, xPoint, yPoint;
    private ShapeDrawable boule = new ShapeDrawable();
    private ShapeDrawable point = new ShapeDrawable();

    public void init() {
        this.x = 50;
        this.y = 50;
        this.xPoint = -100;
        this.yPoint = -100;
        Log.d("init", "initializing");
        this.boule = new ShapeDrawable(new OvalShape());
        this.point = new ShapeDrawable(new OvalShape());
    }

    public AnimatedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimatedView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public AnimatedView(Context context) {
        super(context);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.point.getPaint().setColor(0xffff0000);
        this.point.setBounds(this.xPoint, this.yPoint, this.xPoint + this.width, this.yPoint + this.width);
        this.point.draw(canvas);
        this.boule.setBounds(this.x, this.y, this.x + this.width, this.y + this.width);
        this.boule.draw(canvas);
        invalidate();
    }

    public void setX(int x) {
        this.x -= x;
    }
    public void setY(int y){
        this.y += y;
    }

    public float getX(){ return this.x;}
    public float getY(){ return this.y;}

    public void setXPoint(int x) {
        this.xPoint = x;
    }
    public void setYPoint(int y){
        this.yPoint = y;
    }

    public float getXPoint(){ return this.xPoint;}
    public float getYPoint(){ return this.yPoint;}
}
