package com.edlogiq.neurongym.howtoplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.game.BlinkGame;
import com.edlogiq.neurongym.game.DualFocusProGame;
import com.edlogiq.neurongym.R;

public class HowToPlayDualFocuspro extends Activity {

    int imagename = 1;

    private ImageView image1,image2,image3,image4,image5;
    private boolean slide = true;
    private float x, y;
    private LinearLayout layout1, layout2;
    private ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play_dual_focuspro);

        ((RelativeLayout)findViewById(R.id.howtoplaydualfocuspro)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.howtoplaydualfocuspro)).setBackgroundResource(R.color.black);
        }

        ((ImageView) findViewById(R.id.imageviewright)).setOnClickListener(listner);
        ((ImageView) findViewById(R.id.imageviewleft)).setOnClickListener(listner);
        ((LinearLayout)findViewById(R.id.playlayoute)).setOnClickListener(listner);
        ((LinearLayout)findViewById(R.id.crosslayoute)).setOnClickListener(listner);

        image1=(ImageView)findViewById(R.id.image1);
        image2=(ImageView)findViewById(R.id.image2);
        image3=(ImageView)findViewById(R.id.image3);
        image4=(ImageView)findViewById(R.id.image4);
        image5=(ImageView)findViewById(R.id.image5);

        flipper = (ViewFlipper) findViewById(R.id.viewflipper);
        point(slideval);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                actiondown(event);
                break;

            case MotionEvent.ACTION_MOVE:
                actionmove(event);
                break;

            case MotionEvent.ACTION_UP:
                actionup(event);
                break;
        }

        return super.onTouchEvent(event);
    }

    private void actiondown(MotionEvent event) {
        x = event.getX();
        y = event.getY();

        slide = true;
    }

    private void actionmove(MotionEvent event) {
        if (slide) {

            if ((x - event.getX()) > 3) {
                moveRight();
                slide = false;
            } else if ((x - event.getX()) < -3) {
                moveleft();
                slide = false;
            }
        }
    }

    View.OnClickListener listner = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.imageviewright) {
                moveRight();
            } else if (v.getId() == R.id.imageviewleft) {
                moveleft();
            }else if (v.getId() == R.id.crosslayoute) {
                HowToPlayDualFocuspro.this.finish();
            } else if (v.getId() == R.id.playlayoute) {
                HowToPlayDualFocuspro.this.finish();
                Intent intent=new Intent(getApplicationContext(), DualFocusProGame.class);
                startActivity(intent);
            }
        }

    };

    private void actionup(MotionEvent event) {
        slide = true;
    }

    int slideval=0;
    private void moveRight() {
        if(slideval<4) {
            flipper.setInAnimation(this, R.anim.slide_in_from_right);
            flipper.setOutAnimation(this, R.anim.slide_out_to_left);
            flipper.showNext();
            slideval++;
            point(slideval);
        }

    }

    private void moveleft() {
        if(slideval>0) {
            flipper.setInAnimation(this, R.anim.slide_in_from_left);
            flipper.setOutAnimation(this, R.anim.slide_out_to_right);
            flipper.showPrevious();
            slideval--;
            point(slideval);
        }
    }

    private void point(int slideval){
        if(slideval==0){
            image1.setBackgroundResource(R.drawable.cyan);
            image2.setBackgroundResource(R.drawable.grey);
            image3.setBackgroundResource(R.drawable.grey);
            image4.setBackgroundResource(R.drawable.grey);
            image5.setBackgroundResource(R.drawable.grey);
        }else if(slideval==1){
            image1.setBackgroundResource(R.drawable.grey);
            image2.setBackgroundResource(R.drawable.cyan);
            image3.setBackgroundResource(R.drawable.grey);
            image4.setBackgroundResource(R.drawable.grey);
            image5.setBackgroundResource(R.drawable.grey);
        }else if(slideval==2){
            image1.setBackgroundResource(R.drawable.grey);
            image2.setBackgroundResource(R.drawable.grey);
            image3.setBackgroundResource(R.drawable.cyan);
            image4.setBackgroundResource(R.drawable.grey);
            image5.setBackgroundResource(R.drawable.grey);
        }else if(slideval==3){
            image1.setBackgroundResource(R.drawable.grey);
            image2.setBackgroundResource(R.drawable.grey);
            image3.setBackgroundResource(R.drawable.grey);
            image4.setBackgroundResource(R.drawable.cyan);
            image5.setBackgroundResource(R.drawable.grey);
        }else if(slideval==3){
            image1.setBackgroundResource(R.drawable.grey);
            image2.setBackgroundResource(R.drawable.grey);
            image3.setBackgroundResource(R.drawable.grey);
            image4.setBackgroundResource(R.drawable.grey);
            image5.setBackgroundResource(R.drawable.cyan);
        }
    }

}
