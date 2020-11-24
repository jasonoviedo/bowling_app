package org.jobsity.bowling.engine;

/**
 * API for basic frame info consulting
 */
public interface BowlingFrame {
    boolean isStrike();

    boolean isSpare();

    int getFrameNumber();

    int getScore();
}
