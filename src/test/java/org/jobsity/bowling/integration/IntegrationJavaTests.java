package org.jobsity.bowling.integration;

import org.jobsity.bowling.BowlingPlayReader;
import org.jobsity.bowling.engine.BowlingPlay;
import org.jobsity.bowling.engine.impl.JavaGameEngine;
import org.jobsity.bowling.exceptions.FileCannotBeLoadedException;
import org.jobsity.bowling.reader.InMemoryTxtReader;
import org.jobsity.bowling.reader.TxtReader;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class IntegrationJavaTests {
    private BowlingPlayReader playReader;
    private TxtReader reader;
    private JavaGameEngine engine;

    @Before
    public void setup() {
        engine = new JavaGameEngine();
        reader = new InMemoryTxtReader();
        playReader = new BowlingPlayReader();
    }

    @Test
    public void perfectScoreTest() throws FileCannotBeLoadedException {
        loadFile("data/data4.txt");
        assertEquals(300, engine.getPlayers().get("Carl").score());
    }

    @Test
    public void zeroScoreTest() throws FileCannotBeLoadedException {
        loadFile("data/data3.txt");
        assertEquals(0, engine.getPlayers().get("Carl").score());
    }

    @Test
    public void sampleScoreTest() throws FileCannotBeLoadedException {
        loadFile("data/data1.txt");
        assertEquals(167, engine.getPlayers().get("Jeff").score());
        assertEquals(151, engine.getPlayers().get("John").score());
    }

    private void loadFile(String s) throws FileCannotBeLoadedException {
        List<String> lines = reader.load(s);
        List<BowlingPlay> plays = playReader.toPlays(lines);
        engine.start();
        plays.forEach(engine::addPlay);
    }
}
