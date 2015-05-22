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

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ReversalGame extends Activity {

    public int StartTime = 45;
    int ab = 0;
    Timer timer;
    TimerTask mTimerTask;
    Handler handler = null;
    private TextView timertext,scoretext;
    private ImageView balllayout, rightans, wrongans;
    private boolean tuch = false, move = true;
    private float x, y;
    private Animation anim;
    private String[] ballcolor = {"red", "green"};
    private String moveval, color;
    private int side,prog=0;
    private LinearLayout rightanswer, wronganswerval;
    private RelativeLayout pausepopup;
    private RefrenceWrapper refrence;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reversal_game);

        ((RelativeLayout)findViewById(R.id.reversalgame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.reversalgame)).setBackgroundResource(R.color.black);
        }
        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        refrence = RefrenceWrapper.getRefrenceWrapper(this);
        refrence.setReversal(0);
        handler = new Handler();
        timertext=(TextView)findViewById(R.id.timertextview);
        balllayout = (ImageView) findViewById(R.id.circlesballimageview);
        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswer = (LinearLayout) findViewById(R.id.righttick);
        wronganswerval = (LinearLayout) findViewById(R.id.wrongcross);
        scoretext = (TextView) findViewById(R.id.scoretextview);

        timer = new Timer();
        startTimer();
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

    private void nextlevel() {
        scoretext.setText(""+refrence.getReversal());
        int ball = (new Random().nextInt(2));
        side = (new Random().nextInt(4));
        color = ballcolor[ball];

        if (color.equals("red")) {
            balllayout.setBackgroundResource(R.drawable.red);
        } else if (color.equals("green")) {
            balllayout.setBackgroundResource(R.drawable.green);
        }

        if (side == 0) {
            ((ImageView) findViewById(R.id.basketleftimageview)).setBackgroundResource(R.drawable.basket_orange_left);
            ((ImageView) findViewById(R.id.baskettopimageview)).setBackgroundResource(R.drawable.basket_grey_top);
            ((ImageView) findViewById(R.id.basketrightimageview)).setBackgroundResource(R.drawable.basket_grey_right);
            ((ImageView) findViewById(R.id.basketbottomimageview)).setBackgroundResource(R.drawable.basket_grey_bottom);
        } else if (side == 1) {
            ((ImageView) findViewById(R.id.baskettopimageview)).setBackgroundResource(R.drawable.basket_orange_top);
            ((ImageView) findViewById(R.id.basketleftimageview)).setBackgroundResource(R.drawable.basket_grey_left);
            ((ImageView) findViewById(R.id.basketrightimageview)).setBackgroundResource(R.drawable.basket_grey_right);
            ((ImageView) findViewById(R.id.basketbottomimageview)).setBackgroundResource(R.drawable.basket_grey_bottom);
        } else if (side == 2) {
            ((ImageView) findViewById(R.id.basketrightimageview)).setBackgroundResource(R.drawable.basket_orange_right);
            ((ImageView) findViewById(R.id.basketleftimageview)).setBackgroundResource(R.drawable.basket_grey_left);
            ((ImageView) findViewById(R.id.baskettopimageview)).setBackgroundResource(R.drawable.basket_grey_top);
            ((ImageView) findViewById(R.id.basketbottomimageview)).setBackgroundResource(R.drawable.basket_grey_bottom);
        } else if (side == 3) {
            ((ImageView) findViewById(R.id.basketbottomimageview)).setBackgroundResource(R.drawable.basket_orange_bottom);
            ((ImageView) findViewById(R.id.basketleftimageview)).setBackgroundResource(R.drawable.basket_grey_left);
            ((ImageView) findViewById(R.id.baskettopimageview)).setBackgroundResource(R.drawable.basket_grey_top);
            ((ImageView) findViewById(R.id.basketrightimageview)).setBackgroundResource(R.drawable.basket_grey_right);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actindown(event);
                break;

            case MotionEvent.ACTION_MOVE:
                if (color.equals("green")) {
                    actionmovegreen(event);
                } else if (color.equals("red")) {
                    actionmovered(event);
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

    private void actionmovegreen(MotionEvent event) {

        if (tuch) {

            if ((x - event.getX()) > 10 ) {
                if (move) {
                    moveval = "Right";
                    anim = new TranslateAnimation(0, -500, 0, 0);
                    anim.setDuration(500);
                    move = false;
                }

            } else if ((x - event.getX()) < -10) {
                if (move) {
                    moveval = "Left";
                    anim = new TranslateAnimation(0, 500, 0, 0);
                    anim.setDuration(500);
//                    anim.setFillAfter(true);
                    move = false;
                }
            } else if ((y - event.getY()) > 10) {
                if (move) {
                    moveval = "Top";
                    anim = new TranslateAnimation(0, 0, 0, -500);
                    anim.setDuration(500);
//                    anim.setFillAfter(true);
                    move = false;
                }

            } else if ((y - event.getY()) < -10) {
                if (move) {
                    moveval = "Down";
                    anim = new TranslateAnimation(0, 0, 0, 500);
                    anim.setDuration(500);
//                    anim.setFillAfter(true);
                    move = false;
                }
            }
            balllayout.startAnimation(anim);
            anim.setAnimationListener(animlistner);
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

    private void actionmovered(MotionEvent event) {

        if (tuch) {

            if ((x - event.getX()) > 10) {
                if (move) {
                    moveval = "Right";
                    anim = new TranslateAnimation(0, 500, 0, 0);
                    move = false;
                }

            } else if ((x - event.getX()) < -10) {
                if (move) {
                    moveval = "Left";
                    anim = new TranslateAnimation(0, -500, 0, 0);
                    move = false;
                }
            } else if ((y - event.getY()) > 10) {
                if (move) {
                    moveval = "Top";
                    anim = new TranslateAnimation(0, 0, 0, 500);
                    move = false;
                }

            } else if ((y - event.getY()) < -10) {
                if (move) {
                    moveval = "Down";
                    anim = new TranslateAnimation(0, 0, 0, -500);
                    move = false;
                }
            }
            anim.setDuration(500);
            balllayout.startAnimation(anim);
            anim.setAnimationListener(animlistner);
        }
    }

    private void checkAnswer() {
        if (color.equals("red")) {
            if (side == 0 && moveval.equals("Left")) {
                rightanswer();
            } else if (side == 1 && moveval.equals("Down")) {
                rightanswer();
            } else if (side == 2 && moveval.equals("Right")) {
                rightanswer();
            } else if (side == 3 && moveval.equals("Top")) {
                rightanswer();
            } else {
                wronganswer();
            }

        } else if (color.equals("green")) {
            if (side == 0 && moveval.equals("Right")) {
                rightanswer();
            } else if (side == 1 && moveval.equals("Top")) {
                rightanswer();
            } else if (side == 2 && moveval.equals("Left")) {
                rightanswer();
            } else if (side == 3 && moveval.equals("Down")) {
                rightanswer();
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
                        refrence.setReversal(refrence.getReversal()+100);
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
        if (wronganswerval.getVisibility() == View.GONE) {
            wronganswerval.setVisibility(View.VISIBLE);
            wrongans.setVisibility(View.VISIBLE);
            ScaleAnimation scal = new ScaleAnimation(1f, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF,
                    (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
            scal.setDuration(500);

            AlphaAnimation animation1 = new AlphaAnimation(1f, 0f);
            animation1.setDuration(500);
            wrongans.startAnimation(animation1);
            AnimationSet anim = new AnimationSet(false);
            anim.addAnimation(scal);
            anim.addAnimation(animation1);
            wronganswerval.startAnimation(anim);
            scal.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    wronganswerval.setVisibility(View.GONE);
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
        refrence.setGame("Reversal");
        refrence.setScore(refrence.getReversal());
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
