package com.ghawk1ns.campaignmeasurementsample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.ghawk1ns.campaignmeasurementsample.CampaignMeasurementApplication.TAG;

public class MainActivity extends AppCompatActivity {

    public static final String DOCUMENTATION_URL = "https://developers.google.com/analytics/devguides/collection/android/v4/campaigns";
    public static final String URL_BUILDER_URL = "https://developers.google.com/analytics/devguides/collection/android/v4/campaigns#google-play-url-builder";
    
    SharedPreferences sharedPreferences;
    
    @Bind(R.id.documentation)
    TextView documentation;
    
    @Bind(R.id.url_builder)
    TextView urlBuilder;
    
    @Bind(R.id.referrer_list)
    ListView referrerList;

    ArrayAdapter<String> arrayAdapter;
    TextView footerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.util.Log.d(TAG, "MainActivity.onCreate");

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addLink(documentation, documentation.getText().toString(), DOCUMENTATION_URL);
        addLink(urlBuilder, urlBuilder.getText().toString(), URL_BUILDER_URL);
        
        sharedPreferences = SharedPrefManager.getSharedPrefs(this);
        String referrer = sharedPreferences.getString(LaunchReceiver.REFERRER, null);

        arrayAdapter = new ArrayAdapter<>(this, R.layout.referrer_row);
        footerView = (TextView) LayoutInflater.from(this).inflate(R.layout.referrer_row, null);
        if (referrer != null) {
            footerView.setText("raw: " + referrer);

            List<String> referrerValues = splitString(referrer, "&");
            arrayAdapter.addAll(referrerValues);
        } else {
            footerView.setText("No Referrer Provided");
        }
        referrerList.addFooterView(footerView);
        referrerList.setAdapter(arrayAdapter);
        OttoManager.register(this);
    }

    private List<String> splitString(@Nullable String splittableString, String deliminator) {
        List<String> values = null;
        if (splittableString != null) {
            values = Arrays.asList(splittableString.split(deliminator));
        }
        return values;
    }

    /**
     * Add a link to the TextView which is given.
     *
     * @param textView the field containing the text
     * @param patternToMatch a regex pattern to put a link around
     * @param link the link to add
     */
    private static void addLink(TextView textView, String patternToMatch, final String link) {
        Linkify.TransformFilter filter = new Linkify.TransformFilter() {
            @Override public String transformUrl(Matcher match, String url) {
                return link;
            }
        };
        Linkify.addLinks(textView, Pattern.compile(patternToMatch), null, null,
                filter);
    }

    @Subscribe
    public void referrerEvent(OttoManager.ReferrerEvent event) {
        if (arrayAdapter != null && footerView != null) {
            arrayAdapter.clear();
            String referrer = event.referrer;
            if (!TextUtils.isEmpty(referrer)) {
                List<String> referrerValues = splitString(event.referrer, "&");
                arrayAdapter.addAll(referrerValues);
                footerView.setText("raw: " + referrer);
            } else {
                footerView.setText("No Referrer Provided");
            }
        }
    }
}
