package com.edlogiq.neurongym.game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
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

public class ReversalProGame extends Activity {


    public int StartTime = 45;
    int ab = 0;
    Timer timer;
    TimerTask mTimerTask;
    Handler handler = null;
    private ImageView balllayout;
    ArrayList<Integer> ring = new ArrayList<Integer>();
    private boolean tuch = false, move = true;
    private float x, y;
    private Animation anim;
    private String[] ballcolor = {"red", "green"};
    private String moveval, color;
    private int side, ringcolor,prog=0;
    private LinearLayout ringlayout;
    private TextView timertext,scoretext;
    private RelativeLayout pausepopup;
    private LinearLayout rightanswer, wronganswer;
    private ImageView rightans, wrongans;
    private RefrenceWrapper refrence;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reversal_pro_game);

        ((RelativeLayout)findViewById(R.id.reversalprogame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.reversalprogame)).setBackgroundResource(R.color.black);
        }
        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        refrence = RefrenceWrapper.getRefrenceWrapper(this);
        refrence.setReversalpro(0);
        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswer = (LinearLayout) findViewById(R.id.righttick);
        wronganswer = (LinearLayout) findViewById(R.id.wrongcross);
        balllayout = (ImageView) findViewById(R.id.circlesballimageview);
        ringlayout = (LinearLayout) findViewById(R.id.circlesringlayout);
        scoretext = (TextView) findViewById(R.id.scoretextview);
        handler = new Handler();
        timertext=(TextView)findViewById(R.id.timertextview);
        timer = new Timer();
        startTimer();
        addinteger();
        nextlevel();
        popuplayout();
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

    private void addinteger() {
        ring.clear();
        ring.add(0);
        ring.add(1);
        ring.add(2);
        ring.add(3);
    }

    private void nextlevel() {
        scoretext.setText(""+refrence.getReversalpro());
        int ball = (new Random().nextInt(2));
        if (ring.size() != 0) {
            int ringval = (new Random().nextInt(ring.size()));
            side = ring.get(ringval);
            ring.remove(ringval);
        } else {
            addinteger();
        }
        ringcolor = (new Random().nextInt(4));
        color = ballcolor[ball];

        if (color.equals("red")) {
            balllayout.setBackgroundResource(R.drawable.red);
        } else if (color.equals("green")) {
            balllayout.setBackgroundResource(R.drawable.green);
        }

        if (ringcolor == 0) {
            ringlayout.setBackgroundResource(R.drawable.ring_blue);
        } else if (ringcolor == 1) {
            ringlayout.setBackgroundResource(R.drawable.ring_cyan);
        } else if (ringcolor == 2) {
            ringlayout.setBackgroundResource(R.drawable.ring_orange);
        } else if (ringcolor == 3) {
            ringlayout.setBackgroundResource(R.drawable.ring_pink);
        }

        if (side == 0) {
            ((ImageView) findViewById(R.id.leftbasketimageview)).setBackgroundResource(R.drawable.basket_orange_left);
            ((ImageView) findViewById(R.id.topbasketimageview)).setBackgroundResource(R.drawable.basket_blue_top);
            ((ImageView) findViewById(R.id.rightbasketimageview)).setBackgroundResource(R.drawable.basket_cyan_right);
            ((ImageView) findViewById(R.id.bottombasketimageview)).setBackgroundResource(R.drawable.basket_pink_bottom);
        } else if (side == 1) {
            ((ImageView) findViewById(R.id.leftbasketimageview)).setBackgroundResource(R.drawable.basket_pink_left);
            ((ImageView) findViewById(R.id.topbasketimageview)).setBackgroundResource(R.drawable.basket_orange_top);
            ((ImageView) findViewById(R.id.rightbasketimageview)).setBackgroundResource(R.drawable.basket_blue_right);
            ((ImageView) findViewById(R.id.bottombasketimageview)).setBackgroundResource(R.drawable.basket_cyan_bottom);
        } else if (side == 2) {
            ((ImageView) findViewById(R.id.leftbasketimageview)).setBackgroundResource(R.drawable.basket_cyan_left);
            ((ImageView) findViewById(R.id.topbasketimageview)).setBackgroundResource(R.drawable.basket_pink_top);
            ((ImageView) findViewById(R.id.rightbasketimageview)).setBackgroundResource(R.drawable.basket_orange_right);
            ((ImageView) findViewById(R.id.bottombasketimageview)).setBackgroundResource(R.drawable.basket_blue_bottom);
        } else if (side == 3) {
            ((ImageView) findViewById(R.id.leftbasketimageview)).setBackgroundResource(R.drawable.basket_blue_left);
            ((ImageView) findViewById(R.id.topbasketimageview)).setBackgroundResource(R.drawable.basket_cyan_top);
            ((ImageView) findViewById(R.id.rightbasketimageview)).setBackgroundResource(R.drawable.basket_pink_right);
            ((ImageView) findViewById(R.id.bottombasketimageview)).setBackgroundResource(R.drawable.basket_orange_bottom);

        }
    }


    Animation.AnimationListener animlistner = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            checkAnswer();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actindown(event);
                break;

            case MotionEvent.ACTION_MOVE:
                if (color.equals("red")) {
                    actionmovered(event);
                } else if (color.equals("green")) {
                    actionmovegreen(event);
                }
                break;

            case MotionEvent.ACTION_UP:
                actionup(event);
                break;
        }

        return super.onTouchEvent(event);
    }

    private void actindown(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        move = true;
        tuch = true;
    }

    private void actionup(MotionEvent event) {
        move = true;
    }

    private void actionmovered(MotionEvent event) {

        if (tuch) {

            if ((x - event.getX()) < -5) {
                if (move) {
                    Log.e("hello", "Right");
                    moveval = "Right";
                    anim = new TranslateAnimation(0, -500, 0, 0);
                    anim.setDuration(500);
                    move = false;
                }

            } else if ((x - event.getX()) > 5) {
                if (move) {
                    moveval = "Left";
                    Log.e("hello", "Left");
                    anim = new TranslateAnimation(0, 500, 0, 0);
                    anim.setDuration(500);
//                    anim.setFillAfter(true);
                    move = false;
                }
            } else if ((y - event.getY()) > 5) {
                if (move) {
                    Log.e("hello", "Top");
                    moveval = "Top";
                    anim = new TranslateAnimation(0, 0, 0, 500);
                    anim.setDuration(500);
//                    anim.setFillAfter(true);
                    move = false;
                }

            } else if ((y - event.getY()) < -5) {
                if (move) {
                    Log.e("hello", "Dawn");
                    moveval = "Down";
                    anim = new TranslateAnimation(0, 0, 0, -500);
                    anim.setDuration(500);
//                    anim.setFillAfter(true);
                    move = false;
                }
            }
            ringlayout.startAnimation(anim);
            anim.setAnimationListener(animlistner);
        }
    }

    private void actionmovegreen(MotionEvent event) {

        if (tuch) {

            if ((x - event.getX()) < -5) {
                if (move) {
                    Log.e("hello", "Right");
                    moveval = "Right";
                    anim = new TranslateAnimation(0, 500, 0, 0);
                    anim.setDuration(500);
                    move = false;
                }

            } else if ((x - event.getX()) > 5) {
                if (move) {
                    moveval = "Left";
                    Log.e("hello", "Left");
                    anim = new TranslateAnimation(0, -500, 0, 0);
                    anim.setDuration(500);
//                    anim.setFillAfter(true);
                    move = false;
                }
            } else if ((y - event.getY()) > 5) {
                if (move) {
                    Log.e("hello", "Top");
                    moveval = "Top";
                    anim = new TranslateAnimation(0, 0, 0, -500);
                    anim.setDuration(500);
//                    anim.setFillAfter(true);
                    move = false;
                }

            } else if ((y - event.getY()) < -5) {
                if (move) {
                    Log.e("hello", "Dawn");
                    moveval = "Down";
                    anim = new TranslateAnimation(0, 0, 0, 500);
                    anim.setDuration(500);
//                    anim.setFillAfter(true);
                    move = false;
                }
            }
            ringlayout.startAnimation(anim);
            anim.setAnimationListener(animlistner);
        }
    }

    private void checkAnswer() {
        if (color.equals("red")) {
            if (moveval.equals("Left")) {
                if (side == 2 && ringcolor == 2) {
                    rightanswer();
                } else if (side == 0 && ringcolor == 1) {
                    rightanswer();
                } else if (side == 1 && ringcolor == 0) {
                    rightanswer();
                } else if (side == 3 && ringcolor == 3) {
                    rightanswer();
                } else {
                    wronganswer();
                }

            } else if (moveval.equals("Down")) {
                if (side == 0 && ringcolor == 0) {
                    rightanswer();
                } else if (side == 1 && ringcolor == 2) {
                    rightanswer();
                } else if (side == 2 && ringcolor == 3) {
                    rightanswer();
                } else if (side == 3 && ringcolor == 1) {
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (moveval.equals("Right")) {
                if (side == 0 && ringcolor == 2) {
                    rightanswer();
                } else if (side == 1 && ringcolor == 3) {
                    rightanswer();
                } else if (side == 2 && ringcolor == 1) {
                    rightanswer();
                } else if (side == 3 && ringcolor == 0) {
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (moveval.equals("Top")) {
                if (side == 0 && ringcolor == 3) {
                    rightanswer();
                } else if (side == 1 && ringcolor == 1) {
                    rightanswer();
                } else if (side == 2 && ringcolor == 0) {
                    rightanswer();
                } else if (side == 3 && ringcolor == 2) {
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else {
                wronganswer();
            }

        } else if (color.equals("green")) {
            if (moveval.equals("Left")) {
                if (side == 0 && ringcolor == 2) {
                    rightanswer();
                } else if (side == 1 && ringcolor == 3) {
                    rightanswer();
                } else if (side == 2 && ringcolor == 1) {
                    rightanswer();
                } else if (side == 3 && ringcolor == 0) {
                    rightanswer();
                } else {
                    wronganswer();
                }

            } else if (moveval.equals("Down")) {
                if (side == 0 && ringcolor == 3) {
                    rightanswer();
                } else if (side == 1 && ringcolor == 1) {
                    rightanswer();
                } else if (side == 2 && ringcolor == 0) {
                    rightanswer();
                } else if (side == 3 && ringcolor == 2) {
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (moveval.equals("Right")) {
                if (side == 0 && ringcolor == 1) {
                    rightanswer();
                } else if (side == 1 && ringcolor == 0) {
                    rightanswer();
                } else if (side == 2 && ringcolor == 2) {
                    rightanswer();
                } else if (side == 3 && ringcolor == 3) {
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (moveval.equals("Top")) {
                if (side == 0 && ringcolor == 0) {
                    rightanswer();
                } else if (side == 1 && ringcolor == 2) {
                    rightanswer();
                } else if (side == 2 && ringcolor == 3) {
                    rightanswer();
                } else if (side == 3 && ringcolor == 1) {
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else {
                wronganswer();
            }
        }
    }



    private void rightanswer() {
        {
            if (rightanswer.getVisibility() == View.GONE) {
                rightanswer.setVisibility(View.VISIBLE);
                rightans.setVisibility(View.VISIBLE);
                ScaleAnimation scal = new ScaleAnimation(1f, 1.5f, 1f, 1.5f, Animation.RELATIVE_TO_SELF,
                        (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
                scal.setDuration(500);

                AlphaAnimation animation1 = new AlphaAnimation(1f, 0f);
                animation1.setDuration(500);
                rightans.startAnimation(animation1);
                AnimationSet anim = new AnimationSet(false);
                anim.addAnimation(scal);
                anim.addAnimation(animation1);
                rightanswer.startAnimation(anim);
                scal.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        refrence.setReversalpro(refrence.getReversalpro() + 100);
                        process();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rightanswer.setVisibility(View.GONE);
                        rightans.setVisibility(View.GONE);
                        rightval=rightval+1;
                        nextlevel();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }

    }

    private void wronganswer() {
        if (wronganswer.getVisibility() == View.GONE) {
            wronganswer.setVisibility(View.VISIBLE);
            wrongans.setVisibility(View.VISIBLE);
            ScaleAnimation scal = new ScaleAnimation(1f, 1.5f, 1f, 1.5f, Animation.RELATIVE_TO_SELF,
                    (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
            scal.setDuration(500);

            AlphaAnimation animation1 = new AlphaAnimation(1f, 0f);
            animation1.setDuration(500);
            wrongans.startAnimation(animation1);
            AnimationSet anim = new AnimationSet(false);
            anim.addAnimation(scal);
            anim.addAnimation(animation1);
            wronganswer.startAnimation(anim);
            scal.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    wronganswer.setVisibility(View.GONE);
                    wrongans.setVisibility(View.GONE);
                    if(progress.getProgress()<50){
                        for(int i=progress.getProgress();i>=0;i--){
                            progress.setProgress(i);
                        }
                    }else{
                        for(int i=progress.getProgress();i>=50;i--){
                            progress.setProgress(i);
                        }
                    }
                    wrongval=wrongval+1;
                    nextlevel();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    int multyplyer=0,multiplierval=0;
    private void process(){
        for(int i=prog;i<=(prog+10);i++) {
            progress.setProgress(i);
        }

        prog=prog+10;
        if(prog==50){
            ((LinearLayout)findViewById(R.id.processlayoute2)).
                    setBackgroundResource(R.drawable.circle_text_cyan);
            ((TextView) findViewById(R.id.processtext2)).setTextColor(Color.parseColor("#ffffff"));
            multiplierval=multiplierval+1;
        }
        if(prog==100){
            multiplierval=multiplierval+1;
            for(int i=prog;i>0;i--) {
                progress.setProgress(i);

            }
            prog=0;
            if(multyplyer==0) {
                ((TextView) findViewById(R.id.processtext1)).setText("x3");
                ((TextView) findViewById(R.id.processtext2)).setText("x4");
                ((TextView) findViewById(R.id.processtext3)).setText("x5");
                ((TextView) findViewById(R.id.processtext2)).setTextColor(Color.parseColor("#888888"));
                ((LinearLayout)findViewById(R.id.processlayoute2)).
                        setBackgroundResource(R.drawable.circle_text);
                multyplyer=1;
            }else if(multyplyer==1) {
                ((TextView) findViewById(R.id.processtext1)).setText("x5");
                ((TextView) findViewById(R.id.processtext2)).setText("x6");
                ((TextView) findViewById(R.id.processtext3)).setText("x7");
                ((TextView) findViewById(R.id.processtext2)).setTextColor(Color.parseColor("#888888"));
                ((LinearLayout)findViewById(R.id.processlayoute2)).
                        setBackgroundResource(R.drawable.circle_text);
                multyplyer=2;
            }else if(multyplyer==2) {
                ((TextView) findViewById(R.id.processtext1)).setText("x7");
                ((TextView) findViewById(R.id.processtext2)).setText("x8");
                ((TextView) findViewById(R.id.processtext3)).setText("x9");
                ((TextView) findViewById(R.id.processtext2)).setTextColor(Color.parseColor("#888888"));
                ((LinearLayout)findViewById(R.id.processlayoute2)).
                        setBackgroundResource(R.drawable.circle_text);
                multyplyer=3;
            }else if(multyplyer==3) {
                ((TextView) findViewById(R.id.processtext1)).setText("x9");
                ((TextView) findViewById(R.id.processtext2)).setText("x10");
                ((TextView) findViewById(R.id.processtext3)).setText("x11");
                ((TextView) findViewById(R.id.processtext2)).setTextColor(Color.parseColor("#888888"));
                ((LinearLayout)findViewById(R.id.processlayoute2)).
                        setBackgroundResource(R.drawable.circle_text);
            }

        }
    }

    @Override
    public void onBackPressed() {
        stopTimer();
        super.onBackPressed();
    }

    public void startTimer() {
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

    int wrongval=0,rightval=0;
    private void gameover() {
        refrence.setGame("ReversalPro");
        refrence.setScore(refrence.getReversalpro());
        refrence.setMultiplier(multiplierval);
        int total=wrongval+rightval;
        refrence.setAccuracy((int)((rightval*100)/total));
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
