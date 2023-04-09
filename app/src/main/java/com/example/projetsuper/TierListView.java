package com.example.projetsuper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TierListView extends SurfaceView implements SurfaceHolder.Callback {

    private TierListLoopThread gameLoopThread;
    private TierListImageHero hero1, hero2, hero3, hero4, hero5, hero6, hero7, hero8, hero9, hero10;
    private TierListImage image;
    private int hEcran, wEcran;

    /**
     * fonction qui créée la surface dessin
     */
    public TierListView(Context context, int img1,int img2,int img3,int img4, int img5, int img6, int img7, int img8, int img9, int img10) {
        super(context);
        getHolder().addCallback(this);
        gameLoopThread = new TierListLoopThread(this);

        hero1 = new TierListImageHero(this.getContext(),img1);
        hero2 = new TierListImageHero(this.getContext(),img2);
        hero3 = new TierListImageHero(this.getContext(),img3);
        hero4 = new TierListImageHero(this.getContext(),img4);
        hero5 = new TierListImageHero(this.getContext(),img5);
        hero6 = new TierListImageHero(this.getContext(),img6);
        hero7 = new TierListImageHero(this.getContext(),img7);
        hero8 = new TierListImageHero(this.getContext(),img8);
        hero9 = new TierListImageHero(this.getContext(),img9);
        hero10 = new TierListImageHero(this.getContext(),img10);
        image = new TierListImage(this.getContext());
    }

    /**
     * fonction qui dessine l'ecran d'affichage
     */
    public void doDraw(Canvas canvas) {
        if(canvas==null) {return;}

        canvas.drawColor(Color.GRAY);

        hero1.draw(canvas);
        hero2.draw(canvas);
        hero3.draw(canvas);
        hero4.draw(canvas);
        hero5.draw(canvas);
        hero6.draw(canvas);
        hero7.draw(canvas);
        hero8.draw(canvas);
        hero9.draw(canvas);
        hero10.draw(canvas);
        image.draw(canvas);
    }

    public void update() {
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if(gameLoopThread.getState()==Thread.State.TERMINATED) {
            gameLoopThread=new TierListLoopThread(this);
        }
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameLoopThread.setRunning(false);
        while (retry) {
            try {
                gameLoopThread.join();
                retry = false;
            }
            catch (InterruptedException e) {}
        }
    }

    /**
     * fonction qui gère les touches sur l'écran
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currentX = (int)event.getX();
        int currentY = (int)event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                if(currentX >= hero1.getX() &&
                        currentX <= hero1.getX()+hero1.getImgW() &&
                        currentY >= hero1.getY() && currentY <= hero1.getY()+hero1.getImgH() ) {
                    hero1.setMove(false);
                }
                if(currentX >= hero2.getX() &&
                        currentX <= hero2.getX()+hero2.getImgW() &&
                        currentY >= hero2.getY() && currentY <= hero2.getY()+hero2.getImgH() ) {
                    hero2.setMove(false);
                }
                if(currentX >= hero3.getX() &&
                        currentX <= hero3.getX()+hero3.getImgW() &&
                        currentY >= hero3.getY() && currentY <= hero3.getY()+hero3.getImgH() ) {
                    hero3.setMove(false);
                }
                if(currentX >= hero4.getX() &&
                        currentX <= hero4.getX()+hero4.getImgW() &&
                        currentY >= hero4.getY() && currentY <= hero4.getY()+hero4.getImgH() ) {
                    hero4.setMove(false);
                }
                if(currentX >= hero5.getX() &&
                        currentX <= hero5.getX()+hero5.getImgW() &&
                        currentY >= hero5.getY() && currentY <= hero5.getY()+hero5.getImgH() ) {
                    hero5.setMove(false);
                }
                if(currentX >= hero6.getX() &&
                        currentX <= hero6.getX()+hero6.getImgW() &&
                        currentY >= hero6.getY() && currentY <= hero6.getY()+hero6.getImgH() ) {
                    hero6.setMove(false);
                }
                if(currentX >= hero7.getX() &&
                        currentX <= hero7.getX()+hero7.getImgW() &&
                        currentY >= hero7.getY() && currentY <= hero7.getY()+hero7.getImgH() ) {
                    hero7.setMove(false);
                }
                if(currentX >= hero8.getX() &&
                        currentX <= hero8.getX()+hero8.getImgW() &&
                        currentY >= hero8.getY() && currentY <= hero8.getY()+hero8.getImgH() ) {
                    hero8.setMove(false);
                }
                if(currentX >= hero9.getX() &&
                        currentX <= hero9.getX()+hero9.getImgW() &&
                        currentY >= hero9.getY() && currentY <= hero9.getY()+hero9.getImgH() ) {
                    hero9.setMove(false);
                }
                if(currentX >= hero10.getX() &&
                        currentX <= hero10.getX()+hero10.getImgW() &&
                        currentY >= hero10.getY() && currentY <= hero10.getY()+hero10.getImgH() ) {
                    hero10.setMove(false);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                int decalageX = -hero1.getImgW()/2;
                int decalageY = -hero1.getImgH()/2;

                if(!hero1.isMoving()) {
                    hero1.setX(currentX+decalageX);
                    hero1.setY(currentY+decalageY);
                }
                if(!hero2.isMoving()) {
                    hero2.setX(currentX+decalageX);
                    hero2.setY(currentY+decalageY);
                }
                if(!hero3.isMoving()) {
                    hero3.setX(currentX+decalageX);
                    hero3.setY(currentY+decalageY);
                }
                if(!hero4.isMoving()) {
                    hero4.setX(currentX+decalageX);
                    hero4.setY(currentY+decalageY);
                }
                if(!hero5.isMoving()) {
                    hero5.setX(currentX+decalageX);
                    hero5.setY(currentY+decalageY);
                }
                if(!hero6.isMoving()) {
                    hero6.setX(currentX+decalageX);
                    hero6.setY(currentY+decalageY);
                }
                if(!hero7.isMoving()) {
                    hero7.setX(currentX+decalageX);
                    hero7.setY(currentY+decalageY);
                }
                if(!hero8.isMoving()) {
                    hero8.setX(currentX+decalageX);
                    hero8.setY(currentY+decalageY);
                }
                if(!hero9.isMoving()) {
                    hero9.setX(currentX+decalageX);
                    hero9.setY(currentY+decalageY);
                }
                if(!hero10.isMoving()) {
                    hero10.setX(currentX+decalageX);
                    hero10.setY(currentY+decalageY);
                }
                break;

            case MotionEvent.ACTION_UP:
                if(0<hero1.getCentreY() && hero1.getCentreY()<hEcran/7){
                    hero1.setY(0);
                }
                if(hEcran/7<hero1.getCentreY() && hero1.getCentreY()<hEcran/7*2){
                    hero1.setY(hEcran/7);
                }
                if(hEcran/7*2<hero1.getCentreY() && hero1.getCentreY()<hEcran/7*3){
                    hero1.setY(hEcran/7*2);
                }
                if(hEcran/7*3<hero1.getCentreY() && hero1.getCentreY()<hEcran/7*4){
                    hero1.setY(hEcran/7*3);
                }
                if(hEcran/7*4<hero1.getCentreY() && hero1.getCentreY()<hEcran/7*5){
                    hero1.setY(hEcran/7*4);
                }
                if(0<hero2.getCentreY() && hero2.getCentreY()<hEcran/7){
                    hero2.setY(0);
                }
                if(hEcran/7<hero2.getCentreY() && hero2.getCentreY()<hEcran/7*2){
                    hero2.setY(hEcran/7);
                }
                if(hEcran/7*2<hero2.getCentreY() && hero2.getCentreY()<hEcran/7*3){
                    hero2.setY(hEcran/7*2);
                }
                if(hEcran/7*3<hero2.getCentreY() && hero2.getCentreY()<hEcran/7*4){
                    hero2.setY(hEcran/7*3);
                }
                if(hEcran/7*4<hero2.getCentreY() && hero2.getCentreY()<hEcran/7*5){
                    hero2.setY(hEcran/7*4);
                }
                if(0<hero3.getCentreY() && hero3.getCentreY()<hEcran/7){
                    hero3.setY(0);
                }
                if(hEcran/7<hero3.getCentreY() && hero3.getCentreY()<hEcran/7*2){
                    hero3.setY(hEcran/7);
                }
                if(hEcran/7*2<hero3.getCentreY() && hero3.getCentreY()<hEcran/7*3){
                    hero3.setY(hEcran/7*2);
                }
                if(hEcran/7*3<hero3.getCentreY() && hero3.getCentreY()<hEcran/7*4){
                    hero3.setY(hEcran/7*3);
                }
                if(hEcran/7*4<hero3.getCentreY() && hero3.getCentreY()<hEcran/7*5){
                    hero3.setY(hEcran/7*4);
                }
                if(0<hero4.getCentreY() && hero4.getCentreY()<hEcran/7){
                    hero4.setY(0);
                }
                if(hEcran/7<hero4.getCentreY() && hero4.getCentreY()<hEcran/7*2){
                    hero4.setY(hEcran/7);
                }
                if(hEcran/7*2<hero4.getCentreY() && hero4.getCentreY()<hEcran/7*3){
                    hero4.setY(hEcran/7*2);
                }
                if(hEcran/7*3<hero4.getCentreY() && hero4.getCentreY()<hEcran/7*4){
                    hero4.setY(hEcran/7*3);
                }
                if(hEcran/7*4<hero4.getCentreY() && hero4.getCentreY()<hEcran/7*5){
                    hero4.setY(hEcran/7*4);
                }
                if(0<hero5.getCentreY() && hero5.getCentreY()<hEcran/7){
                    hero5.setY(0);
                }
                if(hEcran/7<hero5.getCentreY() && hero5.getCentreY()<hEcran/7*2){
                    hero5.setY(hEcran/7);
                }
                if(hEcran/7*2<hero5.getCentreY() && hero5.getCentreY()<hEcran/7*3){
                    hero5.setY(hEcran/7*2);
                }
                if(hEcran/7*3<hero5.getCentreY() && hero5.getCentreY()<hEcran/7*4){
                    hero5.setY(hEcran/7*3);
                }
                if(hEcran/7*4<hero5.getCentreY() && hero5.getCentreY()<hEcran/7*5){
                    hero5.setY(hEcran/7*4);
                }
                if(0<hero6.getCentreY() && hero6.getCentreY()<hEcran/7){
                    hero6.setY(0);
                }
                if(hEcran/7<hero6.getCentreY() && hero6.getCentreY()<hEcran/7*2){
                    hero6.setY(hEcran/7);
                }
                if(hEcran/7*2<hero6.getCentreY() && hero6.getCentreY()<hEcran/7*3){
                    hero6.setY(hEcran/7*2);
                }
                if(hEcran/7*3<hero6.getCentreY() && hero6.getCentreY()<hEcran/7*4){
                    hero6.setY(hEcran/7*3);
                }
                if(hEcran/7*4<hero6.getCentreY() && hero6.getCentreY()<hEcran/7*5){
                    hero6.setY(hEcran/7*4);
                }
                if(0<hero7.getCentreY() && hero7.getCentreY()<hEcran/7){
                    hero7.setY(0);
                }
                if(hEcran/7<hero7.getCentreY() && hero7.getCentreY()<hEcran/7*2){
                    hero7.setY(hEcran/7);
                }
                if(hEcran/7*2<hero7.getCentreY() && hero7.getCentreY()<hEcran/7*3){
                    hero7.setY(hEcran/7*2);
                }
                if(hEcran/7*3<hero7.getCentreY() && hero7.getCentreY()<hEcran/7*4){
                    hero7.setY(hEcran/7*3);
                }
                if(hEcran/7*4<hero7.getCentreY() && hero7.getCentreY()<hEcran/7*5){
                    hero7.setY(hEcran/7*4);
                }
                if(0<hero8.getCentreY() && hero8.getCentreY()<hEcran/7){
                    hero8.setY(0);
                }
                if(hEcran/7<hero8.getCentreY() && hero8.getCentreY()<hEcran/7*2){
                    hero8.setY(hEcran/7);
                }
                if(hEcran/7*2<hero8.getCentreY() && hero8.getCentreY()<hEcran/7*3){
                    hero8.setY(hEcran/7*2);
                }
                if(hEcran/7*3<hero8.getCentreY() && hero8.getCentreY()<hEcran/7*4){
                    hero8.setY(hEcran/7*3);
                }
                if(hEcran/7*4<hero8.getCentreY() && hero8.getCentreY()<hEcran/7*5){
                    hero8.setY(hEcran/7*4);
                }
                if(0<hero9.getCentreY() && hero9.getCentreY()<hEcran/7){
                    hero9.setY(0);
                }
                if(hEcran/7<hero9.getCentreY() && hero9.getCentreY()<hEcran/7*2){
                    hero9.setY(hEcran/7);
                }
                if(hEcran/7*2<hero9.getCentreY() && hero9.getCentreY()<hEcran/7*3){
                    hero9.setY(hEcran/7*2);
                }
                if(hEcran/7*3<hero9.getCentreY() && hero9.getCentreY()<hEcran/7*4){
                    hero9.setY(hEcran/7*3);
                }
                if(hEcran/7*4<hero9.getCentreY() && hero9.getCentreY()<hEcran/7*5){
                    hero9.setY(hEcran/7*4);
                }
                if(0<hero10.getCentreY() && hero10.getCentreY()<hEcran/7){
                    hero10.setY(0);
                }
                if(hEcran/7<hero10.getCentreY() && hero10.getCentreY()<hEcran/7*2){
                    hero10.setY(hEcran/7);
                }
                if(hEcran/7*2<hero10.getCentreY() && hero10.getCentreY()<hEcran/7*3){
                    hero10.setY(hEcran/7*2);
                }
                if(hEcran/7*3<hero10.getCentreY() && hero10.getCentreY()<hEcran/7*4){
                    hero10.setY(hEcran/7*3);
                }
                if(hEcran/7*4<hero10.getCentreY() && hero10.getCentreY()<hEcran/7*5){
                    hero10.setY(hEcran/7*4);
                }
                hero1.setMove(true);
                hero2.setMove(true);
                hero3.setMove(true);
                hero4.setMove(true);
                hero5.setMove(true);
                hero6.setMove(true);
                hero7.setMove(true);
                hero8.setMove(true);
                hero9.setMove(true);
                hero10.setMove(true);
        }

        return true;
    }

    /**
     * qui prends la taille de l'écran
     */
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {
        hEcran = h;
        wEcran = w;
        hero1.resize(w,h,0,5);
        hero2.resize(w,h,1,5);
        hero3.resize(w,h,2,5);
        hero4.resize(w,h,3,5);
        hero5.resize(w,h,4,5);
        hero6.resize(w,h,0,6);
        hero7.resize(w,h,1,6);
        hero8.resize(w,h,2,6);
        hero9.resize(w,h,3,6);
        hero10.resize(w,h,4,6);
        image.resize(w,h);
    }
}