package com.example.projetsuper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.button_hero);
    }

    public void hero(View v){
        Intent ia = new Intent (MainActivity.this, AffichageHero.class);
        startActivityForResult(ia, 0);
    }
}