package org.jobsity.bowling.printer;

import org.jobsity.bowling.engine.BowlingGameEngine;

/**
 * API for a bowling game printer
 */
public interface BowlingPrinter {
    void printScore(BowlingGameEngine engine);
}
