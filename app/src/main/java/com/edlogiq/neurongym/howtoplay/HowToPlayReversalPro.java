package com.edlogiq.neurongym.howtoplay;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.game.BlinkGame;
import com.edlogiq.neurongym.game.ReversalProGame;
import com.edlogiq.neurongym.R;

public class HowToPlayReversalPro extends Activity {


    int imagename = 1;

    private ImageView image1, image2;
    private boolean slide = true;
    private float x, y;
    private LinearLayout layout1, layout2;
    private ViewFlipper flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play_reversal_pro);

        ((RelativeLayout)findViewById(R.id.howtoplayreversalpro)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.howtoplayreversalpro)).setBackgroundResource(R.color.black);
        }

        ((ImageView) findViewById(R.id.imageviewright)).setOnClickListener(listner);
        ((ImageView) findViewById(R.id.imageviewleft)).setOnClickListener(listner);
        ((LinearLayout)findViewById(R.id.playlayoute)).setOnClickListener(listner);
        ((LinearLayout)findViewById(R.id.crosslayoute)).setOnClickListener(listner);

        flipper = (ViewFlipper) findViewById(R.id.viewflipper);

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
            } else if (v.getId() == R.id.crosslayoute) {
                HowToPlayReversalPro.this.finish();
            } else if (v.getId() == R.id.playlayoute) {
                HowToPlayReversalPro.this.finish();
                Intent intent=new Intent(getApplicationContext(), ReversalProGame.class);
                startActivity(intent);
            }
        }

    };

    private void actionup(MotionEvent event) {
        slide = true;
    }

    private void moveRight() {
        flipper.setInAnimation(this, R.anim.slide_in_from_right);
        flipper.setOutAnimation(this, R.anim.slide_out_to_left);
        flipper.showNext();

    }

    private void moveleft() {
        flipper.setInAnimation(this, R.anim.slide_in_from_left);
        flipper.setOutAnimation(this, R.anim.slide_out_to_right);
        flipper.showPrevious();
    }
}
