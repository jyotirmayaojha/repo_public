package com.example.joey_07.android_ph_571;

/**
 * Created by joey_07 on 26/11/17.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListFav extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> itemname1;
    public CustomListFav(Activity context, ArrayList<String> itemname1) {
        super(context, R.layout.favourite_list, itemname1);
        this.context=context;
        this.itemname1=itemname1;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.favourite_list, null,true);
        TextView sym = (TextView) rowView.findViewById(R.id.part1);
        TextView lastprice = (TextView) rowView.findViewById(R.id.part2);
        TextView change= (TextView) rowView.findViewById(R.id.part3);
        String[] key_val = itemname1.get(position).split(":");

        String key = key_val[0];
        String value = key_val[1];
        String[] values = key_val[1].split("=");
        //Log.d("Keyval", values.toString());
        String symbol = values[0];
        String lprice = values[1];
        String cg = values[2];
        sym.setText(symbol);
        lastprice.setText(lprice);
        change.setText(cg);
        return rowView;
    }
}
