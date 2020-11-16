package org.jobsity;

import org.jobsity.bowling.BowlingTokenizer;
import org.jobsity.bowling.engine.BowlingGameEngine;
import org.jobsity.bowling.engine.BowlingPlay;
import org.jobsity.bowling.engine.KotlinGameEngine;
import org.jobsity.bowling.reader.InMemoryTxtReader;
import org.jobsity.bowling.reader.TxtReader;

import java.util.List;

public class Bowling {

    //These could be injected using a dependency injector like Guice
    private final TxtReader reader;
    private final BowlingTokenizer tokenizer;
    private final BowlingGameEngine engine;

    private final String file;

    public Bowling(String file) {
        this.file = file;

        reader = new InMemoryTxtReader();
        tokenizer = new BowlingTokenizer();
        engine = new KotlinGameEngine();

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
            List<String> lines = null;
            lines = reader.load(file);

            List<BowlingPlay> plays = tokenizer.toPlays(lines);
            engine.start();

            // the more elegant plays.forEach(engine::addPlay)
            // is not permitted as the invocation requires explicit
            // exception handling
            for (BowlingPlay play : plays) {
                engine.addPlay(play);
            }
            System.out.println();
            System.out.println();
            System.out.println("=================================");
            System.out.println();
            engine.printScore(System.out);
        } catch (Throwable e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Execution terminated");
        }
    }
}
