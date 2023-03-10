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
        Hero hero_bis = new Hero();
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
        private Hero decodeJSON(JSONObject jso) throws Exception {
            Hero h1 = new Hero();
            String jsocod = jso.getString("response");
            if (jsocod == "success") {
                h1.setNom(jso.getString("name"));

                JSONObject jsostats = jso.getJSONObject("powerstats");
                h1.setIntelligence(jsostats.getInt("intelligence"));
                h1.setForce(jsostats.getInt("strength"));
                h1.setVitesse(jsostats.getInt("speed"));
                h1.setDurabilite(jsostats.getInt("durability"));
                h1.setPouvoir(jsostats.getInt("power"));
                h1.setCombat(jsostats.getInt("combat"));

                JSONObject jsobio = jso.getJSONObject("biography");
                h1.setNom_complet(jsobio.getString("full-name"));
                h1.setEditeur(jsobio.getString("publisher"));
                h1.setType(jsobio.getString("alignment"));

                JSONObject jsoapparence = jso.getJSONObject("appearance");
                h1.setGenre(jsoapparence.getString("gender"));
                h1.setRace(jsoapparence.getString("race"));
                h1.setTaille(jsoapparence.getJSONArray("height"));
                h1.setPoids(jsoapparence.getJSONArray("weight"));

                JSONObject jsotravail = jso.getJSONObject("work");
                h1.setTravail(jsotravail.getString("occupation"));

                JSONObject jsoimage = jso.getJSONObject("image");
                h1.setImage(jsoimage.getString("url"));

           } else {

                /*response ="\n Code erreur retourné par le serveur :";
                response += "\n\n \t Code = " + jsocod;
                response += "\n\n \t Message : " + jso.getString("error");*/
            }
            return h1;
        }
        // Méthode appelée lorsque la tâche de fond sera terminée
        //  Affiche le résultat
        protected void onPostExecute(String result) {
            JSONObject toDecode = null;
            try {
                toDecode = new JSONObject(result);
                this.hero_bis = decodeJSON(toDecode);
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
