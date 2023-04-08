package com.example.projetsuper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

class TierListImage {

    private BitmapDrawable imgS=null; // image de la balle
    private BitmapDrawable imgA=null; // image de la balle
    private BitmapDrawable imgB=null; // image de la balle
    private BitmapDrawable imgC=null; // image de la balle
    private BitmapDrawable imgD=null; // image de la balle

    private int xS,yS; // coordonnées x,y de la balle en pixel
    private int xA,yA; // coordonnées x,y de la balle en pixel
    private int xB,yB; // coordonnées x,y de la balle en pixel
    private int xC,yC; // coordonnées x,y de la balle en pixel
    private int xD,yD; // coordonnées x,y de la balle en pixel

    private int SW, SH; // largeur et hauteur de la balle en pixels
    private int AW, AH; // largeur et hauteur de la balle en pixels
    private int BW, BH; // largeur et hauteur de la balle en pixels
    private int CW, CH; // largeur et hauteur de la balle en pixels
    private int DW, DH; // largeur et hauteur de la balle en pixels

    // contexte de l'application Android
    // il servira à accéder aux ressources, dont l'image de la balle
    private final Context mContext;


    // Constructeur de l'objet "Balle"
    public TierListImage(final Context c)
    {
        xS=0; yS=0; // position de départ
        xA=0; yA=256; // position de départ
        xB=0; yB=512; // position de départ
        xC=0; yC=768; // position de départ
        xD=0; yD=1024; // position de départ
        mContext=c; // sauvegarde du contexte
    }

    // on attribue à l'objet "Balle" l'image passée en paramètre
    // w et h sont sa largeur et hauteur définis en pixels
    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h)
    {
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }

    // redimensionnement de l'image selon la largeur/hauteur de l'écran passés en paramètre
    public void resize(int wScreen, int hScreen) {
        // on sauve ces informations en variable globale, car elles serviront

        imgS = setImage(mContext,R.drawable.tier_s,246,246);
        imgA = setImage(mContext,R.drawable.tier_a,246,246);
        imgB = setImage(mContext,R.drawable.tier_b,246,246);
        imgC = setImage(mContext,R.drawable.tier_c,246,246);
        imgD = setImage(mContext,R.drawable.tier_d,246,246);
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

    // on dessine la balle, en x et y
    public void draw(Canvas canvas)
    {
        if(imgS==null) {return;}
        canvas.drawBitmap(imgS.getBitmap(), xS, yS, null);
        canvas.drawBitmap(imgA.getBitmap(), xA, yA, null);
        canvas.drawBitmap(imgB.getBitmap(), xB, yB, null);
        canvas.drawBitmap(imgC.getBitmap(), xC, yC, null);
        canvas.drawBitmap(imgD.getBitmap(), xD, yD, null);
    }

} // public class Balle
