package com.example.projetsuper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

class TierListImageHero
{
    int img;
    private BitmapDrawable imgHero=null; // image de la balle
    private int x, y; // coordonnées x,y de la balle en pixel

    private int imgW, imgH; // largeur et hauteur de la balle en pixels
    private int wEcran,hEcran; // largeur et hauteur de l'écran en pixels
    private boolean move = true; // 'true' si la balle doit se déplacer automatiquement, 'false' sinon

    // pour déplacer la balle on ajoutera INCREMENT à ses coordonnées x et y
    private static final int INCREMENT = 5;
    private int speedX=INCREMENT, speedY=INCREMENT;

    // contexte de l'application Android
    // il servira à accéder aux ressources, dont l'image de la balle
    private final Context mContext;

    // Constructeur de l'objet "Balle"
    public TierListImageHero(final Context c, int img)
    {
        this.img = img;
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

    // retourne 'true' si la balle se déplace automatiquement
    // 'false' sinon
    // car on la bloque sous le doigt du joueur lorsqu'il la déplace
    public boolean isMoving() {
        return move;
    }

    // définit si oui ou non la balle doit se déplacer automatiquement
    // car on la bloque sous le doigt du joueur lorsqu'il la déplace
    public void setMove(boolean move) {
        this.move = move;
    }

    // redimensionnement de l'image selon la largeur/hauteur de l'écran passés en paramètre
    public void resize(int wScreen, int hScreen, int nb,int mb) {
        // wScreen et hScreen sont la largeur et la hauteur de l'écran en pixel
        // on sauve ces informations en variable globale, car elles serviront
        // à détecter les collisions sur les bords de l'écran
        wEcran=wScreen;
        hEcran=hScreen;

        // on définit (au choix) la taille de la balle à 1/5ème de la largeur de l'écran

        //imgH = 278;
        //imgW = 208;   480*640

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

    // déplace la balle en détectant les collisions avec les bords de l'écran
    public void moveWithCollisionDetection()
    {
        // si on ne doit pas déplacer la balle (lorsqu'elle est sous le doigt du joueur)
        // on quitte
        if(!move) {return;}

        // on incrémente les coordonnées X et Y
        x+=speedX;
        y+=speedY;

        // si x dépasse la largeur de l'écran, on inverse le déplacement
        if(x+imgW > wEcran) {speedX=0;}

        // si y dépasse la hauteur l'écran, on inverse le déplacement
        if(y+imgH > hEcran) {speedY=0;}

        // si x passe à gauche de l'écran, on inverse le déplacement
        if(x<0) {speedX=0;}

        // si y passe à dessus de l'écran, on inverse le déplacement
        if(y<0) {speedY=0;}
    }

    // on dessine la balle, en x et y
    public void draw(Canvas canvas)
    {
        if(imgHero==null) {return;}
        canvas.drawBitmap(imgHero.getBitmap(), x, y, null);
    }
    public int getCentreY(){
        return getY()+(imgH/2);
    }

} // public class Balle
