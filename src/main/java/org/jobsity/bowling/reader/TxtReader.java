package org.jobsity.bowling.reader;

import org.jobsity.bowling.exceptions.FileCannotBeLoadedException;

import java.util.List;

public interface TxtReader {

    List<String> load(String file) throws FileCannotBeLoadedException;
}
