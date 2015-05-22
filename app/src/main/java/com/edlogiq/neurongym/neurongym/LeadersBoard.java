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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.signin.Form;
import com.gc.materialdesign.views.Button;
import com.parse.ParseUser;
import com.edlogiq.neurongym.R;


public class LeadersBoard extends ActionBarActivity implements View.OnClickListener{

    Spinner skill_wise_spin, game_wise_spinner;
   private String values="";

    int total_images[] = {R.drawable.congnitive_change_up, R.drawable.congnitive_change_up, R.drawable.congnitive_change_up,
            R.drawable.congnitive_change_up, R.drawable.congnitive_change_up, R.drawable.congnitive_change_up};

    int game_images[]={R.drawable.sub_dual_focus,R.drawable.sub_dual_focus_pro,R.drawable.sub_blink,
    R.drawable.sub_blink_pro,R.drawable.sub_track_the_route,R.drawable.sub_memory_matrix,
    R.drawable.sub_memory_matrix_pro,R.drawable.sub_dancing_ball,R.drawable.sub_shapes,
    R.drawable.sub_match_it,R.drawable.sub_match_it_pro,R.drawable.sub_reversal,
    R.drawable.sub_reversal_pro,R.drawable.sub_money_game,R.drawable.sub_solve_it,
    R.drawable.sub_after_math,R.drawable.sub_speed_shop,R.drawable.sub_spot_it,R.drawable.sub_spot_it_pro,
    R.drawable.sub_brain_flash};

    String spinnerValues[] =new String[6];
    String gameValues[] = new String[20];
    private ListView listView;

    DrawerLayout dLayout;
    FrameLayout fram;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle,mTitle;
    private boolean sliderbar=false;
    private LinearLayout home,game,brain,compare,leaderbord,friend,settings,language,logout,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaders_board);

        //For setting action bar title in center
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.leaders_board);

        ((Button)findViewById(R.id.skillwise)).setOnClickListener(this);
        ((Button)findViewById(R.id.gamevise)).setOnClickListener(this);
        skill_wise_spin = (Spinner) findViewById(R.id.skil_wise_spinner);

        spinnerValues[0]=getResources().getString(R.string.attention);
        spinnerValues[1]=getResources().getString(R.string.memory);
        spinnerValues[2]=getResources().getString(R.string.visual);
        spinnerValues[3]=getResources().getString(R.string.flexibility);
        spinnerValues[4]=getResources().getString(R.string.problem_solving);
        spinnerValues[5]=getResources().getString(R.string.speed);

        gameValues[0]=getResources().getString(R.string.dual_focus);
        gameValues[1]=getResources().getString(R.string.dual_focus_pro);
        gameValues[2]=getResources().getString(R.string.blink);
        gameValues[3]=getResources().getString(R.string.blink_pro);
        gameValues[4]=getResources().getString(R.string.track_route);
        gameValues[5]=getResources().getString(R.string.memory_matrix);
        gameValues[6]=getResources().getString(R.string.memory_matrix_pro);
        gameValues[7]=getResources().getString(R.string.dancing_balls);
        gameValues[8]=getResources().getString(R.string.shapes);
        gameValues[9]=getResources().getString(R.string.match_it);
        gameValues[10]=getResources().getString(R.string.match_it_pro);
        gameValues[11]=getResources().getString(R.string.reversal);
        gameValues[12]=getResources().getString(R.string.reversal_pro);
        gameValues[13]=getResources().getString(R.string.money_game);
        gameValues[14]=getResources().getString(R.string.solve_it);
        gameValues[15]=getResources().getString(R.string.after_math);
        gameValues[16]=getResources().getString(R.string.speed_shop);
        gameValues[17]=getResources().getString(R.string.spot_it);
        gameValues[18]=getResources().getString(R.string.spot_it_pro);
        gameValues[19]=getResources().getString(R.string.brain_flash);

        slidex();
        slidelayoute();
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

        ((TextView)findViewById(R.id.profilename)).setText(DataBase.getUserName(LeadersBoard.this));
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

        dLayout = (DrawerLayout) findViewById(R.id.leadersbords);
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
                DataBase.setLogin("logout",LeadersBoard.this);
                Intent intent=new Intent(getApplicationContext(),Form.class);
                startActivity(intent);
            }else if(v.getId()==R.id.exit){
                exit.setBackgroundResource(R.color.cyan);
            }
            dLayout.closeDrawers();
            LeadersBoard.this.finish();
        }
    };

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.skillwise){
            values="skill";
            skillwise();
        }else if(v.getId()==R.id.gamevise){
            values="game";
            gamewise();
        }
    }

    private void gamewise() {
        //Start of Spinner coding for Selection of section for Skill Wise Spinner Category for showing six gaming section sin list
//        game_wise_spinner = (Spinner) findViewById(R.id.game_wise_spinner);
        skill_wise_spin.setAdapter(new MyAdapter(this, R.layout.slavetype_spinner, gameValues));
        skill_wise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // Object item = parent.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void skillwise() {

        //Start of Spinner coding for Selection of section for Skill Wise Spinner Category for showing six gaming section sin list

        skill_wise_spin.setAdapter(new MyAdapter(this, R.layout.slavetype_spinner, spinnerValues));
        skill_wise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // Object item = parent.getItemAtPosition(pos);


            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    public class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context ctx, int txtViewResourceId, String[] objects) {
            super(ctx, txtViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
            return getCustomView(position, cnvtView, prnt);
        }

        @Override
        public View getView(int pos, View cnvtView, ViewGroup prnt) {

            return getCustomView(pos, cnvtView, prnt);
        }

        public View getCustomView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View mySpinner = inflater.inflate(R.layout.slavetype_spinner, parent, false);
            TextView main_text = (TextView) mySpinner.findViewById(R.id.text_main_seen);
            if(values.equals("skill")) {
                main_text.setText(spinnerValues[position]);
                ImageView left_icon = (ImageView) mySpinner.findViewById(R.id.left_pic);
                left_icon.setImageResource(total_images[position]);
            }else  if(values.equals("game")) {
                main_text.setText(gameValues[position]);
                ImageView left_icon = (ImageView) mySpinner.findViewById(R.id.left_pic);
                left_icon.setImageResource(game_images[position]);
            }

            return mySpinner;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,HomePage.class);
        startActivity(intent);
        this.finish();
    }
}
