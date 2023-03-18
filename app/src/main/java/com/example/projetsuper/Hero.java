package com.example.projetsuper;

import org.json.JSONArray;
import org.json.JSONException;

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
    public Hero(){
        this.intelligence = 25;
        this.force = 25;
        this.vitesse = 25;
        this.durabilite = 25;
        this.pouvoir = 25;
        this.combat = 25;

    }

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

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getDurabilite() {
        return durabilite;
    }

    public void setDurabilite(int durabilite) {
        this.durabilite = durabilite;
    }

    public int getPouvoir() {
        return pouvoir;
    }

    public void setPouvoir(int pouvoir) {
        this.pouvoir = pouvoir;
    }

    public int getCombat() {
        return combat;
    }

    public void setCombat(int combat) {
        this.combat = combat;
    }

    public String getNom_complet() {
        return nom_complet;
    }

    public void setNom_complet(String nom_complet) {
        if(nom_complet == ""){
            this.nom_complet = "Nom complet inconnu";}
        else {
            this.nom_complet = nom_complet;}
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == "bad")
            this.type = "Mauvais";
        else if (type == "good" )
            this.type = "Bon";
        else
            this.type = "Côte inconnu";
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if(genre == null)
            this.genre = "Genre inconnu";
        else
            this.genre = genre;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        if(race == "null")
            this.race = "Genre inconnu";
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

    public void setPoids(JSONArray poids) throws JSONException {

        this.poids = poids.getString(1);
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(JSONArray taille) throws JSONException {

        this.taille = taille.getString(1);
    }
}
