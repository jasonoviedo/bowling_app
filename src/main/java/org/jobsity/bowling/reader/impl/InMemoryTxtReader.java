package org.jobsity.bowling.reader.impl;

import org.jobsity.bowling.exceptions.FileCannotBeLoadedException;
import org.jobsity.bowling.reader.TxtReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Reader that loads a whole txt file into a list of strings, all at once.
 */
public class InMemoryTxtReader implements TxtReader {

    @Override
    public List<String> load(String file) throws FileCannotBeLoadedException {
        try {
            return Files.readAllLines(Paths.get(file));
        } catch (
                IOException e) {
            throw new FileCannotBeLoadedException(e);
        }
    }
}
