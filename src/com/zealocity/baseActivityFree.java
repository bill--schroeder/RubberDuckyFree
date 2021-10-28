package com.zealocity;

//import com.google.ads.*;
import com.mopub.mobileads.*;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
//import android.widget.LinearLayout;

// http://code.google.com/mobile/ads/docs/android/fundamentals.html
// http://www.mobfox.com/

public class baseActivityFree extends baseActivity {

	private MoPubView mAdView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	super.logMessage("baseActivityFree:onCreate");

    	/*
    	// prevent my devices from showing ADs
    	String tmpdeviceId = super.getDeviceUuid().toString();
        if(tmpdeviceId.equals("00000000-4a4c-1132-0000-000000000000")
        		|| tmpdeviceId.equals("ffffffff-9eaf-9658-0000-000000000000z")) {
        	return;
        }
        */
    	
        /*
		<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
		  	xmlns:app="http://schemas.android.com/apk/res/free.droid.talker"
		    android:layout_width="fill_parent"
		    android:layout_height="fill_parent" 
		    android:orientation="vertical" >

    	<!-- Place an AdMob ad at the bottom of the screen. -->
	    <!-- It has white text on a black background. -->
	    <com.admob.android.ads.AdView  
	    	android:id="@+id/ad" 
	        android:layout_width="fill_parent" 
	        android:layout_height="46dip"
	        android:layout_alignParentBottom="true"
			android:layout_gravity="bottom"
		    app:backgroundColor="#000000"
	        app:primaryTextColor="#FFFFFF"
	        app:secondaryTextColor="#CCCCCC"
	        app:keywords="droid talker talk Android multimedia tool tts file text reader read"
	        />
        AdManager.setTestDevices( new String[] {
        		AdManager.TEST_EMULATOR, 	// Android emulator
        		"emulator-5554", 			// My Test Phone
        		} );
         */
        //AdView adView = (AdView)findViewById(R.id.ad);
        /*
    	AdView adView = new AdView(this);
        adView.setKeywords("droid Android multimedia tool");
        adView.setRequestInterval(R.string.AdRequestInterval);
        adView.requestFreshAd();
        */
        //adView.layout(10, 100, 100, 100);
        //ArrayList<View> al = new ArrayList<View>();
        //al.add(adView);
        //mSimulationView.addTouchables(al);
        
        // Setup layout parameters
        FrameLayout.LayoutParams params;
        params = new FrameLayout.LayoutParams(
        		FrameLayout.LayoutParams.FILL_PARENT, 
        		FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        
        
        /*
        // 	*******	this is for AdMob
        // Create the adView
        AdView adView = new AdView(this, AdSize.BANNER, "a14e72724618d2e");
        //AdView adView = new AdView(this, AdSize.BANNER, String.valueOf(R.string.MY_AD_UNIT_ID));
        //AdView adView = (AdView)this.findViewById(R.id.adView);
        // Lookup your LinearLayout assuming it’s been given
        // the attribute android:id="@+id/mainLayout"
        //LinearLayout layout = (LinearLayout)findViewById(R.id.);
        // Add the adView to it
        //layout.addView(adView);
        // Initiate a generic request to load it with an ad
        //adView.loadAd(new AdRequest());
        
        super.addContentView(adView, params);
        
        adView.loadAd(new AdRequest());
		*/
        
        
        // 	*******	this is for MoPub
        //mAdView = (MoPubView) findViewById(R.id.adview);
        mAdView = new MoPubView(this);
        mAdView.setAdUnitId("agltb3B1Yi1pbmNyDQsSBFNpdGUY3L_9EAw"); // Enter your Ad Unit ID from www.mopub.com
        
        super.addContentView(mAdView, params);
        
        mAdView.loadAd();
        
        
    }

}
