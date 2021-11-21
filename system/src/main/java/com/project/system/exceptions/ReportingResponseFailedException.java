package com.project.system.exceptions;

public class ReportingResponseFailedException extends RuntimeException {
    public ReportingResponseFailedException() {
        super();
    }

    public ReportingResponseFailedException(String message) {
        super(message);
    }

    public ReportingResponseFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReportingResponseFailedException(Throwable cause) {
        super(cause);
    }

    protected ReportingResponseFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
