package com.bugscript.internetinteract;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bugscript.internetinteract.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText getQueryString;
    private TextView finalEditInTextView;
    private String userName;
    private URL completeURL;
    private ListView finalList;
    private static final int GITHUB_SEARCH_LOADER=9;
    private String SEARCH_QUERY_URL_EXTRA;


    private ArrayList<Github> appendingGithubFollowers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getQueryString=(EditText) findViewById(R.id.editText);
        finalEditInTextView=(TextView) findViewById(R.id.textView2);
        finalList =(ListView) findViewById(R.id.listview);
    }



    private void cookingQuery(){
        userName=getQueryString.getText().toString().toLowerCase().trim();

        getQueryString.setText("");

        completeURL=NetworkUtils.cookingTheUrl(userName);

        new GithubQueryTask().execute(completeURL);
    }




    public class GithubQueryTask extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String temp="";

            appendingGithubFollowers=new ArrayList<>();
            String doubleParsed="";
            int j=1;
            try {
                temp = NetworkUtils.getResponseFromHttpUrl(searchUrl);

                try {
                    JSONArray JA = new JSONArray(temp);
                    for(int i=0;i<JA.length();i++){
                        JSONObject JO= (JSONObject) JA.get(i);

                        appendingGithubFollowers.add(new Github((String) JO.get("login"),j+""));
                        j+=1;

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return doubleParsed;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {

            GithubAdapter adapter = new GithubAdapter(MainActivity.this,appendingGithubFollowers);
            finalList.setAdapter(adapter);

            finalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                }

            });



            finalList.setVisibility(View.VISIBLE);

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.seachForTheQuery){
            Toast.makeText(MainActivity.this,"Searching for Followers..",Toast.LENGTH_SHORT).show();
            cookingQuery();
        }
        return super.onOptionsItemSelected(item);
    }
}
