package com.example.projetsuper;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class AffichageHeroBis extends AppCompatActivity {

    ProgressBar intelligence, force, vitesse, durabilite, pouvoir, combat;
    TextView nom, nom_complet, type, race_genre, editeur;
    TextView poids, taille, travail;
    Hero hero = new Hero();

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
        //poids = (TextView) findViewById(R.id.poids);
        //taille = (TextView) findViewById(R.id.taille);
        //travail = (TextView) findViewById(R.id.travail);

        //Execution de la requette api et prise des données du héro
        RequeteAPI rAPI = new RequeteAPI();
        rAPI.execute("56");
        hero = rAPI.getHero();
        updateData();
    }

    public void updateData(){
        nom.setText(hero.getNom());
        nom_complet.setText(hero.getNom_complet());
        type.setText("");
        intelligence.setProgress(hero.getIntelligence());
        force.setProgress(hero.getForce());
        vitesse.setProgress(hero.getVitesse());
        durabilite.setProgress(hero.getDurabilite());
        pouvoir.setProgress(hero.getPouvoir());
        combat.setProgress(hero.getCombat());
    }

    public class RequeteAPI extends AsyncTask<String, Void, String> {
        // Le corps de la tâche asynchrone (exécuté en tâche de fond)
        //  lance la requète
        Hero hero = new Hero();

        public Hero getHero() {

            return this.hero;
        }

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

            if (true == true) {
                this.hero.setNom(jso.getString("name"));

                JSONObject jsostats = jso.getJSONObject("powerstats");
                this.hero.setIntelligence(jsostats.getInt("intelligence"));
                this.hero.setForce(jsostats.getInt("strength"));
                this.hero.setVitesse(jsostats.getInt("speed"));
                this.hero.setDurabilite(jsostats.getInt("durability"));
                this.hero.setPouvoir(jsostats.getInt("power"));
                this.hero.setCombat(jsostats.getInt("combat"));

                JSONObject jsobio = jso.getJSONObject("biography");
                this.hero.setNom_complet(jsobio.getString("full-name"));
                this.hero.setEditeur(jsobio.getString("publisher"));
                this.hero.setType(jsobio.getString("alignment"));

                JSONObject jsoapparence = jso.getJSONObject("appearance");
                this.hero.setGenre(jsoapparence.getString("gender"));
                this.hero.setRace(jsoapparence.getString("race"));
                this.hero.setTaille(jsoapparence.getJSONArray("height"));
                this.hero.setPoids(jsoapparence.getJSONArray("weight"));

                JSONObject jsotravail = jso.getJSONObject("work");
                this.hero.setTravail(jsotravail.getString("occupation"));

                JSONObject jsoimage = jso.getJSONObject("image");
                this.hero.setImage(jsoimage.getString("url"));
            } else {
                nom.setText(jso.getString("error"));
            }
        }


        // Méthode appelée lorsque la tâche de fond sera terminée
        //  Affiche le résultat
        protected void onPostExecute(String result) {
            try {
                JSONObject toDecode = new JSONObject(result);
                decodeJSON(toDecode);
                updateData();

            } catch (Exception e) {
                nom_complet.setText("Erreur");
            }
        }
    }
}

