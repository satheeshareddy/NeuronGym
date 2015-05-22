package com.edlogiq.neurongym.game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
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
import java.util.Timer;
import java.util.TimerTask;

public class TrackTheRouteGame extends Activity implements View.OnClickListener{

    CanvasTrackTheRoute canvas;
    Paint mPaint;
    private LinearLayout layout,canvaslay;
    private RelativeLayout pausepopup;
    private int idval;
    private LinearLayout image;
    private TextView  score;
    private ArrayList<LinearLayout> textview = new ArrayList<LinearLayout>();
    private ArrayList<LinearLayout> pointval = new ArrayList<LinearLayout>();
    private ArrayList<Integer> trackX=new ArrayList<>();
    private ArrayList<Integer> trackY=new ArrayList<>();
    int[] posXY;
    int width,hight;
    private ArrayList<Integer> ballvalue=new ArrayList<>();
    private ArrayList<Integer> pointvalue=new ArrayList<>();
    private ArrayList<Integer> Line1=new ArrayList<>();
    private ArrayList<Integer> Line2=new ArrayList<>();
    private ArrayList<Integer> Line3=new ArrayList<>();
    private ArrayList<Integer> Line4=new ArrayList<>();
    private ArrayList<Integer> Line5=new ArrayList<>();
    private ArrayList<Integer> Line6=new ArrayList<>();
    private ImageView rightans,wrongans;
    private LinearLayout  rightanswer, wronganswer;
    private RefrenceWrapper refrence;
    private ProgressBar progress;
    private TextView text;
    int val1=40,val2=40,val3=40,val4=40,val5=40,val6=40,val7=40,val8=40,val9=40,val10=40,val11=40,val12=40,
            val13=40,val14=40,val15=40,val16=40,val17=40,val18=40,val19=40,val20=40,val21=40,val22=40,val23=40,val24=40;
    int lineval1,lineval2,lineval3,lineval4,lineval5=40,lineval6=40;

    private Integer gameImage[] = {R.drawable.track1, R.drawable.track2, R.drawable.track3, R.drawable.track4,
            R.drawable.track5, R.drawable.track6, R.drawable.track7, R.drawable.track8, R.drawable.track9,
            R.drawable.track10, R.drawable.track11, R.drawable.track12, R.drawable.track13, R.drawable.track14,
            R.drawable.track15, R.drawable.track16, R.drawable.track17, R.drawable.track18, R.drawable.track19,
            R.drawable.track20, R.drawable.track21, R.drawable.track22, R.drawable.track23, R.drawable.track24};

    private ArrayList<Integer> randomimage = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_the_route_game);

        ((RelativeLayout)findViewById(R.id.tracktheroughtgame)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.tracktheroughtgame)).setBackgroundResource(R.color.black);
        }
        score=(TextView)findViewById(R.id.scoretextview);
        score.setText(""+0);
        progress=(ProgressBar)findViewById(R.id.progressBar);
        progress.setProgress(0);
        refrence = RefrenceWrapper.getRefrenceWrapper(this);
        refrence.setTracktherought(0);
        rightans = (ImageView) findViewById(R.id.imageright);
        wrongans = (ImageView) findViewById(R.id.imagewrong);
        rightanswer = (LinearLayout) findViewById(R.id.righttick);
        wronganswer = (LinearLayout) findViewById(R.id.wrongcross);
        text=(TextView)findViewById(R.id.textview);
        layout=((LinearLayout)findViewById(R.id.canaslayout));
        canvaslay=((LinearLayout)findViewById(R.id.canvas));
        posXY = new int[2];

        width= (this.getWindowManager().getDefaultDisplay().getWidth())-40;
        hight=width;
        canvas = new CanvasTrackTheRoute(this, null, (width/6)/2, "#d49ddf");
        canvaslay.addView(canvas);

        gamelayout(6, 6);
        popuplayout();

    }

    private void popuplayout() {
        ((LinearLayout) findViewById(R.id.layoutepause)).setOnClickListener(this);

        pausepopup = (RelativeLayout) findViewById(R.id.pausepopup);
        ((LinearLayout) findViewById(R.id.resume)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.replay)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.instructions)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.sound)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.exitgame)).setOnClickListener(this);
    }


    private void gamelayout(int row, int column) {
        score.setText(""+refrence.getTracktherought());
        levelval();
        ballvalue.clear();
        pointvalue.clear();
        textview.clear();
        idval = 0;
        layout.removeAllViews();
        getrandomimage();

        for (int i = 0; i < row; i++) {

            LinearLayout rowlayout = new LinearLayout(this);
            rowlayout.setOrientation(LinearLayout.HORIZONTAL);
            rowlayout.setGravity(Gravity.CENTER_HORIZONTAL);

            for (int j = 0; j < column; j++) {

                LinearLayout layoutbox = new LinearLayout(this);
                LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(width/column, hight/row);
                layoutbox.setLayoutParams(Params1);
                layoutbox.setGravity(Gravity.CENTER);

                image = new LinearLayout(this);
                LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams((width/column)/2, (hight/row)/2);
                image.setGravity(Gravity.CENTER);
                image.setLayoutParams(imageParams);

//                if (idval==val1 ||idval==val2 ||idval==val3 ||idval==val4 ||idval==val5 ||idval==val6 ||idval==val7
//                        ||idval==val8||idval==val9||idval==val10||idval==val11||idval==val12||idval==val13
//                        ||idval==val14||idval==val15||idval==val16||idval==val17||idval==val18||idval==val19
//                        ||idval==val20||idval==val21||idval==val22||idval==val23||idval==val24) {
                if(idval==lineval1 || idval==lineval2 || idval==lineval3 || idval==lineval4 || idval==lineval5
                        || idval==lineval6){
                    image.setBackgroundResource(R.drawable.grey);
                    ImageView img=new ImageView(this);
                    LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams((width/column)/2, (hight/row)/2);

                    img.setLayoutParams(imgParams);
                    img.setBackgroundResource(randomimage.get(idval));
                    image.addView(img);
                    if (!textview.contains(image)) {
                        textview.add(image);
                    }

                } else {
                    image.setBackgroundResource(0);

                }
                layoutbox.addView(image);
                rowlayout.addView(layoutbox);

                idval++;

            }
            layout.addView(rowlayout);
        }
        startgame();
    }


    private void getrandomimage() {
        randomimage.clear();
        while (randomimage.size() < 24) {
            int random = (new Random().nextInt(gameImage.length));
            if (!randomimage.contains(gameImage[random])) {
                randomimage.add(gameImage[random]);
            }
        }
    }


    private void startgame() {

        final Animation pokeLoadingAnim=new Animation() {
            float step = 0;
            int ab=0;
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);

                if (interpolatedTime == 0) {
                    step = 1;
                    text.setText("0");
                }

                if ((interpolatedTime / 0.300) >= step) {
                    if (ab == 0) {
                        text.setText("1");
                        ab=1;
                    }else if (ab == 1) {
                        text.setText("2");
                        ab=2;
                    }else if (ab == 2) {
                       Rought();
                    }
                    ++step;
                }
            }
        };

        pokeLoadingAnim.setDuration(1000);
        pokeLoadingAnim.setInterpolator(new LinearInterpolator());
       text.startAnimation(pokeLoadingAnim);

    }


    float x,y;
    float startX,startY;
    boolean down=false,pausepop=true;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
               if(pausepop) {
                   x = event.getX();
                   y = event.getY();
                   valuew1 = true;
                   if (getball(x, y)) {
                       canvas.updateval();
                       x = startX;
                       y = startY;
                       down = true;
                   }
               }
                break;

            case MotionEvent.ACTION_MOVE:
                if(down) {
                    x = event.getX();
                    y = event.getY();
                    if (getball(x, y)) {

                    } else {
                        Line current = canvas.lines.get(canvas.lines.size() - 1);
                        current.stopX = event.getX();
                        current.stopY = event.getY() - 60;
                        canvas.updateval();
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                if(down) {
                    if (ballvalue.size() == pointvalue.size())
                    {
//                        if (ballvalue.removeAll(pointvalue))
                        {
                            if (ballvalue.equals(pointvalue)) {
                                Log.e("string",""+ballvalue.equals(pointvalue));
                                rightanswer();
                            } else {
                                Log.e("string",""+ballvalue.equals(pointvalue));
                                wronganswer();
                            }
                        }
                    } else {
                        Log.e("valuye",""+ballvalue.size());
                        for(int i=0;i<ballvalue.size();i++){
                            if(ballvalue.get(i)!=pointvalue.get(i)){
                                wronganswer();
                            }
                        }

                    }
                    down=false;
                }
                break;
        }

        return super.onTouchEvent(event);
    }


    boolean valuew1=false;
    int ballx,bally;
    private boolean getball(float Xval,float Yval) {
        for (int i = 0; i < pointval.size(); i++) {

            pointval.get(i).getLocationOnScreen(posXY);
            ballx = posXY[0];
            bally = posXY[1];
            if(ballx<(int)Xval &&(ballx+(width/6)/2)>(int)Xval&& bally<(int)Yval &&(bally+(width/6)/2)>(int)Yval){

                if(!ballvalue.contains(i)){
                    ballvalue.add(i);
                    if(canvas.lines.size()!=0) {
                        Line current = canvas.lines.get(canvas.lines.size() - 1);
                        current.stopX = ballx + (width / 6) / 4;
                        current.stopY = bally - (width / 6) / 5;
                        canvas.updateval();
                    }

                    pointval.get(i).setBackgroundResource(R.drawable.red);
                    startX = ballx+(width/6)/4;
                    startY = bally-(width/6)/5;
                    canvas.lines.add(new Line(startX, startY));
                    valuew1 = false;
                }
                return true;
            }

        }
        return false;
    }


  private void Rought(){
      trackX.clear();
      trackY.clear();
      pointval.clear();
      while(textview.size()>0){
          int pos=(new Random().nextInt(textview.size()));
          pointval.add(textview.get(pos));
          textview.remove(pos);
          Log.e("data",""+pointval.size());
      }
      Log.e("data1111",""+pointval.size());
      for (int i = 0; i < pointval.size(); i++) {

//          if(i==lineval1||i==lineval2||i==lineval3 ||i==lineval4||i==lineval5||i==lineval6)
          {

              pointvalue.add(i);
              pointval.get(i).getLocationOnScreen(posXY);
              ballx = posXY[0];
              bally = posXY[1];
              trackX.add(ballx + (width/6)/4);
              trackY.add(bally - (width/6)/5);
          }
      }

      canvas.update(trackX,trackY);
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
                        canvas.cleardata();
                        canvas.updateval();
                        process();

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        refrence.setTracktherought(refrence.getTracktherought() + 100);
                        rightanswer.setVisibility(View.GONE);
                        rightans.setVisibility(View.GONE);
                        rightval=rightval+1;
                        level=level+1;
                        gamelayout(6, 6);
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
                    canvas.cleardata();
                    canvas.updateval();
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


    private int lifevalue=0;
    private void life(){
        if(lifevalue==1) {
            ((ImageView) findViewById(R.id.imageview1)).setBackgroundResource(R.drawable.life_brain);
            gamelayout(6, 6);
        }else if(lifevalue==2){
            ((ImageView) findViewById(R.id.imageview2)).setBackgroundResource(R.drawable.life_brain);
            gamelayout(6, 6);
        }else if(lifevalue==3){
            ((ImageView) findViewById(R.id.imageview3)).setBackgroundResource(R.drawable.life_brain);
            gameover();
        }
    }

    int wrongval=0,rightval=0;
    private void gameover() {
        refrence.setGame("TrackTheRought");
        refrence.setScore(refrence.getTracktherought());
        refrence.setMultiplier(multiplierval);
        int total=wrongval+rightval;
        refrence.setAccuracy((int)((rightval*100)/total));
        Intent intent=new Intent(this, GameOver.class);
        startActivity(intent);
        this.finish();
    }


    int level=0;
    private void levelval(){
        addlineval();

        int l1=(new Random().nextInt(Line1.size()));
        val1=Line1.get(l1);
        Line1.remove(l1);
        int l2=(new Random().nextInt(Line1.size()));
        val2=Line1.get(l2);
        Line1.remove(l2);
        int l3=(new Random().nextInt(Line1.size()));
        val3=Line1.get(l3);
        Line1.remove(l3);
        int l4=(new Random().nextInt(Line1.size()));
        val4=Line1.get(l4);
        Line1.remove(l4);

        int l5=(new Random().nextInt(Line2.size()));
        val5=Line2.get(l5);
        Line2.remove(l5);
        int l6=(new Random().nextInt(Line2.size()));
        val6=Line2.get(l6);
        Line2.remove(l6);
        int l7=(new Random().nextInt(Line2.size()));
        val7=Line2.get(l7);
        Line2.remove(l7);
        int l8=(new Random().nextInt(Line2.size()));
        val8=Line2.get(l8);
        Line2.remove(l8);

        int l9=(new Random().nextInt(Line3.size()));
        val9=Line3.get(l9);
        Line3.remove(l9);
        int l10=(new Random().nextInt(Line3.size()));
        val10=Line3.get(l10);
        Line3.remove(l10);
        int l11=(new Random().nextInt(Line3.size()));
        val11=Line3.get(l11);
        Line3.remove(l11);
        int l12=(new Random().nextInt(Line3.size()));
        val12=Line3.get(l12);
        Line3.remove(l12);

        int l13=(new Random().nextInt(Line4.size()));
        val13=Line4.get(l13);
        Line4.remove(l13);
        int l14=(new Random().nextInt(Line4.size()));
        val14=Line4.get(l14);
        Line4.remove(l14);
        int l15=(new Random().nextInt(Line4.size()));
        val15=Line4.get(l15);
        Line4.remove(l15);
        int l16=(new Random().nextInt(Line4.size()));
        val16=Line4.get(l16);
        Line4.remove(l16);

        int l17=(new Random().nextInt(Line5.size()));
        val17=Line5.get(l17);
        Line5.remove(l17);
        int l18=(new Random().nextInt(Line5.size()));
        val18=Line5.get(l18);
        Line5.remove(l18);
        int l19=(new Random().nextInt(Line5.size()));
        val19=Line5.get(l19);
        Line5.remove(l19);
        int l20=(new Random().nextInt(Line5.size()));
        val20=Line5.get(l20);
        Line5.remove(l20);

        int l21=(new Random().nextInt(Line6.size()));
        val21=Line6.get(l21);
        Line6.remove(l21);
        int l22=(new Random().nextInt(Line6.size()));
        val22=Line6.get(l22);
        Line6.remove(l22);
        int l23=(new Random().nextInt(Line6.size()));
        val23=Line6.get(l23);
        Line6.remove(l23);
        int l24=(new Random().nextInt(Line6.size()));
        val24=Line6.get(l24);
        Line6.remove(l24);


        if(level==0){
            lineval1=1;lineval2=7;lineval3=10;lineval4=15;
        }else if(level==1){
            lineval1=2;lineval2=7;lineval3=11;lineval4=14;
//        }else if(level==2){
//            lineval1=3;lineval2=7;lineval3=10;lineval4=13;
//        }else if(level==3){
//            lineval1=2;lineval2=5;lineval3=11;lineval4=15;lineval5=18;
//        }else if(level==4){
//            lineval1=0;lineval2=6;lineval3=10;lineval4=15;lineval5=17;
//        }else if(level==5){
//            lineval1=3;lineval2=5;lineval3=11;lineval4=14;lineval5=16;
//        }else if(level==6){
//            lineval1=2;lineval2=7;lineval3=10;lineval4=13;lineval5=18;lineval6=21;
//        }else if(level==7){
//            lineval1=0;lineval2=6;lineval3=9;lineval4=15;lineval5=17;lineval6=22;
//        }else if(level==8){
//            lineval1=3;lineval2=6;lineval3=10;lineval4=15;lineval5=18;lineval6=17;
//        }else if(level==9) {
//            lineval1=2;lineval2=5;lineval3=11;lineval4=14;lineval5=19;lineval6=13;
//        }else if(level==10){
//            lineval1=5;lineval2=7;lineval3=10;lineval4=12;lineval5=18;lineval6=22;
//        }else if(level==11){
//            lineval1=0;lineval2=6;lineval3=16;lineval4=20;lineval5=22;lineval6=12;
//        }else if(level==12){
//            lineval1=13;lineval2=6;lineval3=22;lineval4=15;lineval5=13;lineval6=19;
//        }else if(level==13){
//            lineval1=10;lineval2=16;lineval3=12;lineval4=15;lineval5=18;lineval6=17;
//        }else if(level==14){
//            lineval1=11;lineval2=6;lineval3=0;lineval4=4;lineval5=14;lineval6=19;
//        }else if(level==15){
//            lineval1=3;lineval2=6;lineval3=10;lineval4=15;lineval5=18;lineval6=17;
        }else if(level>=2){

            lineval1=(new Random().nextInt((3 - 0) + 1) + 0);
            lineval2=(new Random().nextInt((7 - 4) + 1) + 4);
            lineval3=(new Random().nextInt((11 - 8) + 1) + 8);
            lineval4=(new Random().nextInt((15 - 12) + 1) + 12);
            lineval5=(new Random().nextInt((19 - 16) + 1) + 16);
            lineval6=(new Random().nextInt((23 - 20) + 1) + 20);

        }
    }
    private void addlineval(){
        Line1.clear();Line2.clear();Line3.clear();Line4.clear();Line5.clear();Line6.clear();
        Line1.add(0);Line1.add(1);Line1.add(2);Line1.add(3);Line1.add(4);Line1.add(5);
        Line2.add(6);Line2.add(7);Line2.add(8);Line2.add(9);Line2.add(10);Line2.add(11);
        Line3.add(12);Line3.add(13);Line3.add(14);Line3.add(15);Line3.add(16);Line3.add(17);
        Line4.add(18);Line4.add(19);Line4.add(20);Line4.add(21);Line4.add(22);Line4.add(23);
        Line5.add(24);Line5.add(25);Line5.add(26);Line5.add(27);Line5.add(28);Line5.add(29);
        Line6.add(30);Line6.add(31);Line6.add(32);Line6.add(33);Line6.add(34);Line6.add(35);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.layoutepause) {
            pausepopup();
        } else if (v.getId() == R.id.resume) {
            pausepopup();
        } else if (v.getId() == R.id.replay) {
            pausepopup();
        } else if (v.getId() == R.id.instructions) {
            pausepopup();
        } else if (v.getId() == R.id.sound) {
            pausepopup();
        } else if (v.getId() == R.id.exitgame) {
            pausepopup();
        }
    }

    private void pausepopup() {
        if (pausepopup.getVisibility() == View.GONE) {
            pausepopup.setVisibility(View.VISIBLE);
            pausepop=false;
        } else {
            pausepopup.setVisibility(View.GONE);
            pausepop=true;
        }
    }
}
