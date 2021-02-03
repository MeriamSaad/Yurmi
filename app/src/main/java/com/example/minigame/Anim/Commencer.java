package com.example.minigame.Anim;

import android.os.Bundle;
import android.os.Message;

import com.example.minigame.Snake;


import java.util.Random;

import static java.lang.Math.sqrt;

public class Commencer implements Runnable{
    private Snake c;
    private Snake.MonHandler monHandler;
    private Snake.MonHandler2 monHandler2;

    public Commencer(Snake c, Snake.MonHandler monHandler, Snake.MonHandler2 monHandler2){
        this.c=c;
        this.monHandler=monHandler;
        this.monHandler2=monHandler2;
    }



    @Override
    public void run() {
        boolean vrai = true;

        c.getCv().invalidate();
        int compteur=0;
        int score =0;
        int perdu =1;
        Message msg= new Message();
        Message msg2= new Message();
        Bundle bundle=new Bundle();
        Bundle bundle2=new Bundle();
        while (true) {

            if(c.getCommencer()==1) {
                msg=monHandler.obtainMessage();
                bundle.putString("clef", "Votre score est : ");
                msg.setData(bundle);
                monHandler.sendMessage(msg);
                msg2=monHandler2.obtainMessage();
                bundle2.putString("clef2", "stop ");
                msg2.setData(bundle2);
                monHandler2.sendMessage(msg2);

                vrai =true;
                while (vrai) {

                    if (compteur >= 100) {
                        compteur = 0;
                        Random rand = new Random();
                        c.getCv().setxCercle(genererInt(50,c.getLayout().getMeasuredWidth()));

                        c.getCv().setyCercle(genererInt(50, c.getLayout().getMeasuredHeight()));


                    }

                    if(c.getCv().getListCercle().get(0).getY()-c.getLayout().getMeasuredHeight() >0){
                        c.getCv().getListCercle().get(0).setY(0);
                    }
                    if(c.getCv().getListCercle().get(0).getY() <0){
                        c.getCv().getListCercle().get(0).setY(c.getLayout().getMeasuredHeight());
                    }
                    if(c.getCv().getListCercle().get(0).getX() - c.getLayout().getMeasuredWidth()>0){
                        c.getCv().getListCercle().get(0).setX(0);
                    }
                    if(c.getCv().getListCercle().get(0).getX() <0){
                        c.getCv().getListCercle().get(0).setX(c.getLayout().getMeasuredWidth());
                    }
                    c.getCv().VousAvezperdu();
                    if(c.getCv().isPerdu()==true){

                        msg2=monHandler2.obtainMessage();
                        bundle2.putString("clef2", "Commencer");
                        msg2.setData(bundle2);
                        monHandler2.sendMessage(msg2);

                        msg=monHandler.obtainMessage();
                        bundle.putString("clef", "Vous avez perdu...");
                        msg.setData(bundle);
                        monHandler.sendMessage(msg);
                        c.getCv().getListCercle().get(0).setX(600);
                        c.getCv().getListCercle().get(0).setX(400);
                        c.getCv().setGagner(0);
                        c.setCommencer(0);
                        score=0;
                        perdu--;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                            ie.printStackTrace();
                        }
                        msg=monHandler.obtainMessage();
                        bundle.putString("clef", "Appuyez sur commencer");
                        msg.setData(bundle);
                        monHandler.sendMessage(msg);
                        vrai = false;

                        c.getCv().setPerdu(false);
                    }
                    if (perdu==0) {
                        perdu ++;
                        c.perdu();




                    }
                    if (sqrt((c.getCv().getListCercle().get(0).getX()-c.getCv().getxCercle())*(c.getCv().getListCercle().get(0).getX()-c.getCv().getxCercle())+(c.getCv().getListCercle().get(0).getY()-c.getCv().getyCercle())*(c.getCv().getListCercle().get(0).getY()-c.getCv().getyCercle()))<80 ) {
                        score++;
                        //c.getCv().Ajouter();
                        c.getCv().setGagner(score);
                        msg=monHandler.obtainMessage();
                        bundle.putString("clef", "le score est :"+ score);
                        msg.setData(bundle);
                        monHandler.sendMessage(msg);

                        compteur = 110;


                    }
                  if(c.getStop()==1){
                        msg=monHandler.obtainMessage();
                        bundle.putString("clef", "La partie est arrété");
                        msg.setData(bundle);
                        monHandler.sendMessage(msg);
                        c.setStop(0);
                        c.getCv().getListCercle().get(0).setX(600);
                        c.getCv().getListCercle().get(0).setX(400);
                        c.getCv().setGagner(0);
                        c.setCommencer(0);
                        score=0;
                        perdu--;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                            ie.printStackTrace();
                        }
                        msg=monHandler.obtainMessage();
                        bundle.putString("clef", "Appuyez sur commencer");
                        msg.setData(bundle);
                        monHandler.sendMessage(msg);
                        vrai = false;

                        c.getCv().setPerdu(false);
                    }

                    if (c.getBas() == 1) {
                        if(c.getCompteur()<=3 && c.getCompteur()>0 && c.getAncienneValeur().get(2)==1){
                            c.getCv().moveright(c.getLayout().getMeasuredWidth());
                            c.getCv().invalidate();
                            int cpt =c.getCompteur();
                            cpt++;
                            c.setCompteur(cpt);
                            if(c.getCompteur()==3) c.setCompteur(0);

                        }else{
                            c.getCv().movebas(c.getLayout().getMeasuredHeight());
                            c.getCv().invalidate();
                        }

                    }
                    if (c.getDroite() == 1) {

                        if(c.getCompteur()<=3 && c.getCompteur()>0 && c.getAncienneValeur().get(0)==1){
                            c.getCv().movehigh();
                            c.getCv().invalidate();
                            int cpt =c.getCompteur();
                            cpt++;
                            c.setCompteur(cpt);
                            if(c.getCompteur()==3) c.setCompteur(0);

                        }else{
                            c.getCv().moveright(c.getLayout().getMeasuredWidth());
                            c.getCv().invalidate();
                        }


                    }
                    if (c.getHaut() == 1) {

                        if(c.getCompteur()<=3 && c.getCompteur()>0 && c.getAncienneValeur().get(3)==1){
                            c.getCv().moveleft();
                            c.getCv().invalidate();
                            int cpt =c.getCompteur();
                            cpt++;
                            c.setCompteur(cpt);
                            if(c.getCompteur()==3) c.setCompteur(0);

                        }else{
                            c.getCv().movehigh();
                            c.getCv().invalidate();
                        }
                    }
                    if (c.getGauche() == 1) {
                        if(c.getCompteur()<=3 && c.getCompteur()>0 && c.getAncienneValeur().get(1)==1){
                            c.getCv().movebas(c.getLayout().getMeasuredHeight());
                            c.getCv().invalidate();
                            int cpt =c.getCompteur();
                            cpt++;
                            c.setCompteur(cpt);
                            if(c.getCompteur()==3) c.setCompteur(0);

                        }else{
                            c.getCv().moveleft();
                            c.getCv().invalidate();
                        }

                    }


                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        ie.printStackTrace();
                    }
                    compteur++;
                }
            }else{

            }

        }
    }
    int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb = borneInf+random.nextInt(borneSup-borneInf);
        return nb;
    }
}
