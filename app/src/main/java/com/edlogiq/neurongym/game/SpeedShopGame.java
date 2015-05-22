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
import android.view.animation.RotateAnimation;
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

public class SpeedShopGame extends Activity {


    public int StartTime = 45;
    int ab = 0,prog=0;
    Timer timer;
    TimerTask mTimerTask;
    Handler handler = null;
    private TextView timertext,scoretext;
    private LinearLayout wheellayout, rightanswer, wronganswer;
    private ImageView image, rightans, wrongans;
    private float x, y;
    private boolean tuch = false, move = false;
    private TranslateAnimation anim;
    private int angle[] = {90, 180, 270}, wheelvalues = 0;
    private ArrayList<Integer> foodicon = new ArrayList<>();
    private ArrayList<Integer> transporticon = new ArrayList<>();
    private ArrayList<Integer> buildingicon = new ArrayList<>();
    private ArrayList<Integer> sportsicon = new ArrayList<>();
    private int imagevalue, moveval, previceangle = 0;
    private int transport = 0, building = 1, sports = 2, food = 3;
    private RelativeLayout pausepopup;
    private RefrenceWrapper refrence;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_shop_game);

        ((RelativeLayout)findViewById(R.id.speedshopgame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.speedshopgame)).setBackgroundResource(R.color.black);
        }

        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        refrence = RefrenceWrapper.getRefrenceWrapper(this);
        refrence.setSpeedshop(0);
        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswer = (LinearLayout) findViewById(R.id.righttick);
        wronganswer = (LinearLayout) findViewById(R.id.wrongcross);
        wheellayout = (LinearLayout) findViewById(R.id.wheel);
        image = (ImageView) findViewById(R.id.imageview);
        handler = new Handler();
        timertext=(TextView)findViewById(R.id.timertextview);
        scoretext = (TextView) findViewById(R.id.scoretextview);


        timer = new Timer();
        startTimer();
        addfoodicone();
        addtransporticone();
        addbuildingicone();
        addsportsicone();
        rotation();
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


    private void addsportsicone() {
        sportsicon.clear();
        sportsicon.add(R.drawable.sport1);
        sportsicon.add(R.drawable.sport2);
        sportsicon.add(R.drawable.sport3);
        sportsicon.add(R.drawable.sport4);
        sportsicon.add(R.drawable.sport5);
        sportsicon.add(R.drawable.sport6);
        sportsicon.add(R.drawable.sport7);
        sportsicon.add(R.drawable.sport8);
        sportsicon.add(R.drawable.sport9);
        sportsicon.add(R.drawable.sport10);
        sportsicon.add(R.drawable.sport11);
        sportsicon.add(R.drawable.sport12);
        sportsicon.add(R.drawable.sport13);
        sportsicon.add(R.drawable.sport14);
        sportsicon.add(R.drawable.sport15);
        sportsicon.add(R.drawable.sport16);
        sportsicon.add(R.drawable.sport17);
        sportsicon.add(R.drawable.sport18);
        sportsicon.add(R.drawable.sport19);
        sportsicon.add(R.drawable.sport20);
        sportsicon.add(R.drawable.sport21);
        sportsicon.add(R.drawable.sport22);
    }

    private void addbuildingicone() {
        buildingicon.clear();
        buildingicon.add(R.drawable.building1);
        buildingicon.add(R.drawable.building2);
        buildingicon.add(R.drawable.building3);
        buildingicon.add(R.drawable.building4);
        buildingicon.add(R.drawable.building5);
        buildingicon.add(R.drawable.building6);
        buildingicon.add(R.drawable.building7);
        buildingicon.add(R.drawable.building8);
        buildingicon.add(R.drawable.building9);
        buildingicon.add(R.drawable.building10);
        buildingicon.add(R.drawable.building11);
        buildingicon.add(R.drawable.building12);
        buildingicon.add(R.drawable.building13);
        buildingicon.add(R.drawable.building14);
        buildingicon.add(R.drawable.building15);
        buildingicon.add(R.drawable.building16);
        buildingicon.add(R.drawable.building17);
        buildingicon.add(R.drawable.building18);
        buildingicon.add(R.drawable.building19);
        buildingicon.add(R.drawable.building20);
        buildingicon.add(R.drawable.building21);

    }

    private void addtransporticone() {
        transporticon.clear();
        transporticon.add(R.drawable.transport1);
        transporticon.add(R.drawable.transport2);
        transporticon.add(R.drawable.transport3);
        transporticon.add(R.drawable.transport4);
        transporticon.add(R.drawable.transport5);
        transporticon.add(R.drawable.transport6);
        transporticon.add(R.drawable.transport7);
        transporticon.add(R.drawable.transport8);
        transporticon.add(R.drawable.transport9);
        transporticon.add(R.drawable.transport10);
        transporticon.add(R.drawable.transport11);
        transporticon.add(R.drawable.transport12);
        transporticon.add(R.drawable.transport13);
        transporticon.add(R.drawable.transport14);
        transporticon.add(R.drawable.transport15);
        transporticon.add(R.drawable.transport16);
        transporticon.add(R.drawable.transport17);
        transporticon.add(R.drawable.transport18);
        transporticon.add(R.drawable.transport19);
        transporticon.add(R.drawable.transport20);
        transporticon.add(R.drawable.transport21);
    }

    private void addfoodicone() {
        foodicon.add(R.drawable.food1);
        foodicon.add(R.drawable.food2);
        foodicon.add(R.drawable.food3);
        foodicon.add(R.drawable.food4);
        foodicon.add(R.drawable.food5);
        foodicon.add(R.drawable.food6);
        foodicon.add(R.drawable.food7);
        foodicon.add(R.drawable.food8);
        foodicon.add(R.drawable.food9);
        foodicon.add(R.drawable.food10);
        foodicon.add(R.drawable.food11);
        foodicon.add(R.drawable.food12);
        foodicon.add(R.drawable.food13);
        foodicon.add(R.drawable.food14);
        foodicon.add(R.drawable.food15);
        foodicon.add(R.drawable.food16);
        foodicon.add(R.drawable.food17);
        foodicon.add(R.drawable.food18);
        foodicon.add(R.drawable.food19);
        foodicon.add(R.drawable.food20);
        foodicon.add(R.drawable.food21);
        foodicon.add(R.drawable.food22);
    }

    private void rotation() {
        scoretext.setText(""+refrence.getSpeedshop());
        wheelvalues = (new Random().nextInt(3));
        RotateAnimation rotate = new RotateAnimation(previceangle, ((angle[wheelvalues]) + previceangle),
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);


        previceangle = ((angle[wheelvalues]) + previceangle);
        if (previceangle >= 360) {
            previceangle = previceangle - 360;
        }
        rotate.setDuration(1000);
        rotate.setFillAfter(true);
        wheellayout.startAnimation(rotate);
        if (wheelvalues == 0) {
            transport = (transport + 1) % 4;
            building = (building + 1) % 4;
            sports = (sports + 1) % 4;
            food = (food + 1) % 4;
        } else if (wheelvalues == 1) {
            transport = (transport + 2) % 4;
            building = (building + 2) % 4;
            sports = (sports + 2) % 4;
            food = (food + 2) % 4;
        } else if (wheelvalues == 2) {
            transport = (transport + 3) % 4;
            building = (building + 3) % 4;
            sports = (sports + 3) % 4;
            food = (food + 3) % 4;
        }

        Log.e("Value", wheelvalues + "  " + transport + "  " + building + "  " + sports + "  " + food);
        imagevalue = (new Random().nextInt(4));

        if (imagevalue == 0) {
            int val = (new Random().nextInt(sportsicon.size()));
            image.setBackgroundResource(sportsicon.get(val));
        } else if (imagevalue == 1) {
            int val = (new Random().nextInt(buildingicon.size()));
            image.setBackgroundResource(buildingicon.get(val));
        } else if (imagevalue == 2) {
            int val = (new Random().nextInt(transporticon.size()));
            image.setBackgroundResource(transporticon.get(val));
        } else if (imagevalue == 3) {
            int val = (new Random().nextInt(foodicon.size()));
            image.setBackgroundResource(foodicon.get(val));
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actindown(event);
                break;

            case MotionEvent.ACTION_MOVE:
                actionmove(event);
                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return super.onTouchEvent(event);
    }

    private void actindown(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        tuch = true;
        move = true;
    }

    private void actionmove(MotionEvent event) {

        if (tuch) {

            if ((x - event.getX()) > 5) {
                if (move) {
                    moveval = 0;
                    anim = new TranslateAnimation(0, -500, 0, 0);
                    anim.setDuration(500);
                    move = false;
                }

            } else if ((x - event.getX()) < -5) {
                if (move) {
                    moveval = 2;
                    anim = new TranslateAnimation(0, 500, 0, 0);
                    anim.setDuration(500);
//                    anim.setFillAfter(true);
                    move = false;
                }
            } else if ((y - event.getY()) > 5) {
                if (move) {
                    moveval = 1;
                    anim = new TranslateAnimation(0, 0, 0, -500);
                    anim.setDuration(500);
//                    anim.setFillAfter(true);
                    move = false;
                }

            } else if ((y - event.getY()) < -5) {
                if (move) {
                    moveval = 3;
                    anim = new TranslateAnimation(0, 0, 0, 500);
                    anim.setDuration(500);
//                    anim.setFillAfter(true);
                    move = false;
                }
            }
            image.startAnimation(anim);
            anim.setAnimationListener(animlistner);
        }
    }


    Animation.AnimationListener animlistner = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            checkval();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private void checkval() {
        if (imagevalue == 0) {
            if (sports == moveval) {
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (imagevalue == 1) {
            if (building == moveval) {
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (imagevalue == 2) {
            if (transport == moveval) {
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (imagevalue == 3) {
            if (food == moveval) {
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
                        refrence.setSpeedshop(refrence.getSpeedshop()+100);
                        process();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rightanswer.setVisibility(View.GONE);
                        rightans.setVisibility(View.GONE);
                        rightval=rightval+1;
                        rotation();
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
                    rotation();
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
        refrence.setGame("SpeedShop");
        refrence.setScore(refrence.getSpeedshop());
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
