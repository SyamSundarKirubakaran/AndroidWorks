package com.bugscript.postergrid;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {

    private TextView movieName,movieDates,movieVotes,movieSummary;
    private ImageView imageViewInDetailsPoster;

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
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        movieName= findViewById(R.id.movieTitleValue);
        movieDates= findViewById(R.id.movieReleaseDateValue);
        movieVotes= findViewById(R.id.movieVoteValue);
        movieSummary= findViewById(R.id.movieSummaryValue);
        imageViewInDetailsPoster= findViewById(R.id.imageViewPosterDetails);


        String gotPosition=getIntent().getStringExtra("position");
        int intGotPosition=Integer.parseInt(gotPosition);


        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(MainActivity.movies[intGotPosition]);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }



        ImageView toolbarImage =  findViewById(R.id.image_id);

        String url = "https://image.tmdb.org/t/p/w1280"+MainActivity.backdrop[intGotPosition];
        picassoLoader(this, toolbarImage, url);

        movieName.setText(MainActivity.movies[intGotPosition]);
        movieDates.setText(MainActivity.dates[intGotPosition]);
        movieVotes.setText(MainActivity.votes[intGotPosition]+" / 10");
        movieSummary.setText("\t\t"+MainActivity.summary[intGotPosition]);
        Picasso.with(DetailedActivity.this)
                .load("https://image.tmdb.org/t/p/w500"+MainActivity.poster[intGotPosition])
                .into(imageViewInDetailsPoster);
        imageViewInDetailsPoster.setVisibility(View.VISIBLE);
    }

    public void picassoLoader(Context context, ImageView imageView, String url){
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.sam)
                .error(R.drawable.sam)
                .into(imageView);
    }

}
