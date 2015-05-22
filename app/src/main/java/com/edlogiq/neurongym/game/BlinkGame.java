package com.edlogiq.neurongym.game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
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

public class BlinkGame extends Activity implements View.OnClickListener {

    int alphabet, number, alphabetposition, digitposition;

    private LinearLayout puash, rightanswer, wronganswer;
    private TextView blinkimage, score;
    private ArrayList<Integer> alphabetgray = new ArrayList<>();
    private ArrayList<Integer> alphabetgreen = new ArrayList<>();
    private ArrayList<Integer> alphabetwhite = new ArrayList<>();
    private ArrayList<Integer> digitgray = new ArrayList<>();
    private ArrayList<Integer> digitwhite = new ArrayList<>();
    private ArrayList<Integer> digitrandom = new ArrayList<>();
    private ArrayList<ImageView> alphabetbtn = new ArrayList<>();
    private ArrayList<ImageView> digitbtn = new ArrayList<>();
    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, rightans, wrongans;
    private int alphabetnum, digitnum;
    private boolean rightdigit = false, rightalphabet = false;
    private RefrenceWrapper refrence;
    private RelativeLayout pausepopup;
    private ProgressBar progress;
    private int levels=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blink_game);

        ((RelativeLayout)findViewById(R.id.blinkgame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.blinkgame)).setBackgroundResource(R.color.black);
        }

        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswer = (LinearLayout) findViewById(R.id.righttick);
        wronganswer = (LinearLayout) findViewById(R.id.wrongcross);
        refrence = RefrenceWrapper.getRefrenceWrapper(this);
        refrence.setBlink(0);
        score = (TextView) findViewById(R.id.scoretextview);
        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);

        blinkimage = (TextView) findViewById(R.id.imageViewtext);
        image1 = (ImageView) findViewById(R.id.imageviewbtn1);
        image2 = (ImageView) findViewById(R.id.imageviewbtn2);
        image3 = (ImageView) findViewById(R.id.imageviewbtn3);
        image4 = (ImageView) findViewById(R.id.imageviewbtn4);
        image5 = (ImageView) findViewById(R.id.imageviewbtn5);
        image6 = (ImageView) findViewById(R.id.imageviewbtn6);
        image7 = (ImageView) findViewById(R.id.imageviewbtn7);
        image8 = (ImageView) findViewById(R.id.imageviewbtn8);


        ((LinearLayout) findViewById(R.id.button1)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.button2)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.button3)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.button4)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.button5)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.button6)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.button7)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.button8)).setOnClickListener(this);


        init();
        addgreenalphabet();
        addgrayDigit();
        addWhitealphabet();
        addWhiteDigit();
        randomposition();
        popuplayout();
    }


    private void popuplayout() {
        ((LinearLayout) findViewById(R.id.layoutepause)).setOnClickListener(listner);
        pausepopup = (RelativeLayout) findViewById(R.id.pausepopup);
        ((LinearLayout) findViewById(R.id.resume)).setOnClickListener(listner);
        ((LinearLayout) findViewById(R.id.replay)).setOnClickListener(listner);
        ((LinearLayout) findViewById(R.id.instructions)).setOnClickListener(listner);
        ((LinearLayout) findViewById(R.id.sound)).setOnClickListener(listner);
        ((LinearLayout) findViewById(R.id.exitgame)).setOnClickListener(listner);
    }

    private void pausepopup() {
        if (pausepopup.getVisibility() == View.GONE) {
            pausepopup.setVisibility(View.VISIBLE);
        } else {
            pausepopup.setVisibility(View.GONE);
        }
    }

    View.OnClickListener listner = new View.OnClickListener() {
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

    private void init() {
        alphabetbtn.clear();
        digitbtn.clear();

        alphabetbtn.add(image1);
        alphabetbtn.add(image2);
        alphabetbtn.add(image3);
        alphabetbtn.add(image4);

        digitbtn.add(image5);
        digitbtn.add(image6);
        digitbtn.add(image7);
        digitbtn.add(image8);

    }

    private void addgrayalphabet() {
        alphabetgray.clear();
        alphabetgray.add(R.drawable.gray_digita);
        alphabetgray.add(R.drawable.gray_digitb);
        alphabetgray.add(R.drawable.gray_digitc);
        alphabetgray.add(R.drawable.gray_digitd);
        alphabetgray.add(R.drawable.gray_digite);
        alphabetgray.add(R.drawable.gray_digitf);
        alphabetgray.add(R.drawable.gray_digitg);
        alphabetgray.add(R.drawable.gray_digith);
        alphabetgray.add(R.drawable.gray_digiti);
        alphabetgray.add(R.drawable.gray_digitj);
        alphabetgray.add(R.drawable.gray_digitk);
        alphabetgray.add(R.drawable.gray_digitl);
        alphabetgray.add(R.drawable.gray_digitm);
        alphabetgray.add(R.drawable.gray_digitn);
        alphabetgray.add(R.drawable.gray_digito);
        alphabetgray.add(R.drawable.gray_digitp);
        alphabetgray.add(R.drawable.gray_digitq);
        alphabetgray.add(R.drawable.gray_digitr);
        alphabetgray.add(R.drawable.gray_digits);
        alphabetgray.add(R.drawable.gray_digitt);
        alphabetgray.add(R.drawable.gray_digitu);
        alphabetgray.add(R.drawable.gray_digitv);
        alphabetgray.add(R.drawable.gray_digitw);
        alphabetgray.add(R.drawable.gray_digitx);
        alphabetgray.add(R.drawable.gray_digity);
        alphabetgray.add(R.drawable.gray_digitz);
    }

    private void addgreenalphabet() {
        alphabetgreen.clear();
        alphabetgreen.add(R.drawable.green_digita);
        alphabetgreen.add(R.drawable.green_digitb);
        alphabetgreen.add(R.drawable.green_digitc);
        alphabetgreen.add(R.drawable.green_digitd);
        alphabetgreen.add(R.drawable.green_digite);
        alphabetgreen.add(R.drawable.green_digitf);
        alphabetgreen.add(R.drawable.green_digitg);
        alphabetgreen.add(R.drawable.green_digith);
        alphabetgreen.add(R.drawable.green_digiti);
        alphabetgreen.add(R.drawable.green_digitj);
        alphabetgreen.add(R.drawable.green_digitk);
        alphabetgreen.add(R.drawable.green_digitl);
        alphabetgreen.add(R.drawable.green_digitm);
        alphabetgreen.add(R.drawable.green_digitn);
        alphabetgreen.add(R.drawable.green_digito);
        alphabetgreen.add(R.drawable.green_digitp);
        alphabetgreen.add(R.drawable.green_digitq);
        alphabetgreen.add(R.drawable.green_digitr);
        alphabetgreen.add(R.drawable.green_digits);
        alphabetgreen.add(R.drawable.green_digitt);
        alphabetgreen.add(R.drawable.green_digitu);
        alphabetgreen.add(R.drawable.green_digitv);
        alphabetgreen.add(R.drawable.green_digitw);
        alphabetgreen.add(R.drawable.green_digitx);
        alphabetgreen.add(R.drawable.green_digity);
        alphabetgreen.add(R.drawable.green_digitz);
        getrandomgreenalphabetpos();
    }

    private void addWhitealphabet() {
        alphabetwhite.clear();
        alphabetwhite.add(R.drawable.white_digita);
        alphabetwhite.add(R.drawable.white_digitb);
        alphabetwhite.add(R.drawable.white_digitc);
        alphabetwhite.add(R.drawable.white_digitd);
        alphabetwhite.add(R.drawable.white_digite);
        alphabetwhite.add(R.drawable.white_digitf);
        alphabetwhite.add(R.drawable.white_digitg);
        alphabetwhite.add(R.drawable.white_digith);
        alphabetwhite.add(R.drawable.white_digiti);
        alphabetwhite.add(R.drawable.white_digitj);
        alphabetwhite.add(R.drawable.white_digitk);
        alphabetwhite.add(R.drawable.white_digitl);
        alphabetwhite.add(R.drawable.white_digitm);
        alphabetwhite.add(R.drawable.white_digitn);
        alphabetwhite.add(R.drawable.white_digito);
        alphabetwhite.add(R.drawable.white_digitp);
        alphabetwhite.add(R.drawable.white_digitq);
        alphabetwhite.add(R.drawable.white_digitr);
        alphabetwhite.add(R.drawable.white_digits);
        alphabetwhite.add(R.drawable.white_digitt);
        alphabetwhite.add(R.drawable.white_digitu);
        alphabetwhite.add(R.drawable.white_digitv);
        alphabetwhite.add(R.drawable.white_digitw);
        alphabetwhite.add(R.drawable.white_digitx);
        alphabetwhite.add(R.drawable.white_digity);
        alphabetwhite.add(R.drawable.white_digitz);
        setbtnphabet();
    }

    private void addgrayDigit() {
        digitgray.clear();
        digitgray.add(R.drawable.gray_digit0);
        digitgray.add(R.drawable.gray_digit1);
        digitgray.add(R.drawable.gray_digit2);
        digitgray.add(R.drawable.gray_digit3);
        digitgray.add(R.drawable.gray_digit4);
        digitgray.add(R.drawable.gray_digit5);
        digitgray.add(R.drawable.gray_digit6);
        digitgray.add(R.drawable.gray_digit7);
        digitgray.add(R.drawable.gray_digit8);
        digitgray.add(R.drawable.gray_digit9);
        getrandomgraydigitpos();
    }

    private void addWhiteDigit() {
        digitwhite.clear();
        digitwhite.add(R.drawable.white_digit0);
        digitwhite.add(R.drawable.white_digit1);
        digitwhite.add(R.drawable.white_digit2);
        digitwhite.add(R.drawable.white_digit3);
        digitwhite.add(R.drawable.white_digit4);
        digitwhite.add(R.drawable.white_digit5);
        digitwhite.add(R.drawable.white_digit6);
        digitwhite.add(R.drawable.white_digit7);
        digitwhite.add(R.drawable.white_digit8);
        digitwhite.add(R.drawable.white_digit9);
        setbtndigit();
    }

    private void randomposition() {
        digitrandom.clear();
        digitrandom.add(0);
        digitrandom.add(1);
        digitrandom.add(2);
        digitrandom.add(3);
        digitrandom.add(4);
        digitrandom.add(5);
        digitrandom.add(6);
        getrandomposition();
    }

    private void setbtnphabet() {

        if (alphabetwhite.size() >= 4) {
            alphabetnum = (new Random().nextInt(alphabetbtn.size()));
            alphabetbtn.get(alphabetnum).setBackgroundResource(alphabetwhite.get(alphabet));
            Log.e("alphabetButton", "" + alphabet);
            alphabetwhite.remove(alphabet);
            alphabetbtn.remove(alphabetnum);

            int img2 = (new Random().nextInt(alphabetbtn.size()));
            int num2 = (new Random().nextInt(alphabetwhite.size()));
            alphabetbtn.get(img2).setBackgroundResource(alphabetwhite.get(num2));
            alphabetwhite.remove(num2);
            alphabetbtn.remove(img2);

            int img3 = (new Random().nextInt(alphabetbtn.size()));
            int num3 = (new Random().nextInt(alphabetwhite.size()));
            alphabetbtn.get(img3).setBackgroundResource(alphabetwhite.get(num3));
            alphabetwhite.remove(num3);
            alphabetbtn.remove(img3);

            int img4 = (new Random().nextInt(alphabetbtn.size()));
            int num4 = (new Random().nextInt(alphabetwhite.size()));
            alphabetbtn.get(img4).setBackgroundResource(alphabetwhite.get(num4));
            alphabetwhite.remove(num4);
            alphabetbtn.remove(img4);
        } else {
            addWhitealphabet();
        }
    }

    private void setbtndigit() {

        if (digitwhite.size() >= 4) {
            digitnum = (new Random().nextInt(digitbtn.size()));
            digitbtn.get(digitnum).setBackgroundResource(digitwhite.get(number));
            Log.e("DigiteButton", "" + number);
            digitwhite.remove(number);
            digitbtn.remove(digitnum);

            int img2 = (new Random().nextInt(digitbtn.size()));
            int num2 = (new Random().nextInt(digitwhite.size()));
            digitbtn.get(img2).setBackgroundResource(digitwhite.get(num2));
            digitbtn.remove(img2);
            digitwhite.remove(num2);

            int img3 = (new Random().nextInt(digitbtn.size()));
            int num3 = (new Random().nextInt(digitwhite.size()));
            digitbtn.get(img3).setBackgroundResource(digitwhite.get(num3));
            digitbtn.remove(img3);
            digitwhite.remove(num3);

            int img4 = (new Random().nextInt(digitbtn.size()));
            int num4 = (new Random().nextInt(digitwhite.size()));
            digitbtn.get(img4).setBackgroundResource(digitwhite.get(num4));
            digitbtn.remove(img4);
            digitwhite.remove(num4);
        } else {
            addWhiteDigit();
        }

    }

    private void getrandomgreenalphabetpos() {
        if (alphabetgreen.size() > 0) {
            alphabet = (new Random().nextInt(alphabetgreen.size()));
            Log.e("alphabet", "" + alphabet);
        } else {
            addgreenalphabet();
        }
    }

    private void getrandomgraydigitpos() {

        if (digitgray.size() > 0) {
            number = (new Random().nextInt(digitgray.size()));
            Log.e("Digite", "" + number);
        } else {
            addgrayDigit();
        }
    }

    private void getrandomposition() {
        if (digitrandom.size() > 2) {
            int alphapos = (new Random().nextInt(digitrandom.size()));
            alphabetposition = digitrandom.get(alphapos);
            digitrandom.remove(alphapos);

            int digitpos = (new Random().nextInt(digitrandom.size()));
            digitposition = digitrandom.get(digitpos);
            digitrandom.remove(digitpos);
        } else {
            randomposition();
        }
        loadingAmin();
    }

    private void loadingAmin() {
        addgrayalphabet();
        score.setText("" + refrence.getBlink());
        final Animation pokeLoadingAnim = new Animation() {
            float step = 0;
            int ab = 0;

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                // TODO Auto-generated method stub
                super.applyTransformation(interpolatedTime, t);
                if (interpolatedTime == 0) {
                    step = 1;
                }

                if ((interpolatedTime /0.125) >= step) {
                    Log.e("bvalueeess",step+ "  "+ab+"  "+levels+"  "+multiplierval);
                    if (ab == 0) {
                        if (alphabetposition == 0) {
                            blinkimage.setBackgroundResource(alphabetgreen.get(alphabet));
                            alphabetgreen.remove(alphabet);
                        } else if (digitposition == 0) {
                            blinkimage.setBackgroundResource(digitgray.get(number));
                            digitgray.remove(number);
                        } else {
                            int pos = (new Random().nextInt(alphabetgray.size()));
                            blinkimage.setBackgroundResource(alphabetgray.get(pos));
                            alphabetgray.remove(pos);
                        }
                        ab = 1;
                    } else if (ab == 1) {
                        if (alphabetposition == 1) {
                            blinkimage.setBackgroundResource(alphabetgreen.get(alphabet));
                            alphabetgreen.remove(alphabet);
                        } else if (digitposition == 1) {
                            blinkimage.setBackgroundResource(digitgray.get(number));
                            digitgray.remove(number);
                        } else {
                            int pos = (new Random().nextInt(alphabetgray.size()));
                            blinkimage.setBackgroundResource(alphabetgray.get(pos));
                            alphabetgray.remove(pos);
                        }
                        ab = 2;
                    } else if (ab == 2) {
                        if (alphabetposition == 2) {
                            blinkimage.setBackgroundResource(alphabetgreen.get(alphabet));
                            alphabetgreen.remove(alphabet);
                        } else if (digitposition == 2) {
                            blinkimage.setBackgroundResource(digitgray.get(number));
                            digitgray.remove(number);
                        } else {
                            int pos = (new Random().nextInt(alphabetgray.size()));
                            blinkimage.setBackgroundResource(alphabetgray.get(pos));
                            alphabetgray.remove(pos);
                        }
                        ab = 3;
                    } else if (ab == 3) {
                        if (alphabetposition == 3) {
                            blinkimage.setBackgroundResource(alphabetgreen.get(alphabet));
                            alphabetgreen.remove(alphabet);
                        } else if (digitposition == 3) {
                            blinkimage.setBackgroundResource(digitgray.get(number));
                            digitgray.remove(number);
                        } else {
                            int pos = (new Random().nextInt(alphabetgray.size()));
                            blinkimage.setBackgroundResource(alphabetgray.get(pos));
                            alphabetgray.remove(pos);
                        }
                        ab = 4;
                    } else if (ab == 4) {
                        if (alphabetposition == 4) {
                            blinkimage.setBackgroundResource(alphabetgreen.get(alphabet));
                            alphabetgreen.remove(alphabet);
                        } else if (digitposition == 4) {
                            blinkimage.setBackgroundResource(digitgray.get(number));
                            digitgray.remove(number);
                        } else {
                            int pos = (new Random().nextInt(alphabetgray.size()));
                            blinkimage.setBackgroundResource(alphabetgray.get(pos));
                            alphabetgray.remove(pos);
                        }
                        ab = 5;
                    } else if (ab == 5) {
                        if (alphabetposition == 5) {
                            blinkimage.setBackgroundResource(alphabetgreen.get(alphabet));
                            alphabetgreen.remove(alphabet);
                        } else if (digitposition == 5) {
                            blinkimage.setBackgroundResource(digitgray.get(number));
                            digitgray.remove(number);
                        } else {
                            int pos = (new Random().nextInt(alphabetgray.size()));
                            blinkimage.setBackgroundResource(alphabetgray.get(pos));
                            alphabetgray.remove(pos);
                        }
                        ab = 6;
                    } else if (ab == 6) {
                        if (alphabetposition == 6) {
                            blinkimage.setBackgroundResource(alphabetgreen.get(alphabet));
                            alphabetgreen.remove(alphabet);
                        } else if (digitposition == 6) {
                            blinkimage.setBackgroundResource(digitgray.get(number));
                            digitgray.remove(number);
                        } else {
                            int pos = (new Random().nextInt(alphabetgray.size()));
                            blinkimage.setBackgroundResource(alphabetgray.get(pos));
                            alphabetgray.remove(pos);
                        }
                        ab = 7;
                    } else if (ab == 7) {
                        blinkimage.setBackgroundResource(0);
                        ab = 0;
                    }
                    ++step;
                }
            }
        };
        pokeLoadingAnim.setDuration(levels);
        pokeLoadingAnim.setInterpolator(new LinearInterpolator());
        blinkimage.startAnimation(pokeLoadingAnim);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button1) {
            if (alphabetnum == 0) {
                ((LinearLayout) findViewById(R.id.button1)).
                        setBackgroundResource(R.drawable.rectanglebtn1);
                rightalphabet = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.button2) {
            if (alphabetnum == 1) {
                ((LinearLayout) findViewById(R.id.button2)).
                        setBackgroundResource(R.drawable.rectanglebtn1);
                rightalphabet = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.button3) {
            if (alphabetnum == 2) {
                ((LinearLayout) findViewById(R.id.button3)).
                        setBackgroundResource(R.drawable.rectanglebtn1);
                rightalphabet = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.button4) {
            if (alphabetnum == 3) {
                ((LinearLayout) findViewById(R.id.button4)).
                        setBackgroundResource(R.drawable.rectanglebtn1);
                rightalphabet = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.button5) {
            if (digitnum == 0) {
                ((LinearLayout) findViewById(R.id.button5)).
                        setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.button6) {
            if (digitnum == 1) {
                ((LinearLayout) findViewById(R.id.button6)).
                        setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.button7) {
            if (digitnum == 2) {
                ((LinearLayout) findViewById(R.id.button7)).
                        setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.button8) {
            if (digitnum == 3) {
                ((LinearLayout) findViewById(R.id.button8)).
                        setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        }

    }


    private void rightanswer() {
        if (rightdigit && rightalphabet) {
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
                        refrence.setBlink(refrence.getBlink() + (100*multiplierval));
                        process();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rightanswer.setVisibility(View.GONE);
                        rightans.setVisibility(View.GONE);
                        rightval=rightval+1;
                        nextvalue();
                        rightdigit = false;
                        rightalphabet = false;
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
                    rightdigit = false;
                    rightalphabet = false;
                    if(progress.getProgress()<50){
                        for(int i=progress.getProgress();i>=0;i--){
                            progress.setProgress(i);
                        }
                    }else{
                        for(int i=progress.getProgress();i>=50;i--){
                            progress.setProgress(i);
                        }
                    }
                    lifevalue=lifevalue+1;
                    wrongval=wrongval+1;
                    life();
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


    private void nextvalue() {
        Log.e("multiplierval",""+multiplierval);
        if(multiplierval==2){
            levels=3000;
        }else if(multiplierval==3){
            levels=2000;
        }else if(multiplierval==4){
            levels=1000;
        }else if(multiplierval==5){
            levels=900;
        }else if(multiplierval==6){
            levels=800;
        }else if(multiplierval>=7){
            levels=700;
        }
        nextque();
    }

    private int lifevalue=0;
    private void life(){
        if(lifevalue==1) {
            ((ImageView) findViewById(R.id.imageview1)).setBackgroundResource(R.drawable.life_brain);
            nextvalue();
        }else if(lifevalue==2){
            ((ImageView) findViewById(R.id.imageview2)).setBackgroundResource(R.drawable.life_brain);
            nextvalue();
        }else if(lifevalue==3){
            ((ImageView) findViewById(R.id.imageview3)).setBackgroundResource(R.drawable.life_brain);
            gameover();
        }
    }


    private void nextque() {
        init();
        addgreenalphabet();
        addgrayDigit();
        getrandomposition();
        button();
        addWhitealphabet();
        addWhiteDigit();
    }

    int wrongval=0,rightval=0;
    private void gameover() {
        refrence.setGame("Blink");
        refrence.setScore(refrence.getBlink());
        refrence.setMultiplier(multiplierval);
        int total=wrongval+rightval;
        refrence.setAccuracy((int)((rightval*100)/total));
        Intent intent=new Intent(this, GameOver.class);
        startActivity(intent);
        this.finish();
    }

    private void button() {
        ((LinearLayout) findViewById(R.id.button1)).setBackgroundResource(R.color.green);
        ((LinearLayout) findViewById(R.id.button2)).setBackgroundResource(R.color.green);
        ((LinearLayout) findViewById(R.id.button3)).setBackgroundResource(R.color.green);
        ((LinearLayout) findViewById(R.id.button4)).setBackgroundResource(R.color.green);
        ((LinearLayout) findViewById(R.id.button5)).setBackgroundResource(R.color.gray);
        ((LinearLayout) findViewById(R.id.button6)).setBackgroundResource(R.color.gray);
        ((LinearLayout) findViewById(R.id.button7)).setBackgroundResource(R.color.gray);
        ((LinearLayout) findViewById(R.id.button8)).setBackgroundResource(R.color.gray);
    }
}
