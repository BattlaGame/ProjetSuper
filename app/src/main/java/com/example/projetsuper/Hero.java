package com.example.projetsuper;

import org.json.JSONArray;

public class Hero {

    protected int id;
    protected String nom;
    protected int intelligence, force, vitesse, durabilite, pouvoir, combat;
    protected String nom_complet, editeur, type, genre, race, travail, image;
    protected JSONArray poids, taille;

    public Hero(int id, String nom, int intelligence, int force, int vitesse,
                int durabilite, int pouvoir, int combat, String nom_complet,
                String editeur, String type, String genre, String race, String travaille,
                String image, JSONArray poids, JSONArray taille) {
        this.id = id;
        this.nom = nom;
        this.intelligence = intelligence;
        this.force = force;
        this.vitesse = vitesse;
        this.durabilite = durabilite;
        this.pouvoir = pouvoir;
        this.combat = combat;
        this.nom_complet = nom_complet;
        this.editeur = editeur;
        this.type = type;
        this.genre = genre;
        this.race = race;
        this.travail = travail;
        this.image = image;
        this.poids = poids;
        this.taille = taille;
    }
    public Hero(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        this.nom_complet = nom_complet;
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
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
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

    public JSONArray getPoids() {
        return poids;
    }

    public void setPoids(JSONArray poids) {
        this.poids = poids;
    }

    public JSONArray getTaille() {
        return taille;
    }

    public void setTaille(JSONArray taille) {
        this.taille = taille;
    }
}
