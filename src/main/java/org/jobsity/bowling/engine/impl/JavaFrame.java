package org.jobsity.bowling.engine.impl;

import org.jetbrains.annotations.NotNull;
import org.jobsity.bowling.exceptions.IllegalPlayException;

import java.util.ArrayList;
import java.util.List;

/**
 * Models a frame. All logic regarding frame scoring and finishing
 * is contained here
 */
@SuppressWarnings("unused")
public class JavaFrame {
    @NotNull
    protected List<JavaPlay> plays;
    private int remainingPinCount;
    private int remainingBalls;
    private boolean unscored;
    protected int score;
    private final List<JavaPlay> nextBalls;
    private int frameNumber;

    public JavaFrame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.plays = new ArrayList<>();
        this.remainingPinCount = 10;
        this.remainingBalls = 2;
        this.unscored = true;
        this.nextBalls = new ArrayList<>();
    }

    /**
     * Adds a play to the frame
     *
     * @return true if the frame is finished
     * @throws IllegalPlayException If the frame has already been completed
     */
    public boolean addPlay(@NotNull JavaPlay play) {
        if (this.remainingBalls == 0) {
            throw new IllegalPlayException("This frame has already finished");
        }

        this.remainingBalls -= 1;
        this.remainingPinCount -= play.getPinCount();
        this.plays.add(play);
        play.setStrike(this.remainingPinCount == 0 && this.plays.size() == 1);
        play.setSpare(this.remainingPinCount == 0 && this.plays.size() == 2);
        this.unscored = play.getStrike() || play.getSpare();
        this.fixRemainingBalls(play);
        return this.remainingBalls == 0;
    }

    /**
     * If strike, the frame ends
     *
     * @param play Current play
     */
    protected void fixRemainingBalls(@NotNull JavaPlay play) {
        if (play.getStrike()) {
            this.remainingBalls = 0;
        }
    }

    @NotNull
    public String getPrintString() {
        return (!this.strike() ? (this.plays.get(0)).getPrintString() : "") + '\t' + (this.strike() ? "x" : (this.spare() ? "/" : (this.plays.get(1)).getPrintString())) + '\t';
    }

    /**
     * Future plays will affect this frame when strike or spare, ignored otherwise
     *
     * @param play Next ball
     */
    public final void recordExtraBall(@NotNull JavaPlay play) {
        if (this.unscored) {
            this.nextBalls.add(play);
            this.scoreFrame();
        }
    }

    public final void scoreFrame() {
        if (this.strike() && this.nextBalls.size() == 2 || this.spare() && this.nextBalls.size() == 1) {
            this.unscored = false;
        }

        this.doCalculateScore();
    }

    protected void doCalculateScore() {
        int res = this.nextBalls
                .stream()
                .mapToInt(JavaPlay::score)
                .sum();
        score = 10 - this.remainingPinCount + res;
    }

    private boolean strike() {
        return this.plays.size() == 1 && this.remainingPinCount == 0;
    }

    private boolean spare() {
        return this.plays.size() == 2 && this.remainingPinCount == 0;
    }

    //********************************************************
    // PROPERTY METHODS
    //********************************************************

    public int getFrameNumber() {
        return this.frameNumber;
    }

    public void setFrameNumber(int var1) {
        this.frameNumber = var1;
    }

    @NotNull
    public final List<JavaPlay> getPlays() {
        return this.plays;
    }

    public final int getRemainingPinCount() {
        return this.remainingPinCount;
    }

    public final void setRemainingPinCount(int value) {
        this.remainingPinCount = value;
    }

    public final int getRemainingBalls() {
        return this.remainingBalls;
    }

    public final void setRemainingBalls(int value) {
        this.remainingBalls = value;
    }

    public final boolean getUnscored() {
        return this.unscored;
    }

    public final void setUnscored(boolean value) {
        this.unscored = value;
    }

    public final int getScore() {
        return this.score;
    }

    public final void setScore(int value) {
        this.score = value;
    }
}
