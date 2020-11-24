package org.jobsity.bowling.engine.impl;

import org.jetbrains.annotations.NotNull;
import org.jobsity.bowling.engine.BowlingGameEngine;
import org.jobsity.bowling.reader.BowlingPlayVO;
import org.jobsity.bowling.engine.BowlingFrame;
import org.jobsity.bowling.engine.BowlingPlay;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation is completely detached of API.
 * <p>
 * Entry point for the business logic. The main purpose of this
 * class is to hold player information, especially validate no more
 * players are added after the first round is completed
 */
@SuppressWarnings("unused")
public final class JavaGameEngine implements BowlingGameEngine {
    private boolean finishedFirstRound;

    @NotNull
    private Map<String, JavaPlayer> players = new LinkedHashMap<>();

    public JavaGameEngine() {
    }

    public void addPlay(@NotNull BowlingPlayVO play) {
        String name = play.getName();
        JavaPlay javaPlay = new JavaPlay(name, play.getPinCount(), play.isFoul());
        JavaPlayer player = this.getOrRecordPlayerFor(javaPlay);
        player.addPlay(javaPlay);
    }

    public void start() {
        this.players.clear();
    }

    @Override
    public List<String> getPlayerList() {
        return players
                .values()
                .stream()
                .map(JavaPlayer::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<BowlingFrame> getFrames(String player) {
        return Arrays.asList(players.get(player).getFrames());
    }

    @Override
    public List<BowlingPlay> getPlays(String player, BowlingFrame frame) {
        @NotNull JavaFrame[] frames = players.get(player).getFrames();
        return new ArrayList<>(frames[frame.getFrameNumber()].getPlays());
    }


    /**
     * Add a new player or access an existing one
     *
     * @throws IllegalStateException When a player is added after first round
     */
    @NotNull
    public final JavaPlayer getOrRecordPlayerFor(@NotNull JavaPlay p) {
        JavaPlayer player = this.players.get(p.getName());

        //Second round, if a player was not present before, throw
        if (player != null) {
            if (player.getCurrentFrame() > 0) {
                this.finishedFirstRound = true;
            }
        } else {
            if (this.finishedFirstRound) {
                throw new IllegalStateException("Player " + p.getName() + " did not play in previous rounds");
            }

            player = new JavaPlayer(p.getName());
            this.players.put(p.getName(), player);
        }

        return player;
    }

    //********************************************************
    // PROPERTY METHODS
    //********************************************************

    @NotNull
    public final Map<String, JavaPlayer> getPlayers() {
        return this.players;
    }

    public final void setPlayers(@NotNull Map<String, JavaPlayer> value) {
        this.players = value;
    }

}
