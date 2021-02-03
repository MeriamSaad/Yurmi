package com.example.minigame.Views;

import android.graphics.Paint;

public class Cercle {
    private int x;
    private int y;
    private Paint dessiner;
    public Cercle (int x, int y, Paint dessiner ){
        this.x=x;
        this.y=y;
        this.dessiner=dessiner;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Paint getDessiner() {
        return dessiner;
    }
    public void setDessiner(){
        this.dessiner=dessiner;
    }
}

