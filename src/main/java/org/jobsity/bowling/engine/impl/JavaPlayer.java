package org.jobsity.bowling.engine.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Holds information for one player
 */
public final class JavaPlayer {

    @NotNull
    private String name;
    /**
     * Frames are inited as standard 1 through 9 and especial the 10th
     */
    @NotNull
    private JavaFrame[] frames = new JavaFrame[10];
    private final List<JavaFrame> unscoredFrames;
    private int currentFrame;

    public JavaPlayer(@NotNull String name) {
        this.name = name;
        for (int i = 0; i < 9; i++)
            frames[i] = new JavaFrame(i);
        frames[9] = new Java10thFrame(9);
        this.unscoredFrames = new ArrayList<>();
    }

    /**
     * Records one play for the current frame,
     * advances to the next frame if necessary
     */
    public final void addPlay(@NotNull JavaPlay play) {
        if (currentFrame == 10)
            return; //ignore

        boolean finished = frames[currentFrame].addPlay(play);
        unscoredFrames.forEach(frame -> frame.recordExtraBall(play));

        if (frames[currentFrame].getUnscored())
            unscoredFrames.add(frames[currentFrame]);
        else
            frames[currentFrame].scoreFrame();

        List<JavaFrame> justScored = unscoredFrames
                .stream()
                .filter(f -> !f.getUnscored())
                .collect(Collectors.toList());
        unscoredFrames.removeAll(justScored);

        if (finished)
            currentFrame += 1;
    }

    public final int score() {
        return Arrays.stream(frames)
                .mapToInt(JavaFrame::getScore)
                .sum();
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final void setName(@NotNull String var1) {
        this.name = var1;
    }


    @NotNull
    public final JavaFrame[] getFrames() {
        return this.frames;
    }

    public final void setFrames(@NotNull JavaFrame[] var1) {
        this.frames = var1;
    }

    public final int getCurrentFrame() {
        return this.currentFrame;
    }

    public final void setCurrentFrame(int value) {
        this.currentFrame = value;
    }

    @NotNull
    public final JavaPlayer copy(@NotNull String name) {
        return new JavaPlayer(name);
    }

    @NotNull
    public String toString() {
        return "BowlingPlayer(name=" + this.name + ")";
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other)
            return true;

        if (other instanceof JavaPlayer) {
            JavaPlayer otherJava = (JavaPlayer) other;
            return Objects.equals(this.name, otherJava.name);
        }

        return false;
    }
}
