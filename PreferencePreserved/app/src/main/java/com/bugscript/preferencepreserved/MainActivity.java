package com.bugscript.preferencepreserved;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private TextView preferencesLabelTextView;
    private ImageView preferedImageInImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferedImageInImageView=findViewById(R.id.imageView);
        preferencesLabelTextView=findViewById(R.id.preferenceLabel);
        getDefaultSetup();
    }

    private void getDefaultSetup(){

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        preferencesLabelTextView.setAllCaps(sharedPreferences.getBoolean("show_caps",true));
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w780//ApYhuwBWzl29Oxe9JJsgL7qILbD.jpg")
                .into(preferedImageInImageView);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.visualizer_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings){
            Intent i=new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("show_caps")){
            preferencesLabelTextView.setAllCaps(sharedPreferences.getBoolean("show_caps",true));
        }
    }

    @Override
    protected void onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);

        super.onDestroy();
    }
}