package org.jobsity.bowling.engine;

/**
 * API for basic play info consulting
 */
public interface BowlingPlay {
    int getScore();

    boolean isStrike();

    boolean isSpare();

    int getPinCount();

    boolean isFoul();
}
