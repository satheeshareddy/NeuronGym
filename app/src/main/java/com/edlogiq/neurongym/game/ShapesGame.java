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

public class ShapesGame extends Activity implements View.OnClickListener {

    public int StartTime = 45;
    int fristval = 0,prog=0;
    Timer timer;
    TimerTask mTimerTask;
    Handler handler = null;
    private LinearLayout layout1, layout2, layout3, layout4, layout5, layout6, layout7, layout8, layout9;
    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    private ArrayList<LinearLayout> color = new ArrayList<>();
    private ArrayList<ImageView> image = new ArrayList<>();
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
    private ArrayList<Integer> randomimage = new ArrayList<>();
    private TextView timertext,scoretext;
    private ImageView rightans, wrongans;
    private LinearLayout rightanswer, wronganswerval;
    private ImageView fristimage, secondimage;
    private boolean clickimage1 = false, clickimage2 = false;
    private RelativeLayout pausepopup;
    private RefrenceWrapper refrence;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes_game);

        ((RelativeLayout)findViewById(R.id.shapesgame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.shapesgame)).setBackgroundResource(R.color.black);
        }

        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        refrence=RefrenceWrapper.getRefrenceWrapper(this);
        refrence.setShapes(0);
        timertext = (TextView) findViewById(R.id.timertextview);
        handler = new Handler();
        timer = new Timer();
        scoretext = (TextView) findViewById(R.id.scoretextview);
        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswer = (LinearLayout) findViewById(R.id.righttick);
        wronganswerval = (LinearLayout) findViewById(R.id.wrongcross);


        init();
        getrandomimage();
        addimagecolor();
        addimage();
        startTimer();
        popuplayout();

    }

    private void init() {
        layout1 = (LinearLayout) findViewById(R.id.gamelayout1);
        layout2 = (LinearLayout) findViewById(R.id.gamelayout2);
        layout3 = (LinearLayout) findViewById(R.id.gamelayout3);
        layout4 = (LinearLayout) findViewById(R.id.gamelayout4);
        layout5 = (LinearLayout) findViewById(R.id.gamelayout5);
        layout6 = (LinearLayout) findViewById(R.id.gamelayout6);
        layout7 = (LinearLayout) findViewById(R.id.gamelayout7);
        layout8 = (LinearLayout) findViewById(R.id.gamelayout8);
        layout9 = (LinearLayout) findViewById(R.id.gamelayout9);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);
        layout5.setOnClickListener(this);
        layout6.setOnClickListener(this);
        layout7.setOnClickListener(this);
        layout8.setOnClickListener(this);
        layout9.setOnClickListener(this);


        image1 = (ImageView) findViewById(R.id.imageview1);
        image2 = (ImageView) findViewById(R.id.imageview2);
        image3 = (ImageView) findViewById(R.id.imageview3);
        image4 = (ImageView) findViewById(R.id.imageview4);
        image5 = (ImageView) findViewById(R.id.imageview5);
        image6 = (ImageView) findViewById(R.id.imageview6);
        image7 = (ImageView) findViewById(R.id.imageview7);
        image8 = (ImageView) findViewById(R.id.imageview8);
        image9 = (ImageView) findViewById(R.id.imageview9);
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
        while (randomimage.size() < 8) {
            int random = (new Random().nextInt(gameImage.length));
            if (!randomimage.contains(gameImage[random])) {
                randomimage.add(gameImage[random]);
            }
        }
    }

    private void addimage() {
        image.clear();
        image.add(image1);
        image.add(image2);
        image.add(image3);
        image.add(image4);
        image.add(image5);
        image.add(image6);
        image.add(image7);
        image.add(image8);
        image.add(image9);
        addrandomimage();
    }

    private void addrandomimage() {
        scoretext.setText(""+refrence.getShapes());
        for (int i = 0; i < 9; i++) {
            int val = (new Random().nextInt(image.size()));
            if (i == 0) {
                image.get(val).setBackgroundResource(randomimage.get(i));
                image.remove(val);
            } else if (i == 1) {
                image.get(val).setBackgroundResource(randomimage.get(i));
                image.remove(val);
            } else if (i == 2) {
                image.get(val).setBackgroundResource(randomimage.get(i));
                image.remove(val);
            } else if (i == 3) {
                image.get(val).setBackgroundResource(randomimage.get(i));
                image.remove(val);
            } else if (i == 4) {
                image.get(val).setBackgroundResource(randomimage.get(i));
                image.remove(val);
            } else if (i == 5) {
                image.get(val).setBackgroundResource(randomimage.get(i));
                image.remove(val);
            } else if (i == 6) {
                image.get(val).setBackgroundResource(randomimage.get(i));
                image.remove(val);
            } else if (i == 7) {
                image.get(val).setBackgroundResource(randomimage.get(i));
                fristimage = image.get(val);
                image.remove(val);
            } else if (i == 8) {
                image.get(val).setBackgroundResource(randomimage.get(7));
                secondimage = image.get(val);
                image.remove(val);
            }
        }
    }

    private void addimagecolor() {
        color.clear();
        color.add(layout1);
        color.add(layout2);
        color.add(layout3);
        color.add(layout4);
        color.add(layout5);
        color.add(layout6);
        color.add(layout7);
        color.add(layout8);
        color.add(layout9);
        setcolorlayout();
    }

    private void setcolorlayout() {
        for (int i = 1; i <= 9; i++) {
            if (i == 1) {
                int val = (new Random().nextInt(color.size()));
                color.get(val).setBackgroundResource(R.color.black);
                color.remove(val);
            } else if (i == 2) {
                int val = (new Random().nextInt(color.size()));
                color.get(val).setBackgroundResource(R.color.orange);
                color.remove(val);
            } else if (i == 3) {
                int val = (new Random().nextInt(color.size()));
                color.get(val).setBackgroundResource(R.color.gray);
                color.remove(val);
            } else if (i == 4) {
                int val = (new Random().nextInt(color.size()));
                color.get(val).setBackgroundResource(R.color.green);
                color.remove(val);
            } else if (i == 5) {
                int val = (new Random().nextInt(color.size()));
                color.get(val).setBackgroundResource(R.color.red);
                color.remove(val);
            } else if (i == 6) {
                int val = (new Random().nextInt(color.size()));
                color.get(val).setBackgroundResource(R.color.violet);
                color.remove(val);
            } else if (i == 7) {
                int val = (new Random().nextInt(color.size()));
                color.get(val).setBackgroundResource(R.color.blue);
                color.remove(val);
            } else if (i == 8) {
                int val = (new Random().nextInt(color.size()));
                color.get(val).setBackgroundResource(R.color.brown);
                color.remove(val);
            } else if (i == 9) {
                int val = (new Random().nextInt(color.size()));
                color.get(val).setBackgroundResource(R.color.pink);
                color.remove(val);
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.gamelayout1) {
            if (fristimage.equals(image1) || secondimage.equals(image1)) {
                layout1.setBackgroundResource(R.color.cyan);
                checkanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.gamelayout2) {
            if (fristimage.equals(image2) || secondimage.equals(image2)) {
                layout2.setBackgroundResource(R.color.cyan);
                checkanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.gamelayout3) {
            if (fristimage.equals(image3) || secondimage.equals(image3)) {
                layout3.setBackgroundResource(R.color.cyan);
                checkanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.gamelayout4) {
            if (fristimage.equals(image4) || secondimage.equals(image4)) {
                layout4.setBackgroundResource(R.color.cyan);
                checkanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.gamelayout5) {
            if (fristimage.equals(image5) || secondimage.equals(image5)) {
                layout5.setBackgroundResource(R.color.cyan);
                checkanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.gamelayout6) {
            if (fristimage.equals(image6) || secondimage.equals(image6)) {
                layout6.setBackgroundResource(R.color.cyan);
                checkanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.gamelayout7) {
            if (fristimage.equals(image7) || secondimage.equals(image7)) {
                layout7.setBackgroundResource(R.color.cyan);
                checkanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.gamelayout8) {
            if (fristimage.equals(image8) || secondimage.equals(image8)) {
                layout8.setBackgroundResource(R.color.cyan);
                checkanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.gamelayout9) {
            if (fristimage.equals(image9) || secondimage.equals(image9)) {
                layout9.setBackgroundResource(R.color.cyan);
                checkanswer();
            } else {
                wronganswer();
            }
        }
    }

    private void checkanswer() {
        if (fristval == 0) {
            clickimage1 = true;
            rightanswer();
            fristval = 1;
        } else if (fristval == 1) {
            clickimage2 = true;
            rightanswer();
            fristval = 0;
        }
    }

    private void rightanswer() {
        if (clickimage1 && clickimage2) {
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
                        refrence.setShapes(refrence.getShapes() + 100);
                        process();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rightanswer.setVisibility(View.GONE);
                        rightans.setVisibility(View.GONE);
                        clickimage1 = false;
                        clickimage2 = false;
                        rightval=rightval+1;
                        getrandomimage();
                        addimagecolor();
                        addimage();
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
                    clickimage1 = false;
                    clickimage2 = false;
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
                    addimagecolor();
                    addimage();
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
        refrence.setGame("Shapes");
        refrence.setScore(refrence.getShapes());
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
