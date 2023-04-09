package com.example.projetsuper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

class TierListImageHero
{

    /**
     * Instanciation de l'image du hero et de ses coordonnées et
     * instanciation de la taille de l'écran
     */
    int img;
    private BitmapDrawable imgHero=null;
    private int x, y;

    private int imgW, imgH;
    private int wEcran,hEcran;
    private boolean move = true;

    private static final int INCREMENT = 5;
    private int speedX=INCREMENT, speedY=INCREMENT;

    private final Context mContext;

    public TierListImageHero(final Context c, int img)
    {
        this.img = img;
        mContext=c;
    }

    /**
     * on attribue à l'objet "imgH" l'image passée en paramètre avec w et h qui sont sa taille
     */
    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h)
    {
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }

    /**
     * fonction qui nous indique si une image bouge ou non
     */
    public boolean isMoving() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    /**
     * on redimensionne l'image selon la largeur/hauteur de l'écran
     */
    public void resize(int wScreen, int hScreen, int nb,int mb) {
        wEcran=wScreen;
        hEcran=hScreen;

        if(wScreen>(hScreen/7)*0.75*5) {
            imgH = hScreen / 7;
            imgW = (int) (imgH * 0.75);
        }
        else {
            imgW = wScreen/5;
            imgH = (int) (imgW*1.33);
        }


        int H = hScreen/7;
        int W = wScreen/5;
        x = W*nb;
        y = H*mb;
        imgHero = setImage(mContext,img,imgW,imgH);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getImgW() {
        return imgW;
    }

    public int getImgH() {
        return imgH;
    }

    /**
     * fonction qui déplace la balle en détectant les bords de l'ecran
     */
    public void moveWithCollisionDetection()
    {
        if(!move) {return;}
        x+=speedX;
        y+=speedY;

        if(x+imgW > wEcran) {speedX=0;}

        if(y+imgH > hEcran) {speedY=0;}

        if(x<0) {speedX=0;}

        if(y<0) {speedY=0;}
    }

    /**
     * on dessine l'image avec ses coordonnées
     */
    public void draw(Canvas canvas)
    {
        if(imgHero==null) {return;}
        canvas.drawBitmap(imgHero.getBitmap(), x, y, null);
    }
    public int getCentreY(){
        return getY()+(imgH/2);
    }

}
