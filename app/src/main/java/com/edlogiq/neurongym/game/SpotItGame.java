package com.edlogiq.neurongym.game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

public class SpotItGame extends Activity implements View.OnClickListener {

    public int StartTime = 45;
    int ab = 0,prog=0;
    Timer timer;
    TimerTask mTimerTask;
    Handler handler = null;
    private TextView timertext,scoretext;
    private ImageView imageleft, imageright, rightans, wrongans;
    private ArrayList<Drawable> image = new ArrayList();
    private int imagepos,imageval;
    private  Drawable mDrawable1;
    private ArrayList<Integer> randomimage=new ArrayList<>();
    private RelativeLayout pausepopup;
    private RefrenceWrapper refrence;
    private ProgressBar progress;
    private LinearLayout imagelayoute1, imagelayoute2, imagelayoute3, rightanswer, wronganswer;
    private Integer gameImage[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,
            R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8, R.drawable.image9,
            R.drawable.image10, R.drawable.image11, R.drawable.image12, R.drawable.image13, R.drawable.image14,
            R.drawable.image15, R.drawable.image16, R.drawable.image17, R.drawable.image18, R.drawable.image19,
            R.drawable.image20, R.drawable.image21, R.drawable.image22, R.drawable.image23, R.drawable.image24,
            R.drawable.image25, R.drawable.image26, R.drawable.image27, R.drawable.image28, R.drawable.image29,
            R.drawable.image30, R.drawable.image31, R.drawable.image32, R.drawable.image33, R.drawable.image34,
            R.drawable.image35, R.drawable.image36, R.drawable.image37, R.drawable.image38, R.drawable.image39,
            R.drawable.image40, R.drawable.image41, R.drawable.image42, R.drawable.image43, R.drawable.image44,
            R.drawable.image45, R.drawable.image46, R.drawable.image47, R.drawable.image48, R.drawable.image49,
            R.drawable.image50, R.drawable.image51, R.drawable.image52, R.drawable.image53, R.drawable.image54,
            R.drawable.image55, R.drawable.image56, R.drawable.image57, R.drawable.image58, R.drawable.image59,
            R.drawable.image60, R.drawable.image61, R.drawable.image62, R.drawable.image63, R.drawable.image64,
            R.drawable.image65, R.drawable.image66, R.drawable.image67, R.drawable.image68, R.drawable.image69,
            R.drawable.image70, R.drawable.image71, R.drawable.image72, R.drawable.image73, R.drawable.image74,
            R.drawable.image75, R.drawable.image76, R.drawable.image77, R.drawable.image78, R.drawable.image79,
            R.drawable.image80, R.drawable.image81, R.drawable.image82, R.drawable.image83, R.drawable.image84,
            R.drawable.image85, R.drawable.image86, R.drawable.image87, R.drawable.image88, R.drawable.image89,
            R.drawable.image90, R.drawable.image91, R.drawable.image92, R.drawable.image93, R.drawable.image94,
            R.drawable.image95, R.drawable.image96, R.drawable.image97, R.drawable.image98, R.drawable.image99,
            R.drawable.image100};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_it_game);


        ((RelativeLayout)findViewById(R.id.spotitgame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.spotitgame)).setBackgroundResource(R.color.black);
        }

        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        refrence = RefrenceWrapper.getRefrenceWrapper(this);
        refrence.setSpotit(0);
        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswer = (LinearLayout) findViewById(R.id.righttick);
        wronganswer = (LinearLayout) findViewById(R.id.wrongcross);
        imageleft = (ImageView) findViewById(R.id.imagelayoutleft);
        imageright = (ImageView) findViewById(R.id.imagelayoutright);
        imagelayoute1 = (LinearLayout) findViewById(R.id.layoutimage1);
        imagelayoute2 = (LinearLayout) findViewById(R.id.layoutimage2);
        imagelayoute3 = (LinearLayout) findViewById(R.id.layoutimage3);
        imagelayoute1.setOnClickListener(this);
        imagelayoute2.setOnClickListener(this);
        imagelayoute3.setOnClickListener(this);
        handler = new Handler();
        timertext=(TextView)findViewById(R.id.timertextview);
        scoretext = (TextView) findViewById(R.id.scoretextview);
        timer = new Timer();
        startTimer();
        getrandomimage();
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



    private void getrandomimage() {
        randomimage.clear();
        while (randomimage.size() < 3) {
            int random = (new Random().nextInt(gameImage.length));
            if (!randomimage.contains(gameImage[random])) {
                randomimage.add(gameImage[random]);
            }
        }
        addimagevalue();
    }

    private void addimagevalue() {
        image.clear();
         imageval=(new Random().nextInt(randomimage.size()));
         mDrawable1 = this.getResources().getDrawable(randomimage.get(imageval));
        mDrawable1.setColorFilter(new PorterDuffColorFilter(Color.parseColor("#f44336"), PorterDuff.Mode.MULTIPLY));
        gamestart();

    }

    private void gamestart() {
        scoretext.setText(""+refrence.getSpotit());
        resetcolor();
        imagepos = (new Random().nextInt(3));
        int image = (new Random().nextInt(4));

        if (image == 0) {
            leftanimation();
        } else if (image == 1) {
            rightanimation();
        } else if (image == 2) {
            upanimation();
        } else if (image == 3) {
            downanimation();
        }
    }

    private void leftanimation() {
        imageleft.setImageDrawable(mDrawable1);
        Animation anim = new TranslateAnimation(-500, 5000, 0, 0);
        anim.setDuration(5000);
        anim.setFillAfter(true);
        imageleft.startAnimation(anim);
        anim.setAnimationListener(listner);
    }

    private void rightanimation() {
        imageright.setImageDrawable(mDrawable1);
        Animation anim = new TranslateAnimation(500, -5000, 0, 0);
        anim.setDuration(5000);
        anim.setFillAfter(true);
        imageright.startAnimation(anim);
        anim.setAnimationListener(listner);
    }

    private void upanimation() {
        imageleft.setImageDrawable(mDrawable1);
        Animation anim = new TranslateAnimation(0, 0, -500, 5000);
        anim.setDuration(5000);
        anim.setFillAfter(true);
        imageleft.startAnimation(anim);
        anim.setAnimationListener(listner);
    }

    private void downanimation() {
        imageright.setImageDrawable(mDrawable1);
        Animation anim = new TranslateAnimation(0, 0, 500, -5000);
        anim.setDuration(5000);
        anim.setFillAfter(true);
        imageright.startAnimation(anim);
        anim.setAnimationListener(listner);
    }


    Animation.AnimationListener listner=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            resetimagecolor();
        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };


    private void resetimagecolor() {

       Drawable drawable = this.getResources().getDrawable(randomimage.get(imageval));
        drawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.MULTIPLY));

        ((ImageView)findViewById(R.id.imageview1)).setBackgroundResource(randomimage.get(0));
        ((ImageView)findViewById(R.id.imageview2)).setBackgroundResource(randomimage.get(1));
        ((ImageView)findViewById(R.id.imageview3)).setBackgroundResource(randomimage.get(2));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.layoutimage1) {
            if (imageval == 0) {
                imagelayoute1.setBackgroundResource(R.drawable.cyan);
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.layoutimage2) {
            if (imageval == 1) {
                imagelayoute2.setBackgroundResource(R.drawable.cyan);
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.layoutimage3) {
            if (imageval == 2) {
                imagelayoute3.setBackgroundResource(R.drawable.cyan);
                rightanswer();
            } else {
                wronganswer();
            }
        }
    }

    private void resetcolor() {
        imagelayoute1.setBackgroundResource(R.drawable.grey);
        imagelayoute2.setBackgroundResource(R.drawable.grey);
        imagelayoute3.setBackgroundResource(R.drawable.grey);
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
                        refrence.setSpotit(refrence.getSpotit()+100);
                        process();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rightanswer.setVisibility(View.GONE);
                        rightans.setVisibility(View.GONE);
                        rightval=rightval+1;
                        getrandomimage();
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
                    getrandomimage();
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
        refrence.setGame("SpotIt");
        refrence.setScore(refrence.getSpotit());
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
