package com.bugscript.moviedb;

import android.graphics.drawable.Drawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {

    private TextView movieName,movieDates,movieVotes,movieSummary;
    private ImageView imageView;
    private ProgressBar progressBarImage;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        movieName=(TextView) findViewById(R.id.movieTitleValue);
        movieDates=(TextView) findViewById(R.id.movieReleaseDateValue);
        movieVotes=(TextView) findViewById(R.id.movieVoteValue);
        movieSummary=(TextView) findViewById(R.id.movieSummaryValue);
        imageView=(ImageView) findViewById(R.id.imageView);
        progressBarImage=(ProgressBar) findViewById(R.id.imageViewFrontProgressBar);

        String gotPosition=getIntent().getStringExtra("position");
        int intGotPosition=Integer.parseInt(gotPosition);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(MainActivity.movies[intGotPosition]);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



        Picasso.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500"+MainActivity.poster[intGotPosition])
                .into(imageView,new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        imageView.setVisibility(View.VISIBLE);
                        progressBarImage.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {
                        progressBarImage.setVisibility(View.VISIBLE);
                    }
                });

        progressBarImage.setVisibility(View.INVISIBLE);
        movieName.setText(MainActivity.movies[intGotPosition]);
        movieDates.setText(MainActivity.dates[intGotPosition]);
        movieVotes.setText(MainActivity.votes[intGotPosition]+" / 10");
        movieSummary.setText("\t\t"+MainActivity.summary[intGotPosition]);



    }
}
