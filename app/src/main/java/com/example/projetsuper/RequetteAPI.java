package com.example.projetsuper;


import android.os.AsyncTask;

import org.json.JSONArray;
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

public class RequetteAPI (String idHero){

    Hero hero = new hero;

    private class RequestTask extends AsyncTask<String, Void, String> {
        // Le corps de la tâche asynchrone (exécuté en tâche de fond)
        //  lance la requète
        protected String doInBackground(String... idHero) {
            String response = requete(idHero[0]);
            return response;
        }
        private String requete(String idHero) {
            String response = "";
            try {
                HttpURLConnection connection = null;
                URL url = new URL("https://superheroapi.com/api/2443123965837268/"+
                        URLEncoder.encode(idHero,"utf8"));
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new
                        InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String ligne = bufferedReader.readLine() ;
                while (ligne!= null){
                    response+=ligne;
                    ligne = bufferedReader.readLine();
                }
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
        private String decodeJSON(JSONObject jso) throws Exception {
            String response = "";
            String jsocod = jso.getString("response");
            if (jsocod == "success") { // ...
                hero.nom = jso.getString("name");

                JSONObject jsostats = jso.getJSONObject("powerstats");
                hero.intelligence = jsostats.getString("intelligence");
                hero.force = jsostats.getString("strength");
                hero.vitesse = jsostats.getString("speed");
                hero.durabilite = jsostats.getString("durability");
                hero.pouvoir = jsostats.getString("power");
                hero.combat = jsostats.getString("combat");

                JSONObject jsobio = jso.getJSONObject("biography");
                hero.nom_complet = jsobio.getString("full-name");
                hero.editeur = jsobio.getString("publisher");
                hero.type = jsobio.getString("alignment");

                JSONObject jsoapparence = jso.getJSONObject("appearance");
                hero.genre = jsoapparence.getString("gender");
                hero.race = jsoapparence.getString("race");
                hero.taille = jsoapparence.getJSONArray("height");
                hero.poids = jsoapparence.getJSONArray("weight");

                JSONObject jsotravail = jso.getJSONObject("work");
                hero.travail = jsotravail.getString("occupation");

                JSONObject jsoimage = jso.getJSONObject("image");
                hero.image = jsoimage.getString("url");

            } else { // ...
                response ="\n Code erreur retourné par le serveur :";
                response += "\n\n \t Code = " + jsocod;
                response += "\n\n \t Message : " + jso.getString("message");
            }
            return response;
        }
        // Méthode appelée lorsque la tâche de fond sera terminée
        //  Affiche le résultat
        protected void onPostExecute(String result) {
            JSONObject toDecode = null;
            try {
                toDecode = new JSONObject(result);
                tResultat.setText(decodeJSON(toDecode));
            } catch (Exception e) {
                tResultat.setText("error parsing JSON");
            }
        }
    }
}
