package com.edlogiq.neurongym.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import com.edlogiq.neurongym.R;

/**
 * Created by incarnation-pc on 3/2/2015.
 */
public class CanvasDancingball extends View {

    Bitmap bitmap;
    int center_x = 250, center_y = 250, angle = 0;
    int xlength = 460, ylength = 460;
    private int xMin = 0;          // This view's bounds
    private int xMax;
    private int yMin = 0;
    private int yMax;
    private float ballRadius = 40; // Ball's radius
    private float ballX = 0, x, y;  // Ball's center (x,y)
    private float ballY = 0;
    private float ballSpeed = 10;  // Ball's speed (x,y)
    private RectF ballBounds,ballBounds1,ballBounds2;      // Needed for Canvas.drawOval
    private Paint paint;
    private boolean ract = true, topTodown = true, smalltrangle = true, smalltrangle1 = true, onetime = true;
    private Animation anim;
    private Path circle;
    private int shape = 1;


    public CanvasDancingball(Context context) {
        super(context);
        ballBounds = new RectF();
        ballBounds1=new RectF();
        ballBounds1=new RectF();
        paint = new Paint();
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {

        if (onetime) {

            if (shape == 1) {
                shape1();
            } else if (shape == 2) {
                shape2();
            } else if (shape == 3) {
                shape3();
            } else if (shape == 4) {
                shape5();
            } else if (shape == 5) {
                shape6();
            } else if (shape == 6) {
                shape7();
            } else if (shape == 7) {
                shape8();
            } else if (shape == 8) {
                shape9();
            } else if (shape == 9) {
                shape10();
            } else if (shape == 10) {
                shape11();
            } else if (shape == 11) {
                shape12();
            } else if (shape == 12) {
                shape13();
            } else if (shape == 13) {
                shape14();
            }

            ballBounds.set(x, y, x + ballRadius, y + ballRadius);
//            ballBounds1.set(x, y, x + ballRadius-15, y + ballRadius-15);
//            ballBounds2.set(x, y, x + ballRadius-25, y + ballRadius-25);
            paint.setColor(Color.parseColor("#e91e63"));
            canvas.drawOval(ballBounds, paint);
        }
        invalidate();

    }

    public void setshape(int _shape) {
        shape = _shape;
        ract = true;
        topTodown = true;
        smalltrangle = true;
        smalltrangle1 = true;
        onetime = true;
        ballSpeed=ballSpeed+10;
        Log.e("Canvasval",ballSpeed+ "  " + shape);
    }

    private void shape1() {
        if (ract) {
            ballX = 0;
            ballY = 0;
            ract = false;
        }
        if (ballX < xlength && ballY <= 0) {
            ballX += ballSpeed;
        } else if (ballY < ylength && ballX >= xlength) {
            ballY += ballSpeed;
        } else if (ballX > 0 && ballY >= ylength) {
            ballX -= ballSpeed;
        } else if (ballY > 0 && ballX >= 0) {
            ballY -= ballSpeed;
            if (ballY <= 0) {
                onetime = false;
            }
        }
        angle = (int) (Math.toDegrees(Math.atan2(center_y - ballY, center_x - ballX)));
        x = (float) (250 + Math.cos(angle * Math.PI / 180F) * 250);
        y = (float) (250 + Math.sin(angle * Math.PI / 180F) * 250);
    }

    private void shape2() {
        if (ract) {
            ballX = 0;
            ballY = 0;
            ract = false;
        }
        if (ballX < xlength && ballY < ylength / 2 && smalltrangle1) {
            ballX += ballSpeed;
            ballY += ballSpeed / 2;
            smalltrangle = true;
        } else if (ballX > 0 && ballY < ylength && smalltrangle) {
            ballX -= ballSpeed;
            ballY += ballSpeed / 2;
        } else if (ballY > ylength / 2 && ballX < 120) {
            smalltrangle = false;
            smalltrangle1 = false;
            ballX += ballSpeed / 2;
            ballY -= ballSpeed;
        } else if (ballX > 0 && ballY > 0) {
            ballX -= ballSpeed / 2;
            ballY -= ballSpeed;
            if (ballY <= 0) {
                onetime = false;
            }
        }
        x = ballX;
        y = ballY;
    }

    private void shape3() {
        if (ract) {
            ballX = xlength / 2;
            ballY = 0;
            ract = false;
        }

        if (ballY < ylength / 2 && ballX >= xlength / 2) {
            ballX += ballSpeed;
            ballY += ballSpeed;
        } else if (ballY < ylength && ballX >= xlength) {
            ballY += ballSpeed;
            ballX=xlength;
        } else if (ballX > 0 && ballY >= ylength) {
            ballX -= ballSpeed;
        } else if (ballX <= 0 && ballY > ylength / 2) {
            ballY -= ballSpeed;
        } else if (ballX < xlength / 2 && ballY > 0) {
            ballX += ballSpeed;
            ballY -= ballSpeed;
            if (ballY <= 0) {
                onetime = false;
            }
        }
        x = ballX;
        y = ballY;


    }

    private void shape5() {
        if (ract) {
            ballX = xlength / 2;
            ballY = 0;
            ract = false;
        }
        if (ballY < ylength && ballX >= xlength / 2) {
            ballX += ballSpeed / 2;
            ballY += ballSpeed;
        } else if (ballY >= ylength && ballX > 0) {
            ballX -= ballSpeed;
        } else if (ballX < xlength / 2 && ballY > 0) {
            ballX += ballSpeed / 2;
            ballY -= ballSpeed;
            if (ballY <= 0) {
                onetime = false;
            }
        }
        x = ballX;
        y = ballY;
    }

    private void shape6() {
        if (ract) {
            ballX = 0;
            ballY = 0;
            ract = false;
        }
        if (ballX < xlength && ballY < ylength && topTodown) {
            ballX += ballSpeed;
            ballY += ballSpeed;
        } else if (ballX >= xlength && ballY > 0) {
            ballY -= ballSpeed;
            topTodown = false;
        } else if (ballX > 0 && ballY < ylength) {
            ballX -= ballSpeed;
            ballY += ballSpeed;
        } else if (ballX <= 0 && ballY > 0) {
            ballY -= ballSpeed;
            if (ballY <= 0) {
                onetime = false;
            }
        }
        x = ballX;
        y = ballY;
    }

    private void shape7() {
        if (ract) {
            ballX = 0;
            ballY = 0;
            ract = false;
        }
        if (ballX < xlength && ballY <= 0) {
            ballX += ballSpeed;
            if(ballX>xlength){
                ballX=xlength;
            }
        } else if (ballY < ylength && ballX >= xlength) {
            ballY += ballSpeed;
            if(ballY>ylength){
                ballY=ylength;
            }
        } else if (ballX > 0 && ballY >= ylength) {
            ballX -= ballSpeed;
            if(ballX<0){
                ballX=0;
            }
        } else if (ballY > 0 && ballX >= 0) {
            ballY -= ballSpeed;
            if (ballY <= 0) {
                onetime = false;
            }
        }
        x = ballX;
        y = ballY;
    }

    private void shape8() {

        if (ract) {
            ballX = 0;
            ballY = ylength / 2;
            ract = false;
        }

        if (ballX < xlength && ballY <= ylength / 2) {
            ballX += ballSpeed;
            if (ballY > 0 && ballX < xlength / 2) {
                ballY -= ballSpeed;
            } else if (ballY <= ylength / 2) {
                ballY += ballSpeed;
            }
        } else if (ballX > 0 && ballY >= ylength / 2) {
            ballX -= ballSpeed;
            if (ballY > 0 && ballX >= xlength / 2) {
                ballY += ballSpeed;
            } else if (ballY > ylength / 2) {
                ballY -= ballSpeed;
                if (ballX <= 0) {
                    onetime = false;
                }
            }
        }
        x = ballX;
        y = ballY;
    }

    private void shape9() {
        if (ract) {
            ballX = xlength / 4; //(120)
            ballY = 0;
            ract = false;
        }
        if (ballX < (xlength * 3) / 4 && ballY == 0) {
            ballX += ballSpeed;
            smalltrangle1 = true;
        } else if (ballX < xlength && ballY < ylength / 2 && smalltrangle1) {
            ballY += ballSpeed;
            ballX += ballSpeed / 2;
        } else if (ballY < ylength && ballX > (xlength * 3) / 4) {
            ballY += ballSpeed;
            ballX -= ballSpeed / 2;
            smalltrangle1 = false;
            if(ballX<(xlength * 3) / 4){
                ballY=ylength;
            }
        } else if (ballX > (xlength) / 4 && ballY >= ylength) {
            ballX -= ballSpeed;
        } else if (ballY > ylength / 2 && ballX > 0) {
            ballX -= ballSpeed / 2;
            ballY -= ballSpeed;
        } else if (ballX < xlength / 4 && ballY > 0) {
            ballY -= ballSpeed;
            ballX += ballSpeed / 2;
            if (ballY <= 0 ||ballX>=xlength / 4 ) {
                onetime = false;
            }
        }
        x = ballX;
        y = ballY;

    }

    private void shape10() {
        if (ract) {
            ballX = 0;
            ballY = 0;
            ract = false;
        }

        if (ballX < xlength && ballY == 0) {
            ballX += ballSpeed;

        } else if (ballX > ((xlength * 3) / 4) && ballY < (ylength / 2)) {
            ballY += ballSpeed;
            ballX -= ballSpeed / 2;
        } else if (ballY < ylength && ballX < xlength && ballX >= (340)) {         //xlength * 3) / 4
            ballY += ballSpeed;
            ballX += ballSpeed / 2;
           if(ballX >= xlength){
               ballX= xlength;
               ballY=ylength;
           }
        } else if (ballX > 0 && ballY >= ylength) {
            ballX -= ballSpeed;
        } else if (ballY > ylength / 2 && ballX < (xlength) / 4) {
            ballX += ballSpeed / 2;
            ballY -= ballSpeed;
        } else if (ballX > 0 && ballY > 0) {
            ballY -= ballSpeed;
            ballX -= ballSpeed / 2;
            if (ballY <= 0 ||ballX<=0) {
                onetime = false;
            }
        }
        x = ballX;
        y = ballY;

    }

    private void shape11() {
        if (ract) {
            ballX = 0;
            ballY = 0;
            ract = false;
        }
        if (ballX < xlength && ballY == 0) {
            ballX += ballSpeed;
        } else if (ballX >= xlength && ballY < ylength / 2) {
            ballY += ballSpeed;
        } else if (ballY >= ylength / 2 && ballX > xlength / 2) {
            ballX -= ballSpeed;
        } else if (ballX <= xlength / 2 && ballY < ylength && ballX >0) {
            ballY += ballSpeed;
        } else if (ballY >= ylength && ballX > 0) {
            ballX -= ballSpeed;
        } else if (ballX <= 0 && ballY > 0) {
            ballY -= ballSpeed;
            if (ballY <= 0) {
                onetime = false;
            }
        }

        x = ballX;
        y = ballY;

    }

    private void shape12() {
        if (ract) {
            ballX = 120;
            ballY = 0;
            ract = false;
        }
        if (ballX < 360 && ballY < 480 && topTodown) {
            ballX += ballSpeed / 2;
            ballY += ballSpeed;
            x = ballX;
            y = ballY;
        } else if (ballX < 480 && ballY >= 480 && ballX >= 360) {
            ballX += ballSpeed;
            eightshape1(ballX, ballY);
            topTodown = false;
        } else if (ballY > 0 && ballX >= 480) {
            ballY -= ballSpeed;
            eightshape1(ballX, ballY);
        } else if (ballX > 360 && ballY <= 0) {
            ballX -= ballSpeed;
            eightshape1(ballX, ballY);
        } else if (ballY < 480 && ballX > 120) {
            ballX -= ballSpeed / 2;
            ballY += ballSpeed;
            x = ballX;
            y = ballY;
            if(x<=120){
                ballY=480;
            }
        } else if (ballX > 0 && ballY >= 480) {
            ballX -= ballSpeed;
            eightshape2(ballX, ballY);
        } else if (ballX <= 0 && ballY > 0) {
            ballY -= ballSpeed;
            eightshape2(ballX, ballY);
        } else if (ballY <= 0 && ballX < 120) {
            ballX += ballSpeed;
            eightshape2(ballX, ballY);
            if (ballX <= 120) {
                onetime = false;
            }
        }

    }

    private void shape13() {
        if (ract) {
            ballX = 0;
            ballY = ylength / 2;
            ract = false;
        }
        if (ballX < xlength && ballY == ylength / 2) {
            ballX += ballSpeed;
            x = ballX;
            y = ballY;
            if(ballX > xlength){
                ballX=xlength;
            }
        } else if (ballX <= xlength && ballY >= ylength / 2) {
            angle = (int) (Math.toDegrees(Math.atan2(ballY - center_y, ballX - center_x)));
            x = (float) (250 + Math.cos(angle * Math.PI / 180F) * 250);
            y = (float) (250 + Math.sin(angle * Math.PI / 180F) * 250);
            if (ballX == xlength && ballY < ylength) {
                ballY += ballSpeed;
            } else if (ballX > 0 && ballY >= ylength) {
                ballX -= ballSpeed;
            } else if (ballX <= 0 && ballY > ylength / 2) {
                ballY -= ballSpeed;
                if (ballY <= ylength / 2) {
                    onetime = false;
                }
            }
        }
    }

    private void shape14() {
        if (ract) {
            ballX = 0;
            ballY = 0;
            ract = false;
        }
        if (ballX < xlength && ballY == 0) {
            ballX += ballSpeed;
        } else if (ballX >= xlength && ballY < ylength) {
            ballY += ballSpeed;
        } else if (ballY > ylength / 2 && ballX > xlength / 2) {
            ballX -= ballSpeed;
            ballY -= ballSpeed;
            if(ballY<ylength/2){
                ballY =ylength/2;
            }
        } else if (ballX > 0 && ballY >= ylength / 2) {
            ballX -= ballSpeed;
            ballY += ballSpeed;
        } else if (ballY > 0 && ballX <= 0) {
            ballY -= ballSpeed;
            if (ballY <= 0) {
                onetime = false;
            }
        }
        x = ballX;
        y = ballY;

    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        xMax = w;
        yMax = h;
    }

    private void eightshape1(float _x, float _y) {
        angle = (int) (Math.toDegrees(Math.atan2(_y - center_y, _x - center_x)));
        x = (float) (250 + Math.cos(angle * Math.PI / 180F) * 250);
        y = (float) (250 + Math.sin(angle * Math.PI / 180F) * 250);

    }

    private void eightshape2(float _x, float _y) {
        angle = (int) (Math.toDegrees(Math.atan2(_y - center_y, _x - center_x)));
        x = (float) (250 + Math.cos(angle * Math.PI / 180F) * 250);
        y = (float) (250 + Math.sin(angle * Math.PI / 180F) * 250);
    }
}
