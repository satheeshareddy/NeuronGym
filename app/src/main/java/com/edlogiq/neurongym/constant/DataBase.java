package com.edlogiq.neurongym.constant;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by incarnation-pc on 3/3/2015.
 */
public class DataBase {

    public DataBase() {

    }

//   Selected Languaje
    public static void setLanguage(String value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE).edit();
        pref.putString("language", value);
        pref.commit();
    }

    public static String getLanguage(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
        return prefs.getString("language", "en");
    }

    //   User Login
    public static void setLogin(String value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("LOGIN", Context.MODE_PRIVATE).edit();
        pref.putString("login", value);
        pref.commit();
    }
    public static String getLogin(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        return prefs.getString("login", "logout");
    }

    //   User parse object Id
    public static void setUserObjectId(String value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("USERID", Context.MODE_PRIVATE).edit();
        pref.putString("userid", value);
        pref.commit();
    }

    public static String getUserObjectId(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("USERID", Context.MODE_PRIVATE);
        return prefs.getString("userid", "null");
    }
    //   Selected theme
    public static void setTheam(String value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("THEAM", Context.MODE_PRIVATE).edit();
        pref.putString("theam", value);
        pref.commit();
    }

    public static String getTheam(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("THEAM", Context.MODE_PRIVATE);
        return prefs.getString("theam", "light");

    }



//   Set User Information

    public static void setUserName(String value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("USERNAME", Context.MODE_PRIVATE).edit();
        pref.putString("username", value);
        pref.commit();
    }
    public static String getUserName(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("USERNAME", Context.MODE_PRIVATE);
        return prefs.getString("username", "");
    }
    public static  String getUserImage(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("IMAGE", Context.MODE_PRIVATE);
        return prefs.getString( "image",null);
    }
    public static void setUserImage(Activity activity, String value) {
        SharedPreferences.Editor prefs = activity.getSharedPreferences("IMAGE", Context.MODE_PRIVATE).edit();
        prefs.putString("image", value);
        prefs.commit();
    }


    //   Set all 20 games Score

    public static void setDualFocus(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("DUALFocus", Context.MODE_PRIVATE).edit();
        pref.putInt("dualfocus", value);
        pref.commit();
    }

    public static int getDualFocus(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("DUALFocus", Context.MODE_PRIVATE);
        return prefs.getInt("dualfocus", 0);

    }

    public static void setDualFocusPro(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("DUALFocusPro", Context.MODE_PRIVATE).edit();
        pref.putInt("dualfocuspro", value);
        pref.commit();
    }

    public static int getDualFocusPro(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("DUALFocusPro", Context.MODE_PRIVATE);
        return prefs.getInt("dualfocuspro", 0);

    }

    public static void setBlink(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("BLINK", Context.MODE_PRIVATE).edit();
        pref.putInt("blink", value);
        pref.commit();
    }

    public static int getBlink(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("BLINK", Context.MODE_PRIVATE);
        return prefs.getInt("blink", 0);

    }

    public static void setBlinkPro(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("BLINKPRO", Context.MODE_PRIVATE).edit();
        pref.putInt("blinkpro", value);
        pref.commit();
    }

    public static int getBlinkPro(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("BLINKPRO", Context.MODE_PRIVATE);
        return prefs.getInt("blinkpro", 0);

    }


    public static void setTrackTheRoute(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("TRACKTHEROUTE", Context.MODE_PRIVATE).edit();
        pref.putInt("tracktheroute", value);
        pref.commit();
    }

    public static int getTrackTheRoute(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("TRACKTHEROUTE", Context.MODE_PRIVATE);
        return prefs.getInt("tracktheroute", 0);

    }

    public static void setMemoryMatrix(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("MEMORYMATRIX", Context.MODE_PRIVATE).edit();
        pref.putInt("memorymatrix", value);
        pref.commit();
    }

    public static int getMemoryMatrix(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("MEMORYMATRIX", Context.MODE_PRIVATE);
        return prefs.getInt("memorymatrix", 0);

    }


    public static void setMemoryMatrixPro(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("MEMORYMATRIXPRO", Context.MODE_PRIVATE).edit();
        pref.putInt("memorymatrixpro", value);
        pref.commit();
    }

    public static int getMemoryMatrixPro(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("MEMORYMATRIXPRO", Context.MODE_PRIVATE);
        return prefs.getInt("memorymatrixpro", 0);

    }


    public static void setDancingBall(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("DANCINGBALL", Context.MODE_PRIVATE).edit();
        pref.putInt("dancingball", value);
        pref.commit();
    }

    public static int getDancingBall(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("DANCINGBALL", Context.MODE_PRIVATE);
        return prefs.getInt("dancingball", 0);

    }


    public static void setShapes(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("SHAPES", Context.MODE_PRIVATE).edit();
        pref.putInt("shapes", value);
        pref.commit();
    }

    public static int getShapes(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SHAPES", Context.MODE_PRIVATE);
        return prefs.getInt("shapes", 0);

    }

    public static void setMatchIt(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("MATCHIT", Context.MODE_PRIVATE).edit();
        pref.putInt("matchit", value);
        pref.commit();
    }

    public static int getMatchIt(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("MATCHIT", Context.MODE_PRIVATE);
        return prefs.getInt("matchit", 0);

    }

    public static void setMatchItPro(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("MATCHITPRO", Context.MODE_PRIVATE).edit();
        pref.putInt("matchitpro", value);
        pref.commit();
    }

    public static int getMatchItPro(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("MATCHITPRO", Context.MODE_PRIVATE);
        return prefs.getInt("matchitpro", 0);

    }

    public static void setReversal(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("REVERSAL", Context.MODE_PRIVATE).edit();
        pref.putInt("reversal", value);
        pref.commit();
    }

    public static int getReversal(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("REVERSAL", Context.MODE_PRIVATE);
        return prefs.getInt("reversal", 0);

    }

    public static void setReversalPro(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("REVERSALPRO", Context.MODE_PRIVATE).edit();
        pref.putInt("reversalpro", value);
        pref.commit();
    }

    public static int getReversalPro(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("REVERSALPRO", Context.MODE_PRIVATE);
        return prefs.getInt("reversalpro", 0);

    }

    public static void setMoneyGame(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("MONEYGAME", Context.MODE_PRIVATE).edit();
        pref.putInt("moneygame", value);
        pref.commit();
    }

    public static int getMoneyGame(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("MONEYGAME", Context.MODE_PRIVATE);
        return prefs.getInt("moneygame", 0);

    }

    public static void setSolveIt(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("SOLVEIT", Context.MODE_PRIVATE).edit();
        pref.putInt("solveit", value);
        pref.commit();
    }

    public static int getSolveIt(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SOLVEIT", Context.MODE_PRIVATE);
        return prefs.getInt("solveit", 0);

    }

    public static void setAfterMath(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("AFTERMATH", Context.MODE_PRIVATE).edit();
        pref.putInt("aftermath", value);
        pref.commit();
    }

    public static int getAfterMath(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("AFTERMATH", Context.MODE_PRIVATE);
        return prefs.getInt("aftermath", 0);

    }

    public static void setSpeedShop(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("SPEEDSHOP", Context.MODE_PRIVATE).edit();
        pref.putInt("speedshop", value);
        pref.commit();
    }

    public static int getSpeedShop(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SPEEDSHOP", Context.MODE_PRIVATE);
        return prefs.getInt("speedshop", 0);

    }

    public static void setSpotIt(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("SPOTIT", Context.MODE_PRIVATE).edit();
        pref.putInt("spotit", value);
        pref.commit();
    }

    public static int getSpotIt(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SPOTIT", Context.MODE_PRIVATE);
        return prefs.getInt("spotit", 0);

    }

    public static void setSpotItPro(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("SPOTITPRO", Context.MODE_PRIVATE).edit();
        pref.putInt("spotitpro", value);
        pref.commit();
    }

    public static int getSpotItPro(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SPOTITPRO", Context.MODE_PRIVATE);
        return prefs.getInt("spotitpro", 0);

    }

    public static void setBrainFlash(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("BRAINFLASH", Context.MODE_PRIVATE).edit();
        pref.putInt("brainflash", value);
        pref.commit();
    }

    public static int getBrainFlash(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("BRAINFLASH", Context.MODE_PRIVATE);
        return prefs.getInt("brainflash", 0);
    }



    //   Set all 20 games multyplyer

    public static void setDualFocusM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("DUALFocusM", Context.MODE_PRIVATE).edit();
        pref.putInt("dualfocusm", value);
        pref.commit();
    }

    public static int getDualFocusM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("DUALFocusM", Context.MODE_PRIVATE);
        return prefs.getInt("dualfocusm", 0);

    }

    public static void setDualFocusProM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("DUALFocusProM", Context.MODE_PRIVATE).edit();
        pref.putInt("dualfocusprom", value);
        pref.commit();
    }

    public static int getDualFocusProM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("DUALFocusProM", Context.MODE_PRIVATE);
        return prefs.getInt("dualfocusprom", 0);

    }

    public static void setBlinkM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("BLINKM", Context.MODE_PRIVATE).edit();
        pref.putInt("blinkm", value);
        pref.commit();
    }

    public static int getBlinkM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("BLINKM", Context.MODE_PRIVATE);
        return prefs.getInt("blinkm", 0);

    }

    public static void setBlinkProM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("BLINKPROM", Context.MODE_PRIVATE).edit();
        pref.putInt("blinkprom", value);
        pref.commit();
    }

    public static int getBlinkProM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("BLINKPROM", Context.MODE_PRIVATE);
        return prefs.getInt("blinkprom", 0);

    }


    public static void setTrackTheRouteM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("TRACKTHEROUTEM", Context.MODE_PRIVATE).edit();
        pref.putInt("tracktheroutem", value);
        pref.commit();
    }

    public static int getTrackTheRouteM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("TRACKTHEROUTEM", Context.MODE_PRIVATE);
        return prefs.getInt("tracktheroutem", 0);

    }

    public static void setMemoryMatrixM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("MEMORYMATRIXM", Context.MODE_PRIVATE).edit();
        pref.putInt("memorymatrixm", value);
        pref.commit();
    }

    public static int getMemoryMatrixM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("MEMORYMATRIXM", Context.MODE_PRIVATE);
        return prefs.getInt("memorymatrixm", 0);

    }


    public static void setMemoryMatrixProM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("MEMORYMATRIXPROM", Context.MODE_PRIVATE).edit();
        pref.putInt("memorymatrixprom", value);
        pref.commit();
    }

    public static int getMemoryMatrixProM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("MEMORYMATRIXPROM", Context.MODE_PRIVATE);
        return prefs.getInt("memorymatrixprom", 0);

    }


    public static void setDancingBallM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("DANCINGBALLM", Context.MODE_PRIVATE).edit();
        pref.putInt("dancingballm", value);
        pref.commit();
    }

    public static int getDancingBallM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("DANCINGBALLM", Context.MODE_PRIVATE);
        return prefs.getInt("dancingballm", 0);

    }


    public static void setShapesM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("SHAPESM", Context.MODE_PRIVATE).edit();
        pref.putInt("shapesm", value);
        pref.commit();
    }

    public static int getShapesM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SHAPESM", Context.MODE_PRIVATE);
        return prefs.getInt("shapesm", 0);

    }

    public static void setMatchItM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("MATCHITM", Context.MODE_PRIVATE).edit();
        pref.putInt("matchitm", value);
        pref.commit();
    }

    public static int getMatchItM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("MATCHITM", Context.MODE_PRIVATE);
        return prefs.getInt("matchitm", 0);

    }

    public static void setMatchItProM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("MATCHITPROM", Context.MODE_PRIVATE).edit();
        pref.putInt("matchitprom", value);
        pref.commit();
    }

    public static int getMatchItProM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("MATCHITPROM", Context.MODE_PRIVATE);
        return prefs.getInt("matchitprom", 0);

    }

    public static void setReversalM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("REVERSALM", Context.MODE_PRIVATE).edit();
        pref.putInt("reversalm", value);
        pref.commit();
    }

    public static int getReversalM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("REVERSALM", Context.MODE_PRIVATE);
        return prefs.getInt("reversalm", 0);

    }

    public static void setReversalProM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("REVERSALPROM", Context.MODE_PRIVATE).edit();
        pref.putInt("reversalprom", value);
        pref.commit();
    }

    public static int getReversalProM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("REVERSALPROM", Context.MODE_PRIVATE);
        return prefs.getInt("reversalprom", 0);

    }

    public static void setMoneyGameM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("MONEYGAMEM", Context.MODE_PRIVATE).edit();
        pref.putInt("moneygamem", value);
        pref.commit();
    }

    public static int getMoneyGameM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("MONEYGAMEM", Context.MODE_PRIVATE);
        return prefs.getInt("moneygamem", 0);

    }

    public static void setSolveItM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("SOLVEITM", Context.MODE_PRIVATE).edit();
        pref.putInt("solveitm", value);
        pref.commit();
    }

    public static int getSolveItM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SOLVEITM", Context.MODE_PRIVATE);
        return prefs.getInt("solveitm", 0);

    }

    public static void setAfterMathM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("AFTERMATHM", Context.MODE_PRIVATE).edit();
        pref.putInt("aftermathm", value);
        pref.commit();
    }

    public static int getAfterMathM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("AFTERMATHM", Context.MODE_PRIVATE);
        return prefs.getInt("aftermathm", 0);

    }

    public static void setSpeedShopM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("SPEEDSHOPM", Context.MODE_PRIVATE).edit();
        pref.putInt("speedshopm", value);
        pref.commit();
    }

    public static int getSpeedShopM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SPEEDSHOPM", Context.MODE_PRIVATE);
        return prefs.getInt("speedshopm", 0);

    }

    public static void setSpotItM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("SPOTITM", Context.MODE_PRIVATE).edit();
        pref.putInt("spotitm", value);
        pref.commit();
    }

    public static int getSpotItM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SPOTITM", Context.MODE_PRIVATE);
        return prefs.getInt("spotitm", 0);

    }

    public static void setSpotItProM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("SPOTITPROM", Context.MODE_PRIVATE).edit();
        pref.putInt("spotitprom", value);
        pref.commit();
    }

    public static int getSpotItProM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("SPOTITPROM", Context.MODE_PRIVATE);
        return prefs.getInt("spotitprom", 0);

    }

    public static void setBrainFlashM(int value, Activity activity) {
        SharedPreferences.Editor pref = activity.getSharedPreferences("BRAINFLASHM", Context.MODE_PRIVATE).edit();
        pref.putInt("brainflashm", value);
        pref.commit();
    }

    public static int getBrainFlashM(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("BRAINFLASHM", Context.MODE_PRIVATE);
        return prefs.getInt("brainflashm", 0);

    }

}
