package org.kst.lms.exceptions;

public class ResourceAlreadyProcessedException extends RuntimeException {
    public ResourceAlreadyProcessedException(String message) {
        super(message);
    }
}
