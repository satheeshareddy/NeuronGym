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
import com.gc.materialdesign.views.Switch;

import java.util.ArrayList;
import java.util.Random;

public class MemoryMatrixProGame extends Activity implements View.OnClickListener {

    private LinearLayout layout;
    private int idval = 0, colorval, randomsweatch;
    private String id = "image";
    private TextView image, score;
    private ArrayList<Integer> number = new ArrayList<Integer>();
    private ArrayList<TextView> textcolorred = new ArrayList<TextView>();
    private ArrayList<TextView> textcolorblue = new ArrayList<TextView>();
    private ImageView rightans, wrongans;
    private LinearLayout rightanswer, wronganswer;
    private RefrenceWrapper refrence;
    private Switch sweatch;
    private boolean ball1 = false, ball2 = false,ball3 = false, ball4 = false, ball5 = false, ball6 = false, ball7 = false
            , ball8 = false, ball9 = false, ball10 = false, ball11 = false, ball12 = false, ball13 = false
            , ball14 = false, ball15 = false, ball16 = false, ball17 = false, ball18 = false, ball19 = false
            , ball20 = false, ball21 = false,ball22=false;
    private RelativeLayout pausepopup;
    private int levelvalue=1,prog=0;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_matrix_pro_game);

        ((RelativeLayout)findViewById(R.id.memorymatrixprogame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.memorymatrixprogame)).setBackgroundResource(R.color.black);
        }

        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        refrence = RefrenceWrapper.getRefrenceWrapper(this);
        refrence.setMemorymatrixpro(0);
        layout = (LinearLayout) findViewById(R.id.gamelayout);
        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswer = (LinearLayout) findViewById(R.id.righttick);
        wronganswer = (LinearLayout) findViewById(R.id.wrongcross);
        score = (TextView) findViewById(R.id.scoretextview);
        sweatch = (Switch) findViewById(R.id.switch1);
        addnumber();
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
        if(levelvalue>0) {
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
        if( levelvalue>4 ) {
            number.add(9);
            number.add(10);
            number.add(11);
            number.add(12);
            number.add(13);
            number.add(14);
            number.add(15);
        }
        if(levelvalue>9)  {
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
        if(levelvalue>14)  {
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

    int val1=55,val2=55,val3=55,val4=55,val5=55,val6=55,val7=55,val8=55,val9=55,val10=55,
            val11=55,val12=55,val13=55,val14=55,val15=55,val16=55,val17=55,val18=55,
            val19=55,val20=55,val21=55,val22=55;

    private void gamelayout(int row, int column) {

        randomsweatch = (new Random().nextInt(2));
        if (randomsweatch == 0) {
            sweatch.setBackgroundColor(Color.parseColor("#f44336"));
            sweatch.setChecked(true);
        } else if (randomsweatch == 1) {
            sweatch.setBackgroundColor(Color.parseColor("#3f51b5"));
            sweatch.setChecked(false);

        }

        score.setText("" + refrence.getMemorymatrixpro());
        idval = 0;
        layout.removeAllViews();
        addnumber();
        textcolorred.clear();
        textcolorblue.clear();
        levelvaluesred();
        levelvaluesblue();


        Animation anim = new ScaleAnimation(1f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF,
                (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
        anim.setDuration(500);
        anim.setFillAfter(true);


        for (int i = 0; i < row; i++) {
            LinearLayout rowlayout = new LinearLayout(this);
            rowlayout.setOrientation(LinearLayout.HORIZONTAL);
            rowlayout.setGravity(Gravity.CENTER_HORIZONTAL);

            for (int j = 0; j < column; j++) {

                LinearLayout layoutbox = new LinearLayout(this);


                int width= this.getWindowManager().getDefaultDisplay().getWidth()-20;
                int hight=width;
                LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(width/column, hight/row);
                layoutbox.setLayoutParams(Params1);
                layoutbox.setBackgroundResource(R.drawable.rectangleborder);
                layoutbox.setGravity(Gravity.CENTER);

                image = new TextView(this);
                LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams((width/column)/2, (hight/row)/2);
                image.setLayoutParams(imageParams);


                if (val1 == idval || val2 == idval || val3 == idval || val4 == idval|| val5 == idval
                        || val6 == idval|| val7 == idval|| val8 == idval|| val9 == idval|| val10 == idval
                        || val11 == idval) {
                    Log.e("redball",idval+"" );
                    image.setBackgroundResource(R.drawable.red);
//                    image.startAnimation(anim);
                    if (!textcolorred.contains(image)) {
                        textcolorred.add(image);
                    }

                } else if ( val12 == idval|| val13 == idval|| val14 == idval|| val15 == idval
                        || val16 == idval|| val17 == idval|| val18 == idval|| val19 == idval
                        || val20 == idval|| val21 == idval|| val22 == idval) {
                    Log.e("Blueball",idval+"" );
                    image.setBackgroundResource(R.drawable.blue);
                    image.startAnimation(anim);
                    if (!textcolorblue.contains(image)) {
                        textcolorblue.add(image);
                    }
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

        anim.setAnimationListener(animlistner);
    }


    Animation.AnimationListener animlistner=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            endanimblue();
            endanimred();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private void levelvaluesred() {

        Log.e("Total",number.size()+" ");
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
        if(levelvalue>=5) {
            int vale = (new Random().nextInt(number.size()));
            val5 = number.get(vale);
            number.remove(vale);
        }
        if(levelvalue>=6) {
            int valf = (new Random().nextInt(number.size()));
            val6 = number.get(valf);
            number.remove(valf);
        }
        if(levelvalue>=9) {
            int valg = (new Random().nextInt(number.size()));
            val7 = number.get(valg);
            number.remove(valg);
        }
        if(levelvalue>=10) {
            int valh = (new Random().nextInt(number.size()));
            val8 = number.get(valh);
            number.remove(valh);
        }
        if(levelvalue>=12) {
            int vali = (new Random().nextInt(number.size()));
            val9 = number.get(vali);
            number.remove(vali);
        }
        if(levelvalue>=14) {
            int valj = (new Random().nextInt(number.size()));
            val10 = number.get(valj);
            number.remove(valj);
        }
        if(levelvalue>=16) {
            int valk = (new Random().nextInt(number.size()));
            val11 = number.get(valk);
            number.remove(valk);
        }

        Log.e("Red", val1+" "+ val2+" "+val3+" "+ val4+" "+val5+" "+val6+" "+
                val7+" "+val8+" "+val9+" "+val10+" "+ val11);

    }

    private void levelvaluesblue() {
        Log.e("TotalBlue",number.size()+" ");
        if(levelvalue>=1) {
            int vala = (new Random().nextInt(number.size()));
            val12 = number.get(vala);
            number.remove(vala);

            int valb = (new Random().nextInt(number.size()));
            val13 = number.get(valb);
            number.remove(valb);

            int valc = (new Random().nextInt(number.size()));
            val14 = number.get(valc);
            number.remove(valc);
            Log.e("Blue", val12+" "+val13+" "+val14);
        }
        if(levelvalue>=3) {
            int vald = (new Random().nextInt(number.size()));
            val15 = number.get(vald);
            number.remove(vald);
            Log.e("Blue", val12+" "+val13+" "+val14+" "+val15);
        }
        if(levelvalue>=4) {
            int vale = (new Random().nextInt(number.size()));
            val16 = number.get(vale);
            number.remove(vale);
            Log.e("Blue", val12+" "+val13+" "+val14+" "+val15+"  "+val16);
        }
        if(levelvalue>=7) {
            int valf = (new Random().nextInt(number.size()));
            val17 = number.get(valf);
            number.remove(valf);
        }
        if(levelvalue>=8) {
            int valg = (new Random().nextInt(number.size()));
            val18 = number.get(valg);
            number.remove(valg);
        }
        if(levelvalue>=11) {
            int valh = (new Random().nextInt(number.size()));
            val19 = number.get(valh);
            number.remove(valh);
        }
        if(levelvalue>=13) {
            int vali = (new Random().nextInt(number.size()));
            val20 = number.get(vali);
            number.remove(vali);
        }
        if(levelvalue>=15) {
            int valj = (new Random().nextInt(number.size()));
            val21 = number.get(valj);
            number.remove(valj);
        }
        if(levelvalue>=17) {
            int valk = (new Random().nextInt(number.size()));
            val22 = number.get(valk);
            number.remove(valk);
        }

    }


    private void endanimblue() {
        int j = 11;
        for (int i = 0; i < textcolorblue.size(); i++) {
            textcolorblue.get(i).setBackgroundResource(R.drawable.grey);
            textcolorblue.get(i).setTag(j);
            j++;
        }
    }

    private void endanimred() {
        for (int i = 0; i < textcolorred.size(); i++) {
            textcolorred.get(i).setBackgroundResource(R.drawable.grey);
            textcolorred.get(i).setTag(i);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() == 0) {
            if(randomsweatch==0) {
                textcolorred.get(0).setBackgroundResource(R.drawable.cyan);
                ball1 = true;
                rightanswer();
            }else{
                wronganswer();
            }
        } else if (v.getTag() == 1) {
            if(randomsweatch==0) {
            textcolorred.get(1).setBackgroundResource(R.drawable.cyan);
            ball2 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        } else if (v.getTag() == 2) {
            if(randomsweatch==0) {
            textcolorred.get(2).setBackgroundResource(R.drawable.cyan);
            ball3 = true;
            rightanswer();
        }else{
                wronganswer();
        }
        }else if (v.getTag() == 3) {
            if(randomsweatch==0) {
            textcolorred.get(3).setBackgroundResource(R.drawable.cyan);
            ball4 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 4) {
            if(randomsweatch==0) {
            textcolorred.get(4).setBackgroundResource(R.drawable.cyan);
            ball5 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 5) {
            if(randomsweatch==0) {
            textcolorred.get(5).setBackgroundResource(R.drawable.cyan);
            ball6 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 6) {
            if(randomsweatch==0) {
            textcolorred.get(6).setBackgroundResource(R.drawable.cyan);
            ball7 = true;
            rightanswer();
        }else{
                wronganswer();
        }
        }else if (v.getTag() == 7) {
            if(randomsweatch==0) {
            textcolorred.get(7).setBackgroundResource(R.drawable.cyan);
            ball8 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 8) {
            if(randomsweatch==0) {
            textcolorred.get(8).setBackgroundResource(R.drawable.cyan);
            ball9 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 9) {
            if(randomsweatch==0) {
            textcolorred.get(9).setBackgroundResource(R.drawable.cyan);
            ball10 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 10) {
                if(randomsweatch==0) {
            textcolorred.get(10).setBackgroundResource(R.drawable.cyan);
            ball11 = true;
            rightanswer();
                }else{
                    wronganswer();
                }
        }else if (v.getTag() == 11) {
            if(randomsweatch==1) {
            textcolorblue.get(0).setBackgroundResource(R.drawable.cyan);
            ball12 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 12) {
            if(randomsweatch==1) {
            textcolorblue.get(1).setBackgroundResource(R.drawable.cyan);
            ball13 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 13) {
            if(randomsweatch==1) {
            textcolorblue.get(2).setBackgroundResource(R.drawable.cyan);
            ball14 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 14) {
            if(randomsweatch==1) {
            textcolorblue.get(3).setBackgroundResource(R.drawable.cyan);
            ball15 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 15) {
            if(randomsweatch==1) {
            textcolorblue.get(4).setBackgroundResource(R.drawable.cyan);
            ball16 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 16) {
            if(randomsweatch==1) {
            textcolorblue.get(5).setBackgroundResource(R.drawable.cyan);
            ball17 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 17) {
            if(randomsweatch==1) {
            textcolorblue.get(6).setBackgroundResource(R.drawable.cyan);
            ball18 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 18) {
            if(randomsweatch==1) {
            textcolorblue.get(7).setBackgroundResource(R.drawable.cyan);
            ball19 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 19) {
            if(randomsweatch==1) {
            textcolorblue.get(8).setBackgroundResource(R.drawable.cyan);
            ball20 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 20) {
            if(randomsweatch==1) {
            textcolorblue.get(9).setBackgroundResource(R.drawable.cyan);
            ball21 = true;
            rightanswer();
            }else{
                wronganswer();
            }
        }else if (v.getTag() == 21) {
            if(randomsweatch==1) {
            textcolorblue.get(10).setBackgroundResource(R.drawable.cyan);
            ball22 = true;
            rightanswer();
        }else{
                wronganswer();
        }
        } else {
            wronganswer();

        }
    }


    private void rightanswer() {
        if (checkanswer()) {
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
                        refrence.setMemorymatrixpro(refrence.getMemorymatrixpro() + 100);
                        process();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rightans.setVisibility(View.GONE);
                        rightanswer.setVisibility(View.GONE);
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
                        nextLevel();

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
        refrence.setGame("MemoryMatrixPro");
        refrence.setScore(refrence.getTracktherought());
        refrence.setMultiplier(multiplierval);
        int total=wrongval+rightval;
        refrence.setAccuracy((int)((rightval*100)/total));
        Intent intent=new Intent(this, GameOver.class);
        startActivity(intent);
        this.finish();
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
            gamelayout(4, 4);
        }else  if(levelvalue==6) {
            gamelayout(4, 4);
        }else  if(levelvalue==7) {
            gamelayout(4, 4);
        }else  if(levelvalue==8) {
            gamelayout(4, 4);
        }else  if(levelvalue==9) {
            gamelayout(4, 4);
        }else  if(levelvalue==10) {
            gamelayout(5, 5);
        }else  if(levelvalue==11) {
            gamelayout(5, 5);
        }else  if(levelvalue==12) {
            gamelayout(5, 5);
        }else  if(levelvalue==13) {
            gamelayout(5, 5);
        }else  if(levelvalue==14) {
            gamelayout(5, 5);
        }else  if(levelvalue==15) {
            gamelayout(6, 6);
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

    private boolean checkanswer(){

        if(levelvalue==1){
            if(ball1&&ball2&&ball3 || ball12&&ball13&&ball14){
                return true;
            }else{
                return false;
            }
        }
        if(levelvalue==2){
            if((ball1&&ball2&&ball3&&ball4) || (ball12&&ball13&&ball14)){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==3){
            if((ball1&&ball2&&ball3&&ball4) || (ball12&&ball13&&ball14&&ball15)){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==4){
            if((ball1&&ball2&&ball3&&ball4) || (ball12&&ball13&&ball14&&ball15&&ball16)){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==5){
            if((ball1&&ball2&&ball3&&ball4&&ball5) ||
                    (ball12&&ball13&&ball14&&ball15&&ball16)){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==6){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6) ||
                    (ball12&&ball13&&ball14&&ball15&&ball16)){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==7){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6) ||
                    (ball12&&ball13&&ball14&&ball15&&ball16&&ball17)){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==8){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6) ||
                    (ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18)){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==9){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7) ||
                    (ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18)){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==10){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8) ||
                    (ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18)){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==11){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8)||
                    (ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18 &&ball19 )){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==12){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9)||
                    (ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18 &&ball19 )){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==13){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9)||
                    (ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18 &&ball19&&ball20 )){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==14){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10)||
                    (ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18 &&ball19&&ball20 )){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==15){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10)||
                    (ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18 &&ball19&&ball20&&ball21 )){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==16){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&&ball11)||
                    (ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18 &&ball19&&ball20&&ball21 )){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==17){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&&ball11)||
                    (ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18 &&ball19&&ball20&&ball21&&ball22 )){
                return true;
            }else{
                return false;
            }
        }if(levelvalue==18){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&&ball11)||
                    (ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18 &&ball19&&ball20&&ball21&&ball22 )){
                return true;
            }else{
                return false;
            }
        }if(levelvalue>=19){
            if((ball1&&ball2&&ball3&&ball4&&ball5&&ball6&&ball7&&ball8&&ball9&&ball10&&ball11)||
                    (ball12&&ball13&&ball14&&ball15&&ball16&&ball17&&ball18 &&ball19&&ball20&&ball21&&ball22 )){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

}