package com.example.projetsuper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Toolbar
    /**
     * Fonction d'un bouton de la toolbar qui envoie vers RechercheHero
     * @param v
     */
    public void recherche_hero(View v){

        Intent ia = new Intent (this, RechercheHero.class);
        startActivity(ia);
    }

    /**
     * Fonction d'un bouton de la toolbar qui envoie vers TierList
     * @param v
     */
    public void tierlist(View v){
        Intent ia = new Intent (this, TierListMain.class);
        startActivity(ia);
    }
    /**
     * Fonction d'un bouton de la toolbar qui envoie vers DBMain
     * @param v
     */
    public void database(View v){
        Intent ia = new Intent (this, DBMain.class);
        startActivity(ia);
    }
    /**
     * Fonction d'un bouton de la toolbar qui envoie vers MainActivity
     * @param v
     */
    public void menu(View v){
        Intent ia = new Intent (this, MainActivity.class);
        startActivity(ia);
    }
    /**
     * Fonction d'un bouton de la toolbar qui envoie vers Parametre
     * @param v
     */
    public void parametre(View v){
        Intent ia = new Intent (this, Parametre.class);
        startActivity(ia);
    }
}