package com.maize.CardMagic;

import com.maize.CardMagic.AppBrain.BrainStateChangeListener;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

/**
 * Main.java - The main activity of the app
 * @author willhou
 * 
 */
public class Main extends Activity 
{
	/* UI components */
	private ImageView cardView;
	
	
	/* App model */
	AppBrain brain;
	
	
	/* Options menu constant */
	private final static int ABOUT = Menu.FIRST; 
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        cardView = (ImageView) findViewById(R.id.card);
        
        brain = new AppBrain();
        brain.addBrainStateChangeListener(new BrainStateChangeListener() 
        {			
			public void swapCard(int card) 
			{
				cardView.setImageResource(card);
			}
		});
        
        // Get instance of SensorManager
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        
        // Checks if the device has a light sensor before proceeding
        if (manager.getSensorList(Sensor.TYPE_PROXIMITY).size() == 0)
        {
        	// OMG, the device has no proximity sensor!!!
        	UIUtils.showSensorMissingDialog(this);
        }
        else
        {
        	// Get the proximity sensor
        	Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            
            // Create a listener that listens to the light sensor's event change
            manager.registerListener(new SensorEventListener() 
            {			
    			// Called when sensor values have changed.
    			public void onSensorChanged(SensorEvent event) 
    			{
    				brain.changeState(event);
    			}

            	// Called when the accuracy of a sensor has changed. (We don't need this)
    			public void onAccuracyChanged(Sensor sensor, int accuracy) 
    			{
    				Log.i("AccuracyChanged", "Accuracy is " + accuracy);
    			}
    			
    		}, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        }        
    }
    
    
    /* Options menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	super.onCreateOptionsMenu(menu);
    	MenuItem item;
    	item = menu.add(0, ABOUT, 0, "About");
    	item.setIcon(android.R.drawable.ic_menu_info_details);
        return true;    	
    }
    
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item)
    {
    	switch (item.getItemId())
    	{
    		case ABOUT:
    			Intent intent = new Intent(this, About.class);
    			startActivity(intent);
    			return true;
    	}
    	return false;
    }
}