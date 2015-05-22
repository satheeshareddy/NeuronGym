package com.edlogiq.neurongym.game;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.edlogiq.neurongym.R;

import java.util.ArrayList;


public class CanvasTrackTheRoute extends View {

    Paint paint;
    float startX, startY,stopX, stopY;
    private ArrayList<Integer> trackX=new ArrayList<>();
    private ArrayList<Integer> trackY=new ArrayList<>();
    int bsize,val=0 ;
    public static ArrayList<Line> lines = new ArrayList<Line>();
    int speedval=10;
    private Bitmap plane;

    public CanvasTrackTheRoute(Context context, AttributeSet attrs,int blockSize , String color) {
        super(context, attrs);
        this.bsize = blockSize;
        paint=new Paint();
        paint.setColor(Color.parseColor(color));
        paint.setStrokeWidth(blockSize / 6);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);       // set to STOKE
        paint.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        paint.setStrokeCap(Paint.Cap.ROUND);
        plane=BitmapFactory.decodeResource(getResources(), R.drawable.aeroplane_red);
        lines.clear();
    }

    @Override
    public void draw(android.graphics.Canvas canvas) {
//        super.draw(canvas);
//        canvas.drawCircle(startX, startY, bsize/15, paint);
//        canvas.drawLine(startX, startY , stopX , stopY , paint);
//        canvas.drawCircle(stopX, stopY, bsize/15, paint);
//        canvas.drawBitmap(plane,startX,startY,paint);
        for (Line l : lines) {
//            canvas.drawLine(l.startX, l.startY, l.stopX, l.stopY, paint);
            canvas.drawCircle(l.startX, l.startY, bsize/15, paint);
            canvas.drawLine(l.startX, l.startY , l.stopX , l.stopY , paint);
            canvas.drawCircle(l.stopX, l.stopY, bsize/15, paint);

        }
        if(frist){
            track();}
    }
    int speedX,speedY,trackline=0;
    boolean valfr=true,frist=false;
    float speed;

    private void track() {
        Log.e("track","track");
        if(trackline==0){
            if(valfr) {
                speedX = trackX.get(0);
                speedY = trackY.get(0);
                lines.add(new Line(speedX, speedY, speedX, speedY));
                int x=Math.abs(speedX-trackX.get(1));
                int y=Math.abs(speedY-trackY.get(1));
                if(y!=0) {
                    speed = (float)x / y;
                }else{speed=1;}
                valfr=false;
            }

            Line current = lines.get(lines.size() - 1);
            Log.e("Line1",""+trackX.get(1)+"   "+trackY.get(1)+"   "+current.stopX+"  "+current.stopY+"  "+speed);
            if(current.stopX<trackX.get(1) && current.stopY<trackY.get(1)) {
                current.stopX +=(speedval*speed);
                current.stopY +=(speedval);
                if(current.stopX>=trackX.get(1) || current.stopY>=trackY.get(1)){
                    trackline=1;
                    valfr=true;
                    current.stopX =trackX.get(1);
                    current.stopY =trackY.get(1);
                }
                invalidate();
            }else if(current.stopX<trackX.get(1) && current.stopY>trackY.get(1)){
                current.stopX +=(speedval*speed);
                current.stopY -=(speedval);
                if(current.stopX>=trackX.get(1) || current.stopY<=trackY.get(1)){
                    trackline=1;
                    valfr=true;
                    current.stopX =trackX.get(1);
                    current.stopY =trackY.get(1);
                }
                invalidate();
            }else if(current.stopX>trackX.get(1) &&current.stopY>trackY.get(1)){
                current.stopX -=(speedval*speed);
                current.stopY -=(speedval);
                if(current.stopX<=trackX.get(1) || current.stopY<=trackY.get(1)){
                    trackline=1;
                    valfr=true;
                    current.stopX =trackX.get(1);
                    current.stopY =trackY.get(1);
                }
                invalidate();
            }else if(current.stopX>trackX.get(1) && current.stopY<trackY.get(1)){
                current.stopX -=(speedval*speed);
                current.stopY +=(speedval);
                if(current.stopX<=trackX.get(1) || current.stopY>=trackY.get(1)){
                    trackline=1;
                    valfr=true;
                    current.stopX =trackX.get(1);
                    current.stopY =trackY.get(1);
                }
                invalidate();
            }else  if(current.stopX<trackX.get(1) && current.stopY==trackY.get(1)) {
                current.stopX +=(speedval*speed);
//                current.stopY +=(10);
                if(current.stopX>=trackX.get(1)){
                    trackline=1;
                    valfr=true;
                    current.stopX =trackX.get(1);
                    current.stopY =trackY.get(1);
                }
                invalidate();
            }else if(current.stopX>trackX.get(1) && current.stopY==trackY.get(1)){
                current.stopX -=(speedval*speed);
//                current.stopY -=(10);
                if(current.stopX>=trackX.get(1)){
                    trackline=1;
                    valfr=true;
                    current.stopX =trackX.get(1);
                    current.stopY =trackY.get(1);
                }
                invalidate();
            }else if(current.stopX==trackX.get(1) &&current.stopY>trackY.get(1)){
//                current.stopX -=(10*speed);
                current.stopY -=(speedval);
                if( current.stopY<=trackY.get(1)){
                    trackline=1;
                    valfr=true;
                    current.stopX =trackX.get(1);
                    current.stopY =trackY.get(1);
                }
                invalidate();
            }else if(current.stopX==trackX.get(1) && current.stopY<trackY.get(1)){
//                current.stopX -=(10*speed);
                current.stopY +=(speedval);
                if( current.stopY>=trackY.get(1)){
                    trackline=1;
                    valfr=true;
                    current.stopX =trackX.get(1);
                    current.stopY =trackY.get(1);
                }
                invalidate();
            }else{
                frist=false;
            }
        }

        //        2nd Line are created-----------------------------------------
        else if(trackline==1){
            if(valfr) {
                speedX = trackX.get(1);
                speedY = trackY.get(1);
                lines.add(new Line(speedX, speedY, speedX, speedY));
                int x=Math.abs(speedX-trackX.get(2));
                int y=Math.abs(speedY-trackY.get(2));
                if(y!=0){ speed = (float)x / y;}
                else{speed=1;}
                Log.e("trackkkk22",speedX+"   "+speedY+"  "+speed);
                valfr=false;
            }

            Line current = lines.get(lines.size() - 1);
            Log.e("Line2",""+trackX.get(1)+"   "+trackY.get(1)+"   "+current.stopX+"  "+current.stopY+"  "+speed);
            if(current.stopX<trackX.get(2) && current.stopY<=trackY.get(2)) {
                current.stopX +=(speedval*speed);
                current.stopY +=(speedval);
                if(current.stopX>=trackX.get(2) || current.stopY>=trackY.get(2)){
                    trackline=2;
                    valfr=true;
                    current.stopX =trackX.get(2);
                    current.stopY =trackY.get(2);
                }
                invalidate();
            }else if(current.stopX<trackX.get(2) && current.stopY>trackY.get(2)){
                current.stopX +=(speedval*speed);
                current.stopY -=(speedval);
                if(current.stopX>=trackX.get(2) || current.stopY<=trackY.get(2)){
                    trackline=2;
                    valfr=true;
                    current.stopX =trackX.get(2);
                    current.stopY =trackY.get(2);
                }
                invalidate();
            }else if(current.stopX>trackX.get(2) &&current.stopY>trackY.get(2)){
                current.stopX -=(speedval*speed);
                current.stopY -=(speedval);
                if(current.stopX<=trackX.get(2) || current.stopY<=trackY.get(2)){
                    trackline=2;
                    valfr=true;
                    current.stopX =trackX.get(2);
                    current.stopY =trackY.get(2);
                }
                invalidate();
            }else if(current.stopX>trackX.get(2) && current.stopY<trackY.get(2)){
                current.stopX -=(speedval*speed);
                current.stopY +=(speedval);
                if(current.stopX<=trackX.get(2) || current.stopY>=trackY.get(2)){
                    trackline=2;
                    valfr=true;
                    current.stopX =trackX.get(2);
                    current.stopY =trackY.get(2);
                }
                invalidate();
            }

            else  if(current.stopX<trackX.get(2) && current.stopY==trackY.get(2)) {
                current.stopX +=(speedval*speed);
//                current.stopY +=(10);
                if(current.stopX>=trackX.get(2) ){
                    trackline=2;
                    valfr=true;
                    current.stopX =trackX.get(2);
                    current.stopY =trackY.get(2);
                }
                invalidate();
            }else if(current.stopX>trackX.get(2) && current.stopY==trackY.get(2)){
                current.stopX -=(speedval*speed);
//                current.stopY -=(10);
                if(current.stopX>=trackX.get(2) ){
                    trackline=2;
                    valfr=true;
                    current.stopX =trackX.get(2);
                    current.stopY =trackY.get(2);
                }
                invalidate();
            }else if(current.stopX==trackX.get(2) &&current.stopY>trackY.get(2)){
//                current.stopX -=(10*speed);
                current.stopY -=(speedval);
                if( current.stopY<=trackY.get(2)){
                    trackline=2;
                    valfr=true;
                    current.stopX =trackX.get(2);
                    current.stopY =trackY.get(2);
                }
                invalidate();
            }else if(current.stopX==trackX.get(2) && current.stopY<trackY.get(2)){
//                current.stopX -=(10*speed);
                current.stopY +=(speedval);
                if( current.stopY>=trackY.get(2)){
                    trackline=2;
                    valfr=true;
                    current.stopX =trackX.get(2);
                    current.stopY =trackY.get(2);
                }
                invalidate();
            }

            else{
                frist=false;
            }
        }
//        3nd Line are created-----------------------------------------
        else if(trackline==2){
            if(valfr) {
                speedX = trackX.get(2);
                speedY = trackY.get(2);
                lines.add(new Line(speedX, speedY, speedX, speedY));
                int x=Math.abs(speedX-trackX.get(3));
                int y=Math.abs(speedY-trackY.get(3));
                if(y!=0) {
                    speed = (float)x / y;
                }else{speed=1;}
                Log.e("trackkkk33",speedX+"   "+speedY+"  "+speed);
                valfr=false;
            }

            Line current = lines.get(lines.size() - 1);
            Log.e("Line3",""+trackX.get(3)+"   "+trackY.get(3)+"   "+current.stopX+"  "+current.stopY);
            if(current.stopX<trackX.get(3) && current.stopY<trackY.get(3)) {
                current.stopX +=(speedval*speed);
                current.stopY +=(speedval);
                if(current.stopX>=trackX.get(3) || current.stopY>=trackY.get(3)){
                    linepoint4(current);
                }
                invalidate();
            }else if(current.stopX<trackX.get(3) && current.stopY>trackY.get(3)){
                current.stopX +=(speedval*speed);
                current.stopY -=(speedval);
                if(current.stopX>=trackX.get(3) || current.stopY<=trackY.get(3)){
                    linepoint4(current);
                }
                invalidate();
            }else if(current.stopX>trackX.get(3) &&current.stopY>trackY.get(3)){
                current.stopX -=(speedval*speed);
                current.stopY -=(speedval);
                if(current.stopX<=trackX.get(3) || current.stopY<=trackY.get(3)){
                    linepoint4(current);
                }
                invalidate();
            }else if(current.stopX>trackX.get(3) && current.stopY<trackY.get(3)){
                current.stopX -=(speedval*speed);
                current.stopY +=(speedval);
                if(current.stopX<=trackX.get(3) || current.stopY>=trackY.get(3)){
                    linepoint4(current);
                }
                invalidate();
            }else  if(current.stopX<trackX.get(3) && current.stopY==trackY.get(3)) {
                current.stopX +=(speedval*speed);
//                current.stopY +=(10);
                if(current.stopX>=trackX.get(3) ){
                    linepoint4(current);
                }
                invalidate();
            }else if(current.stopX>trackX.get(3) && current.stopY==trackY.get(3)){
                current.stopX -=(speedval*speed);
//                current.stopY -=(10);
                if(current.stopX>=trackX.get(3) ){
                    linepoint4(current);
                }
                invalidate();
            }else if(current.stopX==trackX.get(3) &&current.stopY>trackY.get(3)){
//                current.stopX -=(10*speed);
                current.stopY -=(speedval);
                if( current.stopY<=trackY.get(3)){
                    linepoint4(current);
                }
                invalidate();
            }else if(current.stopX==trackX.get(3) && current.stopY<trackY.get(3)){
//                current.stopX -=(10*speed);
                current.stopY +=(speedval);
                if( current.stopY>=trackY.get(3)){
                    linepoint4(current);
                }
                invalidate();
            }
            else{
                frist=false;
            }
        }

        //        4nd Line are created-----------------------------------------
        else if(trackline==3){
            if(valfr) {
                speedX = trackX.get(3);
                speedY = trackY.get(3);
                lines.add(new Line(speedX, speedY, speedX, speedY));
                int x=Math.abs(speedX-trackX.get(4));
                int y=Math.abs(speedY-trackY.get(4));
                if(y!=0) {
                    speed = (float)x / y;
                }else{speed=1;}
                Log.e("trackkkk44",speedX+"   "+speedY+"  "+speed);
                valfr=false;
            }

            Line current = lines.get(lines.size() - 1);
            Log.e("Line4",""+trackX.get(4)+"   "+trackY.get(4)+"   "+current.stopX+"  "+current.stopY);
            if(current.stopX<trackX.get(4) && current.stopY<trackY.get(4)) {
                current.stopX +=(speedval*speed);
                current.stopY +=(speedval);
                if(current.stopX>=trackX.get(4) || current.stopY>=trackY.get(4)){
                    linepoint5(current);
                }
                invalidate();
            }else if(current.stopX<trackX.get(4) && current.stopY>trackY.get(4)){
                current.stopX +=(speedval*speed);
                current.stopY -=(speedval);
                if(current.stopX>=trackX.get(4) || current.stopY<=trackY.get(4)){
                    linepoint5(current);
                }
                invalidate();
            }else if(current.stopX>trackX.get(4) &&current.stopY>trackY.get(4)){
                current.stopX -=(speedval*speed);
                current.stopY -=(speedval);
                if(current.stopX<=trackX.get(4) || current.stopY<=trackY.get(4)){
                    linepoint5(current);
                }
                invalidate();
            }else if(current.stopX>trackX.get(4) && current.stopY<trackY.get(4)){
                current.stopX -=(speedval*speed);
                current.stopY +=(speedval);
                if(current.stopX<=trackX.get(4) || current.stopY>=trackY.get(4)){
                    linepoint5(current);
                }
                invalidate();
            }else  if(current.stopX<trackX.get(4) && current.stopY==trackY.get(4)) {
                current.stopX +=(speedval*speed);
//                current.stopY +=(10);
                if(current.stopX>=trackX.get(4) ){
                    linepoint5(current);
                }
                invalidate();
            }else if(current.stopX>trackX.get(4) && current.stopY==trackY.get(4)){
                current.stopX -=(speedval*speed);
//                current.stopY -=(10);
                if(current.stopX>=trackX.get(4) ){
                    linepoint5(current);
                }
                invalidate();
            }else if(current.stopX==trackX.get(4) &&current.stopY>trackY.get(4)){
//                current.stopX -=(10*speed);
                current.stopY -=(speedval);
                if( current.stopY<=trackY.get(4)){
                    linepoint5(current);
                }
                invalidate();
            }else if(current.stopX==trackX.get(4) && current.stopY<trackY.get(4)){
//                current.stopX -=(10*speed);
                current.stopY +=(speedval);
                if( current.stopY>=trackY.get(4)){
                    linepoint5(current);
                }
                invalidate();
            }
            else{
                frist=false;
            }
        }


        //        5nd Line are created-----------------------------------------
        else if(trackline==4){
            if(valfr) {
                speedX = trackX.get(4);
                speedY = trackY.get(4);
                lines.add(new Line(speedX, speedY, speedX, speedY));
                int x=Math.abs(speedX-trackX.get(5));
                int y=Math.abs(speedY-trackY.get(5));
                if(y!=0) {
                    speed = (float)x / y;
                }else{speed=1;}
                Log.e("trackkkk55",speedX+"   "+speedY+"  "+speed);
                valfr=false;
            }

            Line current = lines.get(lines.size() - 1);
            Log.e("Line5",""+trackX.get(5)+"   "+trackY.get(5)+"   "+current.stopX+"  "+current.stopY);
            if(current.stopX<trackX.get(5) && current.stopY<trackY.get(5)) {
                current.stopX +=(speedval*speed);
                current.stopY +=(speedval);
                if(current.stopX>=trackX.get(5) || current.stopY>=trackY.get(5)){
                    linepoint6(current);
                }
                invalidate();
            }else if(current.stopX<trackX.get(5) && current.stopY>trackY.get(5)){
                current.stopX +=(speedval*speed);
                current.stopY -=(speedval);
                if(current.stopX>=trackX.get(5) || current.stopY<=trackY.get(5)){
                    linepoint6(current);
                }
                invalidate();
            }else if(current.stopX>trackX.get(5) &&current.stopY>trackY.get(5)){
                current.stopX -=(speedval*speed);
                current.stopY -=(speedval);
                if(current.stopX<=trackX.get(5) || current.stopY<=trackY.get(5)){
                    linepoint6(current);
                }
                invalidate();
            }else if(current.stopX>trackX.get(5) && current.stopY<trackY.get(5)){
                current.stopX -=(speedval*speed);
                current.stopY +=(speedval);
                if(current.stopX<=trackX.get(5) || current.stopY>=trackY.get(5)){
                    linepoint6(current);
                }
                invalidate();
            }else  if(current.stopX<trackX.get(5) && current.stopY==trackY.get(5)) {
                current.stopX +=(speedval*speed);
//                current.stopY +=(10);
                if(current.stopX>=trackX.get(5) ){
                    linepoint6(current);
                }
                invalidate();
            }else if(current.stopX>trackX.get(5) && current.stopY==trackY.get(5)){
                current.stopX -=(speedval*speed);
//                current.stopY -=(10);
                if(current.stopX>=trackX.get(5) ){
                    linepoint6(current);
                }
                invalidate();
            }else if(current.stopX==trackX.get(5) &&current.stopY>trackY.get(5)){
//                current.stopX -=(10*speed);
                current.stopY -=(speedval);
                if( current.stopY<=trackY.get(5)){
                    linepoint6(current);
                }
                invalidate();
            }else if(current.stopX==trackX.get(5) && current.stopY<trackY.get(5)){
//                current.stopX -=(10*speed);
                current.stopY +=(speedval);
                if( current.stopY>=trackY.get(5)){
                    linepoint6(current);
                }
                invalidate();
            }
            else{
                frist=false;
            }
        }

    }


    private void linepoint4(Line current){
        if(trackX.size()==4) {
            frist = false;
            current.stopX = trackX.get(3);
            current.stopY = trackY.get(3);
            cleardata();
        }else{
            trackline=3;
            valfr=true;
            current.stopX =trackX.get(3);
            current.stopY =trackY.get(3);
        }
    }

    private void linepoint5(Line current){
        if(trackX.size()==5) {
            frist = false;
            current.stopX = trackX.get(4);
            current.stopY = trackY.get(4);
            cleardata();
        }else{
            trackline=4;
            valfr=true;
            current.stopX =trackX.get(4);
            current.stopY =trackY.get(4);
        }
    }

    private void linepoint6(Line current){
        if(trackX.size()==6) {
            frist = false;
            current.stopX = trackX.get(5);
            current.stopY = trackY.get(5);
            cleardata();
        }else{
            trackline=5;
            valfr=true;
            current.stopX =trackX.get(5);
            current.stopY =trackY.get(5);
        }
    }



    public void cleardata(){
        lines.clear();
        trackX.clear();
        trackY.clear();
    }


    public void update(ArrayList<Integer> _trackvalX, ArrayList<Integer> _trackvalY) {
        speedval=speedval+5;
        trackX=_trackvalX;
        trackY=_trackvalY;
        frist=true;
        valfr=true;
        trackline=0;
        Log.e("trackkkk22",trackX+"   "+trackY);
        invalidate();
    }

    public void updateval(){
        invalidate();
    }
}

class Line {
    float startX, startY, stopX, stopY;
    float joinX, joinY = 0;
    public Line(float startX, float startY, float stopX, float stopY) {
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
    }
    public Line(float startX, float startY) { // for convenience

        this(startX, startY, startX, startY);
    }
}