package com.edlogiq.neurongym.neurongym;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.signin.Form;
import com.edlogiq.neurongym.constant.RefrenceWrapper;
import com.gc.materialdesign.views.Button;
import com.gc.materialdesign.views.Switch;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.ParseUser;

import java.lang.reflect.Field;
import java.util.Calendar;

import com.edlogiq.neurongym.R;


public class ThemeSelect extends ActionBarActivity implements View.OnClickListener{


    private Button Next;
    private TimePicker time_picker,time_picker1;
    private RefrenceWrapper refrence;

    DrawerLayout dLayout;
    FrameLayout fram;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle,mTitle;
    private boolean sliderbar=false;
    private LinearLayout home,game,brain,compare,leaderbord,friend,settings,language,logout,exit;
    private Button monday,tuesday,wednesday,thirsday,friday,saturday,sunday;
    private TextView mondaytext,tuesdaytext,wednesdaytext,thirsdaytext,fridaytext,saturdaytext,sundaytext;
    SharedPreferences pref;
    private Switch reswitch;
    private boolean timepopup=false;
    private String Day;
    final static int REQUEST_CODE = 0;
    AlarmManager alarmManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_select);


        ((DrawerLayout)findViewById(R.id.drawer_layout)).setBackgroundResource(R.color.white);
        ((LinearLayout)findViewById(R.id.left_drawer)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((DrawerLayout)findViewById(R.id.drawer_layout)).setBackgroundResource(R.color.black);
            ((LinearLayout)findViewById(R.id.left_drawer)).setBackgroundResource(R.color.black);
        }
        pref = getPreferences(0);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.settings);
        getSupportActionBar().setTitle(R.string.theam_select);

        reswitch=(Switch)findViewById(R.id.switch1);
        reswitch.setOnClickListener(this);

        time_picker=(TimePicker)findViewById(R.id.timePicker);
        time_picker1=(TimePicker)findViewById(R.id.timePickerReminder);
        time_picker1.setIs24HourView(true);
        time_picker.setIs24HourView(true);
        ((Button) findViewById(R.id.buttonFloat_next)).setOnClickListener(this);
        ((Button)findViewById(R.id.themedark)).setOnClickListener(this);
        ((Button)findViewById(R.id.themelight)).setOnClickListener(this);

        init();
        set_timepicker_text_colour();
        slidex();
        slidelayoute();
//        parse();
    }




    private void init() {
        ((Button)findViewById(R.id.monday)).setOnClickListener(this);
        ((Button)findViewById(R.id.tuesday)).setOnClickListener(this);
        ((Button)findViewById(R.id.wednesday)).setOnClickListener(this);
        ((Button)findViewById(R.id.thirsday)).setOnClickListener(this);
        ((Button)findViewById(R.id.friday)).setOnClickListener(this);
        ((Button)findViewById(R.id.saturday)).setOnClickListener(this);
        ((Button)findViewById(R.id.sunday)).setOnClickListener(this);
        ((TextView)findViewById(R.id.btnReminderTimesave)).setOnClickListener(datelistner);
        ((TextView)findViewById(R.id.btnReminderTimeCancel)).setOnClickListener(datelistner);
    }

    // Time Picker Color Change
    Resources system;
    private void set_timepicker_text_colour(){
        system = Resources.getSystem();
        int hour_numberpicker_id = system.getIdentifier("hour", "id", "android");
        int minute_numberpicker_id = system.getIdentifier("minute", "id", "android");
        int ampm_numberpicker_id = system.getIdentifier("amPm", "id", "android");

        NumberPicker hour_numberpicker = (NumberPicker) time_picker.findViewById(hour_numberpicker_id);
        NumberPicker minute_numberpicker = (NumberPicker) time_picker.findViewById(minute_numberpicker_id);
        NumberPicker ampm_numberpicker = (NumberPicker) time_picker.findViewById(ampm_numberpicker_id);

        set_numberpicker_text_colour(hour_numberpicker);
        set_numberpicker_text_colour(minute_numberpicker);
        set_numberpicker_text_colour(ampm_numberpicker);
    }

    private void set_numberpicker_text_colour(NumberPicker number_picker){
        final int count = number_picker.getChildCount();
        final int color = getResources().getColor(R.color.gray);

        for(int i = 0; i < count; i++){
            View child = number_picker.getChildAt(i);

            try{
                Field wheelpaint_field = number_picker.getClass().getDeclaredField("mSelectorWheelPaint");
                wheelpaint_field.setAccessible(true);

                ((Paint)wheelpaint_field.get(number_picker)).setColor(color);
                ((EditText)child).setTextColor(color);
                number_picker.invalidate();
            }
            catch(NoSuchFieldException e){
                Log.w("setNumberPickerTextColor", e);
            }
            catch(IllegalAccessException e){
                Log.w("setNumberPickerTextColor", e);
            }
            catch(IllegalArgumentException e){
                Log.w("setNumberPickerTextColor", e);
            }
        }
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

        ((TextView)findViewById(R.id.profilename)).setText(DataBase.getUserName(ThemeSelect.this));
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

    private GoogleApiClient mGoogleApiClient;
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
                DataBase.setLogin("logout",ThemeSelect.this);
                Intent intent=new Intent(getApplicationContext(),Form.class);
                startActivity(intent);

            }else if(v.getId()==R.id.exit){
                exit.setBackgroundResource(R.color.cyan);
            }
            dLayout.closeDrawers();
            ThemeSelect.this.finish();
        }
    };


    View.OnClickListener datelistner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            timepopup=false;
            Log.e("dayvalue",Day);
            if(Day.equals("Monday")) {
                if (v.getId() == R.id.btnReminderTimesave) {
                    ((TextView) findViewById(R.id.mondaytime)).setText("" + time_picker1.getCurrentHour()
                            + ":" + time_picker1.getCurrentMinute());
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                    Mondayalarm();
                } else if (v.getId() == R.id.btnReminderTimeCancel) {
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                }
            }else if(Day.equals("Tuesday")) {
                if (v.getId() == R.id.btnReminderTimesave) {
                    ((TextView) findViewById(R.id.tuesdaytime)).setText("" + time_picker1.getCurrentHour()
                            + ":" + time_picker1.getCurrentMinute());
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                    Tuesdayalarm();
                } else if (v.getId() == R.id.btnReminderTimeCancel) {
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                }
            }else if(Day.equals("Wednesday")) {
                if (v.getId() == R.id.btnReminderTimesave) {
                    ((TextView) findViewById(R.id.wednesdaytime)).setText("" + time_picker1.getCurrentHour()
                            + ":" + time_picker1.getCurrentMinute());
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                    Wednesdayalarm();
                } else if (v.getId() == R.id.btnReminderTimeCancel) {
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                }
            }else if(Day.equals("Thursday")) {
                if (v.getId() == R.id.btnReminderTimesave) {
                    ((TextView) findViewById(R.id.thursdaytime)).setText("" + time_picker1.getCurrentHour()
                            + ":" + time_picker1.getCurrentMinute());
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                    Thursdayalaram();
                } else if (v.getId() == R.id.btnReminderTimeCancel) {
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                }
            }else if(Day.equals("Friday")) {
                if (v.getId() == R.id.btnReminderTimesave) {
                    ((TextView) findViewById(R.id.fridaytime)).setText("" + time_picker1.getCurrentHour()
                            + ":" + time_picker1.getCurrentMinute());
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                    Fridayalaram();
                } else if (v.getId() == R.id.btnReminderTimeCancel) {
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                }
            }else if(Day.equals("Saturday")) {
                if (v.getId() == R.id.btnReminderTimesave) {
                    ((TextView) findViewById(R.id.saturdaytime)).setText("" + time_picker1.getCurrentHour()
                            + ":" + time_picker1.getCurrentMinute());
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                    saturdayalaram();
                } else if (v.getId() == R.id.btnReminderTimeCancel) {
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                }
            }else if(Day.equals("Sunday")) {
                if (v.getId() == R.id.btnReminderTimesave) {
                    ((TextView) findViewById(R.id.sundaytime)).setText("" + time_picker1.getCurrentHour()
                            + ":" + time_picker1.getCurrentMinute());
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                    Sundayalaram();
                } else if (v.getId() == R.id.btnReminderTimeCancel) {
                    ((RelativeLayout) findViewById(R.id.ReminderTime)).setVisibility(View.GONE);
                }
            }
        }
    };


    @Override
    public void onClick(View v) {
        if(timepopup){
            return;
        }

        if(v.getId()==R.id.themedark){
            if(DataBase.getTheam(this).equals("light")){
                ((RelativeLayout)findViewById(R.id.themeselect)).setBackgroundResource(R.color.black);
                DataBase.setTheam("dark",this);
            }
        }else if(v.getId()==R.id.themelight){
            if(DataBase.getTheam(this).equals("dark")){
                ((RelativeLayout)findViewById(R.id.themeselect)).setBackgroundResource(R.color.white);
                DataBase.setTheam("light",this);

            }
        }else if(v.getId()==R.id.buttonFloat_next){
            Intent intent1 = new Intent(getApplicationContext(), HomePage.class);
            startActivity(intent1);
        }else if(v.getId()==R.id.monday){
            ((RelativeLayout)findViewById(R.id.ReminderTime)).setVisibility(View.VISIBLE);
            Day="Monday";
            timepopup=true;
        }else if(v.getId()==R.id.tuesday){
            ((RelativeLayout)findViewById(R.id.ReminderTime)).setVisibility(View.VISIBLE);
            timepopup=true;
            Day="Tuesday";
        }else if(v.getId()==R.id.wednesday){
            ((RelativeLayout)findViewById(R.id.ReminderTime)).setVisibility(View.VISIBLE);
            timepopup=true;
            Day="Wednesday";
        }else if(v.getId()==R.id.thirsday){
            ((RelativeLayout)findViewById(R.id.ReminderTime)).setVisibility(View.VISIBLE);
            timepopup=true;
            Day="Thursday";
        }else if(v.getId()==R.id.friday){
            ((RelativeLayout)findViewById(R.id.ReminderTime)).setVisibility(View.VISIBLE);
            timepopup=true;
            Day="Friday";
        }else if(v.getId()==R.id.saturday){
            ((RelativeLayout)findViewById(R.id.ReminderTime)).setVisibility(View.VISIBLE);
            timepopup=true;
            Day="Saturday";
        }else if(v.getId()==R.id.sunday){
            ((RelativeLayout)findViewById(R.id.ReminderTime)).setVisibility(View.VISIBLE);
            timepopup=true;
            Day="Sunday";
        }else if(v.getId()==R.id.switch1){
            reswitch.setChecked(true);
        }
        Log.e("Values",""+ ((RelativeLayout)findViewById(R.id.ReminderTime)).getVisibility());
    }

    private void Mondayalarm() {
        Calendar current = Calendar.getInstance();
        current.get(Calendar.HOUR);
        Calendar nextAlarm = Calendar.getInstance();
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = Calendar.getInstance();
        calSet.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        calSet.set(Calendar.HOUR_OF_DAY,  time_picker1.getCurrentHour());
        calSet.set(Calendar.MINUTE, time_picker1.getCurrentMinute());
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);

//        Log.e("Set Alarm",calSet.getTime()+"      "+calSet);
//        if(calSet.compareTo(current) <= 0){
//            Toast.makeText(getApplicationContext(), "Invalid Date/Time", Toast.LENGTH_LONG).show();
//            calSet.set(Calendar.WEEK_OF_YEAR,(Calendar.WEEK_OF_YEAR)+1);
//            Log.e("date time",calSet.getTime()+"    "+calSet);
//        }
        {
            Intent intent = new Intent(this, Receiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), REQUEST_CODE, intent, 0);
            alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(alarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), alarmManager.INTERVAL_DAY*7, pendingIntent);
            Log.e("Set Alarm",calSet.getTime()+"      "+calSet);
        }

    }


    private void Tuesdayalarm() {
        Calendar current = Calendar.getInstance();
        current.get(Calendar.HOUR);
        Calendar nextAlarm = Calendar.getInstance();
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = Calendar.getInstance();
        calSet.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);
        calSet.set(Calendar.HOUR_OF_DAY,  time_picker1.getCurrentHour());
        calSet.set(Calendar.MINUTE, time_picker1.getCurrentMinute());
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);


//        if(calSet.compareTo(current) <= 0){
//            Toast.makeText(getApplicationContext(), "Invalid Date/Time", Toast.LENGTH_LONG).show();
//            Log.e("date time",calSet.getTime()+"     "+calSet);
//        }
        {
            Intent intent = new Intent(this, Receiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), REQUEST_CODE, intent, 0);
            alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(alarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), alarmManager.INTERVAL_DAY*7, pendingIntent);
            Log.e("Set Alarm",calSet.getTime()+"      "+calSet);
        }

    }


    private void Wednesdayalarm() {
        Calendar current = Calendar.getInstance();
        current.get(Calendar.HOUR);
        Calendar nextAlarm = Calendar.getInstance();
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = Calendar.getInstance();
        calSet.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
        calSet.set(Calendar.HOUR_OF_DAY,  time_picker1.getCurrentHour());
        calSet.set(Calendar.MINUTE, time_picker1.getCurrentMinute());
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);


//        if(calSet.compareTo(current) <= 0){
//            Toast.makeText(getApplicationContext(), "Invalid Date/Time", Toast.LENGTH_LONG).show();
//            Log.e("date time",calSet.getTime()+"    "+calSet);
//        }
        {
            Intent intent = new Intent(this, Receiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), REQUEST_CODE, intent, 0);
            alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(alarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), alarmManager.INTERVAL_DAY*7, pendingIntent);
            Log.e("Set Alarm",calSet.getTime()+"      "+calSet);
        }

    }


    private void Thursdayalaram() {
        Calendar current = Calendar.getInstance();
        current.get(Calendar.HOUR);
        Calendar nextAlarm = Calendar.getInstance();
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = Calendar.getInstance();
        calSet.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
        calSet.set(Calendar.HOUR_OF_DAY,  time_picker1.getCurrentHour());
        calSet.set(Calendar.MINUTE, time_picker1.getCurrentMinute());
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);


//        if(calSet.compareTo(current) <= 0){
//            Toast.makeText(getApplicationContext(), "Invalid Date/Time", Toast.LENGTH_LONG).show();
//            Log.e("date time",calSet.getTime()+"    "+calSet);
//        }
        {
            Intent intent = new Intent(this, Receiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), REQUEST_CODE, intent, 0);
            alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(alarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), alarmManager.INTERVAL_DAY*7, pendingIntent);
            Log.e("Set Alarm",calSet.getTime()+"      "+calSet);
        }

    }


    private void Fridayalaram() {
        Calendar current = Calendar.getInstance();
        current.get(Calendar.HOUR);
        Calendar nextAlarm = Calendar.getInstance();
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = Calendar.getInstance();
        calSet.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
        calSet.set(Calendar.HOUR_OF_DAY,  time_picker1.getCurrentHour());
        calSet.set(Calendar.MINUTE, time_picker1.getCurrentMinute());
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);


//        if(calSet.compareTo(current) <= 0){
//            Toast.makeText(getApplicationContext(), "Invalid Date/Time", Toast.LENGTH_LONG).show();
//            Log.e("date time",calSet.getTime()+"    "+calSet);
//        }
        {
            Intent intent = new Intent(this, Receiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), REQUEST_CODE, intent, 0);
            alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(alarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), alarmManager.INTERVAL_DAY*7, pendingIntent);
            Log.e("Set Alarm",calSet.getTime()+"      "+calSet);
        }

    }


    private void saturdayalaram() {
        Calendar current = Calendar.getInstance();
        current.get(Calendar.HOUR);
        Calendar nextAlarm = Calendar.getInstance();
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = Calendar.getInstance();
        calSet.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
        calSet.set(Calendar.HOUR_OF_DAY,  time_picker1.getCurrentHour());
        calSet.set(Calendar.MINUTE, time_picker1.getCurrentMinute());
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);


//        if(calSet.compareTo(current) <= 0){
//            Toast.makeText(getApplicationContext(), "Invalid Date/Time", Toast.LENGTH_LONG).show();
//            Log.e("date time",calSet.getTime()+"    "+calSet);
//        }
        {
            Intent intent = new Intent(this, Receiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), REQUEST_CODE, intent, 0);
            alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(alarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), alarmManager.INTERVAL_DAY*7, pendingIntent);
            Log.e("Set Alarm",calSet.getTime()+"      "+calSet);
        }

    }

    private void Sundayalaram() {
        Calendar current = Calendar.getInstance();
        current.get(Calendar.HOUR);
        Calendar nextAlarm = Calendar.getInstance();
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = Calendar.getInstance();
        calSet.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        calSet.set(Calendar.HOUR_OF_DAY,  time_picker1.getCurrentHour());
        calSet.set(Calendar.MINUTE, time_picker1.getCurrentMinute());
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);


//        if(calSet.compareTo(current) <= 0){
//            Toast.makeText(getApplicationContext(), "Invalid Date/Time", Toast.LENGTH_LONG).show();
//            Log.e("date time",calSet.getTime()+"    "+calSet);
//        }
        {
            Intent intent = new Intent(this, Receiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), REQUEST_CODE, intent, 0);
            alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(alarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), alarmManager.INTERVAL_DAY*7, pendingIntent);
            Log.e("Set Alarm",calSet.getTime()+"      "+calSet);
        }

    }


}
