package com.project.system.exceptions;

public class StaffExistedException  extends RuntimeException {
    public StaffExistedException() {
        super();
    }

    public StaffExistedException(String message) {
        super(message);
    }

    public StaffExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public StaffExistedException(Throwable cause) {
        super(cause);
    }

    protected StaffExistedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
