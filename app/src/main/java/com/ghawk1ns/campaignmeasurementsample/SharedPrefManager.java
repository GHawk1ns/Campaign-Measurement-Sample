// (c) 2015 Flipboard Inc, All Rights Reserved.
// Author: Guy Hawkins (guy@flipboard.com)
package com.ghawk1ns.campaignmeasurementsample;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    
    private static final String key = "com.ghawk1ns.campaignMeasurementSample"; 
    private static SharedPreferences sharedPreferences;
    
    public static SharedPreferences getSharedPrefs(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(key, context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }
}
