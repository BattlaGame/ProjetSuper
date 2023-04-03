package com.example.projetsuper;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DBCreerHero extends AppCompatActivity {

    EditText db_nom, db_nom_complet, db_race, db_genre, db_image;
    SQLiteDatabase db;
    SeekBar sb_intelligence, sb_force, sb_vitesse, sb_durabilite, sb_pouvoir, sb_combat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_creer_hero);
        
        db_nom = (EditText)findViewById(R.id.db_nom);
        db_nom_complet = (EditText)findViewById(R.id.db_nom_complet);

        sb_intelligence = (SeekBar)findViewById(R.id.seekBar_intelligence);
        sb_force = (SeekBar)findViewById(R.id.seekBar_force);
        sb_vitesse = (SeekBar)findViewById(R.id.seekBar_vitesse);
        sb_durabilite = (SeekBar)findViewById(R.id.seekBar_durabilite);
        sb_pouvoir = (SeekBar)findViewById(R.id.seekBar_pouvoir);
        sb_combat = (SeekBar)findViewById(R.id.seekBar_combat);

        db_genre = (EditText)findViewById(R.id.db_genre);
        db_race = (EditText)findViewById(R.id.db_race);
        db_image = (EditText)findViewById(R.id.db_image);

        Database dbHelper = new Database(this);
        db = dbHelper.getWritableDatabase();
    }
    public void creerHero(View v){

        Hero hero = new Hero();
        hero.setNom(db_nom.getText().toString());
        hero.setNom_complet(db_nom_complet.getText().toString());
        hero.setIntelligence(sb_intelligence.getProgress());
        hero.setForce(sb_force.getProgress());
        hero.setVitesse(sb_vitesse.getProgress());
        hero.setDurabilite(sb_durabilite.getProgress());
        hero.setPouvoir(sb_pouvoir.getProgress());
        hero.setCombat(sb_combat.getProgress());
        hero.setRace(db_race.getText().toString());
        hero.setGenre(db_genre.getText().toString());
        hero.setImage(db_image.getText().toString());

        Database dbHelper = new Database(this);
        dbHelper.addHero(hero);
        Toast.makeText(this, "Héros ajouté avec succès", Toast.LENGTH_SHORT).show();

        Intent ia = new Intent (DBCreerHero.this, DBMain.class);
        startActivity(ia);
    }

}
