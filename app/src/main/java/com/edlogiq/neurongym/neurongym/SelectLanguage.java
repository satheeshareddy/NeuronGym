package com.edlogiq.neurongym.neurongym;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.signin.LoginIn;
import com.edlogiq.neurongym.signin.SignInActivity;
import com.gc.materialdesign.views.Button;

import java.util.Locale;
import com.edlogiq.neurongym.R;


public class SelectLanguage extends ActionBarActivity implements View.OnClickListener{


    Locale myLocale;
    LinearLayout l_english, l_spanish, l_hindi, l_chinese, l_french, l_turkish, l_portugese,
            l_german, l_korean, l_arabic, l_japanese, l_russian, l_italian;


    DrawerLayout dLayout;
    FrameLayout fram;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle,mTitle;
    private boolean sliderbar=false;
    private LinearLayout home,game,brain,compare,leaderbord,friend,settings,language,logout,exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.language_icon);


        ((RelativeLayout)findViewById(R.id.drawer_layout)).setBackgroundResource(R.color.white);
        Log.e("language",""+DataBase.getTheam(this));
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.drawer_layout)).setBackgroundResource(R.color.black);
        }



          init();
//        slidex();
//         slidelayoute();
    }



    private void init() {
        l_english = (LinearLayout) findViewById(R.id.linearLayout2);
        l_spanish = (LinearLayout) findViewById(R.id.linearLayout3);
        l_hindi = (LinearLayout) findViewById(R.id.linearLayout4);
        l_chinese = (LinearLayout) findViewById(R.id.linearLayout5);
        l_french = (LinearLayout) findViewById(R.id.linearLayout6);
        l_japanese = (LinearLayout) findViewById(R.id.linearLayout7);
        l_russian = (LinearLayout) findViewById(R.id.linearLayout8);
        l_korean = (LinearLayout) findViewById(R.id.linearLayout9);
        l_german = (LinearLayout) findViewById(R.id.linearLayout10);
        l_turkish = (LinearLayout) findViewById(R.id.linearLayout11);
        l_portugese = (LinearLayout) findViewById(R.id.linearLayout12);
        l_italian = (LinearLayout) findViewById(R.id.linearLayout13);
        l_arabic = (LinearLayout) findViewById(R.id.linearLayout14);


        l_english.setOnClickListener(this);
        l_spanish.setOnClickListener(this);
        l_hindi.setOnClickListener(this);
        l_chinese.setOnClickListener(this);
        l_french.setOnClickListener(this);
        l_japanese.setOnClickListener(this);
        l_russian.setOnClickListener(this);
        l_korean.setOnClickListener(this);
        l_german.setOnClickListener(this);
        l_turkish.setOnClickListener(this);
        l_portugese.setOnClickListener(this);
        l_italian.setOnClickListener(this);
        l_arabic.setOnClickListener(this);
    }

    private void slidelayoute() {

        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        fram=(FrameLayout)findViewById(R.id.content_frame);
        mTitle = mDrawerTitle = getTitle();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerToggle = new ActionBarDrawerToggle(this, dLayout,
                R.drawable.menu, //nav menu toggle icon
                R.drawable.language_icon, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                sliderbar=false;
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                sliderbar=true;
                invalidateOptionsMenu();
            }
        };
        dLayout.setDrawerListener(mDrawerToggle);
    }




    @Override
    public void setTitle(CharSequence title) {
//        mTitle = title;
//        getSupportActionBar().setTitle(mTitle);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        Log.e("hello","hello1111");
//        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        Log.e("hello","hello");
//        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onClick(View v) {
          if(sliderbar){
              return;
          }
        if(v.getId()==R.id.linearLayout2){
            setLocale("en");
            selectlanguage("en");
        }else  if(v.getId()==R.id.linearLayout3){
            setLocale("es");
            selectlanguage("es");
        }else  if(v.getId()==R.id.linearLayout4){
            setLocale("hi");
            selectlanguage("hi");
        }else  if(v.getId()==R.id.linearLayout5){
            setLocale("zh");
            selectlanguage("zh");
        }else  if(v.getId()==R.id.linearLayout6){
            setLocale("fr");
            selectlanguage("fr");
        }else  if(v.getId()==R.id.linearLayout7){
            setLocale("ja");
            selectlanguage("ja");
        }else  if(v.getId()==R.id.linearLayout8){
            setLocale("ru");
            selectlanguage("ru");
        }else  if(v.getId()==R.id.linearLayout9){
            setLocale("ko");
            selectlanguage("ko");
        }else  if(v.getId()==R.id.linearLayout10){
            setLocale("de");
            selectlanguage("de");
        }else  if(v.getId()==R.id.linearLayout11){
            setLocale("tr");
            selectlanguage("tr");
        }else  if(v.getId()==R.id.linearLayout12){
            setLocale("pt");
            selectlanguage("pt");
        }else  if(v.getId()==R.id.linearLayout13){
            setLocale("it");
            selectlanguage("it");
        }else  if(v.getId()==R.id.linearLayout14){
            setLocale("ar");
            selectlanguage("ar");
        }

    }


    private void selectlanguage(String language){

        if(language.equals("en")){
            l_english.setBackgroundColor(getResources().getColor(R.color.cyan));
        }else {
            l_english.setBackgroundResource(R.drawable.layout_border);
        }
        if(language.equals("es")){
            l_spanish.setBackgroundColor(getResources().getColor(R.color.cyan));
        }else  {
            l_spanish.setBackgroundResource(R.drawable.layout_border);
        }
        if(language.equals("hi")){

            l_hindi.setBackgroundColor(getResources().getColor(R.color.cyan));
        }else  {
            l_hindi.setBackgroundResource(R.drawable.layout_border);
        }
        if(language.equals("zh")){

            l_chinese.setBackgroundColor(getResources().getColor(R.color.cyan));
        }else  {
            l_chinese.setBackgroundResource(R.drawable.layout_border);
        }
        if(language.equals("fr")){

            l_french.setBackgroundColor(getResources().getColor(R.color.cyan));
        }else  {
            l_french.setBackgroundResource(R.drawable.layout_border);
        }
        if(language.equals("ja")){

            l_japanese.setBackgroundColor(getResources().getColor(R.color.cyan));
        }else {
            l_japanese.setBackgroundResource(R.drawable.layout_border);
        }
        if(language.equals("ru")){

            l_russian.setBackgroundColor(getResources().getColor(R.color.cyan));
        }else {
            l_russian.setBackgroundResource(R.drawable.layout_border);
        }
        if(language.equals("ko")){

            l_korean.setBackgroundColor(getResources().getColor(R.color.cyan));
        }else  {
            l_korean.setBackgroundResource(R.drawable.layout_border);
        }
        if(language.equals("de")){

            l_german.setBackgroundColor(getResources().getColor(R.color.cyan));
        }else  {
            l_german.setBackgroundResource(R.drawable.layout_border);
        }
        if(language.equals("tr")){

            l_turkish.setBackgroundColor(getResources().getColor(R.color.cyan));
        }else  {
            l_turkish.setBackgroundResource(R.drawable.layout_border);
        }
        if(language.equals("pt")){

            l_portugese.setBackgroundColor(getResources().getColor(R.color.cyan));
        }else  {
            l_portugese.setBackgroundResource(R.drawable.layout_border);
        }
        if(language.equals("it")){

            l_italian.setBackgroundColor(getResources().getColor(R.color.cyan));
        }else  {
            l_italian.setBackgroundResource(R.drawable.layout_border);
        }
        if(language.equals("ar")){
            l_arabic.setBackgroundColor(getResources().getColor(R.color.cyan));
        }else{
            l_arabic.setBackgroundResource(R.drawable.layout_border);
        }

        if(DataBase.getLogin(this).equals("logout")) {
            Intent refresh = new Intent(this, LoginIn.class);
            startActivity(refresh);
        }else if(DataBase.getLogin(this).equals("login")){
            Intent refresh = new Intent(this, HomePage.class);
            startActivity(refresh);
        }
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        SelectLanguage.this.finish();
    }

    public void setLocale(String lang) {

        DataBase.setLanguage(lang,this);
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

    }


}
