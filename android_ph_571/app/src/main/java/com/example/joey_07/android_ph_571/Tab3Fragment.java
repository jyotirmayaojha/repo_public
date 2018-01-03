package com.example.joey_07.android_ph_571;


/**
 * Created by joey_07 on 24/11/17.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.io.StringReader;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.io.FileInputStream;


public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab3Fragment";
    private String LOG_TAG = "XML";
    private int UpdateFlag = 0;
    private String url ="";
    private int flag_title = 0;
    private int flag_link = 0;
    private ArrayList<String> title1;
    private ArrayList<String> link1;
    private ArrayList<String> pubdate1;
    private ArrayList<String> author1;
    private ArrayList<String> title2 ;
    private ArrayList<String> link2 ;
    private ArrayList<String> pubdate2 ;
    private ArrayList<String> author2 ;
    private ListView newsfeed ;
    private View view;
    ViewPager viewPager;

    String symbol = "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //  if(view == null)
            view = inflater.inflate(R.layout.tab3_fragment,container,false);
       // else
        //{
           // ((ViewGroup) view.getParent()).removeView(view);
       // }
        newsfeed = (ListView) view.findViewById(R.id.newsfeed_main);
        //public ArrayAdapter<String> newsadapter;
        Bundle extras = getActivity().getIntent().getExtras();
        symbol = extras.getString("sym_intent");
        //url = "http://homework8nodejs-env.us-east-2.elasticbeanstalk.com/newsfeed/"+symbol;
        url = "https://seekingalpha.com/api/sa/combined/"+symbol+".xml";
        Log.d("news","here");
        setRetainInstance(true);
        title1 = new ArrayList<String>();
        link1 = new ArrayList<String>();
        pubdate1 = new ArrayList<String>();
        author1 = new ArrayList<String>();

        //viewPager = (ViewPager) view.findViewById(R.id.newsfeed3);
        //viewPager.setOffscreenPageLimit(3);

        new GetXMLFromServer().execute();
        return view;
    }



    public void ParseXML(String xmlString){

        try {
            flag_link = 0;
            flag_title = 0;
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            xmlString = xmlString.replace("$", "");
            parser.setInput(new StringReader(xmlString));
            Log.d("cool", xmlString);
            int eventType = parser.getEventType();


            while (eventType != XmlPullParser.END_DOCUMENT){

                if(eventType== XmlPullParser.START_TAG){

                    String name = parser.getName();
                    if(name.equals("title")){

                        //String ref = parser.getAttributeValue(null,"ref");
                        //Log.d(LOG_TAG,"ref:" + ref);

                        if(parser.next() == XmlPullParser.TEXT) {

                            String UpdateFlag = parser.getText();
                            if(flag_title == 1)
                            {
                                title1.add(UpdateFlag);
                            }
                            Log.d(LOG_TAG,"title:" + UpdateFlag);
                            flag_title = 1;
                        }
                    }
                    else
                        if(name.equals("link")) {

                        if(parser.next() == XmlPullParser.TEXT) {

                            String Name = parser.getText();
                            Log.d(LOG_TAG,"link:" + Name);
                            if(flag_link == 1)
                            {
                                link1.add(Name);
                            }
                            flag_link = 1;
                            if(Name == "https://seekingalpha.com/symbol/"+symbol+"/news?source=feed_symbol_"+symbol)
                            {
                                Log.d("link:", "yes");
                            }
                        }
                    }else if(name.equals("pubDate")) {

                        if(parser.next() == XmlPullParser.TEXT) {
                            String Range = parser.getText();
                            Log.d(LOG_TAG,"date:" + Range);
                            pubdate1.add(Range);
                        }
                    }
                    else if(name.equals("author_name"))
                    {
                        if(parser.next() == XmlPullParser.TEXT) {
                            String Range = parser.getText();
                            Log.d(LOG_TAG,"author:" + Range);
                            author1.add(Range);
                        }
                    }


                }
                else if(eventType== XmlPullParser.END_TAG){


                }
                eventType = parser.next();

            }

            title2 = new ArrayList<String>();
            link2 = new ArrayList<String>();
            pubdate2 = new ArrayList<String>();
            author2 = new ArrayList<String>();

            Log.d("sizes", String.valueOf(title1.size())+String.valueOf(link1.size())+String.valueOf(pubdate1.size())+String.valueOf(author1.size()));
            for(int i=0; i<title1.size(); i++)
            {
                if(link1.get(i).indexOf("article")!=-1)
                {
                    title2.add(title1.get(i));
                    link2.add(link1.get(i));
                    pubdate2.add(pubdate1.get(i));
                    author2.add(author1.get(i));
                }
            }


            CustomListAdapter_news adapter=new CustomListAdapter_news(getActivity(), title2, link2, pubdate2, author2);
            newsfeed.setAdapter(adapter);

            newsfeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            //Toast.makeText(getActivity(),"here"+i, Toast.LENGTH_SHORT).show();
                            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link2.get(i)));
                            if(webIntent.resolveActivity(getActivity().getPackageManager())!=null)
                            {
                                startActivity(webIntent);
                            }
                            else
                            {
                                Toast.makeText(getActivity(),"I am sorry we couldn't reach: "+link2.get(i), Toast.LENGTH_SHORT).show();
                            }
                }
            });
           // getView().findViewById(R.id.loadingPanel2).setVisibility(View.GONE);
        }catch (Exception e){
            Log.d(LOG_TAG,"Error in ParseXML()",e);
            //Toast.makeText(getActivity(),"couldn't perform the volley operation", Toast.LENGTH_SHORT).show();
        }
    }



    private class GetXMLFromServer extends AsyncTask<String,Void,String> {

        HttpHandler nh;

        @Override
        protected String doInBackground(String... strings) {
            String res = "";
            nh =  new HttpHandler();
            InputStream is = nh.CallServer(url);
            if(is!=null){

                res = nh.StreamToString(is);

            }else{
                res = "NotConnected";
            }

            return res;
        }
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(result.equals("NotConnected")){

                Toast.makeText(getActivity(),"Connection Error",Toast.LENGTH_SHORT).show();

            }else {
                ParseXML(result);
            }
        }



    }

}


