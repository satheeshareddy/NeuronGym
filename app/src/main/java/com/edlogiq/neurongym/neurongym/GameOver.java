package com.edlogiq.neurongym.neurongym;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.constant.RefrenceWrapper;
import com.edlogiq.neurongym.game.AfterMatchGame;
import com.edlogiq.neurongym.game.BlinkGame;
import com.edlogiq.neurongym.game.BlinkProGame;
import com.edlogiq.neurongym.game.BrainFlashGame;
import com.edlogiq.neurongym.game.DancingBallGame;
import com.edlogiq.neurongym.game.DualFocusGame;
import com.edlogiq.neurongym.game.DualFocusProGame;
import com.edlogiq.neurongym.game.MatchItGame;
import com.edlogiq.neurongym.game.MatchItProGame;
import com.edlogiq.neurongym.game.MemoryMatrixProGame;
import com.edlogiq.neurongym.game.MemoryMatrixgame;
import com.edlogiq.neurongym.game.MoneyGame;
import com.edlogiq.neurongym.game.ReversalGame;
import com.edlogiq.neurongym.game.ReversalProGame;
import com.edlogiq.neurongym.game.ShapesGame;
import com.edlogiq.neurongym.game.SolveItGame;
import com.edlogiq.neurongym.game.SpeedShopGame;
import com.edlogiq.neurongym.game.SpotItGame;
import com.edlogiq.neurongym.game.SpotItProGame;
import com.edlogiq.neurongym.game.TrackTheRouteGame;
import com.gc.materialdesign.views.Slider;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.util.List;
import com.edlogiq.neurongym.R;


public class GameOver extends ActionBarActivity implements View.OnClickListener{

    private RefrenceWrapper refrence;
    private Slider slider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        refrence=RefrenceWrapper.getRefrenceWrapper(this);


        if((DataBase.getUserObjectId(this).toString()).equals("null")){
            parse();
        }
        ((TextView)findViewById(R.id.scorevalue)).setText("" + refrence.getScore());
        ((LinearLayout)findViewById(R.id.restartgame)).setOnClickListener(this);
        ((LinearLayout)findViewById(R.id.gamepage)).setOnClickListener(this);
        ((TextView)findViewById(R.id.accuracy)).setText(refrence.getAccuracy()+"%");
        slider=(Slider)findViewById(R.id.slider);
        sethighestscore();
    }

    private void sethighestscore() {
        if((refrence.getGame()).equals("DualFocus")){
           if((refrence.getScore())> (DataBase.getDualFocus(this))){
               DataBase.setDualFocus(refrence.getScore(), this);
               if((refrence.getMultiplier())>(DataBase.getDualFocusM(this))){
                   DataBase.setDualFocusM(refrence.getMultiplier(), this);
               }
           }
            updateScore("dualfocus", refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("DualFocusPro")){
            if((refrence.getScore())> (DataBase.getDualFocusPro(this))){
                DataBase.setDualFocusPro(refrence.getScore(), this);
                if((refrence.getMultiplier())>(DataBase.getDualFocusProM(this))){
                    DataBase.setDualFocusProM(refrence.getMultiplier(), this);
                }
            }
            updateScore("dualfocuspro",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("Blink")){
            if((refrence.getScore())> (DataBase.getBlink(this))){
                DataBase.setBlink(refrence.getScore(), this);
                if((refrence.getMultiplier())>(DataBase.getBlinkM(this))){
                    DataBase.setBlinkM(refrence.getMultiplier(), this);
                }
            }
            updateScore("blink",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("BlinkPro")){
            if((refrence.getScore())> (DataBase.getBlinkPro(this))){
                DataBase.setBlinkPro(refrence.getScore(), this);
                if((refrence.getMultiplier())>(DataBase.getBlinkProM(this))){
                    DataBase.setBlinkProM(refrence.getMultiplier(), this);
                }
            }
            updateScore("blinkpro", refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("TrackTheRought")){
            if((refrence.getScore())> (DataBase.getTrackTheRoute(this))){
                DataBase.setTrackTheRoute(refrence.getScore(), this);
                if((refrence.getMultiplier())>(DataBase.getTrackTheRouteM(this))){
                    DataBase.setTrackTheRouteM(refrence.getMultiplier(), this);
                }
            }
            updateScore("tracktherought",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("MemoryMatrix")){
            if((refrence.getScore())> (DataBase.getMemoryMatrix(this))){
                DataBase.setMemoryMatrix(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getMemoryMatrixM(this))){
                    DataBase.setMemoryMatrixM(refrence.getMultiplier(), this);
                }
            }
            updateScore("memorymatrix",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("MemoryMatrixPro")){
            if((refrence.getScore())> (DataBase.getMemoryMatrixPro(this))){
                DataBase.setMemoryMatrixPro(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getMemoryMatrixProM(this))){
                    DataBase.setMemoryMatrixProM(refrence.getMultiplier(), this);
                }
            }
            updateScore("memorymatrixpro",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("DancingBall")){
            if((refrence.getScore())> (DataBase.getDancingBall(this))){
                DataBase.setDancingBall(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getDancingBallM(this))){
                    DataBase.setDancingBallM(refrence.getMultiplier(), this);
                }
            }
            updateScore("dancingball",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("Shapes")){
            if((refrence.getScore())> (DataBase.getShapes(this))){
                DataBase.setShapes(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getShapesM(this))){
                    DataBase.setShapesM(refrence.getMultiplier(), this);
                }
            }
            updateScore("shapes",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("MatchIt")){
            if((refrence.getScore())> (DataBase.getMatchIt(this))){
                DataBase.setMatchIt(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getMatchItM(this))){
                    DataBase.setMatchItM(refrence.getMultiplier(), this);
                }
            }
            updateScore("matchit",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("MatchItPro")){
            if((refrence.getScore())> (DataBase.getMatchItPro(this))){
                DataBase.setMatchItPro(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getMatchItProM(this))){
                    DataBase.setMatchItProM(refrence.getMultiplier(), this);
                }
            }
            updateScore("matchitpro",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("Reversal")){
            if((refrence.getScore())> (DataBase.getReversal(this))){
                DataBase.setReversal(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getReversalM(this))){
                    DataBase.setReversalM(refrence.getMultiplier(), this);
                }
            }
            updateScore("reversal",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("ReversalPro")){
            if((refrence.getScore())> (DataBase.getReversalPro(this))){
                DataBase.setReversalPro(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getReversalProM(this))){
                    DataBase.setReversalProM(refrence.getMultiplier(), this);
                }
            }
            updateScore("reversalpro",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("MoneyGame")){
            if((refrence.getScore())> (DataBase.getMoneyGame(this))){
                DataBase.setMoneyGame(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getMoneyGameM(this))){
                    DataBase.setMoneyGameM(refrence.getMultiplier(), this);
                }
            }
            updateScore("moneygame",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("SolveIt")){
            if((refrence.getScore())> (DataBase.getSolveIt(this))){
                DataBase.setSolveIt(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getSolveItM(this))){
                    DataBase.setSolveItM(refrence.getMultiplier(), this);
                }
            }
            updateScore("solveit",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("AfterMatch")){
            if((refrence.getScore())> (DataBase.getAfterMath(this))){
                DataBase.setAfterMath(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getAfterMathM(this))){
                    DataBase.setAfterMathM(refrence.getMultiplier(), this);
                }
            }
            updateScore("aftermatch",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("SpeedShop")){
            if((refrence.getScore())> (DataBase.getSpeedShop(this))){
                DataBase.setSpeedShop(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getSpeedShopM(this))){
                    DataBase.setSpeedShopM(refrence.getMultiplier(), this);
                }
            }
            updateScore("speedshop",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("SpotIt")){
            if((refrence.getScore())> (DataBase.getSpotIt(this))){
                DataBase.setSpotIt(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getSpotItM(this))){
                    DataBase.setSpotItM(refrence.getMultiplier(), this);
                }
            }
            updateScore("spotit",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("SpotItPro")){
            if((refrence.getScore())> (DataBase.getSpotItPro(this))){
                DataBase.setSpotItPro(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getSpotItProM(this))){
                    DataBase.setSpotItProM(refrence.getMultiplier(), this);
                }
            }
            updateScore("spotitpro",refrence.getScore(),refrence.getMultiplier());
        }else if((refrence.getGame()).equals("BrainFlash")){
            if((refrence.getScore())> (DataBase.getBrainFlash(this))){
                DataBase.setBrainFlash(refrence.getScore(),this);
                if((refrence.getMultiplier())>(DataBase.getBrainFlashM(this))){
                    DataBase.setBrainFlashM(refrence.getMultiplier(), this);
                }
            }
            updateScore("brainflash",refrence.getScore(),refrence.getMultiplier());
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.restartgame){
            game();
        }else if(v.getId()==R.id.gamepage){
            Intent intent=new Intent(this, GameMenu.class);
            startActivity(intent);
            this.finish();
        }
    }

    private void game() {
        if((refrence.getGame()).equals("DualFocus")){
            Intent intent=new Intent(this, DualFocusGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("DualFocusPro")){
            Intent intent=new Intent(this, DualFocusProGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("Blink")){
            Intent intent=new Intent(this, BlinkGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("BlinkPro")){
            Intent intent=new Intent(this, BlinkProGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("TrackTheRought")){
            Intent intent=new Intent(this, TrackTheRouteGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("MemoryMatrix")){
            Intent intent=new Intent(this, MemoryMatrixgame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("MemoryMatrixPro")){
            Intent intent=new Intent(this, MemoryMatrixProGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("DancingBall")){
            Intent intent=new Intent(this, DancingBallGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("Shapes")){
            Intent intent=new Intent(this, ShapesGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("MatchIt")){
            Intent intent=new Intent(this, MatchItGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("MatchItPro")){
            Intent intent=new Intent(this, MatchItProGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("Reversal")){
            Intent intent=new Intent(this, ReversalGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("ReversalPro")){
            Intent intent=new Intent(this, ReversalProGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("MoneyGame")){
            Intent intent=new Intent(this, MoneyGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("SolveIt")){
            Intent intent=new Intent(this, SolveItGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("AfterMatch")){
            Intent intent=new Intent(this, AfterMatchGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("SpeedShop")){
            Intent intent=new Intent(this, SpeedShopGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("SpotIt")){
            Intent intent=new Intent(this, SpotItGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("SpotItPro")){
            Intent intent=new Intent(this, SpotItProGame.class);
            startActivity(intent);
            this.finish();
        }else if((refrence.getGame()).equals("BrainFlash")){
            Intent intent=new Intent(this, BrainFlashGame.class);
            startActivity(intent);
            this.finish();
        }
    }

      double game_val,avg_game;
    private void updateScore(final String value, final int score,final int muilti){
        Log.e("value",value+"  "+score+"   "+ParseUser.getCurrentUser());
        if(value.equals("dualfocus")){
            game_val=1000;
             avg_game=800;
        }else  if(value.equals("dualfocuspro")){
            game_val=1000;
            avg_game=800;
        }else  if(value.equals("blink")){
            game_val=1000;
            avg_game=800;
        }else  if(value.equals("blinkpro")){
            game_val=1000;
            avg_game=800;
        }else  if(value.equals("tracktherought")){
            game_val=1000;
            avg_game=800;
        }else  if(value.equals("memorymatrix")){
            game_val=1000;
            avg_game=800;
        }else  if(value.equals("memorymatrixpro")){
            game_val=1000;
            avg_game=800;
        }else  if(value.equals("dancingball")){
            game_val=1000;
            avg_game=800;
        }else  if(value.equals("shapes")){
            game_val=1000;
            avg_game=800;
        }else  if(value.equals("matchit")){
            game_val=1000;
            avg_game=800;
        }else if(value.equals("matchitpro")){
            game_val=1000;
            avg_game=800;
        }else if(value.equals("reversal")){
            game_val=1000;
            avg_game=800;
        }else if(value.equals("reversalpro")){
            game_val=1000;
            avg_game=800;
        }else if(value.equals("moneygame")){
            game_val=1000;
            avg_game=800;
        }else if(value.equals("solveit")){
            game_val=1000;
            avg_game=800;
        }else if(value.equals("aftermatch")){
            game_val=1000;
            avg_game=800;
        }else if(value.equals("speedshop")){
            game_val=1000;
            avg_game=800;
        }else if(value.equals("spotit")){
            game_val=1000;
            avg_game=800;
        } if(value.equals("spotitpro")){
            game_val=1000;
            avg_game=800;
        }else if(value.equals("brainflash")){
            game_val=1000;
            avg_game=800;
        }
            double ani=500+(((score-game_val)/avg_game)*75);
             Log.e("ANI",""+(int)ani+"  "+(((score-game_val)/avg_game)*75));
             int ani_val=(int)ani;
             slider.setValue(ani_val);
            ((TextView)findViewById(R.id.ani_score)).setText(""+ani_val);
            new myAsyncTask(value,score,ani_val).execute();
    }

    ProgressDialog progressDialog;
    private class myAsyncTask extends AsyncTask<String, Integer, String> {

        String gameval;
        int gamescore,ANI;
        public myAsyncTask(String value, int score,int _ani) {
            gameval=value;
            gamescore=score;
            ANI=_ani;
            Log.e("pre","prehello11111111");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(GameOver.this, "", "Logging in...", true);
            Log.e("pre","prehello");
        }

        @Override
        protected String doInBackground(String... params) {



            ParseObject   userinfo = new ParseObject("GameInfo");
            userinfo.put("user", ParseUser.getCurrentUser());
            userinfo.put("game", gameval);
            userinfo.put("score",gamescore);
            userinfo.put("ani", ANI);
            userinfo.saveEventually();

//            if(gameval.equals("dualfocus")){
//            ParseObject   userinfo = new ParseObject("dualfocus");
//            userinfo.put("user", ParseUser.getCurrentUser());
//            userinfo.put("game", gameval);
//            userinfo.put("score",gamescore);
//            userinfo.put("ani", ANI);
//            userinfo.saveEventually();
//            }else if(gameval.equals("dualfocuspro")){
//                ParseObject   userinfo = new ParseObject("dualfocuspro");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("blink")){
//                ParseObject   userinfo = new ParseObject("blink");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("blinkpro")){
//                ParseObject   userinfo = new ParseObject("blinkpro");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("tracktherought")){
//                ParseObject   userinfo = new ParseObject("tracktherought");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("memorymatrix")){
//                ParseObject   userinfo = new ParseObject("memorymatrix");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("memorymatrixpro")){
//                ParseObject   userinfo = new ParseObject("memorymatrixpro");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("dancingball")){
//                ParseObject   userinfo = new ParseObject("dancingball");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("shapes")){
//                ParseObject   userinfo = new ParseObject("shapes");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("matchit")){
//                ParseObject   userinfo = new ParseObject("matchit");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("matchitpro")){
//                ParseObject   userinfo = new ParseObject("matchitpro");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("reversal")){
//                ParseObject   userinfo = new ParseObject("reversal");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("reversalpro")){
//                ParseObject   userinfo = new ParseObject("reversalpro");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("moneygame")){
//                ParseObject   userinfo = new ParseObject("moneygame");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("solveit")){
//                ParseObject   userinfo = new ParseObject("solveit");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("aftermatch")){
//                ParseObject   userinfo = new ParseObject("aftermatch");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("speedshop")){
//                ParseObject   userinfo = new ParseObject("speedshop");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("spotit")){
//                ParseObject   userinfo = new ParseObject("spotit");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("spotitpro")){
//                ParseObject   userinfo = new ParseObject("spotitpro");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }else if(gameval.equals("brainflash")){
//                ParseObject   userinfo = new ParseObject("brainflash");
//                userinfo.put("user", ParseUser.getCurrentUser());
//                userinfo.put("game", gameval);
//                userinfo.put("score",gamescore);
//                userinfo.put("ani", ANI);
//                userinfo.saveEventually();
//            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            Log.e("end","end");

        }
    }


    private void parse() {
//        Log.e("parse","parse     "+ ParseUser.getCurrentUser().getObjectId().toString());
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("UserIformation");
        query.whereEqualTo("parent", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> markers, ParseException e) {
                if (e == null) {
                    Log.e("objectid", markers + "      ");
                    if (markers.size() != 0)
                        DataBase.setUserObjectId(markers.get(0).getObjectId().toString(), GameOver.this);
                } else {
                    Log.e("value error", "" + e.getMessage());
                }
            }
        });
    }
}
