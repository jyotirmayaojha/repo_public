package com.example.joey_07.android_ph_571;

/**
 * Created by joey_07 on 24/11/17.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;
import android.webkit.WebViewClient;


public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";

    private Button btnTEST;
    private static WebView ourBrow;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);

        Bundle extras = getActivity().getIntent().getExtras();
        //Toast.makeText(getActivity(), extras.getString("sym_intent"),Toast.LENGTH_SHORT).show();
        final String symbol = extras.getString("sym_intent");
        ourBrow = (WebView) view.findViewById(R.id.web_second);
        ourBrow.getSettings().setBuiltInZoomControls(true);
        ourBrow.getSettings().setJavaScriptEnabled(true);

        ourBrow.loadUrl("file:///android_asset/HISTORY.html");
        ourBrow.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url){
                ourBrow.loadUrl("javascript:init('" + symbol + "')");
            }
        });

        return view;
    }
}