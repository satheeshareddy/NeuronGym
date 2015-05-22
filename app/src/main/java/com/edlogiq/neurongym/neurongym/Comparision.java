package com.edlogiq.neurongym.neurongym;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.signin.Form;
import com.gc.materialdesign.views.ButtonFloatSmall;
import com.parse.ParseUser;
import com.edlogiq.neurongym.R;


public class Comparision extends ActionBarActivity implements View.OnClickListener{

    DrawerLayout dLayout;
    FrameLayout fram;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle,mTitle;
    private boolean sliderbar=false;
    private LinearLayout home,game,brain,compare,leaderbord,friend,settings,language,logout,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparision);

        //For setting action bar title in center
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.compare);

        init();
        slidex();
        slidelayoute();
    }

    private void init() {
        ((ButtonFloatSmall)findViewById(R.id.buttonAll)).setOnClickListener(this);
        ((ButtonFloatSmall)findViewById(R.id.buttonFemail)).setOnClickListener(this);
        ((ButtonFloatSmall)findViewById(R.id.buttonMan)).setOnClickListener(this);
        ((ButtonFloatSmall)findViewById(R.id.buttonGlobal)).setOnClickListener(this);
        ((ButtonFloatSmall)findViewById(R.id.buttonCountry)).setOnClickListener(this);
        ((ButtonFloatSmall)findViewById(R.id.buttonAllage)).setOnClickListener(this);
        ((ButtonFloatSmall)findViewById(R.id.buttonKid)).setOnClickListener(this);
        ((ButtonFloatSmall)findViewById(R.id.buttonYouth)).setOnClickListener(this);
        ((ButtonFloatSmall)findViewById(R.id.buttonYadult)).setOnClickListener(this);
        ((ButtonFloatSmall)findViewById(R.id.buttonMidage)).setOnClickListener(this);
        ((ButtonFloatSmall)findViewById(R.id.buttonElder)).setOnClickListener(this);
    }

    private void slidex() {
        home=(LinearLayout)findViewById(R.id.home);
        game=(LinearLayout)findViewById(R.id.game);
        brain=(LinearLayout)findViewById(R.id.brain);
        compare=(LinearLayout)findViewById(R.id.compare);
        leaderbord=(LinearLayout)findViewById(R.id.leaderbord);
        friend=(LinearLayout)findViewById(R.id.friends);
        settings=(LinearLayout)findViewById(R.id.setting);
        language=(LinearLayout)findViewById(R.id.language);
        logout=(LinearLayout)findViewById(R.id.logout);
        exit=(LinearLayout)findViewById(R.id.exit);

        home.setOnClickListener(listner);
        game.setOnClickListener(listner);
        brain.setOnClickListener(listner);
        compare.setOnClickListener(listner);
        leaderbord.setOnClickListener(listner);
        friend.setOnClickListener(listner);
        settings.setOnClickListener(listner);
        language.setOnClickListener(listner);
        logout.setOnClickListener(listner);
        exit.setOnClickListener(listner);

        ((TextView)findViewById(R.id.profilename)).setText(DataBase.getUserName(Comparision.this));
        if(DataBase.getUserImage(this)!=null) {
            byte[] b = Base64.decode(DataBase.getUserImage(this), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);

            Bitmap image_circle = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setShader(shader);
            Canvas c = new Canvas(image_circle);
            c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getWidth() / 3, paint);
            ((ImageView) findViewById(R.id.profileimage)).setImageBitmap(image_circle);

        }
    }

    private void slidelayoute() {

        dLayout = (DrawerLayout) findViewById(R.id.comparision);
        fram=(FrameLayout)findViewById(R.id.content_frame);
        mTitle = mDrawerTitle = getTitle();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerToggle = new ActionBarDrawerToggle(this, dLayout,
                R.drawable.menu, //nav menu toggle icon
                R.drawable.home, // nav drawer open - description for accessibility
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    View.OnClickListener listner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(v.getId()==R.id.home){
                Intent intent=new Intent(getApplicationContext(),HomePage.class);
                startActivity(intent);
            }else if(v.getId()==R.id.game){
                Intent intent=new Intent(getApplicationContext(),GameMenu.class);
                startActivity(intent);
            }else if(v.getId()==R.id.brain){
                Intent intent=new Intent(getApplicationContext(),BrainProfile.class);
                startActivity(intent);
            }else if(v.getId()==R.id.compare){
                Intent intent=new Intent(getApplicationContext(),Comparision.class);
                startActivity(intent);
            }else if(v.getId()==R.id.leaderbord){
                Intent intent=new Intent(getApplicationContext(),LeadersBoard.class);
                startActivity(intent);
            }else if(v.getId()==R.id.friends){
                friend.setBackgroundResource(R.color.cyan);
            }else if(v.getId()==R.id.setting){
                Intent intent=new Intent(getApplicationContext(),ThemeSelect.class);
                startActivity(intent);
            }else if(v.getId()==R.id.language){
                Intent intent=new Intent(getApplicationContext(),SelectLanguage.class);
                startActivity(intent);
            }else if(v.getId()==R.id.logout){
                ParseUser.logOut();
                invalidateOptionsMenu();
                DataBase.setLogin("logout",Comparision.this);
                Intent intent=new Intent(getApplicationContext(),Form.class);
                startActivity(intent);
            }else if(v.getId()==R.id.exit){
                exit.setBackgroundResource(R.color.cyan);
            }
            dLayout.closeDrawers();
            Comparision.this.finish();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,HomePage.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onClick(View v) {

    }

    private void selected(String button,String value){
        if(button.equals("gender")){
            if(value.equals("all")){

            }else{

            } if(value.equals("mail")){

            }else{

            } if(value.equals("female")){

            }else{

            }
        }else if(button.equals("resion")){
            if(value.equals("global")){

            }else{

            } if(value.equals("country")){

            }else{

            }
        }else if(button.equals("age")){
            if(value.equals("all")){

            }else{

            } if(value.equals("kid")){

            }else{

            } if(value.equals("youth")){

            }else{

            } if(value.equals("adult")){

            }else{

            } if(value.equals("mid")){

            }else{

            } if(value.equals("elder")){

            }else{

            }
        }
    }

}
