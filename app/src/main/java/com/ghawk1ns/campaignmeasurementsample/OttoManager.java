// (c) 2015 Flipboard Inc, All Rights Reserved.
// Author: Guy Hawkins (guy@flipboard.com)
package com.ghawk1ns.campaignmeasurementsample;

import com.squareup.otto.Bus;

public class OttoManager {

    private static Bus bus = new Bus();

    private OttoManager() { }

    public static void register(Object object) {
        bus.register(object);
    }

    public static void notifyReferrerReceived(String referrer) {
        ReferrerEvent referrerEvent = new ReferrerEvent();
        referrerEvent.referrer = referrer;
        bus.post(referrerEvent);
    }

    public static class ReferrerEvent {
        public String referrer;
    }
}
