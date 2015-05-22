package com.edlogiq.neurongym.game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

public class MemoryMatrixgame extends Activity implements View.OnClickListener {

    private LinearLayout layout;
    private int idval = 0, colorval;
    private String id = "image";
    private TextView image, score;
    private ArrayList<Integer> number = new ArrayList<Integer>();
    private ArrayList<TextView> textview = new ArrayList<TextView>();
    private ImageView rightans, wrongans;
    private LinearLayout rightanswe, wronganswer;
    private RefrenceWrapper refrence;
    private boolean ball1 = false, ball2 = false,ball3 = false, ball4 = false, ball5 = false, ball6 = false, ball7 = false
            , ball8 = false, ball9 = false, ball10 = false, ball11 = false, ball12 = false, ball13 = false
            , ball14 = false, ball15 = false, ball16 = false, ball17 = false, ball18 = false, ball19 = false
            , ball20 = false, ball21 = false;
    private RelativeLayout pausepopup;
    private int levelvalue=1,prog=0;
    private ProgressBar progress;
    private int blinkred=500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_matrixgame);

        ((RelativeLayout)findViewById(R.id.memorymatrixgame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.memorymatrixgame)).setBackgroundResource(R.color.black);
        }

        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        refrence = RefrenceWrapper.getRefrenceWrapper(this);
        refrence.setMemorymatrix(0);
        layout = (LinearLayout) findViewById(R.id.gamelayout);
        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswe = (LinearLayout) findViewById(R.id.righttick);
        wronganswer = (LinearLayout) findViewById(R.id.wrongcross);
        score = (TextView) findViewById(R.id.scoretextview);

        gamelayout(3, 3);
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

    private void addnumber() {
        number.clear();
        if(levelvalue>=0) {
            number.add(0);
            number.add(1);
            number.add(2);
            number.add(3);
            number.add(4);
            number.add(5);
            number.add(6);
            number.add(7);
            number.add(8);

        }
        if( levelvalue>5 ) {
            number.add(9);
            number.add(10);
            number.add(11);
            number.add(12);
            number.add(13);
            number.add(14);
            number.add(15);
        }
        if(levelvalue>10)  {
            number.add(16);
            number.add(17);
            number.add(18);
            number.add(19);
            number.add(20);
            number.add(21);
            number.add(22);
            number.add(23);
            number.add(24);

        }
        if(levelvalue>15)  {
            number.add(25);
            number.add(26);
            number.add(27);
            number.add(28);
            number.add(29);
            number.add(30);
            number.add(31);
            number.add(32);
            number.add(33);
            number.add(34);
            number.add(35);

        }
        if(levelvalue>18)  {
            number.add(36);
            number.add(37);
            number.add(38);
            number.add(39);
            number.add(40);
            number.add(41);
        }
        }

    private void gamelayout(int row, int column) {
        Log.e("coloum and row",""+refrence.getMemorymatrix());

        score.setText("" + refrence.getMemorymatrix());
        idval = 0;
        layout.removeAllViews();
        addnumber();
        textview.clear();
        levelvalues();

        int width= (this.getWindowManager().getDefaultDisplay().getWidth())-40;
        int hight=width;

        for (int i = 0; i < row; i++) {

            LinearLayout rowlayout = new LinearLayout(this);
            rowlayout.setOrientation(LinearLayout.HORIZONTAL);
            rowlayout.setGravity(Gravity.CENTER_HORIZONTAL);

            for (int j = 0; j < column; j++) {

                LinearLayout layoutbox = new LinearLayout(this);
                LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(width/column, hight/row);
                layoutbox.setLayoutParams(Params1);
                layoutbox.setBackgroundResource(R.drawable.rectangleborder);
                layoutbox.setGravity(Gravity.CENTER);

                image = new TextView(this);
                LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams((width/column)/2, (hight/row)/2);
                image.setLayoutParams(imageParams);

                if (val1 == idval || val2 == idval || val3 == idval || val4 == idval|| val5 == idval
                        || val6 == idval|| val7 == idval|| val8 == idval|| val9 == idval|| val10 == idval
                        || val11 == idval|| val12 == idval|| val13 == idval|| val14 == idval|| val15 == idval
                        || val16 == idval|| val17 == idval|| val18 == idval|| val19 == idval|| val20 == idval) {
                    Log.e("red",""+idval);
                    image.setBackgroundResource(R.drawable.red);
                    Animation anim = new ScaleAnimation(1f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF,
                            (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
                    anim.setDuration(blinkred);
                    anim.setFillAfter(true);
                    image.startAnimation(anim);
                    if (!textview.contains(image)) {
                        textview.add(image);
                    }
                    anim.setAnimationListener(animlistner);

                } else {
                    image.setBackgroundResource(R.drawable.grey);
                }
                image.setOnClickListener(this);
                layoutbox.addView(image);
                rowlayout.addView(layoutbox);
                idval++;
            }
            layout.addView(rowlayout);
        }
    }


    Animation.AnimationListener animlistner=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            endanim();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    int val1=55,val2=55,val3=55,val4=55,val5=55,val6=55,val7=55,val8=55,val9=55,val10=55,val11=55,
            val12=55,val13=55,val14=55,val15=55,val16=55,val17=55,val18=55,val19=55,val20=55;
    private void levelvalues() {

        if(levelvalue>=1) {
            int vala = (new Random().nextInt(number.size()));
            val1 = number.get(vala);
            number.remove(vala);

            int valb = (new Random().nextInt(number.size()));
            val2 = number.get(valb);
            number.remove(valb);

            int valc = (new Random().nextInt(number.size()));
            val3 = number.get(valc);
            number.remove(valc);
        }
        if(levelvalue>=2) {
            int vald = (new Random().nextInt(number.size()));
            val4 = number.get(vald);
            number.remove(vald);
        }
        if(levelvalue>=3) {
            int vale = (new Random().nextInt(number.size()));
            val5 = number.get(vale);
            number.remove(vale);
        }

        if(levelvalue>=4) {
            int valf = (new Random().nextInt(number.size()));
            val6 = number.get(valf);
            number.remove(valf);
        }
        if(levelvalue>=5) {
            int valg = (new Random().nextInt(number.size()));
            val7 = number.get(valg);
            number.remove(valg);
        }
        if(levelvalue>=6) {
            int valh = (new Random().nextInt(number.size()));
            val8 = number.get(valh);
            number.remove(valh);
        }
        if(levelvalue>=7) {
            int vali = (new Random().nextInt(number.size()));
            val9 = number.get(vali);
            number.remove(vali);
        }
        if(levelvalue>=8) {
            int valj = (new Random().nextInt(number.size()));
            val10 = number.get(valj);
            number.remove(valj);
        }
        if(levelvalue>=9) {
            int valk = (new Random().nextInt(number.size()));
            val11 = number.get(valk);
            number.remove(valk);
        }
        if(levelvalue>=10) {
            int vall = (new Random().nextInt(number.size()));
            val12 = number.get(vall);
            number.remove(vall);
        }
        if(levelvalue>=11) {
            int valm = (new Random().nextInt(number.size()));
            val13 = number.get(valm);
            number.remove(valm);
        }
        if(levelvalue>=12) {
            int valn = (new Random().nextInt(number.size()));
            val14 = number.get(valn);
            number.remove(valn);
        }
        if(levelvalue>=13) {
            int valo = (new Random().nextInt(number.size()));
            val15 = number.get(valo);
            number.remove(valo);
        }
        if(levelvalue>=14) {
            int valp = (new Random().nextInt(number.size()));
            val16 = number.get(valp);
            number.remove(valp);
        }
        if(levelvalue>=15) {
            int valq = (new Random().nextInt(number.size()));
            val17 = number.get(valq);
            number.remove(valq);
        }
        if(levelvalue>=16) {
            int valr = (new Random().nextInt(number.size()));
            val18 = number.get(valr);
            number.remove(valr);
        }
        if(levelvalue>=17) {
            int vals = (new Random().nextInt(number.size()));
            val19 = number.get(vals);
            number.remove(vals);
        }
        if(levelvalue>=18) {
            int valt = (new Random().nextInt(number.size()));
            val20 = number.get(valt);
            number.remove(valt);
        }
        Log.e("Total ball",levelvalue+"  "+val1+" "+val2+" "+val3+" "+val4+" "+val5+" "+val6+" "+val7+" "+val8+" "+val9+" "+
                        val10+" "+val11+" "+val12+" "+val13+" "+val14+" "+val15+" "+val16+" "+val17+" "+val18+" "+
                val19+" "+val20);
    }

    private void endanim() {
        for (int i = 0; i < textview.size(); i++) {
            textview.get(i).setBackgroundResource(R.drawable.grey);
            textview.get(i).setTag(i);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() == 0) {
            textview.get(0).setBackgroundResource(R.drawable.cyan);
            ball1 = true;
            rightanswer();
        } else if (v.getTag() == 1) {
            textview.get(1).setBackgroundResource(R.drawable.cyan);
            ball2 = true;
            rightanswer();
        } else if (v.getTag() == 2) {
            textview.get(2).setBackgroundResource(R.drawable.cyan);
            ball3 = true;
            rightanswer();
        }else if (v.getTag() == 3) {
            textview.get(3).setBackgroundResource(R.drawable.cyan);
            ball4 = true;
            rightanswer();
        }else if (v.getTag() == 4) {
            textview.get(4).setBackgroundResource(R.drawable.cyan);
            ball5 = true;
            rightanswer();
        }else if (v.getTag() == 5) {
            textview.get(5).setBackgroundResource(R.drawable.cyan);
            ball6 = true;
            rightanswer();
        }else if (v.getTag() == 6) {
            textview.get(6).setBackgroundResource(R.drawable.cyan);
            ball7 = true;
            rightanswer();
        }else if (v.getTag() == 7) {
            textview.get(7).setBackgroundResource(R.drawable.cyan);
            ball8 = true;
            rightanswer();
        }else if (v.getTag() == 8) {
            textview.get(8).setBackgroundResource(R.drawable.cyan);
            ball9 = true;
            rightanswer();
        }else if (v.getTag() == 9) {
            textview.get(9).setBackgroundResource(R.drawable.cyan);
            ball10 = true;
            rightanswer();
        }else if (v.getTag() == 10) {
            textview.get(10).setBackgroundResource(R.drawable.cyan);
            ball11 = true;
            rightanswer();
        }else if (v.getTag() == 11) {
            textview.get(11).setBackgroundResource(R.drawable.cyan);
            ball12 = true;
            rightanswer();
        }else if (v.getTag() == 12) {
            textview.get(12).setBackgroundResource(R.drawable.cyan);
            ball13 = true;
            rightanswer();
        }else if (v.getTag() == 13) {
            textview.get(13).setBackgroundResource(R.drawable.cyan);
            ball14 = true;
            rightanswer();
        }else if (v.getTag() == 14) {
            textview.get(14).setBackgroundResource(R.drawable.cyan);
            ball15 = true;
            rightanswer();
        }else if (v.getTag() == 15) {
            textview.get(15).setBackgroundResource(R.drawable.cyan);
            ball16 = true;
            rightanswer();
        }else if (v.getTag() == 16) {
            textview.get(16).setBackgroundResource(R.drawable.cyan);
            ball17 = true;
            rightanswer();
        }else if (v.getTag() == 17) {
            textview.get(17).setBackgroundResource(R.drawable.cyan);
            ball18 = true;
            rightanswer();
        }else if (v.getTag() == 18) {
            textview.get(18).setBackgroundResource(R.drawable.cyan);
            ball19 = true;
            rightanswer();
        }else if (v.getTag() == 19) {
            textview.get(19).setBackgroundResource(R.drawable.cyan);
            ball20 = true;
            rightanswer();
        }else if (v.getTag() == 20) {
            textview.get(20).setBackgroundResource(R.drawable.cyan);
            ball21 = true;
            rightanswer();
        } else {
            wronganswer();

        }
    }


    private void rightanswer() {

        if (checkanswer()) {
            if (rightanswe.getVisibility() == View.GONE) {
                rightanswe.setVisibility(View.VISIBLE);
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
                rightanswe.startAnimation(anim);
                scal.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        refrence.setMemorymatrix(refrence.getMemorymatrix() + (100*multiplierval));
                       process();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rightans.setVisibility(View.GONE);
                        rightanswe.setVisibility(View.GONE);

                        if(levelvalue==1) {
                            levelvalue=2;
                        }else  if(levelvalue==2) {
                            levelvalue=3;
                        }else  if(levelvalue==3) {
                            levelvalue=4;
                        }else  if(levelvalue==4) {
                            levelvalue=5;
                        }else  if(levelvalue==5) {
                            levelvalue=6;
                        }else  if(levelvalue==6) {
                            levelvalue=7;
                        }else  if(levelvalue==7) {
                            levelvalue=8;
                        }else  if(levelvalue==8) {
                            levelvalue=9;
                        }else  if(levelvalue==9) {
                            levelvalue=10;
                        }else  if(levelvalue==10) {
                            levelvalue=11;
                        }else  if(levelvalue==11) {
                            levelvalue=12;
                        }else  if(levelvalue==12) {
                            levelvalue=13;
                        }else  if(levelvalue==13) {
                            levelvalue=14;
                        }else  if(levelvalue==14) {
                            levelvalue=15;
                        }else  if(levelvalue==15) {
                            levelvalue=16;
                        }else  if(levelvalue==16) {
                            levelvalue=17;
                        }else  if(levelvalue==17) {
                            levelvalue=18;
                        }else  if(levelvalue==18) {
                            levelvalue=19;
                        }else  if(levelvalue==19) {
                            levelvalue=20;
                        }else  if(levelvalue==20) {
                            levelvalue=20;
                        }
                        rightval=rightval+1;
                        nextvalue();

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
                    lifevalue=lifevalue+1;
                    life();
                }
                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }


    private int lifevalue=0;
    private void life(){
        if(lifevalue==1) {
            ((ImageView) findViewById(R.id.imageview1)).setBackgroundResource(R.drawable.life_brain);
            nextLevel();
        }else if(lifevalue==2){
            ((ImageView) findViewById(R.id.imageview2)).setBackgroundResource(R.drawable.life_brain);
            nextLevel();
        }else if(lifevalue==3){
            ((ImageView) findViewById(R.id.imageview3)).setBackgroundResource(R.drawable.life_brain);
            gameover();
        }
    }

    int wrongval=0,rightval=0;
    private void gameover() {
        refrence.setGame("MemoryMatrix");
        refrence.setScore(refrence.getTracktherought());
        refrence.setMultiplier(multiplierval);
        int total=wrongval+rightval;
        refrence.setAccuracy((int)((rightval*100)/total));
        Intent intent=new Intent(this, GameOver.class);
        startActivity(intent);
        this.finish();
    }


    private void nextvalue() {

        if(multiplierval==2){
            blinkred=400;
        }else if(multiplierval==3){
            blinkred=400;
        }else if(multiplierval==4){
            blinkred=500;
        }else if(multiplierval==5){
            blinkred=600;
        }else if(multiplierval==6){
            blinkred=700;
        }else if(multiplierval>=7){
            blinkred=800;
        }
        Log.e("multiplierval", blinkred+"    "+multiplierval);
        nextLevel();
    }


    int multyplyer=0,multiplierval=1;
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

    private boolean checkanswer(){

        if(levelvalue==1){
            if(ball1&&ball2&&ball3){
                return true;
            }
        }
        if(levelvalue==2){
            if(ball1&&ball2&&ball3&&ball4){
                return true;
            }
        }if(levelvalue==3){
            if(ball1&&ball2&&ball3&&ball4&&ball5){
                return true;
            }
        }if(levelvalue==4){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6){
                return true;
            }
        }if(levelvalue==5){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7){
                return true;
            }
        }if(levelvalue==6){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8){
                return true;
            }
        }if(levelvalue==7){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9){
                return true;
            }
        }if(levelvalue==8){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10){
                return true;
            }
        }if(levelvalue==9){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&ball11){
                return true;
            }
        }if(levelvalue==10){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&ball11&&
                    ball12){
                return true;
            }
        }if(levelvalue==11){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&ball11
                    &&ball12&&ball13){
                return true;
            }
        }if(levelvalue==12){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&ball11
                    &&ball12&&ball13&&ball14){
                return true;
            }
        }if(levelvalue==13){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&ball11
                    &&ball12&&ball13&&ball14&&ball15){
                return true;
            }
        }if(levelvalue==14){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&ball11
                    &&ball12&&ball13&&ball14&&ball15&&ball16){
                return true;
            }
        }if(levelvalue==15){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&ball11
                    &&ball12&&ball13&&ball14&&ball15&&ball16&&ball17){
                return true;
            }
        }if(levelvalue==16){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&ball11
                    &&ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18){
                return true;
            }
        }if(levelvalue==17){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&ball11
                    &&ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18&&ball19){
                return true;
            }
        }if(levelvalue==18){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&ball11
                    &&ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18&&ball19&&ball20){
                return true;
            }
        }if(levelvalue>=19){
            if(ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&ball11
                    &&ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18&&ball19&&ball20){
                return true;
            }
        }
        return false;
    }

    private void nextLevel(){
        ball1 = false; ball2 = false;ball3 = false; ball4 = false; ball5 = false; ball6 = false;
        ball7 = false;ball8 = false; ball9 = false; ball10 = false; ball11 = false; ball12 = false;
        ball13 = false; ball14 = false; ball15 = false; ball16 = false; ball17 = false;
        ball18 = false; ball19 = false; ball20 = false; ball21 = false;
        if(levelvalue==1) {
            gamelayout(3, 3);
        }else  if(levelvalue==2) {
            gamelayout(3, 3);
        }else  if(levelvalue==3) {
            gamelayout(3, 3);
        }else  if(levelvalue==4) {
            gamelayout(3, 3);
        }else  if(levelvalue==5) {
            gamelayout(3, 3);
        }else  if(levelvalue==6) {
            gamelayout(4, 4);
        }else  if(levelvalue==7) {
            gamelayout(4, 4);
        }else  if(levelvalue==8) {
            gamelayout(4, 4);
        }else  if(levelvalue==9) {
            gamelayout(4, 4);
        }else  if(levelvalue==10) {
            gamelayout(4, 4);
        }else  if(levelvalue==11) {
            gamelayout(5, 5);
        }else  if(levelvalue==12) {
            gamelayout(5, 5);
        }else  if(levelvalue==13) {
            gamelayout(5, 5);
        }else  if(levelvalue==14) {
            gamelayout(5, 5);
        }else  if(levelvalue==15) {
            gamelayout(5, 5);
        }else  if(levelvalue==16) {
            gamelayout(6, 6);
        }else  if(levelvalue==17) {
            gamelayout(6, 6);
        }else  if(levelvalue==18) {
            gamelayout(6, 6);
        }else  if(levelvalue==19) {
            gamelayout(7, 6);
        }else  if(levelvalue==20) {
            gamelayout(7, 6);
        }
    }

}

