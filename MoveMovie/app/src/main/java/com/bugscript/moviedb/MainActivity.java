package com.bugscript.moviedb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private Toast to;
    private ListView lv;
    public static String[] movies;
    public static String[] dates;
    public static String[] summary;
    public static String[] votes;
    public static String[] poster;
    private final String MOVIE_URL="https://api.themoviedb.org/3/movie/popular?api_key=47ea50d7ece7e74f25725d5937da4586";
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
                    dates=new String[JA.length()];
                    summary=new String[JA.length()];
                    poster=new String[JA.length()];
                    for(int i=0;i<=JA.length();i++){
                        JSONObject Jinside=JA.getJSONObject(i);
                        movies[i]=Jinside.getString("title");
                        votes[i]=Jinside.getString("vote_average");
                        dates[i]=Jinside.getString("release_date");
                        summary[i]=Jinside.getString("overview");
                        poster[i]=Jinside.getString("poster_path");
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
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    int itemPosition= position;
                    String  itemValue= (String) lv.getItemAtPosition(position);
                    if(to!=null){
                        to.cancel();
                    }
                    Intent i=new Intent(MainActivity.this,DetailedActivity.class);
                    i.putExtra("position",itemPosition+"");
                    startActivity(i);
                }
            });
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


}
