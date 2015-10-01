// (c) 2015 Flipboard Inc, All Rights Reserved.
// Author: Guy Hawkins (guy@flipboard.com)
package com.ghawk1ns.campaignmeasurementsample;

import android.app.Application;

public class CampaignMeasurementApplication extends Application {

    public static final String TAG = "ghawk1ns";

    @Override
    public void onCreate() {
        super.onCreate();
        android.util.Log.d(TAG, "CampaignMeasurementApplication.onCreate");
    }
}
