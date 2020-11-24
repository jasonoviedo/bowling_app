package org.jobsity.bowling.reader;

import org.jobsity.bowling.exceptions.FileCannotBeLoadedException;

import java.util.List;

/**
 * Defines the API for Txt readers. Implementors can choose to read the entire
 * file into memory at once or do it progressively
 */
public interface TxtReader {

    /**
     * Read lines from a file.
     *
     * @param file Path to load
     * @return List of lines read. Implementors can choose to read the entirety
     * of the file at once or do it incrementally
     * @throws FileCannotBeLoadedException If there is an error
     */
    List<String> load(String file) throws FileCannotBeLoadedException;
}
