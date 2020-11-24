package org.jobsity.bowling;

import org.jobsity.bowling.engine.BowlingGameEngine;
import org.jobsity.bowling.engine.impl.JavaGameEngine;
import org.jobsity.bowling.printer.impl.BowlingPrinterImpl;
import org.jobsity.bowling.printer.BowlingPrinter;
import org.jobsity.bowling.reader.BowlingPlayReader;
import org.jobsity.bowling.reader.impl.BowlingPlayReaderImpl;
import org.jobsity.bowling.reader.BowlingPlayVO;
import org.jobsity.bowling.reader.TxtReader;
import org.jobsity.bowling.reader.impl.InMemoryTxtReader;

import java.util.List;

/**
 * According to spec, this class will:
 * 1. Load a data file containing plays
 * 2. Read data into bowling plays
 * 3. Pass the plays to game engine for processing
 * 4. Ask engine to print results
 */
public class Bowling {

    //These could be injected using a dependency injector like Guice
    private final TxtReader reader;
    private final BowlingPlayReader playReader;
    private final BowlingGameEngine engine;
    private final BowlingPrinter printer;

    private final String file;

    public Bowling(String file) {
        this.file = file;

        reader = new InMemoryTxtReader();
        playReader = new BowlingPlayReaderImpl();
        engine = new JavaGameEngine();
        printer = new BowlingPrinterImpl(System.out);

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

            List<BowlingPlayVO> plays = playReader.toPlays(lines);
            engine.start();

            // The more elegant plays.forEach(engine::addPlay)
            // is not permitted as the invocation requires explicit
            // exception handling
            for (BowlingPlayVO play : plays) {
                engine.addPlay(play);
            }
            printer.printScore(engine);
        } catch (Throwable e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Execution terminated");
        }
    }
}
