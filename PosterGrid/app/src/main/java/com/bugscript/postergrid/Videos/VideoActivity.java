package com.bugscript.postergrid.Videos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bugscript.postergrid.DetailedActivity;
import com.bugscript.postergrid.MainActivity;
import com.bugscript.postergrid.R;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        ImageView imageView=findViewById(R.id.image_view_youtube);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+DetailedActivity.keys[0])));
            }
        });


        ArrayList<Video> earthquakes=new ArrayList<>();

        for(int i=0;i<DetailedActivity.video_name.length;i++) {
            earthquakes.add(new Video(DetailedActivity.video_name[i]));
        }

        ListView videoListView= findViewById(R.id.listView);

        VideoAdapter adapter = new VideoAdapter(VideoActivity.this, earthquakes);

        videoListView.setAdapter(adapter);

        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+DetailedActivity.keys[position])));
            }
        });


    }
}
