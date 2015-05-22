package com.edlogiq.neurongym.game;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
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

;

public class DancingBallGame extends Activity implements View.OnClickListener {


    public int StartTime = 45;
    int ab = 0;
    Timer timer;
    TimerTask mTimerTask;
    Handler handler = null;

    public static RefrenceWrapper refrence;
    CanvasDancingball canvasDancingball;
    int imag1, imag2, imag3, shape;
    private ImageView image1, image2, image3;
    private ArrayList<Integer> image = new ArrayList<Integer>();
    private ArrayList<Integer> setshape = new ArrayList<Integer>();
    private TextView timertext,score;
    private RelativeLayout pausepopup;
    private LinearLayout rightanswer, wronganswer;
    private ImageView rightans, wrongans;
    private ProgressBar progress;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dancing_ball_game);

        ((RelativeLayout)findViewById(R.id.dancingballgame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.dancingballgame)).setBackgroundResource(R.color.black);
        }

        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        refrence = RefrenceWrapper.getRefrenceWrapper(this);
        refrence.setDancingball(0);
        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswer = (LinearLayout) findViewById(R.id.righttick);
        wronganswer = (LinearLayout) findViewById(R.id.wrongcross);
        score = (TextView) findViewById(R.id.scoretextview);
        handler = new Handler();
        timertext=(TextView)findViewById(R.id.timertextview);
        layout = ((LinearLayout) findViewById(R.id.canaslayout));
        image1 = (ImageView) findViewById(R.id.imageviewshape1);
        image2 = (ImageView) findViewById(R.id.imageviewshape2);
        image3 = (ImageView) findViewById(R.id.imageviewshape3);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        getImage();

        canvasDancingball = new CanvasDancingball(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(540, 540);

        layout.addView(canvasDancingball, params);
//        layout.setBackgroundColor(Color.WHITE);

        timer = new Timer();
        startTimer();
        setshapeincanvas();
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

    private void setshapeincanvas() {
        score.setText("" + refrence.getDancingball());
        shape = (new Random().nextInt(setshape.size()));
        if (shape == 0) {
            canvasDancingball.setshape(setshape.get(shape));
        } else if (shape == 1) {
            canvasDancingball.setshape(setshape.get(shape));
        } else if (shape == 2) {
            canvasDancingball.setshape(setshape.get(shape));
        }
    }

    private void getImage() {
        image.clear();
        image.add(R.drawable.shape1);
        image.add(R.drawable.shape2);
        image.add(R.drawable.shape3);
//        image.add(R.drawable.shape4);
        image.add(R.drawable.shape5);
        image.add(R.drawable.shape6);
        image.add(R.drawable.shape7);
        image.add(R.drawable.shape8);
        image.add(R.drawable.shape9);
        image.add(R.drawable.shape10);
        image.add(R.drawable.shape11);
        image.add(R.drawable.shape12);
        image.add(R.drawable.shape13);
        image.add(R.drawable.shape14);
        setImage();
    }

    private void setImage() {
        setshape.clear();

        imag1 = (new Random().nextInt(image.size()));
        image1.setBackgroundResource(image.get(imag1));
        setshape.add(imag1 + 1);
        image.remove(imag1);

        imag2 = (new Random().nextInt(image.size()));
        image2.setBackgroundResource(image.get(imag2));
        if (imag2 >= imag1) {
            setshape.add(imag2 + 2);
        } else {
            setshape.add(imag2 + 1);
        }
        image.remove(imag2);


        imag3 = (new Random().nextInt(image.size()));
        image3.setBackgroundResource(image.get(imag3));
        if (imag1 >= imag2) {
            if (imag3 >= imag1 || (imag3 == imag2 && imag3 == imag1)) {
                setshape.add(imag3 + 3);
            } else if (imag3 >= imag2 && imag3 < imag1) {
                setshape.add(imag3 + 2);
            } else {
                setshape.add(imag3 + 1);
            }
        } else {
            if (imag3 > imag2) {
                setshape.add(imag3 + 3);
            } else if (imag3 > imag1) {
                setshape.add(imag3 + 2);
            } else {
                setshape.add(imag3 + 1);
            }
        }
        image.remove(imag3);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageviewshape1) {
            if (setshape.get(shape) == (imag1 + 1)) {
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.imageviewshape2) {
            if ((setshape.get(shape) == (imag2 + 1)) || (setshape.get(shape) == (imag2 + 2))) {
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.imageviewshape3) {
            if (setshape.get(shape) == (imag3 + 1) ||
                    (setshape.get(shape) == (imag3 + 2)||(setshape.get(shape) == (imag3 + 3)))) {
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
                        refrence.setDancingball(refrence.getDancingball() + (100*multiplierval));
                       process();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rightanswer.setVisibility(View.GONE);
                        rightans.setVisibility(View.GONE);
                        rightval=rightval+1;
                        getImage();
                        setshapeincanvas();
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
                    getImage();
                    setshapeincanvas();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    int multyplyer=0,prog=0,multiplierval=0;
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
        refrence.setGame("DancingBall");
        refrence.setScore(refrence.getDancingball());
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
