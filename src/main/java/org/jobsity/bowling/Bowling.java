package org.jobsity.bowling;

import org.jobsity.bowling.engine.BowlingGameEngine;
import org.jobsity.bowling.engine.BowlingPlay;
import org.jobsity.bowling.engine.impl.JavaGameEngine;
import org.jobsity.bowling.reader.InMemoryTxtReader;
import org.jobsity.bowling.reader.TxtReader;

import java.io.PrintStream;
import java.util.List;

public class Bowling {

    //These could be injected using a dependency injector like Guice
    private final TxtReader reader;
    private final BowlingPlayReader playReader;
    private final BowlingGameEngine engine;
    private final PrintStream out;

    private final String file;

    public Bowling(String file) {
        this.file = file;

        reader = new InMemoryTxtReader();
        playReader = new BowlingPlayReader();
        engine = new JavaGameEngine();
        out = System.out;

        safeExecute();
    }

    /**
     * Main program. This invocation is thread safe.
     * Problems during the execution will be logged to stderr.
     *
     * @throws RuntimeException To signal an anomalous program termination
     */
    public void safeExecute() {
        try {
            List<String> lines = reader.load(file);

            List<BowlingPlay> plays = playReader.toPlays(lines);
            engine.start();

            // The more elegant plays.forEach(engine::addPlay)
            // is not permitted as the invocation requires explicit
            // exception handling
            for (BowlingPlay play : plays) {
                engine.addPlay(play);
            }
            out.println();
            out.println();
            out.println("=================================");
            out.println();
            engine.printScore(out);
        } catch (Throwable e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Execution terminated");
        }
    }
}
