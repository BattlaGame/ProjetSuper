package com.example.projetsuper;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AffichageHero extends AppCompatActivity {

    ProgressBar intelligence, force, vitesse, durabilite, pouvoir, combat;
    TextView nom, nom_complet, type, race_genre,editeur;
    TextView poids, taille, travail;
    Hero hero = new Hero();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichage_heros);
        //Création des objet du layout
        intelligence = (ProgressBar) findViewById(R.id.bar_intelligence);
        force = (ProgressBar) findViewById(R.id.bar_force);
        vitesse = (ProgressBar) findViewById(R.id.bar_vitesse);
        durabilite = (ProgressBar) findViewById(R.id.bar_durabilite);
        pouvoir = (ProgressBar) findViewById(R.id.bar_pouvoir);
        combat = (ProgressBar) findViewById(R.id.bar_combat);
        nom = (TextView) findViewById(R.id.nom);
        nom_complet = (TextView) findViewById(R.id.nom_complet);
        type = (TextView) findViewById(R.id.type);
        race_genre = (TextView) findViewById(R.id.race_genre);
        editeur = (TextView) findViewById(R.id.editeur);
        //poids = (TextView) findViewById(R.id.poids);
        //taille = (TextView) findViewById(R.id.taille);
        //travail = (TextView) findViewById(R.id.travail);

        //Execution de la requette api et prise des données du héro
        RequetteAPI rAPI = new RequetteAPI("55");
        hero = rAPI.getHero();

        nom.setText(hero.getNom());

        //

    }
}
