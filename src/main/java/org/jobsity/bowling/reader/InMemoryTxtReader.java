package org.jobsity.bowling.reader;

import org.jobsity.bowling.exceptions.FileCannotBeLoadedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
