package com.example.projetsuper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

class TierListImage {

    /**
     * Instanciation des images S,A,B,C,D et de leurs coordonnées
     */
    private BitmapDrawable imgS=null;
    private BitmapDrawable imgA=null;
    private BitmapDrawable imgB=null;
    private BitmapDrawable imgC=null;
    private BitmapDrawable imgD=null;

    int W,H;
    private int xS,yS;
    private int xA,yA;
    private int xB,yB;
    private int xC,yC;
    private int xD,yD;

    private final Context mContext;

    public TierListImage(final Context c)
    {

        mContext=c;
    }

    /**
     * on attribue à l'objet "img" l'image passée en paramètre avec w et h qui sont sa taille
     */

    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h)
    {
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }

    /**
     * on redimensionne les images selon la largeur/hauteur de l'écran
     */
    public void resize(int wScreen, int hScreen) {
        W = hScreen/9;
        H = hScreen/8;

        imgS = setImage(mContext,R.drawable.tier_s,W,H);
        imgA = setImage(mContext,R.drawable.tier_a,W,H);
        imgB = setImage(mContext,R.drawable.tier_b,W,H);
        imgC = setImage(mContext,R.drawable.tier_c,W,H);
        imgD = setImage(mContext,R.drawable.tier_d,W,H);

        xS=0; yS=0;
        xA=0; yA=hScreen/7;
        xB=0; yB=hScreen/7*2;
        xC=0; yC=hScreen/7*3;
        xD=0; yD=hScreen/7*4;
    }

    public int getxS() {
        return xS;
    }

    public void setxS(int xS) {
        this.xS = xS;
    }

    public int getyS() {
        return yS;
    }

    public void setyS(int yS) {
        this.yS = yS;
    }

    public int getxA() {
        return xA;
    }

    public void setxA(int xA) {
        this.xA = xA;
    }

    public int getyA() {
        return yA;
    }

    public void setyA(int yA) {
        this.yA = yA;
    }

    public int getxB() {
        return xB;
    }

    public void setxB(int xB) {
        this.xB = xB;
    }

    public int getyB() {
        return yB;
    }

    public void setyB(int yB) {
        this.yB = yB;
    }

    public int getxC() {
        return xC;
    }

    public void setxC(int xC) {
        this.xC = xC;
    }

    public int getyC() {
        return yC;
    }

    public void setyC(int yC) {
        this.yC = yC;
    }

    public int getxD() {
        return xD;
    }

    public void setxD(int xD) {
        this.xD = xD;
    }

    public int getyD() {
        return yD;
    }

    public void setyD(int yD) {
        this.yD = yD;
    }

    /**
     * on dessine les images avec leurs coordonnées
     */
    public void draw(Canvas canvas)
    {
        if(imgS==null) {return;}
        canvas.drawBitmap(imgS.getBitmap(), xS, yS, null);
        canvas.drawBitmap(imgA.getBitmap(), xA, yA, null);
        canvas.drawBitmap(imgB.getBitmap(), xB, yB, null);
        canvas.drawBitmap(imgC.getBitmap(), xC, yC, null);
        canvas.drawBitmap(imgD.getBitmap(), xD, yD, null);
    }

}
