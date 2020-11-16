package org.jobsity.bowling.engine.frames

import org.jobsity.bowling.engine.KotlinPlay

/**
 * Models a frame. All logic regarding frame scoring and finishing
 * is contained here
 */
open class BowlingFrame(open var frameNumber: Int) {
    var plays = mutableListOf<KotlinPlay>()
    var remainingPinCount = 10
    var remainingBalls = 2
    var unscored = true
    var score = 0

    private var nextBalls = mutableListOf<KotlinPlay>()

    /**
     * Adds a play to the frame
     * @return true if the frame is finished
     * @throws IllegalStateException
     */
    fun addPlay(play: KotlinPlay): Boolean {
        if (remainingBalls == 0)
            throw IllegalStateException("This frame has already finished")

        remainingBalls -= 1
        remainingPinCount -= play.pinCount
        plays.add(play)
        play.strike = remainingPinCount == 0 && plays.size == 1
        play.spare = remainingPinCount == 0 && plays.size == 2
        unscored = play.strike || play.spare

        fixRemainingBalls(play)

        return remainingBalls == 0
    }

    protected open fun fixRemainingBalls(play: KotlinPlay) {
        if (play.strike)
            remainingBalls -= 1
    }

    open fun getPrintString(): String {
        return "${if (!strike()) plays[0].getPrintString() else ""}\t" +
                "${if (strike()) "x" else (if (spare()) "/" else plays[1].getPrintString())}\t"
    }

    fun recordExtraBall(play: KotlinPlay) {
        if (!unscored)
            return

        nextBalls.add(play)
        scoreFrame()
    }

    fun scoreFrame() {
        if (strike() && nextBalls.size == 2
            || spare() && nextBalls.size == 1
        ) {
            unscored = false
        }
        doCalculateScore()
    }

    protected open fun doCalculateScore() {
        score =
            (10 - remainingPinCount) + nextBalls.sumBy { b1 -> b1.score() }
    }


    private fun strike() = plays.size == 1 && remainingPinCount == 0
    private fun spare() = plays.size == 2 && remainingPinCount == 0
}

