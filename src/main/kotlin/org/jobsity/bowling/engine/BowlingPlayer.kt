package org.jobsity.bowling.engine

import org.jobsity.bowling.engine.frames.Bowling10thFrame
import org.jobsity.bowling.engine.frames.BowlingFrame

/**
 * Holds information for one player
 */
data class BowlingPlayer(var name: String, var score: Int = 0) {
    /**
     * Frames are inited as standard 1 through 9 and especial the 10th
     */
    var frames = Array(10) { i ->
        if (i < 9) BowlingFrame(i) else Bowling10thFrame(
            i
        )
    }

    private var unscoredFrames = mutableListOf<BowlingFrame>()
    var currentFrame = 0

    /**
     * Records one play for the current frame,
     * advances to the next frame if necessary
     */
    fun addPlay(play: BowlingPlay) {
        if (currentFrame == 10)
            return //ignore

        val finished = frames[currentFrame].addPlay(play)
        unscoredFrames.forEach { frame -> frame.recordExtraBall(play) }

        if (frames[currentFrame].unscored)
            unscoredFrames.add(frames[currentFrame])
        else
            frames[currentFrame].scoreFrame()

        val justScored = unscoredFrames.filter { f -> !f.unscored }
        unscoredFrames.removeAll(justScored)

        if (finished)
            currentFrame += 1
    }
}