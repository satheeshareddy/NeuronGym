package com.edlogiq.neurongym.neurongym;

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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.signin.Form;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.edlogiq.neurongym.R;


public class GameMenu extends ActionBarActivity {

    ArrayList Game_name;
    RelativeLayout dual_focus, dual_focus_pro, blink, blink_pro, track_the_route, memory_matrix, memory_matrix_pro,
            dancing_balls, shapes, match_it, match_it_pro, reversal, reversal_pro, money_game, solve_it, after_math, speed_shop,
            spot_it, spot_it_pro, brain_flash;


    DrawerLayout dLayout;
    FrameLayout fram;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle,mTitle;
    private boolean sliderbar=false;
    private LinearLayout home,game,brain,compare,leaderbord,friend,settings,language,logout,exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        ((DrawerLayout)findViewById(R.id.gamemenu)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this)=="dark"){
            ((DrawerLayout)findViewById(R.id.gamemenu)).setBackgroundResource(R.color.black);
        }
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.game);
        getSupportActionBar().setTitle(R.string.title_activity_game_menu);
        

        dual_focus = (RelativeLayout) findViewById(R.id.relativeLayout1);
        dual_focus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
//                Anilitic("dualfocus");
                Intent intent1 = new Intent(getApplicationContext(), DualFocusInstruction.class);
                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);

            }

        });


        dual_focus_pro = (RelativeLayout) findViewById(R.id.relativeLayout2);
        dual_focus_pro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), DualFocusProInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        blink = (RelativeLayout) findViewById(R.id.relativeLayout3);
        blink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), BlinkInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        blink_pro = (RelativeLayout) findViewById(R.id.relativeLayout4);
        blink_pro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), BlinkProInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        track_the_route = (RelativeLayout) findViewById(R.id.relativeLayout5);
        track_the_route.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), TrackTheRouteInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        memory_matrix = (RelativeLayout) findViewById(R.id.relativeLayout6);
        memory_matrix.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), MemoryMatrixInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });


        memory_matrix_pro = (RelativeLayout) findViewById(R.id.relativeLayout7);
        memory_matrix_pro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), MemoryMatrixProInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        dancing_balls = (RelativeLayout) findViewById(R.id.relativeLayout8);
        dancing_balls.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), DancingBallInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        shapes = (RelativeLayout) findViewById(R.id.relativeLayout9);
        shapes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), ShapesInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        match_it = (RelativeLayout) findViewById(R.id.relativeLayout10);
        match_it.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), MatchItInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        match_it_pro = (RelativeLayout) findViewById(R.id.relativeLayout11);
        match_it_pro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), MatchItProInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        reversal = (RelativeLayout) findViewById(R.id.relativeLayout12);
        reversal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), ReversalInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        reversal_pro = (RelativeLayout) findViewById(R.id.relativeLayout13);
        reversal_pro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), ReversalProInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        money_game = (RelativeLayout) findViewById(R.id.relativeLayout14);
        money_game.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), MoneyGameInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        solve_it = (RelativeLayout) findViewById(R.id.relativeLayout15);
        solve_it.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), SolveItInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        after_math = (RelativeLayout) findViewById(R.id.relativeLayout16);
        after_math.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), AfterMathInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        speed_shop = (RelativeLayout) findViewById(R.id.relativeLayout17);
        speed_shop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), SpeedShopInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        spot_it = (RelativeLayout) findViewById(R.id.relativeLayout18);
        spot_it.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), SpotItInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        spot_it_pro = (RelativeLayout) findViewById(R.id.relativeLayout19);
        spot_it_pro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), SpotItProInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

        brain_flash = (RelativeLayout) findViewById(R.id.relativeLayout20);
        brain_flash.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent1 = new Intent(getApplicationContext(), BrainFlashInstruction.class);

                startActivity(intent1);
                //push from bottom to top
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                //slide from right to left
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });
        slidex();
        slidelayoute();
    }



    private void Anilitic(String val){
        Map<String, String> dimensions = new HashMap<String, String>();
// Define ranges to bucket data points into meaningful segments
        dimensions.put("Game", val);
// Did the user filter the query?
        dimensions.put("userId", ParseUser.getCurrentUser().getObjectId());
// Do searches happen more often on weekdays or weekends?
        dimensions.put("dayType", "weekday");
        Log.e("Log",""+ParseUser.getCurrentUser().getObjectId());
// Send the dimensions to Parse along with the 'search' event
        ParseAnalytics.trackEventInBackground("search", dimensions);
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

        ((TextView)findViewById(R.id.profilename)).setText(DataBase.getUserName(GameMenu.this));
        if(DataBase.getUserImage(this)!=null) {
            byte[] b = Base64.decode(DataBase.getUserImage(this), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);

            Bitmap image_circle = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setShader(shader);
            Canvas c = new Canvas(image_circle);
            c.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 3, paint);
            ((ImageView) findViewById(R.id.profileimage)).setImageBitmap(image_circle);

        }
    }

    private void slidelayoute() {

        dLayout = (DrawerLayout) findViewById(R.id.gamemenu);
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
                DataBase.setLogin("logout",GameMenu.this);
                Intent intent=new Intent(getApplicationContext(),Form.class);
                startActivity(intent);
            }else if(v.getId()==R.id.exit){
                exit.setBackgroundResource(R.color.cyan);
            }
            dLayout.closeDrawers();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(this,HomePage.class);
        startActivity(intent);
        GameMenu.this.finish();
    }
}
