package com.edlogiq.neurongym.constant;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edlogiq.neurongym.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;


public class Facebook extends android.support.v4.app.Fragment {

    private CallbackManager mcallback;
    private AccessTokenTracker mtracker;
    private ProfileTracker mprofiletracker;

    private FacebookCallback<LoginResult> mcall=new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            AccessToken accesstocket=loginResult.getAccessToken();
            Profile profil=Profile.getCurrentProfile();
            display(profil);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

//    public Facebook(){
//
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mcallback=CallbackManager.Factory.create();

        mtracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldtocken, AccessToken newtocken) {
                Log.e("Accesstovcken",oldtocken+"   "+newtocken);
                if(newtocken!=null)
                facebook();
            }
        };

        mprofiletracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldprofile, Profile newprofile) {
                Log.e("Accessprofile",oldprofile+"   "+newprofile);
            }
        };
        mtracker.startTracking();
        mprofiletracker.startTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_facebook, container, false);
        Log.e("fregment","onCreateView");

        LoginButton fbbutton=(LoginButton)rootView.findViewById(R.id.login_button);
        fbbutton.setReadPermissions("user_friends");
        fbbutton.setFragment(this);
        fbbutton.registerCallback(mcallback,mcall);

        return rootView;
    }



    private void facebook(){
        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, AccessToken.getCurrentAccessToken().getPermissions(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {

                Log.e("Facebook",user+"    "+ err.getMessage());

                if (user == null) {
                    Log.e("MyApp", "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    Log.e("MyApp", "User signed up and logged in through Facebook!");
                } else {
                    Log.e("MyApp", "User logged in through Facebook!");
                }
            }
        });

    }



    @Override
    public void onResume() {
        super.onResume();
        Profile profile=Profile.getCurrentProfile();
        display(profile);
    }

    @Override
    public void onStop() {
        super.onStop();
        mtracker.stopTracking();
        mprofiletracker.stopTracking();
    }

    private void display(Profile profile){
        if(profile!=null){
            Log.e("Name",profile.getName());
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mcallback.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }
}
