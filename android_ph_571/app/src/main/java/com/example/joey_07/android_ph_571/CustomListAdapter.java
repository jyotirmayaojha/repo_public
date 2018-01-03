package com.example.joey_07.android_ph_571;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by joey_07 on 25/11/17.
 */

public class CustomListAdapter extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] itemname1;
    private final String[] itemname2;
    private final Integer[] imgid;

    public CustomListAdapter(Activity context, String[] itemname1, String[] itemname2,Integer[] imgid) {
        super(context, R.layout.mylist, itemname1);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname1=itemname1;
        this.itemname2=itemname2;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item1);
        TextView extratxt = (TextView) rowView.findViewById(R.id.item2);
        if(position == 2)
        {
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            imageView.setImageResource(imgid[position]);
        }

            txtTitle.setText(itemname1[position]);
            extratxt.setText(itemname2[position]);


        return rowView;
    }
}

