package com.edlogiq.neurongym.neurongym;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.signin.LoginIn;
import com.edlogiq.neurongym.signin.SignInActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;import com.edlogiq.neurongym.R;



public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        data();

        setLocale(DataBase.getLanguage(this));


        Thread mythread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
                   boolean boll = settings.getBoolean("FIRST_RUN", true);
                    if (boll) {
                        Intent myIntent = new Intent(Splash.this, SelectLanguage.class);
                        startActivity(myIntent);
                        settings = getSharedPreferences("PREFS_NAME", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean("FIRST_RUN", false);
                        editor.commit();
                    }else if(DataBase.getLogin(Splash.this).equals("login")){
                        Intent myIntent = new Intent(Splash.this, HomePage.class);
                        startActivity(myIntent);
                    }else if(DataBase.getLogin(Splash.this).equals("logout")){
                        Intent myIntent = new Intent(Splash.this, LoginIn.class);
                        startActivity(myIntent);
                    }

                    finish();


                }
            }

        };
        mythread.start();
    }


    private void data(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.edlogiq.neurongym.neurongym",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("KeyHash:11111",e.getMessage());

        } catch (NoSuchAlgorithmException e) {
            Log.e("KeyHash22222:","Application222222");

        }
    }


    public void setLocale(String lang) {
        Locale  myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

    }
}
