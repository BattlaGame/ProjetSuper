package com.example.projetsuper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hero.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_HEROES = "hero";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_NOM_COMPLET = "nom_complet";
    public static final String COLUMN_INTELLIGENCE = "intelligence";
    public static final String COLUMN_FORCE = "force";
    public static final String COLUMN_VITESSE = "vitesse";
    public static final String COLUMN_DURABILITE = "durabilite";
    public static final String COLUMN_POUVOIR = "pouvoir";
    public static final String COLUMN_COMBAT = "combat";
    public static final String COLUMN_RACE = "race";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_IMAGE = "image";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Création de la base de donnée
        String CREATE_HEROES_TABLE = "CREATE TABLE " + TABLE_HEROES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOM + " TEXT NOT NULL,"
                + COLUMN_NOM_COMPLET + " TEXT,"
                + COLUMN_INTELLIGENCE + " INTEGER,"
                + COLUMN_FORCE + " INTEGER,"
                + COLUMN_VITESSE + " INTEGER,"
                + COLUMN_DURABILITE + " INTEGER,"
                + COLUMN_POUVOIR + " INTEGER,"
                + COLUMN_COMBAT + " INTEGER,"
                + COLUMN_RACE + " TEXT,"
                + COLUMN_GENRE + " TEXT,"
                + COLUMN_IMAGE + " TEXT)";
        db.execSQL(CREATE_HEROES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HEROES);
        onCreate(db);
    }

    /**
     * Fonction qui prend l'id d'un héro pour le supprimer de la BDD
     * @param id
     */
    public void deleteHero(String id) {
        //Fonction pour supprimer un hero de la BDD
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HEROES, COLUMN_ID + "=?", new String[]{id});
        db.close();
    }

    /**
     * Fonction qui prend un hero en parametre pour l'ajouter à la BDD
     * @param hero
     */
    public void addHero(Hero hero) {
        //Fonction pour ajouter un héro à la BDD
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOM, hero.getNom());
        values.put(COLUMN_NOM_COMPLET, hero.getNom_complet());
        values.put(COLUMN_INTELLIGENCE, hero.getIntelligence());
        values.put(COLUMN_FORCE, hero.getForce());
        values.put(COLUMN_VITESSE, hero.getVitesse());
        values.put(COLUMN_DURABILITE, hero.getDurabilite());
        values.put(COLUMN_POUVOIR, hero.getPouvoir());
        values.put(COLUMN_COMBAT, hero.getCombat());
        values.put(COLUMN_RACE, hero.getRace());
        values.put(COLUMN_GENRE, hero.getGenre());
        values.put(COLUMN_IMAGE, hero.getImage());

        long newRowId = db.insert(TABLE_HEROES, null, values);
        db.close();
    }

    /**
     * Fonction qui prend en paramètre l'id d'un héro pour
     * récupérer toutes les données dans la BDD
     * et renvoie le héro créé avec les données
     * @param id
     * @return
     */
    public Hero getHeroById(String id) {
        //Fonction pour récupérer les informations d'un héro de la BDD grâce à son id et renvoyer un héro
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_HEROES, null, COLUMN_ID + "=?", new String[]{id}, null, null, null);
        
        if (cursor != null && cursor.moveToFirst()) {
            //Récupération des données dans la BDD
            String nom = cursor.getString(cursor.getColumnIndex(COLUMN_NOM));
            String nomComplet = cursor.getString(cursor.getColumnIndex(COLUMN_NOM_COMPLET));
            String genre = cursor.getString(cursor.getColumnIndex(COLUMN_GENRE));
            String race = cursor.getString(cursor.getColumnIndex(COLUMN_RACE));
            int intelligence = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_INTELLIGENCE)));
            int force = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_FORCE)));
            int vitesse = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_VITESSE)));
            int durabilite = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_DURABILITE)));
            int pouvoir = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_POUVOIR)));
            int combat = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_COMBAT)));
            String image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));

            //Création du héro
            Hero hero = new Hero();
            hero.setId(id);
            hero.setNom(nom);
            hero.setNom_complet(nomComplet);
            hero.setGenre(genre);
            hero.setRace(race);
            hero.setIntelligence(intelligence);
            hero.setForce(force);
            hero.setVitesse(vitesse);
            hero.setDurabilite(durabilite);
            hero.setPouvoir(pouvoir);
            hero.setCombat(combat);
            hero.setImage(image);

            cursor.close();
            db.close();
            return hero;
        } else {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
            return null;
        }
    }
}
