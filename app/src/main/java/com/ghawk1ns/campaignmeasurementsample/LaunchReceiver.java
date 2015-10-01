// (c) 2015 Flipboard Inc, All Rights Reserved.
// Author: Guy Hawkins (guy@flipboard.com)
package com.ghawk1ns.campaignmeasurementsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import static com.ghawk1ns.campaignmeasurementsample.CampaignMeasurementApplication.TAG;

public class LaunchReceiver extends BroadcastReceiver {
    
    public static final String INSTALL_REFERRER_ACTION = "com.android.vending.INSTALL_REFERRER";
    public static final String REFERRER = "referrer";

    @Override
    public void onReceive(Context context, Intent intent) {
        android.util.Log.d(TAG, "LaunchReceiver.onReceive");
        final String action = intent.getAction();
        if (!TextUtils.isEmpty(action) && INSTALL_REFERRER_ACTION.equals(action)) {
            android.util.Log.d(TAG, String.format("LaunchReceiver.onReceive action=%s", action));
            String referrer = intent.getStringExtra(REFERRER);
            if (!TextUtils.isEmpty(referrer)) {
                android.util.Log.d(TAG, String.format("LaunchReceiver.onReceive referrer=%s", referrer));
                SharedPreferences sharedPreferences = SharedPrefManager.getSharedPrefs(context);
                sharedPreferences.edit().putString(REFERRER, referrer).apply();
            }
        }
    }
}