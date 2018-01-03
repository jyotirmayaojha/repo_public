package com.example.joey_07.android_ph_571;

/**
 * Created by joey_07 on 24/11/17.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import com.google.gson.Gson;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private Button btnTEST;
    private ListView stock_info;
    private String sym, lastprice, change, timestamp, Open, Close, Range, Volume, change_percent;
    private Spinner spin;
    private static WebView ourBrow;
    private float change_f;
    private AutoCompleteTextView user_input;
    private SharedPreferences sharedPref_key;



    @Override
    public void onResume() {
        super.onResume();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab1_fragment,container,false);
        final ImageView star_e = (ImageView) view.findViewById(R.id.star_empty);
        final TextView change_param = (TextView) view.findViewById(R.id.changegraph);



       /* final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        */

        Bundle extras = getActivity().getIntent().getExtras();
        setRetainInstance(true);
        final String tempstr = extras.getString("sym_intent");
        Log.d("check this:", tempstr.trim());
        stock_info = (ListView) view.findViewById(R.id.stock);
        spin = (Spinner) view.findViewById(R.id.spinner1);
        user_input= view.findViewById(R.id.autoCompleteTextView1);



        SharedPreferences sharedPref_key = getContext().getSharedPreferences("file", getContext().MODE_PRIVATE);

        /*sharedPref_key.edit().remove("AAPL").commit();
        sharedPref_key.edit().remove("MSFT").commit();
        sharedPref_key.edit().remove("ORCL").commit();*/
        int flag = 0;
        Map<String, ?> allEntries = sharedPref_key.getAll();
        ArrayList<String> add_fav = new ArrayList<String>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("VAL", entry.getKey().trim());
            if(entry.getKey().trim().equals(tempstr.trim()))
            {
                flag = 1;
            }
        }



        Log.d("VAL", tempstr+flag);


        if (flag == 1)
        {
            star_e.setImageResource(R.drawable.filled);
            star_e.setTag("filled");
        }
        else
        {
            star_e.setImageResource(R.drawable.empty);
            star_e.setTag("empty");
        }



            RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
            String url = "http://homework8nodejs-env.us-east-2.elasticbeanstalk.com/PRICE-VOLUME/"+tempstr;
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
                                sym = tempstr;
                                lastprice = timestamp_bef.getString("4. close");
                                change_f = Float.valueOf(timestamp_bef.getString("4. close")) - Float.valueOf(timestamp_data.getString("4. close"));
                                change = String.format(" %.2f",(Float.valueOf(timestamp_bef.getString("4. close")) - Float.valueOf(timestamp_data.getString("4. close"))));
                                timestamp = key_timestamp;
                                Open = timestamp_data.getString("1. open");
                                Close = timestamp_data.getString("4. close");
                                Range = timestamp_data.getString("3. low") +" - "+ timestamp_data.getString("2. high");
                                Volume = timestamp_data.getString("5. volume");
                                change_percent = String.format(" %.2f", ((Float.valueOf(timestamp_bef.getString("4. close")) - Float.valueOf(timestamp_data.getString("4. close")))/100));
                                //dialog.dismiss();
                                view.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                            }
                            catch(JSONException e)
                            {
                                view.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                                Toast.makeText(getActivity(),"couldn't perform the volley operation", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();

                            }

                            String [] item1 = {"Stock Symbol", "Last Price", "Change", "Timestamp", "Open", "Close", "Day's Range", "Volume"};
                            String [] item2 = {sym, lastprice, change + " ("+change_percent+"%)  ", timestamp, Open, Close, Range, Volume};
                            Integer [] imgid = new Integer[8];
                            if(change_f>=0)
                            {
                                for(int j = 0; j<8; j++)
                                    imgid[j]=R.drawable.up;
                            }
                            else
                            {
                                for(int j = 0; j<8; j++)
                                    imgid[j]=R.drawable.down;
                            }

                            /*ArrayAdapter<String> listviewadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listitems);*/
                            //ArrayAdapter<String> listviewadapter=new ArrayAdapter<String>(getActivity(), android.R.layout.mylist, listitems);
                            CustomListAdapter adapter=new CustomListAdapter(getActivity(), item1, item2, imgid);
                            stock_info.setAdapter(adapter);
                            String[] dropoptions = {"PRICE", "SMA", "EMA", "MACD", "RSI", "ADX", "CCI"};
                            //spin.setOnItemClickListener(getActivity());

                            ArrayAdapter<String> aa = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, dropoptions);
                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spin.setAdapter(aa);
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        // Handles errors that occur due to Volley
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Volley", "Error");
                            view.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                            Toast.makeText(getActivity(),"no Internet", Toast.LENGTH_SHORT).show();
                        }
                    }
            );

            requestQueue.add(stringRequest);


        ourBrow = view.findViewById(R.id.webview_charts);
        ourBrow.getSettings().setBuiltInZoomControls(true);
        ourBrow.getSettings().setJavaScriptEnabled(true);

        change_param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Toast.makeText(getActivity(), "HELLO"+spin.getSelectedItem().toString().trim(), Toast.LENGTH_SHORT).show();
                        String source = "file:///android_asset/"+spin.getSelectedItem().toString()+".html";
                    //if(spin.getSelectedItem().toString().trim() !="PRICE") {
                        ourBrow.loadUrl(source);
                        Log.d("check", tempstr);
                        ourBrow.setWebViewClient(new WebViewClient() {
                            public void onPageFinished(WebView view, String url) {
                                ourBrow.loadUrl("javascript:init('" + tempstr + "')");

                            }
                        });
                    //}
            }
        });

        star_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final String first = sym;
                final String second = lastprice;
                final String third= change + "("+change_percent+"%)";
                String send_mainactivity = first.trim()+"="+second.trim()+"="+third.trim();
                SharedPreferences sharedPref = getContext().getSharedPreferences("file", getActivity().MODE_PRIVATE);

                if(star_e.getTag() == "empty")
                {
                    star_e.setImageResource(R.drawable.filled);
                    star_e.setTag("filled");
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(sym, send_mainactivity.trim());
                    editor.commit();
                }
                else
                {
                    sharedPref.edit().remove(tempstr).commit();
                    star_e.setImageResource(R.drawable.empty);
                    star_e.setTag("empty");
                }
            }
        });


            return view;
    }



}