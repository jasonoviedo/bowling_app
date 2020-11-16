package org.jobsity.bowling.engine.frames

import org.jobsity.bowling.engine.BowlingPlay
import java.io.PrintStream

/**
 * Models the special case for the 10th frame, whose logic changes
 * slightly (especially scoring)
 */
class Bowling10thFrame(override var frameNumber: Int) : BowlingFrame(frameNumber) {
    override fun fixRemainingBalls(play: BowlingPlay) {
        if (play.strike) {
            remainingBalls += 1
            remainingPinCount += 20
        }

        if (play.spare) {
            remainingBalls += 1
            remainingPinCount += 10
        }
    }

    /**
     * Extend base functionality to include the third ball when appropriate
     */
    override fun getPrintString(): String {
        val thirdBall = if (plays.size == 3) plays[2].getPrintString() else ""
        return super.getPrintString() + thirdBall
    }

    /**
     * Score is slightly different for the 10th frame
     */
    override fun doCalculateScore() {
        score = plays.sumBy { it.pinCount }
    }
}