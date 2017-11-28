package com.bugscript.internetinteract;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syamsundark on 28/11/17.
 */

public class GithubAdapter extends ArrayAdapter<Github> {

    public GithubAdapter(@NonNull Context context, @NonNull List<Github> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listitemView = convertView;
        if(listitemView == null){
            listitemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout,parent,false
            );

        }

        Github currentfollower = getItem(position);

        TextView followerViewName =(TextView) listitemView.findViewById(R.id.name);
        followerViewName.setText(currentfollower.getName());

        TextView followerNumberView = (TextView) listitemView.findViewById(R.id.number);
        followerNumberView.setText("#"+currentfollower.getNumber());


        return listitemView;
    }


}
