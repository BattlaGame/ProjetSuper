package com.example.projetsuper;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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

    /**
     * Fonction onCreate qui créer les objets du layout
     * et execution de la requete api avec la valeur récupérer dans l'intent
     * @param savedInstanceState
     */
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

    /**
     *
     * Fonction permettant de récupérer les données du héro construit avec l'api
     * et d'afficher les données du héro
     */
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

    /**
     * Fonction permettant de renvoyer vers une url pour faire un recherche internet
     * avec le nom du héro
     * @param v
     */
    public void recherche_internet(View v){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ecosia.org/search?method=index&q="+hero.getNom()));
        startActivity(i);
    }
    
    public class RequeteAPI extends AsyncTask<String, Void, String> {
        // Requete de l'API prennant l'id du héro en paramètre

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

            //Condition pour que la récupération des données s'effectue
            if (jso.getString("response").equals("success")) {

                //récupération des données en les mettant dans un objet héro
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
                //Si la condition n'est pas valide affichage de l'erreur
                hero.setNom(jso.getString("response")+"\n"+jso.getString("error"));
                hero.setNom_complet("Votre saisie n'est pas bonne" +
                        "\nVeuillez mettre un id de 1 à 732");
            }
        }


        // Méthode appelée lorsque la tâche de fond sera terminée
        protected void onPostExecute(String result) {
            try {
                JSONObject toDecode = new JSONObject(result);
                decodeJSON(toDecode);
                //Fonction qui permet d'afficher avec les données du héro
                update();

            } catch (Exception e) {
                hero.setNom_complet("Erreur");
            }
        }
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
