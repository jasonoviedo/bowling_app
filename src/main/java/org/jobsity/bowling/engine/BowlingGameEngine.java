package org.jobsity.bowling.engine;

import org.jobsity.bowling.exceptions.IllegalPlayException;
import org.jobsity.bowling.reader.BowlingPlayVO;

import java.util.List;

/**
 * API for a Bowling Game Engine.
 * <p>
 * The engine will accept one play at a time, in order, but needs to
 * be started first. Plays are validated as they are added to the
 * engine. The game engine knows how to keep score
 */
public interface BowlingGameEngine {

    /**
     * Adds the next play to the engine.
     *
     * @param play Play to be added
     */
    void addPlay(BowlingPlayVO play) throws IllegalPlayException;

    /**
     * Prepares the engine
     */
    void start();

    List<String> getPlayerList();

    List<BowlingFrame> getFrames(String player);

    List<BowlingPlay> getPlays(String player, BowlingFrame frame);
}