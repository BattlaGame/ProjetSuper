package com.example.projetsuper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

    }

    public void recherche_hero(View v){

        Intent ia = new Intent (MainActivity.this, RechercheHero.class);
        startActivity(ia);
    }

    public void tierlist(View v){
        //Intent ia = new Intent (MainActivity.this, AffichageListe.class);
        //startActivity(ia);
    }
    public void database(View v){
        Intent ia = new Intent (MainActivity.this, DBMain.class);
        startActivity(ia);
    }
}