package com.ionicframework.challengeurs706961;

import com.facebook.AppEventsLogger;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ConnectionActivity extends FragmentActivity {

	private ConnectionFragment mainFragment;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            mainFragment = new ConnectionFragment();
            getSupportFragmentManager()
            .beginTransaction()
            .add(android.R.id.content, mainFragment)
            .commit();
        } else {
            // Or set the fragment from restored state info
            mainFragment = (ConnectionFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
        }

    }
	
	@Override
	protected void onResume() {
	  super.onResume();

	  // Logs 'install' and 'app activate' App Events.
	  AppEventsLogger.activateApp(this);
	}
	
	@Override
	protected void onPause() {
	  super.onPause();

	  // Logs 'app deactivate' App Event.
	  AppEventsLogger.deactivateApp(this);
	}
	
}
