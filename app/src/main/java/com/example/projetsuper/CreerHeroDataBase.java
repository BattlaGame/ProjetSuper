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

public class CreerHeroDataBase extends AppCompatActivity {

    EditText db_nom, db_nom_complet, db_race, db_genre;
    TableLayout table;
    SQLiteDatabase db;
    Context context = this;
    SeekBar sb_intelligence, sb_force, sb_vitesse, sb_durabilite, sb_pouvoir, sb_combat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_hero_database);

        table = (TableLayout)findViewById(R.id.tablelayout);
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

        Database dbHelper = new Database(this);
        dbHelper.addHero(hero);
        Toast.makeText(this, "Héros ajouté avec succès", Toast.LENGTH_SHORT).show();
    }

    public void actualisation(View v) {
        Database dbHelper = new Database(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(Database.TABLE_HEROES, null, null, null, null, null, null);

        StringBuilder heroesList = new StringBuilder();
        while (cursor.moveToNext()) {

            //Récupération des données dans la database
            String id = cursor.getString(cursor.getColumnIndex(Database.COLUMN_ID));
            String nom = cursor.getString(cursor.getColumnIndex(Database.COLUMN_NOM));
            String nomComplet = cursor.getString(cursor.getColumnIndex(Database.COLUMN_NOM_COMPLET));

            //Création des objets pour les mettre dans le tableau
            TableRow row = new TableRow(context);
            TextView tv_nom = new TextView(context);
            TextView tv_nom_complet = new TextView(context);
            Button button = new Button(context);

            //Set des textes
            tv_nom.setText(nom);
            tv_nom_complet.setText(nomComplet);

            //Bouton
            button.setText(id);
            button.setId(Integer.parseInt(id));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // La méthode onClick() personnalisée pour chaque bouton

                    //récupération de l'id du héro
                    String id = String.valueOf(v.getId());
                    Intent ia = new Intent (CreerHeroDataBase.this, AffichageHeroDB.class);
                    ia.putExtra("id", id);
                    startActivity(ia);
                }
            });

            //Création des paramètre pour les objets
            tv_nom.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f));
            tv_nom_complet.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f));
            button.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f));



            row.addView(tv_nom);
            row.addView(tv_nom_complet);
            row.addView(button);

            table.addView(row, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        }

        cursor.close();
        db.close();

    }

}
