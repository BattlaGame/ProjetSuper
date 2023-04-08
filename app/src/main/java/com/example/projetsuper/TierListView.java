package com.example.projetsuper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

// SurfaceView est une surface de dessin.
// référence : https://developer.android.com/reference/android/view/SurfaceView
// SurfaceView est une surface de dessin.
// référence : https://developer.android.com/reference/android/view/SurfaceView
public class TierListView extends SurfaceView implements SurfaceHolder.Callback {

    // déclaration de l'objet définissant la boucle principale de déplacement et de rendu
    private TierListLoopThread gameLoopThread;
    private TierListImageHero hero1, hero2, hero3, hero4, hero5, hero6, hero7, hero8, hero9, hero10;
    private TierListImage image;

    // création de la surface de dessin
    public TierListView(Context context, int img1,int img2,int img3,int img4, int img5, int img6, int img7, int img8, int img9, int img10) {
        super(context);
        getHolder().addCallback(this);
        gameLoopThread = new TierListLoopThread(this);

        // création d'un objet "balle", dont on définira la largeur/hauteur
        // selon la largeur ou la hauteur de l'écran
        hero1 = new TierListImageHero(this.getContext(),img1,256,0);
        hero2 = new TierListImageHero(this.getContext(),img2,0,1360);
        hero3 = new TierListImageHero(this.getContext(),img3,218,1360);
        hero4 = new TierListImageHero(this.getContext(),img4,436,1360);
        hero5 = new TierListImageHero(this.getContext(),img5,654,1360);
        hero6 = new TierListImageHero(this.getContext(),img6,872,1360);
        hero7 = new TierListImageHero(this.getContext(),img7,0,1648);
        hero8 = new TierListImageHero(this.getContext(),img8,218,1648);
        hero9 = new TierListImageHero(this.getContext(),img9,436,1648);
        hero10 = new TierListImageHero(this.getContext(),img10,654,1648);
        image = new TierListImage(this.getContext());
    }

    // Fonction qui "dessine" un écran de jeu
    public void doDraw(Canvas canvas) {
        if(canvas==null) {return;}

        // on efface l'écran, en blanc
        canvas.drawColor(Color.WHITE);

        // on dessine la balle
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

    // Fonction appelée par la boucle principale (gameLoopThread)
    // On gère ici le déplacement des objets
    public void update() {
    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée immédiatement après la création de l'objet SurfaceView
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // création du processus GameLoopThread si cela n'est pas fait
        if(gameLoopThread.getState()==Thread.State.TERMINATED) {
            gameLoopThread=new TierListLoopThread(this);
        }
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée juste avant que l'objet ne soit détruit.
    // on tente ici de stopper le processus de gameLoopThread
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

    // Gère les touchés sur l'écran
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currentX = (int)event.getX();
        int currentY = (int)event.getY();

        switch (event.getAction()) {

            // code exécuté lorsque le doigt touche l'écran.
            case MotionEvent.ACTION_DOWN:
                // si le doigt touche la balle :
                if(currentX >= hero1.getX() &&
                        currentX <= hero1.getX()+hero1.getImgW() &&
                        currentY >= hero1.getY() && currentY <= hero1.getY()+hero1.getImgH() ) {
                    // on arrête de déplacer la balle
                    hero1.setMove(false);
                }
                if(currentX >= hero2.getX() &&
                        currentX <= hero2.getX()+hero2.getImgW() &&
                        currentY >= hero2.getY() && currentY <= hero2.getY()+hero2.getImgH() ) {
                    // on arrête de déplacer la balle
                    hero2.setMove(false);
                }
                if(currentX >= hero3.getX() &&
                        currentX <= hero3.getX()+hero3.getImgW() &&
                        currentY >= hero3.getY() && currentY <= hero3.getY()+hero3.getImgH() ) {
                    // on arrête de déplacer la balle
                    hero3.setMove(false);
                }
                if(currentX >= hero4.getX() &&
                        currentX <= hero4.getX()+hero4.getImgW() &&
                        currentY >= hero4.getY() && currentY <= hero4.getY()+hero4.getImgH() ) {
                    // on arrête de déplacer la balle
                    hero4.setMove(false);
                }
                if(currentX >= hero5.getX() &&
                        currentX <= hero5.getX()+hero5.getImgW() &&
                        currentY >= hero5.getY() && currentY <= hero5.getY()+hero5.getImgH() ) {
                    // on arrête de déplacer la balle
                    hero5.setMove(false);
                }
                if(currentX >= hero6.getX() &&
                        currentX <= hero6.getX()+hero6.getImgW() &&
                        currentY >= hero6.getY() && currentY <= hero6.getY()+hero6.getImgH() ) {
                    // on arrête de déplacer la balle
                    hero6.setMove(false);
                }
                if(currentX >= hero7.getX() &&
                        currentX <= hero7.getX()+hero7.getImgW() &&
                        currentY >= hero7.getY() && currentY <= hero7.getY()+hero7.getImgH() ) {
                    // on arrête de déplacer la balle
                    hero7.setMove(false);
                }
                if(currentX >= hero8.getX() &&
                        currentX <= hero8.getX()+hero8.getImgW() &&
                        currentY >= hero8.getY() && currentY <= hero8.getY()+hero8.getImgH() ) {
                    // on arrête de déplacer la balle
                    hero8.setMove(false);
                }
                if(currentX >= hero9.getX() &&
                        currentX <= hero9.getX()+hero9.getImgW() &&
                        currentY >= hero9.getY() && currentY <= hero9.getY()+hero9.getImgH() ) {
                    // on arrête de déplacer la balle
                    hero9.setMove(false);
                }
                if(currentX >= hero10.getX() &&
                        currentX <= hero10.getX()+hero10.getImgW() &&
                        currentY >= hero10.getY() && currentY <= hero10.getY()+hero10.getImgH() ) {
                    // on arrête de déplacer la balle
                    hero10.setMove(false);
                }
                break;

            // code exécuté lorsque le doight glisse sur l'écran.
            case MotionEvent.ACTION_MOVE:
                int decalageX = -60;
                int decalageY = -180;
                // on déplace la balle sous le doigt du joueur
                // si elle est déjà sous son doigt (oui si on a setMove à false)
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

            // lorsque le doigt quitte l'écran
            case MotionEvent.ACTION_UP:
                // on reprend le déplacement de la balle
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

        return true;  // On retourne "true" pour indiquer qu'on a géré l'évènement
    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée à la CREATION et MODIFICATION et ONRESUME de l'écran
    // nous obtenons ici la largeur/hauteur de l'écran en pixels
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {
        hero1.resize(w,h); // on définit la taille de la balle selon la taille de l'écran
        hero2.resize(w,h); // on définit la taille de la balle selon la taille de l'écran
        hero3.resize(w,h); // on définit la taille de la balle selon la taille de l'écran
        hero4.resize(w,h); // on définit la taille de la balle selon la taille de l'écran
        hero5.resize(w,h); // on définit la taille de la balle selon la taille de l'écran
        hero6.resize(w,h); // on définit la taille de la balle selon la taille de l'écran
        hero7.resize(w,h); // on définit la taille de la balle selon la taille de l'écran
        hero8.resize(w,h); // on définit la taille de la balle selon la taille de l'écran
        hero9.resize(w,h); // on définit la taille de la balle selon la taille de l'écran
        hero10.resize(w,h); // on définit la taille de la balle selon la taille de l'écran
        image.resize(w,h); // on définit la taille de la balle selon la taille de l'écran
    }
} // class GameView