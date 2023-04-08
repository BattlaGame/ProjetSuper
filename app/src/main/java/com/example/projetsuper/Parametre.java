package com.example.projetsuper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Parametre extends AppCompatActivity {

    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parametre);

        sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Change le thème de l'application en fonction du boolean du switch
             * @param compoundButton
             * @param b
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode((AppCompatDelegate.MODE_NIGHT_NO));
                }
            }
        });
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
