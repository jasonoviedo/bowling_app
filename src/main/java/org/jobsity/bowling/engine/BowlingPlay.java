package org.jobsity.bowling.engine;

/**
 * Data class to hold play (ball) info.
 */
public class BowlingPlay {
    private final String name;
    private final int pinCount;
    private final boolean foul;

    public BowlingPlay(String name, int pinCount, boolean foul) {
        if (pinCount > 10 || pinCount < 0)
            throw new IllegalArgumentException("Invalid pin count: " + pinCount + ". Only values 0 -> 10 are permitted");

        if (foul && pinCount != 0)
            throw new IllegalArgumentException("Foul and pinCount set for the same play");

        this.name = name;
        this.pinCount = pinCount;
        this.foul = foul;
    }


    public String getName() {
        return name;
    }

    public int getPinCount() {
        return pinCount;
    }

    public boolean isFoul() {
        return foul;
    }
}