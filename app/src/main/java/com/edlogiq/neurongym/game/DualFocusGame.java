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
import com.edlogiq.neurongym.howtoplay.HowToPlayDualFocuse;
import com.edlogiq.neurongym.neurongym.GameOver;
import com.edlogiq.neurongym.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DualFocusGame extends Activity implements View.OnClickListener {

    public int StartTime = 45;
    int ab = 0;
    Timer timer;
    TimerTask mTimerTask;

    Handler handler = null;
    private ImageView image, image1, image2, image3, image4, image5, image6, image7, image8,
            image9, image10, image11, image12, image13, image14, image15, image16, image17, image18,
            image19, image20, image21, image22, image23, image24, image25, image26, image27, image28,
            image29, image30, image31, image32;


    private LinearLayout imglayoute1, imglayoute2, imglayoute3, imglayoute4, imglayoute5, imglayoute6,
            imglayoute7, imglayoute8, imglayoute9, imglayoute10, imglayoute11, imglayoute12, imglayoute13,
            imglayoute14, imglayoute15, imglayoute16, imglayoute17, imglayoute18, imglayoute19, imglayoute20,
            imglayoute21, imglayoute22, imglayoute23, imglayoute24, imglayoute25, imglayoute26, imglayoute27,
            imglayoute28, imglayoute29, imglayoute30, imglayoute31, imglayoute32;
    private ArrayList<LinearLayout> imagelayoute = new ArrayList<>();
    private ArrayList<ImageView> imagelist = new ArrayList<ImageView>();
    private TextView digittext, timertext, scoretext;
    private int digit, imageval;
    private boolean truedigit = false, trueimage = false;
    private RelativeLayout pausepopup;
    private LinearLayout layoute0, layoute1, layoute2, layoute3, layoute4, layoute5, layoute6, layoute7,
            layoute8, layoute9, rightanswer, wronganswerval;
    private ImageView rightans, wrongans;
    private RefrenceWrapper refrence;
    private ArrayList<Integer> showimage = new ArrayList<>();
    private boolean rightdigit = false, rightimage = false;
    private ProgressBar progress;
    private int levels=400;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_focus_game);

        ((RelativeLayout)findViewById(R.id.dualfocusgame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.dualfocusgame)).setBackgroundResource(R.color.black);
        }

        handler = new Handler();
        refrence = RefrenceWrapper.getRefrenceWrapper(this);
        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswer = (LinearLayout) findViewById(R.id.righttick);
        wronganswerval = (LinearLayout) findViewById(R.id.wrongcross);
        digittext = (TextView) findViewById(R.id.imageviewquestion);
        timertext = (TextView) findViewById(R.id.timertextview);
        scoretext = (TextView) findViewById(R.id.scoretextview);
        refrence.setDualfocus(0);

        showimage.add(R.drawable.image34);
        showimage.add(R.drawable.image42);
        showimage.add(R.drawable.image35);
        showimage.add(R.drawable.image39);

        imagerefrence();
        btnlayoutrefrence();
        imagelayoutrefrence();
        addimagelist();

        gamestart();
        timer = new Timer();
        startTimer();
        popuplayout();
    }

    private void imagelayoutrefrence() {

        imglayoute1 = (LinearLayout) findViewById(R.id.columnlayout11);
        imglayoute2 = (LinearLayout) findViewById(R.id.columnlayout12);
        imglayoute3 = (LinearLayout) findViewById(R.id.columnlayout13);
        imglayoute4 = (LinearLayout) findViewById(R.id.columnlayout14);
        imglayoute5 = (LinearLayout) findViewById(R.id.columnlayout15);
        imglayoute6 = (LinearLayout) findViewById(R.id.columnlayout16);
        imglayoute7 = (LinearLayout) findViewById(R.id.columnlayout21);
        imglayoute8 = (LinearLayout) findViewById(R.id.columnlayout22);
        imglayoute9 = (LinearLayout) findViewById(R.id.columnlayout23);
        imglayoute10 = (LinearLayout) findViewById(R.id.columnlayout24);
        imglayoute11 = (LinearLayout) findViewById(R.id.columnlayout25);
        imglayoute12 = (LinearLayout) findViewById(R.id.columnlayout26);
        imglayoute13 = (LinearLayout) findViewById(R.id.columnlayout31);
        imglayoute14 = (LinearLayout) findViewById(R.id.columnlayout32);
        imglayoute15 = (LinearLayout) findViewById(R.id.columnlayout33);
        imglayoute16 = (LinearLayout) findViewById(R.id.columnlayout34);
        imglayoute17 = (LinearLayout) findViewById(R.id.columnlayout41);
        imglayoute18 = (LinearLayout) findViewById(R.id.columnlayout42);
        imglayoute19 = (LinearLayout) findViewById(R.id.columnlayout43);
        imglayoute20 = (LinearLayout) findViewById(R.id.columnlayout44);
        imglayoute21 = (LinearLayout) findViewById(R.id.columnlayout51);
        imglayoute22 = (LinearLayout) findViewById(R.id.columnlayout52);
        imglayoute23 = (LinearLayout) findViewById(R.id.columnlayout53);
        imglayoute24 = (LinearLayout) findViewById(R.id.columnlayout54);
        imglayoute25 = (LinearLayout) findViewById(R.id.columnlayout55);
        imglayoute26 = (LinearLayout) findViewById(R.id.columnlayout56);
        imglayoute27 = (LinearLayout) findViewById(R.id.columnlayout61);
        imglayoute28 = (LinearLayout) findViewById(R.id.columnlayout62);
        imglayoute29 = (LinearLayout) findViewById(R.id.columnlayout63);
        imglayoute30 = (LinearLayout) findViewById(R.id.columnlayout64);
        imglayoute31 = (LinearLayout) findViewById(R.id.columnlayout65);
        imglayoute32 = (LinearLayout) findViewById(R.id.columnlayout66);

        imglayoute1.setOnClickListener(listener);
        imglayoute2.setOnClickListener(listener);
        imglayoute3.setOnClickListener(listener);
        imglayoute4.setOnClickListener(listener);
        imglayoute5.setOnClickListener(listener);
        imglayoute6.setOnClickListener(listener);
        imglayoute7.setOnClickListener(listener);
        imglayoute8.setOnClickListener(listener);
        imglayoute9.setOnClickListener(listener);
        imglayoute10.setOnClickListener(listener);
        imglayoute11.setOnClickListener(listener);
        imglayoute12.setOnClickListener(listener);
        imglayoute13.setOnClickListener(listener);
        imglayoute14.setOnClickListener(listener);
        imglayoute15.setOnClickListener(listener);
        imglayoute16.setOnClickListener(listener);
        imglayoute17.setOnClickListener(listener);
        imglayoute18.setOnClickListener(listener);
        imglayoute19.setOnClickListener(listener);
        imglayoute20.setOnClickListener(listener);
        imglayoute21.setOnClickListener(listener);
        imglayoute22.setOnClickListener(listener);
        imglayoute23.setOnClickListener(listener);
        imglayoute24.setOnClickListener(listener);
        imglayoute25.setOnClickListener(listener);
        imglayoute26.setOnClickListener(listener);
        imglayoute27.setOnClickListener(listener);
        imglayoute28.setOnClickListener(listener);
        imglayoute29.setOnClickListener(listener);
        imglayoute30.setOnClickListener(listener);
        imglayoute31.setOnClickListener(listener);
        imglayoute32.setOnClickListener(listener);
    }

    private void btnlayoutrefrence() {
        layoute0 = ((LinearLayout) findViewById(R.id.digitlayout0));
        layoute1 = ((LinearLayout) findViewById(R.id.digitlayout1));
        layoute2 = ((LinearLayout) findViewById(R.id.digitlayout2));
        layoute3 = ((LinearLayout) findViewById(R.id.digitlayout3));
        layoute4 = ((LinearLayout) findViewById(R.id.digitlayout4));
        layoute5 = ((LinearLayout) findViewById(R.id.digitlayout5));
        layoute6 = ((LinearLayout) findViewById(R.id.digitlayout6));
        layoute7 = ((LinearLayout) findViewById(R.id.digitlayout7));
        layoute8 = ((LinearLayout) findViewById(R.id.digitlayout8));
        layoute9 = ((LinearLayout) findViewById(R.id.digitlayout9));

        layoute0.setOnClickListener(this);
        layoute1.setOnClickListener(this);
        layoute2.setOnClickListener(this);
        layoute3.setOnClickListener(this);
        layoute4.setOnClickListener(this);
        layoute5.setOnClickListener(this);
        layoute6.setOnClickListener(this);
        layoute7.setOnClickListener(this);
        layoute8.setOnClickListener(this);
        layoute9.setOnClickListener(this);
    }

    private void imagerefrence() {
        image1 = (ImageView) findViewById(R.id.imagecolumn11);
        image2 = (ImageView) findViewById(R.id.imagecolumn12);
        image3 = (ImageView) findViewById(R.id.imagecolumn13);
        image4 = (ImageView) findViewById(R.id.imagecolumn14);
        image5 = (ImageView) findViewById(R.id.imagecolumn15);
        image6 = (ImageView) findViewById(R.id.imagecolumn16);
        image7 = (ImageView) findViewById(R.id.imagecolumn21);
        image8 = (ImageView) findViewById(R.id.imagecolumn22);
        image9 = (ImageView) findViewById(R.id.imagecolumn23);
        image10 = (ImageView) findViewById(R.id.imagecolumn24);
        image11 = (ImageView) findViewById(R.id.imagecolumn25);
        image12 = (ImageView) findViewById(R.id.imagecolumn26);
        image13 = (ImageView) findViewById(R.id.imagecolumn31);
        image14 = (ImageView) findViewById(R.id.imagecolumn32);
        image15 = (ImageView) findViewById(R.id.imagecolumn33);
        image16 = (ImageView) findViewById(R.id.imagecolumn34);
        image17 = (ImageView) findViewById(R.id.imagecolumn41);
        image18 = (ImageView) findViewById(R.id.imagecolumn42);
        image19 = (ImageView) findViewById(R.id.imagecolumn43);
        image20 = (ImageView) findViewById(R.id.imagecolumn44);
        image21 = (ImageView) findViewById(R.id.imagecolumn51);
        image22 = (ImageView) findViewById(R.id.imagecolumn52);
        image23 = (ImageView) findViewById(R.id.imagecolumn53);
        image24 = (ImageView) findViewById(R.id.imagecolumn54);
        image25 = (ImageView) findViewById(R.id.imagecolumn55);
        image26 = (ImageView) findViewById(R.id.imagecolumn56);
        image27 = (ImageView) findViewById(R.id.imagecolumn61);
        image28 = (ImageView) findViewById(R.id.imagecolumn62);
        image29 = (ImageView) findViewById(R.id.imagecolumn63);
        image30 = (ImageView) findViewById(R.id.imagecolumn64);
        image31 = (ImageView) findViewById(R.id.imagecolumn65);
        image32 = (ImageView) findViewById(R.id.imagecolumn66);
    }

    //    All value of popup layout.....
    private void popuplayout() {
        ((LinearLayout) findViewById(R.id.layoutepause)).setOnClickListener(listner);

        pausepopup = (RelativeLayout) findViewById(R.id.pausepopup);
        ((LinearLayout) findViewById(R.id.resume)).setOnClickListener(listner);
        ((LinearLayout) findViewById(R.id.replay)).setOnClickListener(listner);
        ((LinearLayout) findViewById(R.id.instructions)).setOnClickListener(listner);
        ((LinearLayout) findViewById(R.id.sound)).setOnClickListener(listner);
        ((LinearLayout) findViewById(R.id.exitgame)).setOnClickListener(listner);
    }

    private void addimagelist() {

        imagelist.add(image1);
        imagelist.add(image2);
        imagelist.add(image3);
        imagelist.add(image4);
        imagelist.add(image5);
        imagelist.add(image6);
        imagelist.add(image7);
        imagelist.add(image8);
        imagelist.add(image9);
        imagelist.add(image10);
        imagelist.add(image11);
        imagelist.add(image12);
        imagelist.add(image13);
        imagelist.add(image14);
        imagelist.add(image15);
        imagelist.add(image16);
        imagelist.add(image17);
        imagelist.add(image18);
        imagelist.add(image19);
        imagelist.add(image20);
        imagelist.add(image21);
        imagelist.add(image22);
        imagelist.add(image23);
        imagelist.add(image24);
        imagelist.add(image25);
        imagelist.add(image26);
        imagelist.add(image27);
        imagelist.add(image28);
        imagelist.add(image29);
        imagelist.add(image30);
        imagelist.add(image31);
        imagelist.add(image32);
    }

    int showimageval;
    private void gamestart() {
        resetbtn();
        truedigit = false;
        scoretext.setText("" + refrence.getDualfocus());
        digit = (new Random().nextInt(10));
        if (imagelist.size() != 0) {
            imageval = (new Random().nextInt(imagelist.size()));
        } else {
            addimagelist();
            imageval = (new Random().nextInt(imagelist.size()));
        }
        image = imagelist.get(imageval);
//        if (digittext.getVisibility() == View.GONE) {
//            digittext.setVisibility(View.VISIBLE);
//        }
        digittext.setText("" + digit);

         showimageval = (new Random().nextInt(showimage.size()));



        final Animation blinkanim=new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);

                if(interpolatedTime<0.50){
                    digittext.setText("" + digit);

                    image.setBackgroundResource(showimage.get(showimageval));
                }else{
                    image.setBackgroundResource(0);
                    digittext.setText(" ");
                }
            }
        };

        blinkanim.setDuration(levels);
        blinkanim.setInterpolator(new LinearInterpolator());
        image.startAnimation(blinkanim);
    }

    private void resetbtn() {

        layoute0.setBackgroundResource(R.drawable.rectanglebtn);
        layoute1.setBackgroundResource(R.drawable.rectanglebtn);
        layoute2.setBackgroundResource(R.drawable.rectanglebtn);
        layoute3.setBackgroundResource(R.drawable.rectanglebtn);
        layoute4.setBackgroundResource(R.drawable.rectanglebtn);
        layoute5.setBackgroundResource(R.drawable.rectanglebtn);
        layoute6.setBackgroundResource(R.drawable.rectanglebtn);
        layoute7.setBackgroundResource(R.drawable.rectanglebtn);
        layoute8.setBackgroundResource(R.drawable.rectanglebtn);
        layoute9.setBackgroundResource(R.drawable.rectanglebtn);

        imglayoute1.setBackgroundResource(R.drawable.grey);
        imglayoute2.setBackgroundResource(R.drawable.grey);
        imglayoute3.setBackgroundResource(R.drawable.grey);
        imglayoute4.setBackgroundResource(R.drawable.grey);
        imglayoute5.setBackgroundResource(R.drawable.grey);
        imglayoute6.setBackgroundResource(R.drawable.grey);
        imglayoute7.setBackgroundResource(R.drawable.grey);
        imglayoute8.setBackgroundResource(R.drawable.grey);
        imglayoute9.setBackgroundResource(R.drawable.grey);
        imglayoute10.setBackgroundResource(R.drawable.grey);
        imglayoute11.setBackgroundResource(R.drawable.grey);
        imglayoute12.setBackgroundResource(R.drawable.grey);
        imglayoute13.setBackgroundResource(R.drawable.grey);
        imglayoute14.setBackgroundResource(R.drawable.grey);
        imglayoute15.setBackgroundResource(R.drawable.grey);
        imglayoute16.setBackgroundResource(R.drawable.grey);
        imglayoute17.setBackgroundResource(R.drawable.grey);
        imglayoute18.setBackgroundResource(R.drawable.grey);
        imglayoute19.setBackgroundResource(R.drawable.grey);
        imglayoute20.setBackgroundResource(R.drawable.grey);
        imglayoute21.setBackgroundResource(R.drawable.grey);
        imglayoute22.setBackgroundResource(R.drawable.grey);
        imglayoute23.setBackgroundResource(R.drawable.grey);
        imglayoute24.setBackgroundResource(R.drawable.grey);
        imglayoute25.setBackgroundResource(R.drawable.grey);
        imglayoute26.setBackgroundResource(R.drawable.grey);
        imglayoute27.setBackgroundResource(R.drawable.grey);
        imglayoute28.setBackgroundResource(R.drawable.grey);
        imglayoute29.setBackgroundResource(R.drawable.grey);
        imglayoute30.setBackgroundResource(R.drawable.grey);
        imglayoute31.setBackgroundResource(R.drawable.grey);
        imglayoute32.setBackgroundResource(R.drawable.grey);


    }

    private void pausepopup() {
        if (pausepopup.getVisibility() == View.GONE) {
            pausepopup.setVisibility(View.VISIBLE);
        } else {
            pausepopup.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.digitlayout0) {
            if (digit == 0) {
                layoute0.setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.digitlayout1) {
            if (digit == 1) {
                layoute1.setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.digitlayout2) {
            if (digit == 2) {
                layoute2.setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.digitlayout3) {
            if (digit == 3) {
                layoute3.setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.digitlayout4) {
            if (digit == 4) {
                layoute4.setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.digitlayout5) {
            if (digit == 5) {
                layoute5.setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.digitlayout6) {
            if (digit == 6) {
                layoute6.setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.digitlayout7) {
            if (digit == 7) {
                layoute7.setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.digitlayout8) {
            if (digit == 8) {
                layoute8.setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        } else if (v.getId() == R.id.digitlayout9) {
            if (digit == 9) {
                layoute9.setBackgroundResource(R.drawable.rectanglebtn1);
                rightdigit = true;
                rightanswer();
            } else {
                wronganswer();
            }
        }

    }

    View.OnClickListener listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.layoutepause) {
                if (mTimerTask != null) {
                    mTimerTask.cancel();
                }
                pausepopup();
            } else if (v.getId() == R.id.resume) {
                startTimer();
                pausepopup();
            } else if (v.getId() == R.id.replay) {
                startTimer();
                StartTime = 45;
                pausepopup();
                gamestart();
            } else if (v.getId() == R.id.instructions) {
                Intent intent = new Intent(DualFocusGame.this, HowToPlayDualFocuse.class);
                startActivity(intent);
                DualFocusGame.this.finish();
                pausepopup();
            } else if (v.getId() == R.id.sound) {

            } else if (v.getId() == R.id.exitgame) {
                DualFocusGame.this.finish();
            }

        }
    };

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.columnlayout11) {
                if (image1 == image) {
                    imglayoute1.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }

            } else if (v.getId() == R.id.columnlayout12) {
                if (image2 == image) {
                    imglayoute2.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout13) {
                if (image3 == image) {
                    imglayoute3.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout14) {
                if (image4 == image) {
                    imglayoute4.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout15) {
                if (image5 == image) {
                    imglayoute5.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout16) {
                if (image6 == image) {
                    imglayoute6.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout21) {
                if (image7 == image) {
                    imglayoute7.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout22) {
                if (image8 == image) {
                    imglayoute8.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout23) {
                if (image9 == image) {
                    imglayoute9.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout24) {
                if (image10 == image) {
                    imglayoute10.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout25) {
                if (image11 == image) {
                    imglayoute11.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout26) {
                if (image12 == image) {
                    imglayoute12.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout31) {
                if (image13 == image) {
                    imglayoute13.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout32) {
                if (image14 == image) {
                    imglayoute14.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout33) {
                if (image15 == image) {
                    imglayoute15.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout34) {
                if (image16 == image) {
                    imglayoute16.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout41) {
                if (image17 == image) {
                    imglayoute17.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout42) {
                if (image18 == image) {
                    imglayoute18.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout43) {
                if (image19 == image) {
                    imglayoute19.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout44) {
                if (image20 == image) {
                    imglayoute20.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout51) {
                if (image21 == image) {
                    imglayoute21.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout52) {
                if (image22 == image) {
                    imglayoute22.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout53) {
                if (image23 == image) {
                    imglayoute23.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout54) {
                if (image24 == image) {
                    imglayoute24.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout55) {
                if (image25 == image) {
                    imglayoute25.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout56) {
                if (image26 == image) {
                    imglayoute26.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout61) {
                if (image27 == image) {
                    imglayoute27.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout62) {
                if (image28 == image) {
                    imglayoute28.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout63) {
                if (image29 == image) {
                    imglayoute29.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout64) {
                if (image30 == image) {
                    imglayoute30.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout65) {
                if (image31 == image) {
                    imglayoute31.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            } else if (v.getId() == R.id.columnlayout66) {
                if (image32 == image) {
                    imglayoute32.setBackgroundResource(R.drawable.cyan);
                    imagelist.remove(imageval);
                    rightimage = true;
                    rightanswer();
                } else {
                    wronganswer();
                }
            }

        }
    };
    int prog=0;
    private void rightanswer() {
        if (rightdigit && rightimage) {
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
                        refrence.setDualfocus(refrence.getDualfocus() + (100*multiplierval));
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rightanswer.setVisibility(View.GONE);
                        rightans.setVisibility(View.GONE);
                        rightdigit = false;
                        rightimage = false;
                        rightval=rightval+1;
                        process();
                        nextque();

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
                    rightdigit = false;
                    rightimage = false;
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
                    nextque();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }
    int multyplyer=0,multiplierval=1;
    private void process(){
        for(int i=prog;i<=(prog+10);i++) {
            progress.setProgress(i);
        }

        prog=prog+15;
        if(prog==60){
            ((LinearLayout)findViewById(R.id.processlayoute2)).
                    setBackgroundResource(R.drawable.circle_text_cyan);
            ((TextView) findViewById(R.id.processtext2)).setTextColor(Color.parseColor("#ffffff"));
            multiplierval=multiplierval+1;

        }
        if(prog==120){
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

    private void nextque() {
        Log.e("multiplierval",""+multiplierval);
       if(multiplierval==2){
            levels=200;
        }else if(multiplierval==3){
            levels=100;
        }else if(multiplierval==4){
            levels=50;
        }else if(multiplierval==5){
            levels=30;
        }else if(multiplierval==6){
            levels=20;
        }else if(multiplierval>=7){
            levels=10;
        }
        gamestart();
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
  int wrongval=0,rightval=0;
    private void gameover() {
        refrence.setGame("DualFocus");
        refrence.setScore(refrence.getDualfocus());
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
