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
import com.edlogiq.neurongym.howtoplay.HowToPlayDualFocuse;
import com.edlogiq.neurongym.neurongym.GameOver;
import com.edlogiq.neurongym.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AfterMatchGame extends Activity implements View.OnClickListener{

    public int StartTime = 45;
    int ab = 0,levelvalue=0,fristvalue,secondvalue,answer,tap;
    private String opraterval;
    Timer timer;
    TimerTask mTimerTask;
    Handler handler = null;
    private TextView timertext,equationtext,answertextview,scoretext;
    private RelativeLayout pausepopup;
    private ArrayList<String> oprater=new ArrayList<>();
    private ArrayList<String> oprater2=new ArrayList<>();
    private List<Integer> numberList = new ArrayList<>();
    private ImageView rightans,wrongans;
    private LinearLayout  rightanswer, wronganswer;
    private RefrenceWrapper refrence;
    private ProgressBar progress;
    private int rightval=0,wrongval=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_match_game);

        ((RelativeLayout)findViewById(R.id.aftermatchgame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.aftermatchgame)).setBackgroundResource(R.color.black);
        }

        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        refrence = RefrenceWrapper.getRefrenceWrapper(this);
        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswer = (LinearLayout) findViewById(R.id.righttick);
        wronganswer = (LinearLayout) findViewById(R.id.wrongcross);
        scoretext = (TextView) findViewById(R.id.scoretextview);
        handler = new Handler();
        timertext=(TextView)findViewById(R.id.timertextview);
        equationtext=(TextView)findViewById(R.id.gametextview);
        ((ImageView)findViewById(R.id.rightimageview)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.wrongimageview)).setOnClickListener(this);
        refrence.setAftermatch(0);
         timer = new Timer();
        startTimer();
        addoprater();
        popuplayout();
        gameStart();
    }



    private void addoprater() {
        oprater.clear();
        oprater2.clear();
        oprater.add("+");
        oprater.add("-");
        oprater.add("*");
        oprater.add("/");

        oprater2.add(">");
        oprater2.add("<");
        oprater2.add("=");

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

    private void gameStart() {
        scoretext.setText(""+refrence.getAftermatch());
        level();
        operater();


        if(opraterval.equals("+")){
            answer=fristvalue+secondvalue;
            complitequition("+");
        }else if(opraterval.equals("-")){
            answer=fristvalue-secondvalue;
            complitequition("-");
        }else if(opraterval.equals("*")){
            answer=fristvalue*secondvalue;
            complitequition("*");
        }else if(opraterval.equals("/")){
            fristvalue=fristvalue*secondvalue;
            answer=fristvalue/secondvalue;
            complitequition("/");
        }

    }

    private void complitequition(String val){

        int opra=(new Random().nextInt(oprater2.size()));
        tap=(new Random().nextInt(2));

        if((oprater2.get(opra).equals(">"))){
            if(tap==0){
                equationtext.setText(fristvalue+val+secondvalue+">"+(answer-2));
            }else if(tap==1){
                equationtext.setText(fristvalue+val+secondvalue+">"+(answer+2));
            }
        }else if((oprater2.get(opra).equals("<"))){
            if(tap==0){
                equationtext.setText(fristvalue+val+secondvalue+"<"+(answer+2));
            }else if(tap==1){
                equationtext.setText(fristvalue+val+secondvalue+"<"+(answer-2));
            }
        }else if((oprater2.get(opra).equals("="))){
            if(tap==0){
                equationtext.setText(fristvalue+val+secondvalue+"="+(answer));
            }else if(tap==1){
                equationtext.setText(fristvalue+val+secondvalue+"="+(answer+2));
            }
        }
    }



    private void operater() {
        if(oprater.size()==0) {
            addoprater();
        }
        int ope = (new Random().nextInt(oprater.size()));
        opraterval = oprater.get(ope);
        oprater.remove(ope);
    }

    private void level() {
        if(multiplierval==1){
            numberList.clear();
            for(int i=1; i<=9; i++){
                numberList.add(i);
            }
        }else if(multiplierval==2){
            numberList.clear();
            for(int i=10; i<=19; i++){
                numberList.add(i);
            }
        }else if(multiplierval==3){
            numberList.clear();
            for(int i=10; i<=29; i++){
                numberList.add(i);
            }
        }else if(multiplierval==4){
            numberList.clear();
            for(int i=10; i<=49; i++){
                numberList.add(i);
            }
        }else if(multiplierval==5){
            numberList.clear();
            for(int i=50; i<=99; i++){
                numberList.add(i);
            }
        }else if(multiplierval>=6){
            numberList.clear();
            for(int i=99; i<=999; i++){
                numberList.add(i);
            }
        }
        int val1=(new Random().nextInt(numberList.size()));
        fristvalue=numberList.get(val1);

        int val2=(new Random().nextInt(numberList.size()));
        secondvalue=numberList.get(val2);
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


    @Override
    public void onClick(View v) {
       if(v.getId()==R.id.wrongimageview){
           if(tap==1){
               Log.e("riteAnswer","riteAnswer");
               rightanswer();

           }else{
               wronganswer();
           }
       }else if(v.getId()==R.id.rightimageview){
           if(tap==0){
               rightanswer();
           }else{
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
                        process();
                        refrence.setAftermatch(refrence.getAftermatch() +(100*multiplierval));
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rightanswer.setVisibility(View.GONE);
                        rightans.setVisibility(View.GONE);
                        rightval=rightval+1;
                        gameStart();;
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
                    gameStart();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    int multyplyer=0,prog=0,multiplierval=1;
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
        refrence.setGame("AfterMatch");
        refrence.setScore(refrence.getAftermatch());
        refrence.setMultiplier(multiplierval);
        int total=wrongval+rightval;
        refrence.setAccuracy((rightval/total)*100);
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
