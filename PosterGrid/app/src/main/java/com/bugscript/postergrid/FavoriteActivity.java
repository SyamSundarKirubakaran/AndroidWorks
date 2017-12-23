package com.bugscript.postergrid;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class FavoriteActivity extends AppCompatActivity {

    GridView favoriteGridView;
    private static final String GRID_VIEW_POSITION="gridviewPos";
    private int gridPos = -1;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(GRID_VIEW_POSITION, favoriteGridView.getFirstVisiblePosition());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        favoriteGridView=findViewById(R.id.fav_gridview);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if(MoviesDB.fav_poster!=null) {
            favoriteGridView.setAdapter(new FavoriteAdapter(FavoriteActivity.this));
            favoriteGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    Intent i = new Intent(FavoriteActivity.this, FavoriteDetails.class);
                    i.putExtra("position", position + "");
                    startActivity(i);
                }

            });
            if (gridPos > -1)
                favoriteGridView.setSelection(gridPos);
        }else{
            favoriteGridView.setVisibility(View.INVISIBLE);
        }
    }
}
