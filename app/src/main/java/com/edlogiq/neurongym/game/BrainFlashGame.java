package com.edlogiq.neurongym.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.constant.RefrenceWrapper;
import com.edlogiq.neurongym.neurongym.GameOver;
import com.edlogiq.neurongym.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BrainFlashGame extends Activity implements View.OnClickListener {

    public int StartTime = 45;
    int ab = 0;
    Timer timer;
    TimerTask mTimerTask;
    Handler handler = null;
    private TextView timertext;
    private ImageView image1, image2, image3;
    private ArrayList<Integer> color = new ArrayList<>();
    private int imageval = 1, colorval = 0;
    private boolean clickval = false;
    private RelativeLayout pausepopup;
    private RefrenceWrapper refrence;
    private LinearLayout circle1,circle2,circle3,brain;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_flash_game);


        ((RelativeLayout)findViewById(R.id.brainflashgame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.brainflashgame)).setBackgroundResource(R.color.black);
        }
        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        refrence=RefrenceWrapper.getRefrenceWrapper(this);
        image1 = (ImageView) findViewById(R.id.imageview1);
        image2 = (ImageView) findViewById(R.id.imageview2);
        image3 = (ImageView) findViewById(R.id.imageview3);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        circle1=(LinearLayout)findViewById(R.id.circle1);
        circle2=(LinearLayout)findViewById(R.id.circle2);
        circle3=(LinearLayout)findViewById(R.id.circle3);
        brain=(LinearLayout)findViewById(R.id.brainflash);
        color.add(R.drawable.red);
        color.add(R.drawable.green);
//        color.add(R.drawable.grey);
        refrence.setBrainflash(0);
        handler = new Handler();
        timertext=(TextView)findViewById(R.id.timertextview);
        timer = new Timer();
        startTimer();

        popuplayout();
        gamerotation();
        gamestart();
    }

    private void gamerotation() {

        RotateAnimation uanim=new RotateAnimation(0,360,Animation.RELATIVE_TO_PARENT,
                (float) 0.11,Animation.RELATIVE_TO_PARENT, (float) 0.5);
        uanim.setRepeatCount(Animation.INFINITE);
        uanim.setRepeatMode(Animation.RESTART);
        uanim.setDuration(5000);
        uanim.setFillAfter(true);

        RotateAnimation ranim=new RotateAnimation(0,360,Animation.RELATIVE_TO_PARENT,
                -(float) 0.22,Animation.RELATIVE_TO_PARENT, -(float) 0.10);
        ranim.setRepeatCount(Animation.INFINITE);
        ranim.setRepeatMode(Animation.RESTART);
        ranim.setDuration(5000);
        ranim.setFillAfter(true);

        RotateAnimation lanim=new RotateAnimation(0,360,Animation.RELATIVE_TO_PARENT,
                (float) 0.44,Animation.RELATIVE_TO_PARENT, -(float) 0.10);
        lanim.setRepeatCount(Animation.INFINITE);
        lanim.setRepeatMode(Animation.RESTART);
        lanim.setDuration(5000);
        lanim.setFillAfter(true);

//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
//        circle1.startAnimation(animation);
//        Log.e("Hello",""+Animation.RELATIVE_TO_SELF);

        circle1.startAnimation(uanim);
        circle2.startAnimation(ranim);
        circle3.startAnimation(lanim);

//        lanim.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                gamestart();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
    }


    private void popuplayout() {
        ((LinearLayout) findViewById(R.id.layoutepause)).setOnClickListener(listner1);

        pausepopup = (RelativeLayout) findViewById(R.id.pausepopup);
        ((LinearLayout) findViewById(R.id.resume)).setOnClickListener(listner1);
        ((LinearLayout) findViewById(R.id.replay)).setOnClickListener(listner1);
        ((LinearLayout) findViewById(R.id.instructions)).setOnClickListener(listner1);
        ((LinearLayout) findViewById(R.id.sound)).setOnClickListener(listner1);
        ((LinearLayout) findViewById(R.id.exitgame)).setOnClickListener(listner1);
    }

    private void pausepopup() {
        if (pausepopup.getVisibility() == View.GONE) {
            pausepopup.setVisibility(View.VISIBLE);
        } else {
            pausepopup.setVisibility(View.GONE);
        }
    }

    View.OnClickListener listner1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.layoutepause) {
                pausepopup();
            } else if (v.getId() == R.id.resume) {

            } else if (v.getId() == R.id.replay) {


            } else if (v.getId() == R.id.instructions) {

                pausepopup();
            } else if (v.getId() == R.id.sound) {

            } else if (v.getId() == R.id.exitgame) {

            }

        }
    };

    private void gamestart() {
        time=0;
        time1=0;
        colorval = (new Random().nextInt(2));
        ScaleAnimation scal = new ScaleAnimation(1f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF,
                (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
        scal.setDuration(2000);

        if(colorval==0 ||colorval==1 ){
            ScaleAnimation scalanim = new ScaleAnimation(.8f, 1f, .8f, 1f, Animation.RELATIVE_TO_SELF,
                    (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
            scalanim.setRepeatCount(1);
            scalanim.setRepeatMode(Animation.RESTART);
            scalanim.setFillAfter(true);
            scalanim.setDuration(100);
            brain.startAnimation(scalanim);


        }

        if (imageval == 1) {
            imageval = 2;
            image1.setBackgroundResource(color.get(colorval));
            image2.setBackgroundResource(R.drawable.grey);
            image3.setBackgroundResource(R.drawable.grey);
            image1.startAnimation(scal);

        } else if (imageval == 2) {
            imageval = 3;
            image2.setBackgroundResource(color.get(colorval));
            image1.setBackgroundResource(R.drawable.grey);
            image3.setBackgroundResource(R.drawable.grey);
            image2.startAnimation(scal);
        } else if (imageval == 3) {
            imageval = 1;
            image3.setBackgroundResource(color.get(colorval));
            image2.setBackgroundResource(R.drawable.grey);
            image1.setBackgroundResource(R.drawable.grey);
            image3.startAnimation(scal);
        }

        scal.setAnimationListener(listner);
    }

    Animation.AnimationListener listner = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
             time= System.currentTimeMillis();
        }

        @Override
        public void onAnimationEnd(Animation animation) {
//            clickval = false;
            gamestart();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private long time,time1;

    @Override
    public void onClick(View v) {
        {
            if (v.getId() == R.id.imageview1) {
                 if(colorval==1){
                     time1= System.currentTimeMillis();
                     Log.e("Time",time1+"   "+ time +"   "+(time1-time));
                 }else{
                     Log.e("Error",time1+"   "+ time +"   "+(time1-time));
                 }
            } else if (v.getId() == R.id.imageview2) {
                if(colorval==1){
                    time1= System.currentTimeMillis();
                    Log.e("Time",time1+"   "+ time +"   "+(time1-time));
                }else{
                    Log.e("Error",time1+"   "+ time +"   "+(time1-time));
                }
            } else if (v.getId() == R.id.imageview3) {
                if(colorval==1){
                    time1= System.currentTimeMillis();
                    Log.e("Time",time1+"   "+ time +"   "+(time1-time));
                }else{
                    Log.e("Error",time1+"   "+ time +"   "+(time1-time));
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        stopTimer();
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        stopTimer();
        super.onStop();
    }

    public void startTimer() {

        Log.e("StartTime", "" + StartTime);
        mTimerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (StartTime > 0) {
                            StartTime--;
                        }
                        if (StartTime == 0) {
                            stopTimer();
                            gameover();
                        }
                        timertext.setText("" + StartTime);
                    }
                });
            }
        };
        timer.schedule(mTimerTask, 0, 1000);

    }

    private void gameover() {
        refrence.setGame("BrainFlash");
        refrence.setScore(refrence.getBlink());
//        refrence.setMultiplier(multiplierval);
        Intent intent=new Intent(this, GameOver.class);
        startActivity(intent);
        this.finish();
    }

    public void stopTimer() {
        if (mTimerTask != null) {
            mTimerTask.cancel();
            StartTime = 0;
        }
    }


}
