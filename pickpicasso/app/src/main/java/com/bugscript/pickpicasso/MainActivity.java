package com.bugscript.pickpicasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView getImageTextView = (TextView) findViewById(R.id.getImage);
        final ImageView getImageImageView = (ImageView) findViewById(R.id.imageDisplay);

        getImageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(getApplicationContext())
                        .load("https://image.tmdb.org/t/p/w500//oSLd5GYGsiGgzDPKTwQh7wamO8t.jpg")
                        .into(getImageImageView);
            }
        });

    }
}
