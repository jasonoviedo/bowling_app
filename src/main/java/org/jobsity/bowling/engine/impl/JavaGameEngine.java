package org.jobsity.bowling.engine.impl;

import org.jetbrains.annotations.NotNull;
import org.jobsity.bowling.engine.BowlingGameEngine;
import org.jobsity.bowling.engine.BowlingPlay;

import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implementation is completely detached of API,
 * it can even be implemented in a different language
 * <p>
 * Entry point for the business logic. The main purpose of this
 * class is to hold player information, especially validate no more
 * players are added after the first round is completed
 */
public final class JavaGameEngine implements BowlingGameEngine {
    private boolean finishedFirstRound;

    @NotNull
    private Map<String, JavaPlayer> players = new LinkedHashMap<>();

    public JavaGameEngine() {
    }

    /**
     * Add a new player or access an existing one
     *
     * @throws IllegalStateException When a player is added after frist round
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

    /**
     * Print scores according to specification
     */
    public void printScore(@NotNull PrintStream out) {
//       out.println("Frame\t\t" + List(10) { it + 1 }.joinToString(separator = "\t\t"))
        for (JavaPlayer player : players.values()) {
            out.println(player.getName());
            int cumulativeScore = 0;
//          val str1 = player.frames.joinToString(separator = "") { it.getPrintString() }
//          val str2 =
//                  player.frames.joinToString(separator = "\t\t") { cumulativeScore += it.score; "$cumulativeScore" }
//          out.println("Pinfalls\t$str1")
//          out.println("Score\t $str2")
        }
    }

    public void addPlay(@NotNull BowlingPlay play) {
        String name = play.getName();
        JavaPlay javaPlay = new JavaPlay(name, play.getPinCount(), play.isFoul());
        JavaPlayer player = this.getOrRecordPlayerFor(javaPlay);
        player.addPlay(javaPlay);
    }

    public void start() {
        this.players.clear();
    }

    @NotNull
    public final Map<String, JavaPlayer> getPlayers() {
        return this.players;
    }

    public final void setPlayers(@NotNull Map<String, JavaPlayer> value) {
        this.players = value;
    }

}
