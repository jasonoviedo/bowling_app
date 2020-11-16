package org.jobsity.bowling.exceptions;

import java.io.IOException;

public class FileCannotBeLoadedException extends Throwable {
    public FileCannotBeLoadedException(IOException e) {
        super((e));
    }
}
