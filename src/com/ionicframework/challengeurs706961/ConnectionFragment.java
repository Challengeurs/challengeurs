package com.ionicframework.challengeurs706961;

import java.util.Arrays;

import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;

import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import com.user.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ConnectionFragment extends Fragment {
	private static final String TAG = "ConnectionFragment"; 
	private GraphUser graphUser;
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	private UiLifecycleHelper uiHelper;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
		Session.openActiveSession(getActivity(), this, true, new Session.StatusCallback() {

  		  @Override
  		  public void call(Session session, SessionState state, Exception exception) {
  		    if (session.isOpened() == true) {
  		        Request.newMeRequest(session, new GraphUserCallback() {
  		          
  		            @Override
  		            public void onCompleted(GraphUser user, Response response) {
  		                if(user != null) {
  		                    //on affiche le nom et le prénom de l'utilisateur
  		                    Toast.makeText(getActivity(), user.getFirstName() + " " + user.getLastName(), Toast.LENGTH_LONG).show();
  		                    Log.e(TAG, user.getFirstName()+" "+user.getLastName());
  		                    Intent intent = new Intent();
  		                    intent.putExtra("user", new User(user));
  		                    getActivity().setResult(android.app.Activity.RESULT_OK, intent);
  		                    getActivity().finish();
  		                }
  		                else {
  		                    //on affiche un message d'erreur
  		                    Toast.makeText(getActivity(), "Impossible de récupérer les informations", Toast.LENGTH_LONG).show();
  		                }
  		            }
  		        }).executeAsync();
  		       
  		    }
  	    else {
  		        if(exception != null) {
  		        //On affiche le message d'erreur
  		        Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
  		      }
  		    }
  		  }});
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.connection_layout, container, false);
	   LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setFragment(this);
	    authButton.setReadPermissions(Arrays.asList("user_likes", "user_status"));
	    return view;
	}
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    if (state.isOpened()) {
	    	Log.e(TAG,"Loggin in...");
	    } else if (state.isClosed()) {
	        Log.e(TAG, "Logged out...");
	    }
	}
	
	public GraphUser getGraphUser(){
		return graphUser;
	}
	
	public void setGraphUser(GraphUser graph){
		this.graphUser = graph;
	}
/*	
	@Override
	public void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}
*/
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	    
	}
	
	
}
