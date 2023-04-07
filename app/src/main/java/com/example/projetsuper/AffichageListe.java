package com.example.projetsuper;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.widget.NestedScrollView;

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
import java.util.ArrayList;
import java.util.List;

public class AffichageListe extends AppCompatActivity {

    Hero hero = new Hero();
    ArrayList<Hero> listeHero = new ArrayList<>();
    ArrayList<String> listeId = new ArrayList<>();
    ArrayList<String> listeNom = new ArrayList<>();
    ArrayList<String> listeNom_complet = new ArrayList<>();
    ArrayList<Button> listeButton = new ArrayList<>();
    TableLayout table;
    Context context = this;
    String recherche_nom;
    TextView titre_button;
    RequeteAPI rAPI = new RequeteAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_hero);

        //
        recherche_nom = getIntent().getStringExtra("nom");

        //Création des différents entités du layout
        table = (TableLayout)findViewById(R.id.tablelayout);
        titre_button = (TextView)findViewById(R.id.titre_button);

        //Execution de la requette api
        rAPI.execute(recherche_nom);
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

    public class RequeteAPI extends AsyncTask<String, Void, String> {
        // Le corps de la tâche asynchrone (exécuté en tâche de fond)
        //  lance la requète

        protected String doInBackground(String... nomHero) {
            String response = requete(nomHero[0]);
            return response;
        }

        private String requete(String nomHero) {
            String response = "";
            try {
                URL url = new URL("https://www.superheroapi.com/api.php/2443123965837268/search/" +
                        URLEncoder.encode(nomHero, "utf8"));
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
                JSONArray jsoliste = jso.getJSONArray("results");
                for(int i = 0; i < jsoliste.length(); i++){
                    listeId.add(jsoliste.getJSONObject(i).getString("id"));
                    listeNom.add(jsoliste.getJSONObject(i).getString("name"));

                    JSONObject jsobio = jsoliste.getJSONObject(i).getJSONObject("biography");
                    listeNom_complet.add(jsobio.getString("full-name"));
                }
            } else {
                //si la condition n'est pas remplie alors affiche l'erreur
                TextView erreur = new TextView(context);
                erreur.setText(jso.getString("error"));
                table.addView(erreur, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        }


        // Méthode appelée lorsque la tâche de fond sera terminée
        protected void onPostExecute(String result) {
            try {
                JSONObject toDecode = new JSONObject(result);
                decodeJSON(toDecode);

                //Boucle for du nombre de héro qui a été récupé avec la requete
                for(int i = 0; i < listeId.size(); i++){
                    listeHero.add(new Hero(listeId.get(i),listeNom.get(i),listeNom_complet.get(i)));

                    //Création des objets pour les mettre dans le tableau
                    TableRow row = new TableRow(context);
                    TextView tv_nom = new TextView(context);
                    TextView tv_nom_complet = new TextView(context);
                    Button button = new Button(context);

                    //Set des textes
                    tv_nom.setText(listeHero.get(i).getNom());
                    tv_nom_complet.setText(listeHero.get(i).getNom_complet());

                    //Bouton
                    button.setText(listeHero.get(i).getId());
                    int id = Integer.parseInt(listeHero.get(i).getId());
                    button.setId(id);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // La méthode onClick() personnalisée pour chaque bouton

                            //récupération de l'id du héro
                            String id = String.valueOf(v.getId());
                            Intent ia = new Intent (AffichageListe.this, AffichageHero.class);
                            ia.putExtra("id", id);
                            startActivity(ia);
                        }
                    });

                    //Création des paramètre pour les objets
                    tv_nom.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f));
                    tv_nom_complet.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f));
                    button.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f));

                    //Ajout dans la tablerow
                    row.addView(tv_nom);
                    row.addView(tv_nom_complet);
                    row.addView(button);

                    //Ajout dans la table layout
                    table.addView(row, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }

            } catch (Exception e) {
                titre_button.setText("Erreur");
            }
        }
    }
}
