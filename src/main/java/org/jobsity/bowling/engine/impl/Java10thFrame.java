package org.jobsity.bowling.engine.impl;

import org.jetbrains.annotations.NotNull;

/**
 * Models the special case for the 10th frame, whose logic changes
 * slightly (especially scoring)
 */
public final class Java10thFrame extends JavaFrame {
    private int frameNumber;

    public Java10thFrame(int frameNumber) {
        super(frameNumber);
        this.frameNumber = frameNumber;
    }

    @Override
    protected void fixRemainingBalls(@NotNull JavaPlay play) {
        if (play.getStrike()) {
            this.setRemainingBalls(this.getRemainingBalls() + 1);
            this.setRemainingPinCount(this.getRemainingPinCount() + 20);
        }

        if (play.getSpare()) {
            this.setRemainingBalls(this.getRemainingBalls() + 1);
            this.setRemainingPinCount(this.getRemainingPinCount() + 10);
        }
    }

    /**
     * Extend base functionality to include the third ball when appropriate
     */
    @NotNull
    public String getPrintString() {
        String thirdBall = this.getPlays().size() == 3
                ? this.getPlays().get(2).getPrintString()
                : "";
        return super.getPrintString() + thirdBall;
    }

    /**
     * Score is slightly different for the 10th frame
     */
    protected void doCalculateScore() {
        score = plays
                .stream()
                .mapToInt(JavaPlay::score)
                .sum();
    }

    public int getFrameNumber() {
        return this.frameNumber;
    }

    public void setFrameNumber(int value) {
        this.frameNumber = value;
    }
}
