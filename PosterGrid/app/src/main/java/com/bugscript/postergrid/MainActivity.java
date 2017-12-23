package com.bugscript.postergrid;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bugscript.postergrid.Utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static String[] movies;
    public static String[] dates;
    public static String[] summary;
    public static String[] votes;
    public static String[] poster;
    public static String[] backdrop;
    public static String[] id;
    public static String last_item;
    private String MOVIE_URL;
    private static final String LIFE_CYCLE_CALLBACKS="callbacks";
    private URL url;
    private static final String GRID_VIEW_POSITION="gridviewPos";
    private ProgressBar progressBar;
    private GridView gridview;
    public static ContentResolver contentResolver;
    private int gridPos = -1;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            last_item=item.getItemId()+"";
            switch (item.getItemId()) {
                case R.id.navigation_popular:
                    progressBar.setVisibility(View.VISIBLE);
                    MOVIE_URL="https://api.themoviedb.org/3/movie/popular?api_key="+getResources().getString(R.string.API_key);
                    doFunctionGrid();
                    gridPos = -1;
                    return true;
                case R.id.navigation_top_rated:
                    progressBar.setVisibility(View.VISIBLE);
                    MOVIE_URL="https://api.themoviedb.org/3/movie/top_rated?api_key="+getResources().getString(R.string.API_key);
                    doFunctionGrid();
                    gridPos = -1;
                    return true;
                case R.id.navigation_now_playing:
                    progressBar.setVisibility(View.VISIBLE);
                    MOVIE_URL="https://api.themoviedb.org/3/movie/now_playing?api_key="+getResources().getString(R.string.API_key);
                    doFunctionGrid();
                    gridPos = -1;
                    return true;
                case R.id.navigation_up_coming:
                    progressBar.setVisibility(View.VISIBLE);
                    MOVIE_URL="https://api.themoviedb.org/3/movie/upcoming?api_key="+getResources().getString(R.string.API_key);
                    doFunctionGrid();
                    gridPos = -1;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String savedMenuItem=last_item;
        outState.putString(LIFE_CYCLE_CALLBACKS,savedMenuItem+"");
        outState.putInt(GRID_VIEW_POSITION, gridview.getFirstVisiblePosition());
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        progressBar = findViewById(R.id.progressbar);
        contentResolver = MainActivity.this.getContentResolver();
        DetailedActivity.mdb.getFavoriteMovies(contentResolver);
        gridview = findViewById(R.id.gridview);

        if(isNetworkAvailable()) {
            MOVIE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + getResources().getString(R.string.API_key);
            doFunctionGrid();
            if (savedInstanceState != null) {
                if (savedInstanceState.containsKey(LIFE_CYCLE_CALLBACKS)) {
                    String allPreviousLifecycleCallbacks = savedInstanceState
                            .getString(LIFE_CYCLE_CALLBACKS);
                    if (allPreviousLifecycleCallbacks.equals("null")) {
                        MOVIE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + getResources().getString(R.string.API_key);
                        doFunctionGrid();
                    } else {
                        int gotID = Integer.parseInt(allPreviousLifecycleCallbacks);
                        switch (gotID) {
                            case 2131230839:
                                progressBar.setVisibility(View.VISIBLE);
                                MOVIE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + getResources().getString(R.string.API_key);
                                doFunctionGrid();

                                break;
                            case 2131230840:
                                progressBar.setVisibility(View.VISIBLE);
                                MOVIE_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + getResources().getString(R.string.API_key);
                                doFunctionGrid();
                                break;
                            case 2131230838:
                                progressBar.setVisibility(View.VISIBLE);
                                MOVIE_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + getResources().getString(R.string.API_key);
                                doFunctionGrid();
                                break;
                            case 2131230841:
                                progressBar.setVisibility(View.VISIBLE);
                                MOVIE_URL = "https://api.themoviedb.org/3/movie/upcoming?api_key=" + getResources().getString(R.string.API_key);
                                doFunctionGrid();
                                break;
                        }
                    }
                }
                if(savedInstanceState.containsKey(GRID_VIEW_POSITION)){
                    gridPos = savedInstanceState.getInt(GRID_VIEW_POSITION);
                }
            }
        }else {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(MainActivity.this,"Network Error..",Toast.LENGTH_LONG).show();
        }

    }

    public void doFunctionGrid(){
        try{
            url=new URL(MOVIE_URL);
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"URL not Recognized..",Toast.LENGTH_LONG).show();
        }
        new GetMovies().execute(url);
    }

    public class GetMovies extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String MovieResults = null;
            try {
                MovieResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                try {
                    JSONObject JO = new JSONObject(MovieResults);
                    JSONArray JA = JO.getJSONArray("results");
                    movies = new String[JA.length()];
                    votes = new String[JA.length()];
                    dates = new String[JA.length()];
                    summary = new String[JA.length()];
                    poster = new String[JA.length()];
                    backdrop = new String[JA.length()];
                    id = new String[JA.length()];
                    for (int i = 0; i <= JA.length(); i++) {
                        JSONObject Jinside = JA.getJSONObject(i);
                        movies[i] = Jinside.getString("title");
                        votes[i] = Jinside.getString("vote_average");
                        dates[i] = Jinside.getString("release_date");
                        summary[i] = Jinside.getString("overview");
                        poster[i] = Jinside.getString("poster_path");
                        backdrop[i] = Jinside.getString("backdrop_path");
                        id[i] = Jinside.getString("id");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return MovieResults;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
            gridview.setAdapter(new PosterAdapter(MainActivity.this));
            gridview.setVisibility(View.VISIBLE);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    Intent i = new Intent(MainActivity.this, DetailedActivity.class);
                    i.putExtra("position", position + "");
                    startActivity(i);
                }

            });
            if (gridPos > -1)
                gridview.setSelection(gridPos);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.fav_list){
            Intent i=new Intent(MainActivity.this,FavoriteActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
