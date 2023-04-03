package com.example.projetsuper;

import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.temporal.TemporalAdjuster;

public class AffichageHero extends AppCompatActivity {

    ProgressBar intelligence, force, vitesse, durabilite, pouvoir, combat;
    TextView nom, nom_complet, type, race_genre,editeur;
    TextView poids, taille, travail;
    ImageView image;
    Hero hero = new Hero();
    RequeteAPI rAPI = new RequeteAPI();

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichage_heros);

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

        //Execution de la requette api
        rAPI.execute(id);
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
        travail.setText(hero.getTravail());

        String url = hero.getImage();
        Glide.with(this)
                .load(url)
                .fitCenter()
                .into(image);

    }

    public class RequeteAPI extends AsyncTask<String, Void, String> {
        // Le corps de la tâche asynchrone (exécuté en tâche de fond)
        //  lance la requète

        protected String doInBackground(String... idHero) {
            String response = requete(idHero[0]);
            return response;
        }

        private String requete(String idHero) {
            String response = "";
            try {
                URL url = new URL("https://www.superheroapi.com/api.php/2443123965837268/" +
                        URLEncoder.encode(idHero, "utf8"));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new
                        InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String ligne;
                while ((ligne = bufferedReader.readLine()) != null) {
                    response += ligne;
                }
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();


            } catch (UnsupportedEncodingException e) {
                response = "problème d'encodage";
            } catch (MalformedURLException e) {
                response = "problème d'URL ";
            } catch (IOException e) {
                response = "problème de connexion ";
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }

        private void decodeJSON(JSONObject jso) throws Exception {

            if (jso.getString("response").equals("success")) {
                hero.setNom(jso.getString("name"));

                JSONObject jsostats = jso.getJSONObject("powerstats");
                hero.setIntelligenceString(jsostats.getString("intelligence"));
                hero.setForceString(jsostats.getString("strength"));
                hero.setVitesseString(jsostats.getString("speed"));
                hero.setDurabiliteString(jsostats.getString("durability"));
                hero.setPouvoirString(jsostats.getString("power"));
                hero.setCombatString(jsostats.getString("combat"));

                JSONObject jsobio = jso.getJSONObject("biography");
                hero.setNom_complet(jsobio.getString("full-name"));
                hero.setEditeur(jsobio.getString("publisher"));
                hero.setType(jsobio.getString("alignment"));

                JSONObject jsoapparence = jso.getJSONObject("appearance");
                hero.setGenre(jsoapparence.getString("gender"));
                hero.setRace(jsoapparence.getString("race"));
                hero.setTaille(jsoapparence.getJSONArray("height"));
                hero.setPoids(jsoapparence.getJSONArray("weight"));

                JSONObject jsotravail = jso.getJSONObject("work");
                hero.setTravail(jsotravail.getString("occupation"));

                JSONObject jsoimage = jso.getJSONObject("image");
                hero.setImage(jsoimage.getString("url"));
            } else {
                hero.setNom(jso.getString("error"));
            }
        }


        // Méthode appelée lorsque la tâche de fond sera terminée
        //  Affiche le résultat
        protected void onPostExecute(String result) {
            try {
                JSONObject toDecode = new JSONObject(result);
                decodeJSON(toDecode);
                update();

            } catch (Exception e) {
                hero.setNom_complet("Erreur");
            }
        }
    }


}
