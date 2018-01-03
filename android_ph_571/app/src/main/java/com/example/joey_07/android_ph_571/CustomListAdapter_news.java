package com.example.joey_07.android_ph_571;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by joey_07 on 26/11/17.
 */

public class CustomListAdapter_news extends ArrayAdapter<String>{
    private final Activity context;
    private final ArrayList<String> itemname1;
    private final ArrayList<String> itemname2;
    private final ArrayList<String> itemname3;
    private final ArrayList<String> itemname4;

    public CustomListAdapter_news(Activity context, ArrayList<String> itemname1, ArrayList<String> itemname2, ArrayList<String> itemname3, ArrayList<String> itemname4)
    {
        super(context, R.layout.newsfeed_list_item, itemname1);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname1=itemname1;
        this.itemname2=itemname2;
        this.itemname3=itemname3;
        this.itemname4=itemname4;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.newsfeed_list_item, null,true);
            TextView Title = (TextView) rowView.findViewById(R.id.newsfeed1);
            TextView  pubDate = (TextView) rowView.findViewById(R.id.newsfeed2);
            TextView author = (TextView) rowView.findViewById(R.id.newsfeed3);
            Title.setText(itemname1.get(position));
            pubDate.setText(itemname3.get(position));
            author.setText(itemname4.get(position));
        return rowView;
    }
}
