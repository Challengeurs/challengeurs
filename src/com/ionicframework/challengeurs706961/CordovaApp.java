/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.ionicframework.challengeurs706961;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.apache.cordova.*;

import com.user.User;

public class CordovaApp extends CordovaActivity
{
	private static final String TAG = "CordovaApp"; 
    private static final int PICK_USER = 0;

	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.init();
        
        Intent intent = new Intent(CordovaApp.this,ConnectionActivity.class);
      //  startActivityForResult(intent, PICK_USER);
        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_USER) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                User user = data.getParcelableExtra("user");
                if(user != null){
                	  Toast.makeText(getActivity(), "Bienvenue, "+user.getFirstName(), Toast.LENGTH_LONG).show();
                }else{
                	Log.e(TAG,"user was null, anable to do something");
                }
            }
        }
    }
}
