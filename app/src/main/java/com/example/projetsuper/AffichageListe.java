package com.example.projetsuper;

import android.app.ActionBar;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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

    public void clic(View v){

    }

    public class RequeteAPI extends AsyncTask<String, Void, String> {
        // Le corps de la tâche asynchrone (exécuté en tâche de fond)
        //  lance la requète
        Hero hero = new Hero();

        public Hero getHero() {
            return this.hero;
        }

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

            if (true == true) {
                JSONArray jsoliste = jso.getJSONArray("results");
                for(int i = 0; i < jsoliste.length(); i++){
                    listeId.add(jsoliste.getJSONObject(i).getString("id"));
                    listeNom.add(jsoliste.getJSONObject(i).getString("name"));

                    JSONObject jsobio = jsoliste.getJSONObject(i).getJSONObject("biography");
                    listeNom_complet.add(jsobio.getString("full-name"));
                }
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



                for(int i = 0; i < listeId.size(); i++){
                    listeHero.add(new Hero(listeId.get(i),listeNom.get(i),listeNom_complet.get(i)));

                    TableRow row = new TableRow(context);
                    TextView tv_nom = new TextView(context);
                    TextView tv_nom_complet = new TextView(context);
                    Button button = new Button(context);

                    tv_nom.setText(listeHero.get(i).getNom());
                    tv_nom_complet.setText(listeHero.get(i).getNom_complet());

                    row.addView(tv_nom);
                    row.addView(tv_nom_complet);
                    row.addView(button);

                    table.addView(row, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }

            } catch (Exception e) {
                titre_button.setText("Erreur");
            }
        }
    }
}
