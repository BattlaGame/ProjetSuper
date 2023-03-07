package com.example.projetsuper;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AffichageHero extends AppCompatActivity {

    ProgressBar intelligence;
    ProgressBar force;
    ProgressBar vitesse;
    ProgressBar durabilite;
    ProgressBar pouvoir;
    ProgressBar combat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichage_heros);
        intelligence = (ProgressBar) findViewById(R.id.bar_intelligence);
        force = (ProgressBar) findViewById(R.id.bar_force);
        vitesse = (ProgressBar) findViewById(R.id.bar_vitesse);
        durabilite = (ProgressBar) findViewById(R.id.bar_durabilite);
        pouvoir = (ProgressBar) findViewById(R.id.bar_pouvoir);
        combat = (ProgressBar) findViewById(R.id.bar_combat);


    }
}
