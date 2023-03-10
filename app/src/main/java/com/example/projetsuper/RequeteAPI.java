package com.example.projetsuper;

import android.os.AsyncTask;

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
            this.hero.setNom(jso.getString("error"));
        }
    }


    // Méthode appelée lorsque la tâche de fond sera terminée
    //  Affiche le résultat
    protected void onPostExecute(String result) {
        try {
            JSONObject toDecode = new JSONObject(result);
            decodeJSON(toDecode);

        } catch (Exception e) {
            hero.setNom_complet("Erreur");
        }
    }
}