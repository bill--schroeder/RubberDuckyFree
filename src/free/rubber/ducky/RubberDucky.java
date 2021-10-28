
package free.rubber.ducky;

import com.zealocity.*;

//import java.io.IOException;
//import android.graphics.Matrix;
//import android.media.MediaPlayer;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
//import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
//import android.widget.FrameLayout;
import android.widget.ImageView;

public class RubberDucky extends baseActivityAmazon implements OnTouchListener, OnInitListener
{
	//Matrix matrix = new Matrix();
	
	//http://developer.android.com/reference/android/media/SoundPool.html
	//http://stackoverflow.com/questions/3253108/how-do-i-know-that-the-soundpool-is-ready-using-sdk-target-below-2-2
	//http://www.anddev.org/using_soundpool_instead_of_mediaplayer-t3115.html
	
	private SoundManager _SoundManager = null;
	
	private TextToSpeech _tts = null;
	static final int TTS_CHECK_CODE = 0;
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
/*	     if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	 //	preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
	    	 return true;
	     }*/
	     if (keyCode == KeyEvent.KEYCODE_SEARCH) {
	    	 //	preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
	    	 return true;
	     }

	     return super.onKeyDown(keyCode, event);    
	}
	//@Override
	//public void onBackPressed() {
	//   return;
	//}
	@Override
	public void onAttachedToWindow() {  
	       this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);     
	       super.onAttachedToWindow();  
	}
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.main);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        _tts = new TextToSpeech(this, this);
        
        ImageView t = (ImageView)findViewById(R.id.imageView);
        t.setOnTouchListener(this);

        _SoundManager = new SoundManager();
        _SoundManager.initSounds(getBaseContext());
        _SoundManager.addSound(1, R.raw.rubberduck1);
        _SoundManager.addSound(2, R.raw.rubberduck2);
        _SoundManager.addSound(3, R.raw.rubberduck3);
        
        /*
        AdManager.setTestDevices( new String[] {
        		AdManager.TEST_EMULATOR, // Android emulator
        		"emulator-5554", // My Test Phone
        		} );
         */

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        
        _SoundManager = null;

        _tts.stop();
        _tts.shutdown();
        _tts = null;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.Exit:
	    	super.finish();
	    	this.finishActivity(0);
	    	this.finish();
	    	break;
		default:
		    return super.onOptionsItemSelected(item);
	    }
	    
	    return true;
	}
	
    
    //@Override
	public void onInit(int initStatus) {
		if (initStatus == TextToSpeech.SUCCESS)
		{
			int pitch = 1;			// default = 1
			_tts.setPitch(pitch);
			float speechRate = 1;	// default = 1
			_tts.setSpeechRate(speechRate);
			
			_tts.speak("Duck", TextToSpeech.QUEUE_FLUSH, null);
		}
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		   StringBuilder sb = new StringBuilder();
		   
		   int action = event.getAction();
		   int actionCode = action & MotionEvent.ACTION_MASK;
		   
				String names[] = { "DOWN" , "UP" , "MOVE" , "CANCEL" , "OUTSIDE" ,
			      "POINTER_DOWN" , "POINTER_UP" , "7?" , "8?" , "9?" };
				
				sb.append("getAction:").append(action);
				sb.append("|ACTION_MASK:").append(actionCode);

			   sb.append("event ACTION_" ).append(names[actionCode]);
			   			   
			   
			   //ImageView view = (ImageView) v;
			   //ImageView t = (ImageView)findViewById(v.getId());
			   if (actionCode == 0){
				   _SoundManager.playSound(2);
				   
				   //t.setScaleType(ScaleType.CENTER);
				   //t.setColorFilter(0);
				   //float scale = -50f;
				   //matrix.postScale(scale, scale, event.getX(),event.getY());
				   
			   } else if(actionCode == 1){
				   _SoundManager.playSound(3);
				   
				   //t.setScaleType(ScaleType.CENTER_CROP);
			   }
			   //t.forceLayout();
			   //t.requestLayout();

			   // Perform the transformation
			   //view.setImageMatrix(matrix);
			      			   
			   
			   //if (actionCode == MotionEvent.ACTION_POINTER_DOWN
				//         || actionCode == MotionEvent.ACTION_POINTER_UP)
			   if (actionCode == 0) {
			      //sb.append("(pid " ).append(action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
			      //sb.append(")" );
				   sb.append("-quack!-" );
				   
				   //_MediaPlayer.stop();
				   //_MediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.rubberduck);
				   //_MediaPlayer.reset();
				   /*
				   _MediaPlayer.seekTo(1000);
				   try {
					   _MediaPlayer.prepare();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   _MediaPlayer.start();
					*/
				   
				   //_SoundManager.playSound(1);

			   }
			   
			   /*
			   sb.append("[" );
			   for (int i = 0; i < event.getPointerCount(); i++) {
			      sb.append("#" ).append(i);
			      sb.append("(pid " ).append(event.getPointerId(i));
			      sb.append(")=" ).append((int) event.getHistoricalX(i));
			      sb.append("," ).append((int) event.getHistoricalY(i));
			      sb.append("|" ).append((int) event.getX(i));
			      sb.append("," ).append((int) event.getY(i));
			      if (i + 1 < event.getPointerCount())
			         sb.append(";" );
			   }
			   sb.append("]" );
		      	*/
			   
		   //super.showgMessage(sb.toString());
	        
		   return true;
	}
    
}
