package org.jobsity.model

import java.lang.RuntimeException

data class BowlingPlay(var name: String, var pinCount: Int = 0, var fault: Boolean) {
    var strike = false
    var spare = false

    fun score(): Int {
        return if (fault) 0 else pinCount
    }
}

class GameEngine() {
    var firstPlayer: String? = null
    var finishedFirstRound = false;
    var players = mutableMapOf<String, BowlingPlayer>()
    fun getOrRecordPlayerFor(p: BowlingPlay): BowlingPlayer {
        //Second round
        if (p.name == firstPlayer)
            finishedFirstRound

        //Just starting
        if (players.isEmpty())
            firstPlayer = p.name

        var player = players[p.name]
        if (player == null) {
            if (finishedFirstRound)
                throw RuntimeException("Player ${p.name} did not play in previous rounds")
            player = BowlingPlayer(p.name)
            players[p.name] = player
        }
        return player
    }

    fun printScores() {
        println("Frame\t\t" + List(10, { it + 1 }).joinToString(separator = "\t\t|"))
        for (player in players.values) {
            val str1 = player.frames.map { it.printPinfalls() }.joinToString(separator = "")
            val str2 = player.frames.map { it.printScore() }.joinToString(separator = "")
            println("Pinfalls\t$str1")
            println("Score\t $str2 (${player.frames.sumBy { it.score }})")
        }
    }
}

class BowlingFrame(var frameNumber: Int) {
    var balls = mutableListOf<BowlingPlay>()
    var remainingPinCount = 10;
    var remainingBalls = 2
    var open = true;
    var nextBalls = mutableListOf<BowlingPlay>();
    var score = 0;

    fun addPlay(play: BowlingPlay): Boolean {
        //if(play.fault) // TODO
        remainingBalls -= 1
        remainingPinCount -= play.pinCount
        balls.add(play)
        play.strike = remainingPinCount == 0 && balls.size == 1
        play.spare = remainingPinCount == 0 && balls.size == 2
        open = play.strike || play.spare

        if (frameNumber == 9 && play.strike) {
            remainingBalls += 1
            remainingPinCount += 20
        }
        if (frameNumber == 9 && play.spare) {
            remainingBalls += 1
            remainingPinCount += 10
        }
        if (frameNumber != 9 && play.strike)
            remainingBalls -= 1
        return remainingBalls == 0
    }

    fun printPinfalls(): String {
        val thirdBall = balls.size == 3
        return "${if (!strike()) balls[0].pinCount.toString() else ""}\t" +
                "${if (strike()) "x" else (if (spare()) "/" else balls[1].pinCount.toString())}\t" +
                "${if (thirdBall) balls[2].pinCount else ""}" + "|"
    }

    fun printScore(): String {
        return "$score\t\t|"
    }

    fun recordExtraBall(play: BowlingPlay) {
        nextBalls.add(play)
        scoreFrame()
    }

    fun scoreFrame() {
        if (strike() && nextBalls.size == 2
            || spare() && nextBalls.size == 1
        ) {
            open = false;
        }
        score =
            if (frameNumber == 9) balls.sumBy { it.pinCount }
            else (10 - remainingPinCount) + nextBalls.sumBy { b1 -> b1.score() }
    }


    fun strike() = balls.size == 1 && remainingPinCount == 0;
    fun spare() = balls.size == 2 && remainingPinCount == 0;
}

data class BowlingPlayer(var name: String, var score: Int = 0) {
    var frames = Array(10) { i -> BowlingFrame(i) }
    var openFrames = mutableListOf<BowlingFrame>();
    var currentFrame = 0

    fun addPlay(play: BowlingPlay) {
        if (currentFrame == 10)
            return //ignore

        val finished = frames[currentFrame].addPlay(play)
        openFrames.forEach { frame -> frame.recordExtraBall(play) }

        if (frames[currentFrame].open)
            openFrames.add(frames[currentFrame])
        else
            frames[currentFrame].scoreFrame();

        val justClosed = openFrames.filter { f -> !f.open }
        openFrames.removeAll(justClosed)

        if (finished)
            currentFrame += 1
    }
}