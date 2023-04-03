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
    public Hero(){
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
    public void setCombatString(String combat) {
        if(combat.equals("null"))
            this.combat = 0;
        else
            this.combat = Integer.parseInt(combat);
    }

    public String getNom_complet() {
        return nom_complet;
    }

    public void setNom_complet(String nom_complet) {
        if(nom_complet.equals("")){
            this.nom_complet = "Nom complet inconnu";}
        else {
            this.nom_complet = nom_complet;}
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        if(editeur.equals(""))
            this.editeur = "Editeur inconnu";
        else
            this.editeur = editeur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equals("bad"))
            this.type = "Mauvais";
        else if (type.equals("good"))
            this.type = "Bon";
        else
            this.type = "Côte inconnu";
    }

    public String getGenre() {
        return genre;
    }

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
