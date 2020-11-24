package org.jobsity.bowling.integration

import org.jobsity.bowling.BowlingPlayReader
import org.jobsity.bowling.engine.BowlingPlay
import org.jobsity.bowling.engine.KotlinPlay
import org.jobsity.bowling.engine.KotlinGameEngine
import org.jobsity.bowling.engine.impl.JavaGameEngine
import org.jobsity.bowling.reader.InMemoryTxtReader
import org.jobsity.bowling.reader.TxtReader
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*


class IntegrationTests {
    private lateinit var playReader: BowlingPlayReader
    private lateinit var reader: TxtReader
    private lateinit var engine: JavaGameEngine

    @Before
    fun setup() {
        engine = JavaGameEngine()
        reader = InMemoryTxtReader()
        playReader = BowlingPlayReader()
    }

    @Test
    fun perfectScoreTest() {
        loadFile("data/data4.txt")
        assertEquals(300, engine.players["Carl"]?.score())
    }

    @Test
    fun zeroScoreTest() {
        loadFile("data/data3.txt")
        assertEquals(0, engine.players["Carl"]?.score())
    }

    @Test
    fun sampleScoreTest() {
        loadFile("data/data1.txt")
        assertEquals(167, engine.players["Jeff"]?.score())
        assertEquals(151, engine.players["John"]?.score())
    }

    private fun loadFile(s: String) {
        val lines = reader.load(s)
        val plays: List<BowlingPlay> = playReader.toPlays(lines)
        engine.start()
        plays.forEach(engine::addPlay)
    }
}