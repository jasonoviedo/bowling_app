package org.jobsity.bowling.reader;

import org.jobsity.bowling.exceptions.PlayCannotBeLoadedException;

import java.util.List;

/**
 * Defines the API for converting a bunch of lines to BowlingPlaysVO. Callers and implementors
 * should be aware that this API could be called repeatedly.
 */
public interface BowlingPlayReader {
    List<BowlingPlayVO> toPlays(List<String> lines) throws PlayCannotBeLoadedException;
}
