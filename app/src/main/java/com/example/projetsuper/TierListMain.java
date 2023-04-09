package com.example.projetsuper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TierListMain extends AppCompatActivity {

    private TierListView tierlistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tierlist_main);
    }

    /**
     * fonction qui va defenir quelle image sera affiché
     */
    public void tierlist1(View v){
        int img1 = R.drawable.marvel_aurora;
        int img2 = R.drawable.marvel_captaine_america;
        int img3 = R.drawable.marvel_hulk;
        int img4 = R.drawable.marvel_iron_man;
        int img5 = R.drawable.marvel_spider_man;
        int img6 = R.drawable.marvel_ant_man;
        int img7 = R.drawable.marvel_black_panther;
        int img8 = R.drawable.marvel_daredevil;
        int img9 = R.drawable.marvel_doctor_strange;
        int img10 = R.drawable.marvel_thor;
        tierlistview = new TierListView(this, img1,img2,img3,img4,img5,img6,img7,img8,img9,img10);
        setContentView(tierlistview);
    }
    public void tierlist2(View v){
        int img1 = R.drawable.dc_aquaman;
        int img2 = R.drawable.dc_atom;
        int img3 = R.drawable.dc_batman;
        int img4 = R.drawable.dc_black_adam;
        int img5 = R.drawable.dc_catwoman;
        int img6 = R.drawable.dc_cyborg;
        int img7 = R.drawable.dc_flash;
        int img8 = R.drawable.dc_green_arrow;
        int img9 = R.drawable.dc_wonder_woman;
        int img10 = R.drawable.dc_superman;
        tierlistview = new TierListView(this, img1,img2,img3,img4,img5,img6,img7,img8,img9,img10);
        setContentView(tierlistview);
    }

    /**
     * Toolbar
     */
    public void recherche_hero(View v){

        Intent ia = new Intent (this, RechercheHero.class);
        startActivity(ia);
    }
    public void tierlist(View v){
        Intent ia = new Intent (this, TierListMain.class);
        startActivity(ia);
    }
    public void database(View v){
        Intent ia = new Intent (this, DBMain.class);
        startActivity(ia);
    }
    public void menu(View v){
        Intent ia = new Intent (this, MainActivity.class);
        startActivity(ia);
    }
    public void parametre(View v){
        Intent ia = new Intent (this, Parametre.class);
        startActivity(ia);
    }

}
