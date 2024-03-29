package com.example.projetsuper;

import org.json.JSONArray;
import org.json.JSONException;
/*
* Classe héro correspondant à un héro soit dans l'api soit dans la base de donnée
* avec tous ses attributs qui le compose*/
public class Hero {

    protected String id;
    protected String nom;
    protected int intelligence, force, vitesse, durabilite, pouvoir, combat;
    protected String nom_complet, editeur, type, genre, race, travail, image;
    protected String poids, taille;

    public Hero(String id, String nom, String nom_complet) {
        this.id = id;
        this.nom = nom;
        this.nom_complet = nom_complet;
    }
    public Hero(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public void setIntelligenceString(String intelligence) {
        if(intelligence.equals("null"))
            this.intelligence = 0;
        else
            this.intelligence = Integer.parseInt(intelligence);
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }
    public void setForceString(String force) {
        if(force.equals("null"))
            this.force = 0;
        else
            this.force = Integer.parseInt(force);
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    /**
     * Setter qui permet de mettre une valeur même si celle-ci est null
     * @param vitesse
     */
    public void setVitesseString(String vitesse) {
        if(vitesse.equals("null"))
            this.vitesse = 0;
        else
            this.vitesse = Integer.parseInt(vitesse);
    }

    public int getDurabilite() {
        return durabilite;
    }

    public void setDurabilite(int durabilite) {
        this.durabilite = durabilite;
    }

    /**
     * Setter qui permet de mettre une valeur même si celle-ci est null
     * @param durabilite
     */
    public void setDurabiliteString(String durabilite) {
        if(durabilite.equals("null"))
            this.durabilite = 0;
        else
            this.durabilite = Integer.parseInt(durabilite);
    }

    public int getPouvoir() {
        return pouvoir;
    }

    public void setPouvoir(int pouvoir) {
        this.pouvoir = pouvoir;
    }

    /**
     * Setter qui permet de mettre une valeur même si celle-ci est null
     * @param pouvoir
     */
    public void setPouvoirString(String pouvoir) {
        if(pouvoir.equals("null"))
            this.pouvoir = 0;
        else
            this.pouvoir = Integer.parseInt(pouvoir);
    }

    public int getCombat() {
        return combat;
    }

    public void setCombat(int combat) {
        this.combat = combat;
    }

    /**
     * Setter qui permet de mettre une valeur même si celle-ci est null
     * @param combat
     */
    public void setCombatString(String combat) {
        if(combat.equals("null"))
            this.combat = 0;
        else
            this.combat = Integer.parseInt(combat);
    }

    public String getNom_complet() {
        return nom_complet;
    }

    /**
     * Setter qui permet de mettre une valeur même si celle-ci est null
     * @param nom_complet
     */
    public void setNom_complet(String nom_complet) {
        if(nom_complet.equals("")){
            this.nom_complet = "Nom complet inconnu";}
        else {
            this.nom_complet = nom_complet;}
    }

    public String getEditeur() {
        return editeur;
    }

    /**
     * Setter qui permet de mettre une valeur même si celle-ci est null
     * @param editeur
     */
    public void setEditeur(String editeur) {
        if(editeur.equals(""))
            this.editeur = "Editeur inconnu";
        else
            this.editeur = editeur;
    }

    public String getType() {
        return type;
    }

    /**
     * Setter qui permet de modifier la langue ou mettre une valeur si le type est null
     * @param type
     */
    public void setType(String type) {
        if (type.equals("bad"))
            this.type = "Mauvais";
        else if (type.equals("good"))
            this.type = "Bon";
        else if (type.equals("neutral"))
            this.type = "Neutre";
        else
            this.type = "Côte inconnu";
    }

    public String getGenre() {
        return genre;
    }

    /**
     * Setter qui permet de mettre une valeur si la valeur est null ou vide
     * @param genre
     */
    public void setGenre(String genre) {
        if(genre.equals("null"))
            this.genre = "Genre inconnu";
        else if (genre.equals(""))
            this.genre = "Genre inconnu";
        else
            this.genre = genre;
    }

    public String getRace() {
        return race;
    }

    /**
     * Setter qui permet de mettre une valeur si la valeur est null ou vide
     * @param race
     */
    public void setRace(String race) {
        if(race.equals("null"))
            this.race = "Race inconnu";
        else if (race.equals(""))
            this.race = "Race inconnu";
        else
            this.race = race;
    }

    public String getTravail() {
        return travail;
    }

    public void setTravail(String travail) {
        this.travail = travail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPoids() {
        return poids;
    }

    /**
     * Setter qui prend la 2e valeur de la liste de poids pour prendre le poids en "kg"
     * @param poids
     * @throws JSONException
     */
    public void setPoids(JSONArray poids) throws JSONException {
        this.poids = poids.getString(1);
    }

    public String getTaille() {
        return taille;
    }

    /**
     * Setter qui prend la 2e valeur de la liste de taille pour prendre la taille en "cm"
     * @param taille
     * @throws JSONException
     */
    public void setTaille(JSONArray taille) throws JSONException {
        this.taille = taille.getString(1);
    }
}
