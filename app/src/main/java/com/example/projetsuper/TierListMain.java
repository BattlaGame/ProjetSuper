package com.example.projetsuper;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TierListMain extends AppCompatActivity {

    private TierListView tierlistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tierlist_main);
    }

    public void tierlist1(View v){
        int img1 = R.drawable.aurora;
        int img2 = R.drawable.captaine_america;
        int img3 = R.drawable.hulk;
        int img4 = R.drawable.iron_man;
        int img5 = R.drawable.spider_man;
        tierlistview = new TierListView(this, img1,img2,img3,img4,img5);
        // et on l'affiche.
        setContentView(tierlistview);
    }
    public void tierlist2(View v){
    }

}
