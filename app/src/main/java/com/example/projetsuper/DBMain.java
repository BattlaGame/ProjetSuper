package com.example.projetsuper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class DBMain extends AppCompatActivity {

    TableLayout table;

    /**
     *
     * @param savedInstanceState
     */
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_main);

        table = (TableLayout)findViewById(R.id.tablelayout);
        Database dbHelper = new Database(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(Database.TABLE_HEROES, null, null, null, null, null, null);

        while (cursor.moveToNext()) {

            //Récupération des données dans la database
            String id = cursor.getString(cursor.getColumnIndex(Database.COLUMN_ID));
            String nom = cursor.getString(cursor.getColumnIndex(Database.COLUMN_NOM));
            String nomComplet = cursor.getString(cursor.getColumnIndex(Database.COLUMN_NOM_COMPLET));

            //Création des objets pour les mettre dans le tableau
            TableRow row = new TableRow(this);
            TextView tv_nom = new TextView(this);
            TextView tv_nom_complet = new TextView(this);
            Button button = new Button(this);

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
                    Intent ia = new Intent (DBMain.this, DBAffichageHero.class);
                    ia.putExtra("id", id);
                    startActivity(ia);
                }
            });

            //Création des paramètre pour les objets
            TableRow.LayoutParams parametre = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
            tv_nom.setLayoutParams(parametre);
            tv_nom_complet.setLayoutParams(parametre);
            button.setLayoutParams(parametre);

            //Ajout des différents objets dans la Table Row
            row.addView(tv_nom);
            row.addView(tv_nom_complet);
            row.addView(button);

            //Ajout de la Table Row dans la Table Layout
            table.addView(row, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        }
    }

    /**
     * Fonction d'un bouton pour aller dans l'activité DBCreerHero
     * @param v
     */
    public void ajouterHero(View v){
        Intent ia = new Intent (DBMain.this, DBCreerHero.class);
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
