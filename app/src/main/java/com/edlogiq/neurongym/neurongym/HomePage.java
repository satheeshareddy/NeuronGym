package com.edlogiq.neurongym.neurongym;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.signin.Form;
import com.edlogiq.neurongym.constant.RefrenceWrapper;
import com.gc.materialdesign.views.ButtonFloatSmall;
import com.parse.FindCallback;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.HashMap;
import java.util.List;
import com.edlogiq.neurongym.R;


public class HomePage extends ActionBarActivity {

    private  LinearLayout  brain_profile, comparision, leaders_board, game_menu;

    private Context mContext;
    private RefrenceWrapper refrence;

    DrawerLayout dLayout;
    FrameLayout fram;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle,mTitle;
    private boolean sliderbar=false;
    private LinearLayout home,game,brain,compare,leaderbord,friend,settings,language,logout,exit;
    TranslateAnimation anim;
    ScaleAnimation scal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        refrence=RefrenceWrapper.getRefrenceWrapper(this);


        ((RelativeLayout)findViewById(R.id.homepage)).setBackgroundResource(R.color.white);
        ((LinearLayout)findViewById(R.id.left_drawer)).setBackgroundResource(R.color.white);

        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.homepage)).setBackgroundResource(R.color.black);
            ((LinearLayout)findViewById(R.id.left_drawer)).setBackgroundResource(R.color.black);

        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.home);
        getSupportActionBar().setTitle(R.string.home_page);


        //Brain Profile Page calling by button_brain_profile
        brain_profile = ( LinearLayout ) findViewById(R.id.buttonFloat_brain_profile);

        //Comparision Page calling by buttonFloat_comparision
        comparision = ( LinearLayout ) findViewById(R.id.buttonFloat_comparision);

        //LeadersBoard Page calling by buttonFloat_leaders_board
        leaders_board = ( LinearLayout ) findViewById(R.id.buttonFloat_leaders_board);

        //LeadersBoard Page calling by buttonFloat_leaders_board
        game_menu = (LinearLayout) findViewById(R.id.buttonFloat_game_menu);


        anim =new TranslateAnimation(0,0,-400,0);
        anim.setDuration(630);
        ((LinearLayout)findViewById(R.id.linearLayout2)).startAnimation(anim);

        scal = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF,
                (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
        scal.setDuration(630);

        ((LinearLayout)findViewById(R.id.brainflashlay)).startAnimation(scal);
        ((LinearLayout)findViewById(R.id.comparelay)).startAnimation(scal);
        ((LinearLayout)findViewById(R.id.leaderbordlay)).startAnimation(scal);
        ((LinearLayout)findViewById(R.id.gamelay)).startAnimation(scal);

        brain_profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                endanim("brainp");
            }

        });


        comparision.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                endanim("comparisionn");

            }

        });


        leaders_board.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                endanim("leader");
                    }

        });


        game_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                endanim("gamem");

            }

        });
        slidex();
        slidelayoute();
        parse();
        cloudcode();
    }

    private void cloudcode() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userScore", "150");
        params.put("Ani", "ani");
        ParseCloud.callFunctionInBackground("dualAni", params, new FunctionCallback<Double>() {
            public void done(Double ratings, ParseException e) {
                if (e == null) {
                  Log.e("Cloud code",""+e);
                }
                Log.e("Cloud code",""+ratings);
            }
        });
    }

    private void parse() {
//        Log.e("parse","parse     "+ ParseUser.getCurrentUser().getObjectId().toString());
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("UserIformation");
        query.whereEqualTo("parent", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> markers, ParseException e) {
                if (e == null) {
                    if(markers.size()!=0)
                        DataBase.setUserObjectId(markers.get(0).getObjectId().toString(),HomePage.this);
//                        Log.e("objectid",markers+"      "+markers.get(0).getObjectId().toString());

                } else {
                    Log.e("value error",""+e.getMessage());
                }
            }
        });
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

        ((TextView)findViewById(R.id.profilename)).setText(DataBase.getUserName(HomePage.this));
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

        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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
                DataBase.setLogin("logout",HomePage.this);
                Intent intent=new Intent(getApplicationContext(),Form.class);
                startActivity(intent);
            }else if(v.getId()==R.id.exit){
                exit.setBackgroundResource(R.color.cyan);
            }
            dLayout.closeDrawers();
            HomePage.this.finish();
        }
    };

    private void endanim(final String value){

        TranslateAnimation  anim1 =new TranslateAnimation(0,0,0,-400);
        anim1.setDuration(630);
        anim1.setFillAfter(true);
        ((LinearLayout)findViewById(R.id.linearLayout2)).startAnimation(anim1);

        ScaleAnimation scal1 = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF,
                (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
        scal1.setDuration(630);
        scal1.setFillAfter(true);
        ((LinearLayout)findViewById(R.id.brainflashlay)).startAnimation(scal1);
        ((LinearLayout)findViewById(R.id.comparelay)).startAnimation(scal1);
        ((LinearLayout)findViewById(R.id.leaderbordlay)).startAnimation(scal1);
        ((LinearLayout)findViewById(R.id.gamelay)).startAnimation(scal1);

        scal1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(value.equals("brainp")){
                    Intent intent1 = new Intent(getApplicationContext(), BrainProfile.class);
                    startActivity(intent1);
                }else if(value.equals("comparisionn")){
                    Intent intent1 = new Intent(getApplicationContext(), Comparision.class);
                    startActivity(intent1);
                }else if(value.equals("leader")){
                    Intent intent1 = new Intent(getApplicationContext(), LeadersBoard.class);
                    startActivity(intent1);
                }else if(value.equals("gamem")){
                    Intent intent1 = new Intent(getApplicationContext(), GameMenu.class);
                    startActivity(intent1);
                }
                HomePage.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

}
