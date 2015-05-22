package com.edlogiq.neurongym.constant;

import android.app.Activity;
import android.graphics.Bitmap;

/**
 * Created by incarnation-pc on 3/2/2015.
 */
public class RefrenceWrapper {

    public static RefrenceWrapper refrenceWrapper;
    private Activity activity;
    private int dualfocus, dualfocuspro, blink, blinkpro, tracktherought, memorymatrix, memorymatrixpro,
            dancingball, shapes, matchit, matchitpro, reversal, reversalpro, moneygame, solveit,
            aftermatch, speedshop, spotit, spotitpro, brainflash,accuracy;

    private int shape,score,multiplier;
    private String game,name,email,gender;
    private Bitmap image;
    private boolean onetime;

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getScore() {
        return score;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public boolean isOnetime() {
        return onetime;
    }

    public void setOnetime(boolean onetime) {
        this.onetime = onetime;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getGame() {
        return game;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setScore(int score) {

        this.score = score;
    }

    private String theam;



    public RefrenceWrapper(Activity _activity) {
        this.activity = _activity;
    }

    public static RefrenceWrapper getRefrenceWrapper(Activity _activity) {
        if (refrenceWrapper == null) {
            refrenceWrapper = new RefrenceWrapper(_activity);

        }
        return refrenceWrapper;
    }

    public String getTheam() {
        return theam;
    }

    public void setTheam(String theam) {
        this.theam = theam;
    }

    public int getDualfocus() {
        return dualfocus;
    }

    public void setDualfocus(int dualfocus) {
        this.dualfocus = dualfocus;
    }

    public int getDualfocuspro() {
        return dualfocuspro;
    }

    public void setDualfocuspro(int dualfocuspro) {
        this.dualfocuspro = dualfocuspro;
    }

    public int getBlink() {
        return blink;
    }

    public void setBlink(int blink) {
        this.blink = blink;
    }

    public int getBlinkpro() {
        return blinkpro;
    }

    public void setBlinkpro(int blinkpro) {
        this.blinkpro = blinkpro;
    }

    public int getTracktherought() {
        return tracktherought;
    }

    public void setTracktherought(int tracktherought) {
        this.tracktherought = tracktherought;
    }

    public int getMemorymatrix() {
        return memorymatrix;
    }

    public void setMemorymatrix(int memorymatrix) {
        this.memorymatrix = memorymatrix;
    }

    public int getMemorymatrixpro() {
        return memorymatrixpro;
    }

    public void setMemorymatrixpro(int memorymatrixpro) {
        this.memorymatrixpro = memorymatrixpro;
    }

    public int getDancingball() {
        return dancingball;
    }

    public void setDancingball(int dancingball) {
        this.dancingball = dancingball;
    }

    public int getShapes() {
        return shapes;
    }

    public void setShapes(int shapes) {
        this.shapes = shapes;
    }

    public int getMatchit() {
        return matchit;
    }

    public void setMatchit(int matchit) {
        this.matchit = matchit;
    }

    public int getMatchitpro() {
        return matchitpro;
    }

    public void setMatchitpro(int matchitpro) {
        this.matchitpro = matchitpro;
    }

    public int getReversal() {
        return reversal;
    }

    public void setReversal(int reversal) {
        this.reversal = reversal;
    }

    public int getReversalpro() {
        return reversalpro;
    }

    public void setReversalpro(int reversalpro) {
        this.reversalpro = reversalpro;
    }

    public int getMoneygame() {
        return moneygame;
    }

    public void setMoneygame(int moneygame) {
        this.moneygame = moneygame;
    }

    public int getSolveit() {
        return solveit;
    }

    public void setSolveit(int solveit) {
        this.solveit = solveit;
    }

    public int getAftermatch() {
        return aftermatch;
    }

    public void setAftermatch(int aftermatch) {
        this.aftermatch = aftermatch;
    }

    public int getSpeedshop() {
        return speedshop;
    }

    public void setSpeedshop(int speedshop) {
        this.speedshop = speedshop;
    }

    public int getSpotit() {
        return spotit;
    }

    public void setSpotit(int spotit) {
        this.spotit = spotit;
    }

    public int getSpotitpro() {
        return spotitpro;
    }

    public void setSpotitpro(int spotitpro) {
        this.spotitpro = spotitpro;
    }

    public int getBrainflash() {
        return brainflash;
    }

    public void setBrainflash(int brainflash) {
        this.brainflash = brainflash;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

}
