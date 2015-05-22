package com.edlogiq.neurongym.constant;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseTwitterUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by incarnation-pc on 3/31/2015.
 */
public class Parseinable extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "XmtbPmL82G7jUj1sELf8LX8MGPznVhZMkDKEDElg", "eVVO4PTtLabJ8u6EgqgpaUPiL7vS4rPFnqC1ljoT");
        ParseFacebookUtils.initialize(this);
        FacebookSdk.sdkInitialize(this);
        ParseTwitterUtils.initialize("DodtJop3YlNcKqkk8rHb6jlJA", "2IvWglBwn2U1qhj6gmEsAeXzggnQVJM6bUGP6xG2f3iDo0BL8T");
//        Log.e("KeyHash:","Application");
    }



}
