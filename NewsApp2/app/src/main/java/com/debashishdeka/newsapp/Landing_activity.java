package com.debashishdeka.newsapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Debashish on 10/22/2017.
 */
public class Landing_activity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_activity);

        TextView textView = (TextView)findViewById(R.id.text_view);
        textView.setText(getIntent().getStringExtra("user_name"));


    }
}
