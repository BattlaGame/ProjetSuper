package com.example.projetsuper;


import android.content.Intent;
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

public class RequetteAPI {

    Hero hero = new Hero();
    String idHero;
    RequestTask rt = new RequestTask();
    public RequetteAPI(String id){
        this.idHero = id;
    }

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
                hero.setNom(jso.getString("name"));

                JSONObject jsostats = jso.getJSONObject("powerstats");
                hero.setIntelligence(jsostats.getInt("intelligence"));
                hero.setForce(jsostats.getInt("strength"));
                hero.setVitesse(jsostats.getInt("speed"));
                hero.setDurabilite(jsostats.getInt("durability"));
                hero.setPouvoir(jsostats.getInt("power"));
                hero.setCombat(jsostats.getInt("combat"));

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
                hero.travail = jsotravail.getString("occupation");

                JSONObject jsoimage = jso.getJSONObject("image");
                hero.image = jsoimage.getString("url");

            } else { // ...

                /*response ="\n Code erreur retourné par le serveur :";
                response += "\n\n \t Code = " + jsocod;
                response += "\n\n \t Message : " + jso.getString("error");*/
            }
            return response;
        }
        // Méthode appelée lorsque la tâche de fond sera terminée
        //  Affiche le résultat
        protected void onPostExecute(String result) {
            JSONObject toDecode = null;
            try {
                toDecode = new JSONObject(result);
                decodeJSON(toDecode);
            } catch (Exception e) {
                //Intent myIntent = new Intent(AffichageHero.class, PageErreur.class);
                //startActivityForResult(myIntent, 0);
                //tResultat.setText("error parsing JSON");
            }
        }
    }

    public Hero getHero(){
        rt.execute(idHero);
        return hero;
    }
}
