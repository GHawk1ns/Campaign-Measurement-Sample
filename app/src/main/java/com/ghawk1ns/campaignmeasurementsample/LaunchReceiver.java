// (c) 2015 Flipboard Inc, All Rights Reserved.
// Author: Guy Hawkins (guy@flipboard.com)
package com.ghawk1ns.campaignmeasurementsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class LaunchReceiver extends BroadcastReceiver {
    
    public static final String INSTALL_REFERRER_ACTION = "com.android.vending.INSTALL_REFERRER";
    public static final String REFERRER = "referrer";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (!TextUtils.isEmpty(action) && INSTALL_REFERRER_ACTION.equals(action)) {
            String referrer = intent.getStringExtra(REFERRER);
            if (!TextUtils.isEmpty(referrer)) {
                SharedPreferences sharedPreferences = SharedPrefManager.getSharedPrefs(context);
                sharedPreferences.edit().putString(REFERRER, referrer).apply();
                System.out.println(String.format("Referrer = %s", referrer));
            }
        }
    }
}