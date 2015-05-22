package com.edlogiq.neurongym.game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
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

public class MoneyGame extends Activity implements View.OnClickListener {

    public int StartTime = 45;
    int ab = 0;
    Timer timer;
    TimerTask mTimerTask;
    Handler handler = null;
    private TextView timertext,scoretext;

    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10,
            image11, image12, image13, image14, image15, image16, image17, image18, image19, image20;
    private LinearLayout greater, less, equall;
    private ArrayList<LinearLayout> layoutcolm1 = new ArrayList<>();
    private ArrayList<LinearLayout> layoutcolm2 = new ArrayList<>();
    private ArrayList<LinearLayout> layoutcolm3 = new ArrayList<>();
    private ArrayList<LinearLayout> layoutcolm4 = new ArrayList<>();

    private ArrayList<Integer> imageview1 = new ArrayList<>();
    private ArrayList<Integer> imageview2 = new ArrayList<>();


    private ArrayList<Integer> digite = new ArrayList<>();
    private int fristsideval = 0, secondsideval = 0;
    private RelativeLayout pausepopup;
    private LinearLayout rightanswer, wronganswer,colom1,colom2,colom3,colom4;
    private ImageView rightans, wrongans;
    private RefrenceWrapper refrence;
    private ProgressBar progress;
    private LinearLayout coloum1,coloum2,coloum3,coloum4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_game);

        ((RelativeLayout)findViewById(R.id.moneygame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.moneygame)).setBackgroundResource(R.color.black);
        }

        refrence = RefrenceWrapper.getRefrenceWrapper(this);
        refrence.setMoneygame(0);
        handler = new Handler();
        timertext=(TextView)findViewById(R.id.timertextview);
        greater = (LinearLayout) findViewById(R.id.linearlayout33);
        less = (LinearLayout) findViewById(R.id.linearlayout31);
        equall = (LinearLayout) findViewById(R.id.linearlayout32);
        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswer = (LinearLayout) findViewById(R.id.righttick);
        wronganswer = (LinearLayout) findViewById(R.id.wrongcross);
        scoretext = (TextView) findViewById(R.id.scoretextview);
        coloum1=(LinearLayout)findViewById(R.id.coloum1);
        coloum2=(LinearLayout)findViewById(R.id.coloum2);
        coloum3=(LinearLayout)findViewById(R.id.coloum3);
        coloum4=(LinearLayout)findViewById(R.id.coloum4);


        greater.setOnClickListener(this);
        less.setOnClickListener(this);
        equall.setOnClickListener(this);
        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        timer = new Timer();
        startTimer();
//        init();
//        addimage();
        adddigite();
        popuplayout();
        showvaluecolume1();
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

    private void addimage() {
        imageview1.clear();
        imageview2.clear();


        imageview1.add(0);
        imageview1.add(1);
        imageview1.add(2);
        imageview1.add(3);
        imageview1.add(4);

        imageview2.add(0);
        imageview2.add(1);
        imageview2.add(2);
        imageview2.add(3);
        imageview2.add(4);



    }

    private void adddigite() {
        digite.clear();
        digite.add(R.drawable.digit5);
        digite.add(R.drawable.digit10);
        digite.add(R.drawable.digit15);
        digite.add(R.drawable.digit20);
        digite.add(R.drawable.digit25);
    }


    private void coloumfrist() {
        layoutcolm1.clear();
        layoutcolm2.clear();
        layoutcolm3.clear();
        layoutcolm4.clear();
        showvaluecolume1();

    }


    private void showvaluecolume1() {
        level();
        adddigite();
        coloum1.removeAllViews();
        coloum2.removeAllViews();
        coloum3.removeAllViews();
        coloum4.removeAllViews();
        scoretext.setText(""+refrence.getMoneygame());
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = (displaymetrics.widthPixels)/7;
        int imagesize=width-30;
        Log.e("width","  "+width);

        for(int i=0;i<5;i++){
            colom1 = new LinearLayout(this);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(width, width);
            imageParams.setMargins(0,10,0,0);
            colom1.setGravity(Gravity.CENTER);
            colom1.setLayoutParams(imageParams);
            if(i==firstval || i==firstval1|| i==firstval2|| i==firstval3|| i==firstval4){
                colom1.setBackgroundResource(R.drawable.grey);
                ImageView img=new ImageView(this);
                LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(imagesize, imagesize);
                img.setLayoutParams(imgParams);
                int num=(new Random().nextInt(digite.size()));
                addvaluefristside(num);
                img.setBackgroundResource(digite.get(num));
                digite.remove(num);
                colom1.addView(img);
            }else{
                colom1.setBackgroundResource(0);
            }
            coloum1.addView(colom1);
        }

        adddigite();
        for(int i=0;i<5;i++){
            colom1 = new LinearLayout(this);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(width, width);
            colom1.setGravity(Gravity.CENTER);
            colom1.setLayoutParams(imageParams);
            if(i==firstval5 || i==firstval6|| i==firstval7|| i==firstval8|| i==firstval9){
                colom1.setBackgroundResource(R.drawable.grey);
                ImageView img=new ImageView(this);
                LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(imagesize, imagesize);

                img.setLayoutParams(imgParams);
                int num=(new Random().nextInt(digite.size()));
                addvaluefristside(num);
                img.setBackgroundResource(digite.get(num));
                digite.remove(num);
                colom1.addView(img);
            }else{
                colom1.setBackgroundResource(0);
            }
            coloum2.addView(colom1);
        }
        adddigite();
        for(int i=0;i<5;i++){
            colom1 = new LinearLayout(this);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(width, width);
            colom1.setGravity(Gravity.CENTER);
            colom1.setLayoutParams(imageParams);
            if(i==secondval || i==secondval1|| i==secondval2|| i==secondval3|| i==secondval4){
                colom1.setBackgroundResource(R.drawable.grey);
                ImageView img=new ImageView(this);
                LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(imagesize, imagesize);

                img.setLayoutParams(imgParams);
                int num=(new Random().nextInt(digite.size()));
                addvaluesecondside(num);
                img.setBackgroundResource(digite.get(num));
                digite.remove(num);
                colom1.addView(img);
            }else{
                colom1.setBackgroundResource(0);
            }
            coloum3.addView(colom1);
        }

        adddigite();
        for(int i=0;i<5;i++){
            colom1 = new LinearLayout(this);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(width, width);
            colom1.setGravity(Gravity.CENTER);
            colom1.setLayoutParams(imageParams);
            if(i==secondval5 || i==secondval6|| i==secondval7|| i==secondval8|| i==secondval9){
                colom1.setBackgroundResource(R.drawable.grey);
                ImageView img=new ImageView(this);
                LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(imagesize, imagesize);

                img.setLayoutParams(imgParams);
                int num=(new Random().nextInt(digite.size()));
                addvaluesecondside(num);
                img.setBackgroundResource(digite.get(num));
                digite.remove(num);
                colom1.addView(img);
            }else{
                colom1.setBackgroundResource(0);
            }
            coloum4.addView(colom1);
        }


    }

    int firstval=11,firstval1=11,firstval2=11,firstval3=11,firstval4=11,
            firstval5=11,firstval6=11,firstval7=11,firstval8=11,firstval9=11
            ,secondval=11,secondval1=11,secondval2=11,secondval3=11,secondval4=11,
            secondval5=11,secondval6=11,secondval7=11,secondval8=11,secondval9=11,
            levelval=1;

    private void level(){
        addimage();
        if(levelval==1){
            int val1 =(new Random().nextInt(imageview1.size()));
            firstval=imageview1.get(val1);
            imageview1.remove(val1);

            int val2 =(new Random().nextInt(imageview1.size()));
            firstval1=imageview1.get(val2);
            imageview1.remove(val2);

            addimage();

            int val3 =(new Random().nextInt(imageview2.size()));
            firstval5=imageview2.get(val3);
            imageview2.remove(val3);

            addimage();

            int val11 =(new Random().nextInt(imageview1.size()));
            secondval=imageview1.get(val11);
            imageview1.remove(val11);

            int val12 =(new Random().nextInt(imageview1.size()));
            secondval1=imageview1.get(val12);
            imageview1.remove(val12);

            addimage();

            int val13 =(new Random().nextInt(imageview2.size()));
            secondval5=imageview2.get(val13);
            imageview2.remove(val13);

            int val14 =(new Random().nextInt(imageview2.size()));
            secondval6=imageview2.get(val14);
            imageview2.remove(val14);
        }else  if(levelval==2){
            int val1 =(new Random().nextInt(imageview1.size()));
            firstval=imageview1.get(val1);
            imageview1.remove(val1);

            int val2 =(new Random().nextInt(imageview1.size()));
            firstval1=imageview1.get(val2);
            imageview1.remove(val2);

            addimage();

            int val3 =(new Random().nextInt(imageview2.size()));
            firstval5=imageview2.get(val3);
            imageview2.remove(val3);

            int val4 =(new Random().nextInt(imageview2.size()));
            firstval6=imageview2.get(val4);
            imageview2.remove(val4);

            addimage();

            int val11 =(new Random().nextInt(imageview1.size()));
            secondval=imageview1.get(val11);
            imageview1.remove(val11);

            int val12 =(new Random().nextInt(imageview1.size()));
            secondval1=imageview1.get(val12);
            imageview1.remove(val12);

            addimage();

            int val13 =(new Random().nextInt(imageview2.size()));
            secondval5=imageview2.get(val13);
            imageview2.remove(val13);
        }else  if(levelval==3){
            int val1 =(new Random().nextInt(imageview1.size()));
            firstval=imageview1.get(val1);
            imageview1.remove(val1);

            int val2 =(new Random().nextInt(imageview1.size()));
            firstval1=imageview1.get(val2);
            imageview1.remove(val2);

            addimage();

            int val3 =(new Random().nextInt(imageview2.size()));
            firstval5=imageview2.get(val3);
            imageview2.remove(val3);

            int val4 =(new Random().nextInt(imageview2.size()));
            firstval6=imageview2.get(val4);
            imageview2.remove(val4);

            addimage();

            int val11 =(new Random().nextInt(imageview1.size()));
            secondval=imageview1.get(val11);
            imageview1.remove(val11);

            int val12 =(new Random().nextInt(imageview1.size()));
            secondval1=imageview1.get(val12);
            imageview1.remove(val12);

            addimage();

            int val13 =(new Random().nextInt(imageview2.size()));
            secondval5=imageview2.get(val13);
            imageview2.remove(val13);

            int val14 =(new Random().nextInt(imageview2.size()));
            secondval6=imageview2.get(val14);
            imageview2.remove(val14);
        }else  if(levelval==4){
            int val1 =(new Random().nextInt(imageview1.size()));
            firstval=imageview1.get(val1);
            imageview1.remove(val1);

            int val2 =(new Random().nextInt(imageview1.size()));
            firstval1=imageview1.get(val2);
            imageview1.remove(val2);

            addimage();

            int val3 =(new Random().nextInt(imageview2.size()));
            firstval5=imageview2.get(val3);
            imageview2.remove(val3);

            int val4 =(new Random().nextInt(imageview2.size()));
            firstval6=imageview2.get(val4);
            imageview2.remove(val4);

            addimage();

            int val11 =(new Random().nextInt(imageview1.size()));
            secondval=imageview1.get(val11);
            imageview1.remove(val11);

            int val12 =(new Random().nextInt(imageview1.size()));
            secondval1=imageview1.get(val12);
            imageview1.remove(val12);

            addimage();

            int val13 =(new Random().nextInt(imageview2.size()));
            secondval5=imageview2.get(val13);
            imageview2.remove(val13);

            int val14 =(new Random().nextInt(imageview2.size()));
            secondval6=imageview2.get(val14);
            imageview2.remove(val14);

            int val15 =(new Random().nextInt(imageview2.size()));
            secondval7=imageview2.get(val15);
            imageview2.remove(val15);

        }else  if(levelval==5){
            int val1 =(new Random().nextInt(imageview1.size()));
            firstval=imageview1.get(val1);
            imageview1.remove(val1);

            int val2 =(new Random().nextInt(imageview1.size()));
            firstval1=imageview1.get(val2);
            imageview1.remove(val2);

            addimage();

            int val3 =(new Random().nextInt(imageview2.size()));
            firstval5=imageview2.get(val3);
            imageview2.remove(val3);

            int val4 =(new Random().nextInt(imageview2.size()));
            firstval6=imageview2.get(val4);
            imageview2.remove(val4);

            int val5 =(new Random().nextInt(imageview2.size()));
            firstval7=imageview2.get(val5);
            imageview2.remove(val5);

            addimage();

            int val11 =(new Random().nextInt(imageview1.size()));
            secondval=imageview1.get(val11);
            imageview1.remove(val11);

            int val12 =(new Random().nextInt(imageview1.size()));
            secondval1=imageview1.get(val12);
            imageview1.remove(val12);

            addimage();

            int val13 =(new Random().nextInt(imageview2.size()));
            secondval5=imageview2.get(val13);
            imageview2.remove(val13);

            int val14 =(new Random().nextInt(imageview2.size()));
            secondval6=imageview2.get(val14);
            imageview2.remove(val14);
        }else  if(levelval==6){
            int val1 =(new Random().nextInt(imageview1.size()));
            firstval=imageview1.get(val1);
            imageview1.remove(val1);

            int val2 =(new Random().nextInt(imageview1.size()));
            firstval1=imageview1.get(val2);
            imageview1.remove(val2);

            int val3 =(new Random().nextInt(imageview1.size()));
            firstval2=imageview1.get(val3);
            imageview1.remove(val3);

            addimage();

            int val6 =(new Random().nextInt(imageview2.size()));
            firstval5=imageview2.get(val6);
            imageview2.remove(val6);

            int val7 =(new Random().nextInt(imageview2.size()));
            firstval6=imageview2.get(val7);
            imageview2.remove(val7);

            int val8 =(new Random().nextInt(imageview2.size()));
            firstval7=imageview2.get(val8);
            imageview2.remove(val8);

            addimage();

            int val11 =(new Random().nextInt(imageview1.size()));
            secondval=imageview1.get(val11);
            imageview1.remove(val11);

            int val12 =(new Random().nextInt(imageview1.size()));
            secondval1=imageview1.get(val12);
            imageview1.remove(val12);

            int val13 =(new Random().nextInt(imageview1.size()));
            secondval2=imageview1.get(val13);
            imageview1.remove(val13);

            addimage();

            int val16 =(new Random().nextInt(imageview2.size()));
            secondval5=imageview2.get(val16);
            imageview2.remove(val16);

            int val17 =(new Random().nextInt(imageview2.size()));
            secondval6=imageview2.get(val17);
            imageview2.remove(val17);

            int val18 =(new Random().nextInt(imageview2.size()));
            secondval7=imageview2.get(val18);
            imageview2.remove(val18);
        }else  if(levelval>=7){
            int ab=(new Random().nextInt(3));
            if(ab==0) {
                int val1 = (new Random().nextInt(imageview1.size()));
                firstval = imageview1.get(val1);
                imageview1.remove(val1);

                int val2 = (new Random().nextInt(imageview1.size()));
                firstval1 = imageview1.get(val2);
                imageview1.remove(val2);

                int val3 = (new Random().nextInt(imageview1.size()));
                firstval2 = imageview1.get(val3);
                imageview1.remove(val3);
            }else if(ab==1) {
                int val1 = (new Random().nextInt(imageview1.size()));
                firstval = imageview1.get(val1);
                imageview1.remove(val1);

                int val2 = (new Random().nextInt(imageview1.size()));
                firstval1 = imageview1.get(val2);
                imageview1.remove(val2);

                int val3 = (new Random().nextInt(imageview1.size()));
                firstval2 = imageview1.get(val3);
                imageview1.remove(val3);

                int val4 = (new Random().nextInt(imageview1.size()));
                firstval3 = imageview1.get(val4);
                imageview1.remove(val4);
            }else if(ab==2) {
                int val1 = (new Random().nextInt(imageview1.size()));
                firstval = imageview1.get(val1);
                imageview1.remove(val1);

                int val2 = (new Random().nextInt(imageview1.size()));
                firstval1 = imageview1.get(val2);
                imageview1.remove(val2);

                int val3 = (new Random().nextInt(imageview1.size()));
                firstval2 = imageview1.get(val3);
                imageview1.remove(val3);

                int val4 = (new Random().nextInt(imageview1.size()));
                firstval3 = imageview1.get(val4);
                imageview1.remove(val4);

                int val5 = (new Random().nextInt(imageview1.size()));
                firstval4 = imageview1.get(val5);
                imageview1.remove(val5);
            }

            addimage();
            int ab1=(new Random().nextInt(3));
            if(ab1==0) {
                int val6 = (new Random().nextInt(imageview2.size()));
                firstval5 = imageview2.get(val6);
                imageview2.remove(val6);

                int val7 = (new Random().nextInt(imageview2.size()));
                firstval6 = imageview2.get(val7);
                imageview2.remove(val7);

                int val8 = (new Random().nextInt(imageview2.size()));
                firstval7 = imageview2.get(val8);
                imageview2.remove(val8);
            }else if(ab1==1){
                int val6 = (new Random().nextInt(imageview2.size()));
                firstval5 = imageview2.get(val6);
                imageview2.remove(val6);

                int val7 = (new Random().nextInt(imageview2.size()));
                firstval6 = imageview2.get(val7);
                imageview2.remove(val7);

                int val8 = (new Random().nextInt(imageview2.size()));
                firstval7 = imageview2.get(val8);
                imageview2.remove(val8);

                int val9 = (new Random().nextInt(imageview2.size()));
                firstval8 = imageview2.get(val9);
                imageview2.remove(val9);
            }else if(ab1==2){
                int val6 = (new Random().nextInt(imageview2.size()));
                firstval5 = imageview2.get(val6);
                imageview2.remove(val6);

                int val7 = (new Random().nextInt(imageview2.size()));
                firstval6 = imageview2.get(val7);
                imageview2.remove(val7);

                int val8 = (new Random().nextInt(imageview2.size()));
                firstval7 = imageview2.get(val8);
                imageview2.remove(val8);

                int val9 = (new Random().nextInt(imageview2.size()));
                firstval8 = imageview2.get(val9);
                imageview2.remove(val9);

                int val10 = (new Random().nextInt(imageview2.size()));
                firstval9 = imageview2.get(val10);
                imageview2.remove(val10);
            }

            addimage();
            int ab2=(new Random().nextInt(3));
            if(ab2==0) {
                int val11 = (new Random().nextInt(imageview1.size()));
                secondval = imageview1.get(val11);
                imageview1.remove(val11);

                int val12 = (new Random().nextInt(imageview1.size()));
                secondval1 = imageview1.get(val12);
                imageview1.remove(val12);

                int val13 = (new Random().nextInt(imageview1.size()));
                secondval2 = imageview1.get(val13);
                imageview1.remove(val13);
            }else  if(ab2==1) {
                int val11 = (new Random().nextInt(imageview1.size()));
                secondval = imageview1.get(val11);
                imageview1.remove(val11);

                int val12 = (new Random().nextInt(imageview1.size()));
                secondval1 = imageview1.get(val12);
                imageview1.remove(val12);

                int val13 = (new Random().nextInt(imageview1.size()));
                secondval2 = imageview1.get(val13);
                imageview1.remove(val13);

                int val14 = (new Random().nextInt(imageview1.size()));
                secondval3 = imageview1.get(val14);
                imageview1.remove(val14);
            }else  if(ab2==2) {
                int val11 = (new Random().nextInt(imageview1.size()));
                secondval = imageview1.get(val11);
                imageview1.remove(val11);

                int val12 = (new Random().nextInt(imageview1.size()));
                secondval1 = imageview1.get(val12);
                imageview1.remove(val12);

                int val13 = (new Random().nextInt(imageview1.size()));
                secondval2 = imageview1.get(val13);
                imageview1.remove(val13);

                int val14 = (new Random().nextInt(imageview1.size()));
                secondval3 = imageview1.get(val14);
                imageview1.remove(val14);

                int val15 = (new Random().nextInt(imageview1.size()));
                secondval4 = imageview1.get(val15);
                imageview1.remove(val15);
            }

            addimage();
            int ab3=(new Random().nextInt(3));
            if(ab3==0) {
                int val16 = (new Random().nextInt(imageview2.size()));
                secondval5 = imageview2.get(val16);
                imageview2.remove(val16);

                int val17 = (new Random().nextInt(imageview2.size()));
                secondval6 = imageview2.get(val17);
                imageview2.remove(val17);

                int val18 = (new Random().nextInt(imageview2.size()));
                secondval7 = imageview2.get(val18);
                imageview2.remove(val18);
            }else if(ab3==1) {
                int val16 = (new Random().nextInt(imageview2.size()));
                secondval5 = imageview2.get(val16);
                imageview2.remove(val16);

                int val17 = (new Random().nextInt(imageview2.size()));
                secondval6 = imageview2.get(val17);
                imageview2.remove(val17);

                int val18 = (new Random().nextInt(imageview2.size()));
                secondval7 = imageview2.get(val18);
                imageview2.remove(val18);

                int val19 = (new Random().nextInt(imageview2.size()));
                secondval8 = imageview2.get(val19);
                imageview2.remove(val19);
            }else if(ab3==2) {
                int val16 = (new Random().nextInt(imageview2.size()));
                secondval5 = imageview2.get(val16);
                imageview2.remove(val16);

                int val17 = (new Random().nextInt(imageview2.size()));
                secondval6 = imageview2.get(val17);
                imageview2.remove(val17);

                int val18 = (new Random().nextInt(imageview2.size()));
                secondval7 = imageview2.get(val18);
                imageview2.remove(val18);

                int val19 = (new Random().nextInt(imageview2.size()));
                secondval8 = imageview2.get(val19);
                imageview2.remove(val19);

                int val20 = (new Random().nextInt(imageview2.size()));
                secondval9 = imageview2.get(val20);
                imageview2.remove(val20);
            }
        }
        fristsideval=0;
        secondsideval=0;
    }





    private void addvaluefristside(int digitval) {
        if (digite.get(digitval) == R.drawable.digit5) {
            fristsideval = fristsideval + 5;
        } else if (digite.get(digitval) == R.drawable.digit10) {
            fristsideval = fristsideval + 10;
        } else if (digite.get(digitval) == R.drawable.digit15) {
            fristsideval = fristsideval + 15;
        } else if (digite.get(digitval) == R.drawable.digit20) {
            fristsideval = fristsideval + 20;
        } else if (digite.get(digitval) == R.drawable.digit25) {
            fristsideval = fristsideval + 25;
        }

    }

    private void addvaluesecondside(int digitval) {
        if (digite.get(digitval) == R.drawable.digit5) {
            secondsideval = secondsideval + 5;
        } else if (digite.get(digitval) == R.drawable.digit10) {
            secondsideval = secondsideval + 10;
        } else if (digite.get(digitval) == R.drawable.digit15) {
            secondsideval = secondsideval + 15;
        } else if (digite.get(digitval) == R.drawable.digit20) {
            secondsideval = secondsideval + 20;
        } else if (digite.get(digitval) == R.drawable.digit25) {
            secondsideval = secondsideval + 25;
        }
    }

    @Override
    public void onClick(View v) {
        Log.e("Value", "" + fristsideval + "   " + secondsideval);
        if (v.getId() == R.id.linearlayout33) {
            if (fristsideval > secondsideval) {
                rightanswer();
            }
            else{
                wronganswer();
            }
        } else if (v.getId() == R.id.linearlayout32) {
            if (fristsideval == secondsideval) {
                rightanswer();
            }else{
                wronganswer();
            }
        } else if (v.getId() == R.id.linearlayout31) {
            if (fristsideval < secondsideval) {
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
                        refrence.setMoneygame(refrence.getMoneygame() + (100*multiplierval));
                        process();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rightanswer.setVisibility(View.GONE);
                        rightans.setVisibility(View.GONE);
                        rightval=rightval+1;
                        levelval=levelval+1;
//                        reset();
//                        addimage();
                        showvaluecolume1();
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
//                    reset();
//                    addimage();
                    showvaluecolume1();
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
        mTimerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (StartTime > 0) {
                            StartTime--;
                        }
                        if (StartTime == 0) {
                            stopTimer();
//                            gameover();
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
        refrence.setGame("MoneyGame");
        refrence.setScore(refrence.getMoneygame());
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
