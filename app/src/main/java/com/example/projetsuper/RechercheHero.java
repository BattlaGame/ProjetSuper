package com.example.projetsuper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

public class RechercheHero extends AppCompatActivity {

    EditText edt_id, edt_string;
    Button button_id, button_string;

    /**
     * Fonction onCreate qui créer les objets du layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_hero);
        button_id = (Button) findViewById(R.id.button_id);
        edt_id = (EditText) findViewById(R.id.edt_id);
        button_string = (Button) findViewById(R.id.button_string);
        edt_string = (EditText) findViewById(R.id.edt_string);

    }

    /**
     * Fonction d'un bouton qui envoie vers AffichageHero
     * en mettant la valeur d'un edit text dans l'intent
     * @param v
     */
    public void recherche_id(View v){

        Intent ia = new Intent (RechercheHero.this, AffichageHero.class);
        ia.putExtra("id", edt_id.getText().toString());
        startActivity(ia);
    }

    /**
     * Fonction d'un bouton qui envoie vers AffichageListe
     * en mettant la valeur d'un edit text dans l'intent
     * @param v
     */
    public void recherche_string(View v){
        Intent ia = new Intent (RechercheHero.this, AffichageListe.class);
        ia.putExtra("nom", edt_string.getText().toString());
        startActivity(ia);
    }

    //Toolbar
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