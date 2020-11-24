package org.jobsity.bowling.engine;

import org.jobsity.bowling.exceptions.IllegalPlayException;

import java.io.PrintStream;

/**
 * API for a Bowling Game Engine.
 *
 * The engine will accept one play at a time, in order, but needs to
 * be started first. Plays are validated as they are added to the
 * engine.
 */
public interface BowlingGameEngine {
    /**
     * Print scores according to specification
     * @param out
     */
    void printScore(PrintStream out);

    /**
     * Adds the next play to the engine.
     * @param play Play to be added
     */
    void addPlay(BowlingPlay play) throws IllegalPlayException;

    /**
     * Prepares the engine
     */
    void start();
}