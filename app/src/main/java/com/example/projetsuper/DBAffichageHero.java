package com.example.projetsuper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.Glide;


public class DBAffichageHero extends AppCompatActivity {

    ProgressBar intelligence, force, vitesse, durabilite, pouvoir, combat;
    TextView nom, nom_complet, type, race_genre,editeur;
    TextView poids, taille, travail;
    ImageView image;
    Hero hero = new Hero();
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_affichage_heros);

        //Création des objets du layout
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
        poids = (TextView) findViewById(R.id.poids);
        taille = (TextView) findViewById(R.id.taille);
        travail = (TextView) findViewById(R.id.travail);

        image = (ImageView) findViewById(R.id.image_perso);

        //Récupération de l'intent
        id = getIntent().getStringExtra("id");

        //Execution de la requette
        Database dbHelper = new Database(this);
        hero = dbHelper.getHeroById(id);
        update();
    }


    public void update(){
        nom.setText(hero.getNom());
        nom_complet.setText(hero.getNom_complet());
        type.setText("");
        intelligence.setProgress(hero.getIntelligence());
        force.setProgress(hero.getForce());
        vitesse.setProgress(hero.getVitesse());
        durabilite.setProgress(hero.getDurabilite());
        pouvoir.setProgress(hero.getPouvoir());
        combat.setProgress(hero.getCombat());
        editeur.setText(hero.getEditeur());
        type.setText(hero.getType());
        race_genre.setText(hero.getGenre()+" / "+hero.getRace());
        taille.setText(hero.getPoids());
        poids.setText(hero.getTaille());
        travail.setText(hero.getImage());

        String url = hero.getImage();
        Glide.with(this)
                .load(url)
                .fitCenter()
                .into(image);

    }
    public void supprimer(View v){
        Database db = new Database(this);
        db.deleteHero(id);
        notification();
        Intent ia = new Intent (DBAffichageHero.this, DBMain.class);
        startActivity(ia);

    }

    public void notification() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("notif","notif", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"notif");
        builder.setContentTitle("ProjetSuper");
        builder.setContentText("La base de donnée a été mise à jour. Un héro a été supprimer");
        builder.setSmallIcon(R.drawable.icone_menu);
        builder.setAutoCancel(true);


        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1,builder.build());

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
