package org.jobsity.bowling.engine

/**
 * Data class to hold play (ball) info.
 * @throws Constructor throws IllegalArgumentException if pin count is invalid
 * or if pin count and foul are set at the same time
 */
data class KotlinPlay(var name: String, var pinCount: Int = 0, var foul: Boolean) {
    var strike = false
    var spare = false

    init {
        if (pinCount > 10 || pinCount < 0)
            throw IllegalArgumentException("Invalid pin count: $pinCount. Only values 0 -> 10 are permitted")

        if (foul && pinCount != 0)
            throw IllegalArgumentException("Foul and pinCount set for the same play")
    }

    fun score(): Int {
        return if (foul) 0 else pinCount
    }

    fun getPrintString(): String {
        return if (pinCount == 10) "x" else if (foul) "F" else pinCount.toString()
    }
}