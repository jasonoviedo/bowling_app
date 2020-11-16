package org.jobsity.bowling.engine

import java.io.PrintStream

/**
 * Implementation is completely detached of API,
 * it can even be implemented in a different language
 *
 * Entry point for the business logic. The main purpose of this
 * class is to hold player information, especially validate no more
 * players are added after the fisrt round is completed
 */
class KotlinGameEngine : BowlingGameEngine {
    var firstPlayer: String? = null
    var finishedFirstRound = false

    var players = mutableMapOf<String, BowlingPlayer>()

    /**
     * Add a new player or access an existing one
     * @throws IllegalStateException When a player is added after frist round
     */
    fun getOrRecordPlayerFor(p: BowlingPlay): BowlingPlayer {

        //Just starting
        if (players.isEmpty())
            firstPlayer = p.name

        var player = players[p.name]

        //Second round
        if (player != null) {
            if (player.currentFrame > 0)
                finishedFirstRound = true
        } else {
            if (finishedFirstRound)
                throw IllegalStateException("Player ${p.name} did not play in previous rounds")
            player = BowlingPlayer(p.name)
            players[p.name] = player
        }
        return player
    }

    /**
     * Print scores according to specification
     */
    override fun printScore(out: PrintStream) {
        out.println("Frame\t\t" + List(10) { it + 1 }.joinToString(separator = "\t\t"))
        for (player in players.values) {
            out.println(player.name)
            var cumulativeScore = 0;
            val str1 = player.frames.joinToString(separator = "") { it.getPrintString() }
            val str2 =
                player.frames.joinToString(separator = "\t\t") { cumulativeScore += it.score; "$cumulativeScore" }
            out.println("Pinfall\t$str1")
            out.println("Score\t $str2")
        }
    }

    override fun addPlay(play: BowlingPlay) {
        val player = getOrRecordPlayerFor(play)
        player.addPlay(play)
    }

    override fun start() {
        players.clear()
    }
}

