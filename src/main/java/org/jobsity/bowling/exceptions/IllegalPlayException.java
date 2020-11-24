package org.jobsity.bowling.exceptions;

public class IllegalPlayException extends RuntimeException {
    public IllegalPlayException(String message) {
        super(message);
    }
}
