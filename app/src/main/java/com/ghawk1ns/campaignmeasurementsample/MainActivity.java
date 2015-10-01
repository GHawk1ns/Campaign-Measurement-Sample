package com.ghawk1ns.campaignmeasurementsample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = SharedPrefManager.getSharedPrefs(this);
        textView = (TextView) findViewById(R.id.referrer);
        if (textView != null) {
            List<String> referrerValues = getValues();
            if (referrerValues != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String val: referrerValues) {
                    stringBuilder.append(val).append("\n\n");
                }
                textView.setText(stringBuilder.toString());
            } else {
                textView.setText("There was no referrer");
            }
        }
    }

    private List<String> getValues() {
        List<String> values = null;
        String referrer = sharedPreferences.getString(LaunchReceiver.REFERRER, null);
        if (referrer != null) {
            values = Arrays.asList(referrer.split("&"));
        }
        return values;
    }
}
