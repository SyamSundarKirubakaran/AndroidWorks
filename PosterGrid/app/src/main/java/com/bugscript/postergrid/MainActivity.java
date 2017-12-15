package com.bugscript.postergrid;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private String MOVIE_URL;
    private String API_KEY;
    private URL url;
    private ProgressBar progressBar;
    private GridView gridview;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_popular:
                    MOVIE_URL="https://api.themoviedb.org/3/movie/popular?api_key="+getResources().getString(R.string.API_key);
                    doFunctionGrid();
                    return true;
                case R.id.navigation_top_rated:
                    MOVIE_URL="https://api.themoviedb.org/3/movie/top_rated?api_key="+getResources().getString(R.string.API_key);
                    doFunctionGrid();
                    return true;
                case R.id.navigation_now_playing:
                    MOVIE_URL="https://api.themoviedb.org/3/movie/now_playing?api_key="+getResources().getString(R.string.API_key);
                    doFunctionGrid();
                    return true;
                case R.id.navigation_up_coming:
                    MOVIE_URL="https://api.themoviedb.org/3/movie/upcoming?api_key="+getResources().getString(R.string.API_key);
                    doFunctionGrid();
                    return true;
                case R.id.navigation_favorite:
                    Toast.makeText(MainActivity.this,"Yet to be implemented..",Toast.LENGTH_LONG).show();
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        progressBar= findViewById(R.id.progressbar);
        MOVIE_URL="https://api.themoviedb.org/3/movie/popular?api_key="+getResources().getString(R.string.API_key);
        doFunctionGrid();
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
                try{
                    JSONObject JO=new JSONObject(MovieResults);
                    JSONArray JA= JO.getJSONArray("results");
                    movies=new String[JA.length()];
                    votes=new String[JA.length()];
                    dates=new String[JA.length()];
                    summary=new String[JA.length()];
                    poster=new String[JA.length()];
                    backdrop=new String[JA.length()];
                    id=new String[JA.length()];
                    for(int i=0;i<=JA.length();i++){
                        JSONObject Jinside=JA.getJSONObject(i);
                        movies[i]=Jinside.getString("title");
                        votes[i]=Jinside.getString("vote_average");
                        dates[i]=Jinside.getString("release_date");
                        summary[i]=Jinside.getString("overview");
                        poster[i]=Jinside.getString("poster_path");
                        backdrop[i]=Jinside.getString("backdrop_path");
                        id[i]=Jinside.getString("id");
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
            gridview = (GridView) findViewById(R.id.gridview);
            int orientation = getResources().getConfiguration().orientation;
            gridview.setNumColumns(orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2);
            gridview.setAdapter(new PosterAdapter(MainActivity.this));
            gridview.setVisibility(View.VISIBLE);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    Intent i=new Intent(MainActivity.this,DetailedActivity.class);
                    i.putExtra("position",position+"");
                    startActivity(i);
                }

            });
        }
    }

}
