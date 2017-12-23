package com.bugscript.postergrid;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FavoriteDetails extends AppCompatActivity {

    private TextView movieName,movieDates,movieVotes,movieSummary;
    private ImageView imageViewInDetailsPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_details);

        movieName= findViewById(R.id.movieTitleValue);
        movieDates= findViewById(R.id.movieReleaseDateValue);
        movieVotes= findViewById(R.id.movieVoteValue);
        movieSummary= findViewById(R.id.movieSummaryValue);
        imageViewInDetailsPoster= findViewById(R.id.imageViewPosterDetails);
        final FloatingActionButton fab2 = findViewById(R.id.fab1);

        String gotPosition=getIntent().getStringExtra("position");
        int intGotPosition=Integer.parseInt(gotPosition);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(MoviesDB.fav_movies[intGotPosition]);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        movieName.setText(MoviesDB.fav_movies[intGotPosition]);
        movieDates.setText(MoviesDB.fav_dates[intGotPosition]);
        movieVotes.setText(MoviesDB.fav_votes[intGotPosition]+" / 10");
        movieSummary.setText("\t\t"+MoviesDB.fav_summary[intGotPosition]);
        Picasso.with(FavoriteDetails.this)
                .load("https://image.tmdb.org/t/p/w500"+MoviesDB.fav_poster[intGotPosition])
                .into(imageViewInDetailsPoster);
        imageViewInDetailsPoster.setVisibility(View.VISIBLE);


    }
}
