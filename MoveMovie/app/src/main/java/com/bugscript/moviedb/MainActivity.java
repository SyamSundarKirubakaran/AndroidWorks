package com.bugscript.moviedb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bugscript.moviedb.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private String[] movies;
    private String[] votes;
    private final String MOVIE_URL="https://api.themoviedb.org/3/movie/popular?api_key=";
    private URL url;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView) findViewById(R.id.listview);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        try{
            url=new URL(MOVIE_URL);
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"URL not Recognized..",Toast.LENGTH_LONG).show();
        }
        new GetMovies().execute(url);
    }


    public class GetMovies extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String MovieResults = null;
            try {
                MovieResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                try{
                    JSONObject JO=new JSONObject(MovieResults);
                    JSONArray JA= JO.getJSONArray("results");
                    movies=new String[JA.length()];
                    votes=new String[JA.length()];
                    for(int i=0;i<=JA.length();i++){
                        JSONObject Jinside=JA.getJSONObject(i);
                        movies[i]=Jinside.getString("title");
                        votes[i]=Jinside.getString("vote_average");
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
            ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                    R.layout.simplerow, R.id.rowTextView, movies);
            lv.setAdapter(adapter);
            lv.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


}
