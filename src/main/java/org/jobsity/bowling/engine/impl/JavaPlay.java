package org.jobsity.bowling.engine.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Data class to hold play (ball) info.
 */
public final class JavaPlay {
    @NotNull
    private String name;
    private boolean strike;
    private boolean spare;
    private int pinCount;
    private boolean foul;

    /**
     * @param name     Player name
     * @param pinCount Play pin count
     * @param foul     Whether foul or not
     * @throws IllegalArgumentException if pin count is invalid
     *                                  or if pin count and foul are set at the same time
     */
    public JavaPlay(@NotNull String name, int pinCount, boolean foul) {
        this.name = name;
        this.pinCount = pinCount;
        this.foul = foul;
        if (this.pinCount <= 10 && this.pinCount >= 0) {
            if (this.foul && this.pinCount != 0) {
                throw new IllegalArgumentException("Foul and pinCount set for the same play");
            }
        } else {
            throw new IllegalArgumentException("Invalid pin count: " + this.pinCount + ". Only values 0 -> 10 are permitted");
        }
    }

    public final int score() {
        return this.foul ? 0 : this.pinCount;
    }

    @NotNull
    public final String getPrintString() {
        return this.pinCount == 10 ? "x" : (this.foul ? "F" : String.valueOf(this.pinCount));
    }


    public final boolean getStrike() {
        return this.strike;
    }

    public final void setStrike(boolean value) {
        this.strike = value;
    }

    public final boolean getSpare() {
        return this.spare;
    }

    public final void setSpare(boolean value) {
        this.spare = value;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final void setName(@NotNull String value) {
        this.name = value;
    }

    public final int getPinCount() {
        return this.pinCount;
    }

    public final void setPinCount(int value) {
        this.pinCount = value;
    }

    public final boolean getFoul() {
        return this.foul;
    }

    public final void setFoul(boolean value) {
        this.foul = value;
    }

    @NotNull
    public String toString() {
        return "JavaPlay(name=" + this.name + ", pinCount=" + this.pinCount + ", foul=" + this.foul + ")";
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }

        if (other instanceof JavaPlay) {
            JavaPlay otherPlay = (JavaPlay) other;
            return Objects.equals(this.name, otherPlay.name)
                    && this.pinCount == otherPlay.pinCount
                    && this.foul == otherPlay.foul;
        }

        return false;
    }
}
