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
        Resources resources = getResources();
        boolean themeActuel = (resources != null) && (resources.getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
        sw.setChecked(themeActuel);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode((AppCompatDelegate.MODE_NIGHT_NO));
                }
            }
        });

        //Ajout nom créateur donc moi et l'autre
        //le nom de l'application, la version, les droits d'auteur et les licences
    }

    public void modeAvion(View v) {
        showNotification();
    }

    public void showNotification() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"My Notification");
        builder.setContentTitle("Title");
        builder.setContentText("C'est le texte");
        builder.setSmallIcon(R.drawable.icone_menu);
        builder.setAutoCancel(true);


        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(10,builder.build());

    }
    public void recherche_hero(View v){

        Intent ia = new Intent (this, RechercheHero.class);
        startActivity(ia);
    }
    public void tierlist(View v){
        //Intent ia = new Intent (this, tierlist.class);
        //startActivity(ia);
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
