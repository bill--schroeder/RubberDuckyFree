package com.zealocity;

import com.amazon.device.ads.*;
//import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
//import android.view.View;
import android.widget.FrameLayout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class baseActivityAmazon extends baseActivity
{
    private AdLayout adView; // The ad view used to load and display the ad.
    private static final String APP_KEY = "53474a5a5a5649304b5a4c4a47304933"; // Sample Application Key. Replace this variable with your Application Key
    private static final String LOG_TAG = "RubberDuckyFree"; // Tag used to prefix all log messages

    private Handler mHandler = new Handler();
    
    /**
     * When the activity starts, load an ad and set up the button's click event to load another ad when it's clicked.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        // For debugging purposes enable logging, but disable for production builds.
        AdRegistration.enableLogging(false);
        // For debugging purposes flag all ad requests as tests, but set to false for production builds.
        AdRegistration.enableTesting(false);
        
        // Setup layout parameters
        FrameLayout.LayoutParams params;
        params = new FrameLayout.LayoutParams(
        		FrameLayout.LayoutParams.FILL_PARENT, 
        		FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        
        //this.adView = (AdLayout) findViewById(R.id.ad_view);
        this.adView = new AdLayout(this);
        this.adView.setListener(new SampleAdListener());
        
        super.addContentView(this.adView, params);
        //this.adView.setListener(this);
        
        try {
            AdRegistration.setAppKey(APP_KEY);
        } catch (final IllegalArgumentException e) {
            Log.e(LOG_TAG, "IllegalArgumentException thrown: " + e.toString());
            return;
        }
                
        // Assign an onClick handler to the button that will load our ad.
        /*
        final Button button = (Button) findViewById(R.id.load_ad_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                loadAd();
            }
        });
        */
        
        // Calling load ad in the Activity's onCreate method allows a new ad to be loaded 
        // when the application starts and also when the device is rotated.
        LoadAd();
    }

    @Override
    public void onDestroy()
    {
		super.logMessage("onDestroy");
		
		super.onDestroy();
		
		mHandler.removeCallbacks(mRefreshAdTask);

		adView.destroy();
		adView = null;
    }
    
	private Runnable mRefreshAdTask = new Runnable() {
	   public void run() {
		   LoadAd();
	   }
	};	

    /**
     * Load a new ad.
     */
    public void LoadAd() { 
        // Load the ad with the appropriate ad targeting options.
        AdTargetingOptions adOptions = new AdTargetingOptions();
        adView.loadAd(adOptions);
        
        // load a new ad in ... 30,000 = 30 seconds
        mHandler.removeCallbacks(mRefreshAdTask);
        mHandler.postDelayed(mRefreshAdTask, 30000);
    }
    
    /**
     * Load a new ad.
     */
    public void loadAd() {
        // Load an ad with default ad targeting.
        this.adView.loadAd();
        
        // Note: You can choose to provide additional targeting information to modify how your ads
        // are targeted to your users. This is done via an AdTargetingOptions parameter that's passed
        // to the loadAd call. See an example below:
        //
        //    final AdTargetingOptions adOptions = new AdTargetingOptions();
        //    adOptions.enableGeoLocation(true);
        //    this.adView.loadAd(adOptions);
    }
    
    /**
     * This class is for an event listener that tracks ad lifecycle events.
     * It extends DefaultAdListener, so you can override only the methods that you need.
     */
    class SampleAdListener extends DefaultAdListener {
        /**
         * This event is called once an ad loads successfully.
         */
        @Override
        public void onAdLoaded(final Ad ad, final AdProperties adProperties) {
            Log.i(LOG_TAG, adProperties.getAdType().toString() + " ad loaded successfully.");
        }
        
        /**
         * This event is called if an ad fails to load.
         */
        @Override
        public void onAdFailedToLoad(final Ad ad, final AdError error) {
            Log.w(LOG_TAG, "Ad failed to load. Code: " + error.getCode() + ", Message: " + error.getMessage());
        }
    
        /**
         * This event is called after a rich media ad expands.
         */
        @Override
        public void onAdExpanded(final Ad ad) {
            Log.i(LOG_TAG, "Ad expanded.");
            // You may want to pause your activity here.
        }
        
        /**
         * This event is called after a rich media ad has collapsed from an expanded state.
         */
        @Override
        public void onAdCollapsed(final Ad ad) {
            Log.i(LOG_TAG, "Ad collapsed.");
            // Resume your activity here, if it was paused in onAdExpanded.
        }
    }
}
