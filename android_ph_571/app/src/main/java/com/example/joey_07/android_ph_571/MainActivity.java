package com.example.joey_07.android_ph_571;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;
import android.util.Log;
import android.widget.Button;
import android.widget.AdapterView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.widget.AdapterView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView user_input;
    private ArrayAdapter<String> autocompleteadapter;
    String data = "";
    private Spinner spin;
    private Spinner spin1;
    private Switch s;
    ArrayList<String> mylist = new ArrayList<String>();
    TextView results;
    RequestQueue requestQueue;
    private SharedPreferences sharedPref_key;
    private String sym, lastprice, change, timestamp, Open, Close, Range, Volume, change_percent;
    private float change_f;

    public void getrefreshed(View view) {
        Toast.makeText(this, "here", Toast.LENGTH_SHORT).show();
        sharedPref_key = getSharedPreferences("file", getApplicationContext().MODE_PRIVATE);


        Map<String, ?> allEntries = sharedPref_key.getAll();
        final ArrayList<String> update_refresh = new ArrayList<String>();
        for (final Map.Entry<String, ?> entry : allEntries.entrySet()) {
            //Log.d("watch", entry.getKey());
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            String url = "https://bootstrap-stocksearch.appspot.com/compact?stocktickersymbol="+entry.getKey();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONArray jsonArr = null;
                            try
                            {
                                JSONObject jsonObj = new JSONObject(response);
                                Log.d("stock", response);
                                Log.d("stock", jsonObj.getString("Time Series (Daily)"));
                                String parse_this = jsonObj.getString("Time Series (Daily)");
                                JSONObject json_ = new JSONObject(parse_this);
                                Log.d("stock_", json_.toString());
                                Iterator<?> keys = json_.keys();
                                String key_timestamp = (String)keys.next();
                                String key_sec = (String)keys.next();
                                Log.d("key", json_.getString(key_timestamp));
                                JSONObject timestamp_data = new JSONObject(json_.getString(key_timestamp));


                                Log.d("key", json_.getString(key_sec));
                                JSONObject timestamp_bef = new JSONObject(json_.getString(key_sec));
                                sym = entry.getKey();
                                lastprice = timestamp_bef.getString("4. close");
                                change_f = Float.valueOf(timestamp_bef.getString("4. close")) - Float.valueOf(timestamp_data.getString("4. close"));
                                change = String.format(" %.2f",(Float.valueOf(timestamp_bef.getString("4. close")) - Float.valueOf(timestamp_data.getString("4. close"))));
                                timestamp = key_timestamp;
                                Open = timestamp_data.getString("1. open");
                                Close = timestamp_data.getString("4. close");
                                Range = timestamp_data.getString("3. low") +" - "+ timestamp_data.getString("2. high");
                                Volume = timestamp_data.getString("5. volume");
                                change_percent = String.format(" %.2f", ((Float.valueOf(timestamp_bef.getString("4. close")) - Float.valueOf(timestamp_data.getString("4. close")))/100));
                                final String first = sym;
                                final String second = lastprice;
                                final String third= change + "("+change_percent+"%)";
                                String send_mainactivity = first.trim()+":"+first.trim()+"="+second.trim()+"="+third.trim();
                                update_refresh.add(send_mainactivity);

                            }
                            catch(JSONException e)
                            {
                                display_fav();
                                e.printStackTrace();
                                Toast.makeText(MainActivity.this,"couldn't perform the volley operation", Toast.LENGTH_SHORT).show();
                            }




                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        // Handles errors that occur due to Volley
                        public void onErrorResponse(VolleyError error) {
                            display_fav();
                            Log.e("Volley", "Error");

                        }
                    }
            );

            requestQueue.add(stringRequest);

        }


    }

    protected void display_fav() {

        sharedPref_key = getSharedPreferences("file", getApplicationContext().MODE_PRIVATE);

       /* sharedPref_key.edit().remove("BAB").commit();
        sharedPref_key.edit().remove("AAPL").commit();
        sharedPref_key.edit().remove("AAN").commit();

        */

        Map<String, ?> allEntries = sharedPref_key.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("watch", entry.getKey());
        }

        /*for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            sharedPref_key.edit().remove(entry.getKey()).commit();
        }*/


        ArrayList<String> add_fav = new ArrayList<String>();
        int count = 0;
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("dekh", entry.getKey() + ": " + entry.getValue().toString());
            String keyvalue = entry.getKey() + ": " + entry.getValue().toString();
            add_fav.add(keyvalue);
            count++;
        }

        ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>(count);
        for (int i = 0; i<count; i++)
        {
            ArrayList<String> now= new ArrayList<String>();
            String[] key_val = add_fav.get(i).split(":");
            String key = key_val[0];
            String value = key_val[1];
            String[] values = key_val[1].split("=");
            //Log.d("Keyval", values.toString());
            String symbol = values[0];
            String lprice = values[1];
            String cg = values[2];

            String cg1 = cg.split("\\(")[0];
            String cg2 = cg.split("\\(")[1].split("\\)")[0];
            Log.d("cg", cg+cg1+cg2);
            now.add(0, key);
            now.add(1, lprice);
            now.add(2, cg1);
            now.add(3, cg2);
            list1.add(now);
        }


            Collections.sort(list1, new Comparator<ArrayList<String>>() {
                @Override
                public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                    return o1.get(0).trim().compareTo(o2.get(0).trim());
                }
            });


        for(ArrayList<String> innerList : list1) {
            for(String number : innerList) {
                Log.d("find", number);
            }
        }

        //if(! (spin.getSelectedItem().toString().matches("Name") && spin1.getSelectedItem().toString().matches("Ascending")))
               /* {
                    for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                        sharedPref_key.edit().remove(entry.getKey()).commit();
                    }
                }*/

        final SharedPreferences sharedPref = getSharedPreferences("file", getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();


        ArrayList<String> update_asc = new ArrayList<String>();

        for(ArrayList<String> innerList : list1) {
            String a = innerList.get(0) + ":" + innerList.get(0) + "=" + innerList.get(1) + "=" + innerList.get(2)+"("+innerList.get(3)+")";
            update_asc.add(a);
            //editor.putString(innerList.get(0), a);
            //editor.commit();


        }


        CustomListFav adapter = new CustomListFav(this, update_asc);
        ListView fav_list_view = findViewById(R.id.favourites);
        fav_list_view.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();



        //Toast.makeText(this, "Welcome back", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);
        user_input = findViewById(R.id.autoCompleteTextView1);
        display_fav();
        SharedPreferences sharedPref = getSharedPreferences("sym", getApplicationContext().MODE_PRIVATE);
        user_input.setText(sharedPref.getString("symbol", ""));
        user_input.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        String tempstr = charSequence.toString();
                        tempstr = tempstr.trim();
                        if(!tempstr.isEmpty())
                        {
                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            String url = "http://homework8nodejs-env.us-east-2.elasticbeanstalk.com/autocomplete/"+tempstr;
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            JSONArray jsonArr = null;
                                            try
                                            {
                                                Log.d("auto", response);
                                                jsonArr = new JSONArray(response);
                                            }
                                            catch(JSONException e)
                                            {
                                                e.printStackTrace();
                                            }

                                            ArrayList<String> displayAutocomplete = new ArrayList<String>();
                                            for(int i = 0; i< jsonArr.length(); i++)
                                            {
                                                JSONObject jsonObject = null;
                                                String symbol = "";
                                                String name = "";
                                                String exchange = "";
                                                try
                                                {
                                                    jsonObject = jsonArr.getJSONObject(i);
                                                    symbol = jsonObject.getString("Symbol");
                                                    name = jsonObject.getString("Name");
                                                    exchange = jsonObject.getString("Exchange");
                                                }
                                                catch(Exception e)
                                                {
                                                    e.printStackTrace();
                                                }
                                                String disp = symbol + " - "+ name+" ("+exchange+")";
                                                displayAutocomplete.add(disp);

                                                //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_list_item, data);
                                                //view.setAdapter(adapter);

                                                autocompleteadapter = new ArrayAdapter<String>(getBaseContext(), R.layout.my_list_item, displayAutocomplete);
                                                user_input.setAdapter(autocompleteadapter);
                                                autocompleteadapter.notifyDataSetChanged();
                                            }

                                        }

                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        // Handles errors that occur due to Volley
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("Volley", "Error");
                                        }
                                    }
                            );
                            requestQueue.add(stringRequest);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "empty symbol", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                }
        );

        spin = findViewById(R.id.spinner_sortby);
        spin1 = findViewById(R.id.spinner_order);

        String[] opt1 = {"Name", "Price", "Change", "Change Percent"};
        String[] opt2 = {"Ascending", "Descending"};

        ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item , opt1);
        aa.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spin.setAdapter(aa);

        ArrayAdapter<String> aa1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item , opt2);
        aa1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spin1.setAdapter(aa1);


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                sharedPref_key = getSharedPreferences("file", getApplicationContext().MODE_PRIVATE);
                //Toast.makeText(MainActivity.this, spin.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplication(), "HELLO"+spin.getSelectedItem().toString().trim(), Toast.LENGTH_SHORT).show();
                Map<String, ?> allEntries = sharedPref_key.getAll();
                ArrayList<String> add_fav = new ArrayList<String>();
                int count = 0;
                for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                    Log.d("dekh", entry.getKey() + ": " + entry.getValue().toString());
                    String keyvalue = entry.getKey() + ": " + entry.getValue().toString();
                    add_fav.add(keyvalue);
                    count++;
                }

                ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>(count);
                for (int i = 0; i<count; i++)
                {
                    ArrayList<String> now= new ArrayList<String>();
                    String[] key_val = add_fav.get(i).split(":");
                    String key = key_val[0];
                    String value = key_val[1];
                    String[] values = key_val[1].split("=");
                    //Log.d("Keyval", values.toString());
                    String symbol = values[0];
                    String lprice = values[1];
                    String cg = values[2];

                    String cg1 = cg.split("\\(")[0];
                    String cg2 = cg.split("\\(")[1].split("\\)")[0];
                    Log.d("cg", cg+cg1+cg2);
                    now.add(0, key);
                    now.add(1, lprice);
                    now.add(2, cg1);
                    now.add(3, cg2);
                    list1.add(now);
                }

                if(parentView.getItemAtPosition(position).toString().matches("Name") && spin1.getSelectedItem().toString().matches("Ascending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return o1.get(0).trim().compareTo(o2.get(0).trim());
                        }
                    });
                }
                else if(parentView.getItemAtPosition(position).toString().matches("Price") && spin1.getSelectedItem().toString().matches("Ascending"))
                {
                    // Toast.makeText(MainActivity.this, "wqefwejkbwekrgn", Toast.LENGTH_SHORT).show();
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            //Toast.makeText(MainActivity.this, o1.get(1)+":"+o2.get(1) , Toast.LENGTH_SHORT).show();
                            return Float.compare(Float.parseFloat(o1.get(1).trim()), Float.parseFloat(o2.get(1).trim()));
                        }
                    });
                }
                else if(parentView.getItemAtPosition(position).toString().matches("Change") && spin1.getSelectedItem().toString().matches("Ascending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return Float.compare(Float.parseFloat(o1.get(2).trim()), Float.parseFloat(o2.get(2).trim()));
                        }
                    });
                }
                else if(parentView.getItemAtPosition(position).toString().matches("Change Percent") && spin1.getSelectedItem().toString().matches("Ascending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return (Float.compare(Float.parseFloat(o1.get(3).split("%")[0].trim()), Float.parseFloat(o2.get(3).split("%")[0].trim())));
                        }
                    });
                }

                if(parentView.getItemAtPosition(position).toString().matches("Name") && spin1.getSelectedItem().toString().matches("Descending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return -o1.get(0).trim().compareTo(o2.get(0).trim());
                        }
                    });
                }
                else if(parentView.getItemAtPosition(position).toString().matches("Price") && spin1.getSelectedItem().toString().matches("Descending"))
                {
                    // Toast.makeText(MainActivity.this, "wqefwejkbwekrgn", Toast.LENGTH_SHORT).show();
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            //Toast.makeText(MainActivity.this, o1.get(1)+":"+o2.get(1) , Toast.LENGTH_SHORT).show();
                            return -Float.compare(Float.parseFloat(o1.get(1).trim()), Float.parseFloat(o2.get(1).trim()));
                        }
                    });
                }
                else if(parentView.getItemAtPosition(position).toString().matches("Change") && spin1.getSelectedItem().toString().matches("Descending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return -Float.compare(Float.parseFloat(o1.get(2).trim()), Float.parseFloat(o2.get(2).trim()));
                        }
                    });
                }
                else if(parentView.getItemAtPosition(position).toString().matches("Change Percent") && spin1.getSelectedItem().toString().matches("Descending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return -(Float.compare(Float.parseFloat(o1.get(3).split("%")[0].trim()), Float.parseFloat(o2.get(3).split("%")[0].trim())));
                        }
                    });
                }

                for(ArrayList<String> innerList : list1) {
                    for(String number : innerList) {
                        Log.d("find", number);
                    }
                }

                //if(! (spin.getSelectedItem().toString().matches("Name") && spin1.getSelectedItem().toString().matches("Ascending")))
               /* {
                    for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                        sharedPref_key.edit().remove(entry.getKey()).commit();
                    }
                }*/

                final SharedPreferences sharedPref = getSharedPreferences("file", getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();


                ArrayList<String> update_asc = new ArrayList<String>();

                for(ArrayList<String> innerList : list1) {
                    String a = innerList.get(0) + ":" + innerList.get(0) + "=" + innerList.get(1) + "=" + innerList.get(2)+"("+innerList.get(3)+")";
                    update_asc.add(a);
                    //editor.putString(innerList.get(0), a);
                    //editor.commit();
                    Log.d("asc", a);

                }

                CustomListFav adapter = new CustomListFav(MainActivity.this, update_asc);
                ListView fav_list_view = findViewById(R.id.favourites);
                fav_list_view.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                //display_fav();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                sharedPref_key = getSharedPreferences("file", getApplicationContext().MODE_PRIVATE);
                //Toast.makeText(MainActivity.this, spin1.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();


                Map<String, ?> allEntries = sharedPref_key.getAll();
                ArrayList<String> add_fav = new ArrayList<String>();
                int count = 0;
                for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                    Log.d("dekh", entry.getKey() + ": " + entry.getValue().toString());
                    String keyvalue = entry.getKey() + ": " + entry.getValue().toString();
                    add_fav.add(keyvalue);
                    count++;
                }

                ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>(count);
                for (int i = 0; i<count; i++)
                {
                    ArrayList<String> now= new ArrayList<String>();
                    String[] key_val = add_fav.get(i).split(":");
                    String key = key_val[0];
                    String value = key_val[1];
                    String[] values = key_val[1].split("=");
                    //Log.d("Keyval", values.toString());
                    String symbol = values[0];
                    String lprice = values[1];
                    String cg = values[2];
                    String cg1 = cg.split("\\(")[0];
                    String cg2 = cg.split("\\(")[1].split("\\)")[0];
                    now.add(0, key);
                    now.add(1, lprice);
                    now.add(2, cg1);
                    now.add(3, cg2);
                    list1.add(now);
                }


                if(spin.getSelectedItem().toString().matches("Name") && parentView.getItemAtPosition(position).toString().matches("Ascending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return (o1.get(0).compareTo(o2.get(0)));
                        }
                    });
                }
                else if(spin.getSelectedItem().toString().matches("Price") && parentView.getItemAtPosition(position).toString().matches("Ascending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return (Float.compare(Float.parseFloat(o1.get(1).trim()), Float.parseFloat(o2.get(1).trim())));
                        }
                    });
                }
                else if(spin.getSelectedItem().toString().matches("Change") && parentView.getItemAtPosition(position).toString().matches("Ascending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return (Float.compare(Float.parseFloat(o1.get(2).trim()), Float.parseFloat(o2.get(2).trim())));
                        }
                    });
                }
                else if(spin.getSelectedItem().toString().matches("Change Percent") && parentView.getItemAtPosition(position).toString().matches("Ascending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return (Float.compare(Float.parseFloat(o1.get(3).split("%")[0].trim()), Float.parseFloat(o2.get(3).split("%")[0].trim())));
                        }
                    });
                }




                if(spin.getSelectedItem().toString().matches("Name") && parentView.getItemAtPosition(position).toString().matches("Descending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return -(o1.get(0).compareTo(o2.get(0)));
                        }
                    });
                }
                else if(spin.getSelectedItem().toString().matches("Price") && parentView.getItemAtPosition(position).toString().matches("Descending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return - (Float.compare(Float.parseFloat(o1.get(1).trim()), Float.parseFloat(o2.get(1).trim())));
                        }
                    });
                }
                else if(spin.getSelectedItem().toString().matches("Change") && parentView.getItemAtPosition(position).toString().matches("Descending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return -(Float.compare(Float.parseFloat(o1.get(2).trim()), Float.parseFloat(o2.get(2).trim())));
                        }
                    });
                }
                else if(spin.getSelectedItem().toString().matches("Change Percent") && parentView.getItemAtPosition(position).toString().matches("Descending"))
                {
                    Collections.sort(list1, new Comparator<ArrayList<String>>() {
                        @Override
                        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                            return -(Float.compare(Float.parseFloat(o1.get(3).split("%")[0].trim()), Float.parseFloat(o2.get(3).split("%")[0].trim())));
                        }
                    });
                }

                for(ArrayList<String> innerList : list1) {
                    for(String number : innerList) {
                        Log.d("find", number);
                    }
                }

                //if(spin.getSelectedItem().toString().matches("Name") && spin1.getSelectedItem().toString().matches("Ascending"))
                /*for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                    sharedPref_key.edit().remove(entry.getKey()).commit();
                }*/


                ArrayList<String> update_des = new ArrayList<String>();
                sharedPref_key = getSharedPreferences("file", getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref_key.edit();
                for(ArrayList<String> innerList : list1) {
                    String a = innerList.get(0) + ":" + innerList.get(0) + "=" + innerList.get(1) + "=" + innerList.get(2)+"("+innerList.get(3)+")";
                    update_des.add(a);
                    //sharedPref_key.edit().remove(innerList.get(0)).commit();
                    //editor.putString(innerList.get(0), a);
                    //editor.commit();
                }

                CustomListFav adapter = new CustomListFav(MainActivity.this, update_des);
                ListView fav_list_view = findViewById(R.id.favourites);
                fav_list_view.setAdapter(adapter);
                adapter.notifyDataSetChanged();


                //display_fav();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        ImageView img = findViewById(R.id.imageView1);

        s = (Switch) findViewById(R.id.auto_refresh);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) //Line A
            {
                if(s.isChecked()==true)
                {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();


                            sharedPref_key = getSharedPreferences("file", getApplicationContext().MODE_PRIVATE);


                            Map<String, ?> allEntries = sharedPref_key.getAll();
                            final ArrayList<String> update_refresh = new ArrayList<String>();
                            for (final Map.Entry<String, ?> entry : allEntries.entrySet()) {
                                //Log.d("watch", entry.getKey());
                                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                String url = "https://bootstrap-stocksearch.appspot.com/compact?stocktickersymbol="+entry.getKey();
                                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                JSONArray jsonArr = null;
                                                try
                                                {
                                                    JSONObject jsonObj = new JSONObject(response);
                                                    Log.d("stock", response);
                                                    Log.d("stock", jsonObj.getString("Time Series (Daily)"));
                                                    String parse_this = jsonObj.getString("Time Series (Daily)");
                                                    JSONObject json_ = new JSONObject(parse_this);
                                                    Log.d("stock_", json_.toString());
                                                    Iterator<?> keys = json_.keys();
                                                    String key_timestamp = (String)keys.next();
                                                    String key_sec = (String)keys.next();
                                                    Log.d("key", json_.getString(key_timestamp));
                                                    JSONObject timestamp_data = new JSONObject(json_.getString(key_timestamp));


                                                    Log.d("key", json_.getString(key_sec));
                                                    JSONObject timestamp_bef = new JSONObject(json_.getString(key_sec));
                                                    sym = entry.getKey();
                                                    lastprice = timestamp_bef.getString("4. close");
                                                    change_f = Float.valueOf(timestamp_bef.getString("4. close")) - Float.valueOf(timestamp_data.getString("4. close"));
                                                    change = String.format(" %.2f",(Float.valueOf(timestamp_bef.getString("4. close")) - Float.valueOf(timestamp_data.getString("4. close"))));
                                                    timestamp = key_timestamp;
                                                    Open = timestamp_data.getString("1. open");
                                                    Close = timestamp_data.getString("4. close");
                                                    Range = timestamp_data.getString("3. low") +" - "+ timestamp_data.getString("2. high");
                                                    Volume = timestamp_data.getString("5. volume");
                                                    change_percent = String.format(" %.2f", ((Float.valueOf(timestamp_bef.getString("4. close")) - Float.valueOf(timestamp_data.getString("4. close")))/100));
                                                    final String first = sym;
                                                    final String second = lastprice;
                                                    final String third= change + "("+change_percent+"%)";
                                                    String send_mainactivity = first.trim()+":"+first.trim()+"="+second.trim()+"="+third.trim();
                                                    update_refresh.add(send_mainactivity);

                                                }
                                                catch(JSONException e)
                                                {
                                                    display_fav();
                                                    e.printStackTrace();
                                                    Toast.makeText(MainActivity.this,"couldn't perform the volley operation", Toast.LENGTH_SHORT).show();
                                                }




                                            }

                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            // Handles errors that occur due to Volley
                                            public void onErrorResponse(VolleyError error) {
                                                display_fav();
                                                Log.e("Volley", "Error");

                                            }
                                        }
                                );

                                requestQueue.add(stringRequest);

                            }
                            if(s.isChecked()==true)
                            handler.postDelayed(this, 5000);


                        }
                    }, 5000);

                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Switch s = (Switch) findViewById(R.id.auto_refresh);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_input = findViewById(R.id.autoCompleteTextView1);
        
        SharedPreferences sharedPref = getSharedPreferences("sym", getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("symbol", "");
        editor.apply();
        //display_fav();



        user_input.addTextChangedListener(
               new TextWatcher() {
                   @Override
                   public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                   }

                   @Override
                   public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    String tempstr = charSequence.toString();
                    tempstr = tempstr.trim();
                    if(!tempstr.isEmpty())
                    {
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        String url = "http://homework8nodejs-env.us-east-2.elasticbeanstalk.com/autocomplete/"+tempstr;
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        JSONArray jsonArr = null;
                                        try
                                        {
                                            Log.d("auto", response);
                                            jsonArr = new JSONArray(response);
                                        }
                                        catch(JSONException e)
                                        {
                                            e.printStackTrace();
                                        }


                                        for(int i = 0; i< jsonArr.length(); i++)
                                        {
                                            ArrayList<String> displayAutocomplete = new ArrayList<String>();
                                            JSONObject jsonObject = null;
                                            String symbol = "";
                                            String name = "";
                                            String exchange = "";
                                            try
                                            {
                                                jsonObject = jsonArr.getJSONObject(i);
                                                symbol = jsonObject.getString("Symbol");
                                                name = jsonObject.getString("Name");
                                                exchange = jsonObject.getString("Exchange");
                                            }
                                            catch(Exception e)
                                            {
                                                e.printStackTrace();
                                            }
                                            String disp = symbol + " - "+ name+" ("+exchange+")";
                                            displayAutocomplete.add(disp);

                                            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_list_item, data);
                                            //view.setAdapter(adapter);
                                            Log.d("auto", disp);
                                            autocompleteadapter = new ArrayAdapter<String>(getBaseContext(), R.layout.my_list_item, displayAutocomplete);
                                            user_input.setAdapter(autocompleteadapter);
                                            autocompleteadapter.notifyDataSetChanged();
                                        }

                                    }

                                },
                        new Response.ErrorListener() {
                            @Override
                            // Handles errors that occur due to Volley
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Volley", "Error");
                            }
                        }
                        );
                        requestQueue.add(stringRequest);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "empty symbol", Toast.LENGTH_SHORT).show();
                    }
                       /* insert logic */
                   }

                   @Override
                   public void afterTextChanged(Editable editable) {

                   }
               }
       );


        sharedPref_key = getSharedPreferences("file", getApplicationContext().MODE_PRIVATE);
        String rec = sharedPref.getString("symbol", "");
        String rec1 = sharedPref_key.getString(rec.split("\\s+")[0], "");
        //Toast.makeText(this, rec1 , Toast.LENGTH_SHORT).show();
        display_fav();

        spin = findViewById(R.id.spinner_sortby);
        spin1 = findViewById(R.id.spinner_order);

        String[] opt1 = {"Name", "Price", "Change", "Change Percent"};
        String[] opt2 = {"Ascending", "Descending"};

        ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item , opt1);
        aa.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spin.setAdapter(aa);

        ArrayAdapter<String> aa1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item , opt2);
        aa1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spin1.setAdapter(aa1);





    }



    public void submit_symbol(View view) {
        if (user_input.getText().toString().length()==0)
        {
            Toast.makeText(this, "Please enter a stock name or symbol", Toast.LENGTH_SHORT).show();
        }
        else
        {
            SharedPreferences sharedPref = getSharedPreferences("sym", getApplicationContext().MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("symbol", user_input.getText().toString());
            editor.putString("symbol_str", user_input.getText().toString().split("\\s+")[0]);
            Log.d("auto", user_input.getText().toString().split("\\s+")[0]);
            editor.commit();
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),SecActivity.class);
            intent.putExtra("sym_intent", user_input.getText().toString().split("\\s+")[0]);
            startActivity(intent);
        }
    }


    public void clear_btn(View view) {
        user_input.setText("");
        SharedPreferences sharedPref = getSharedPreferences("sym", getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("symbol", "");
        editor.apply();
        autocompleteadapter.notifyDataSetChanged();
    }




}

