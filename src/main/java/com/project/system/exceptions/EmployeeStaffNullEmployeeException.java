package com.project.system.exceptions;

public class EmployeeStaffNullEmployeeException extends RuntimeException {
    public EmployeeStaffNullEmployeeException() {
        super();
    }

    public EmployeeStaffNullEmployeeException(String message) {
        super(message);
    }

    public EmployeeStaffNullEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeStaffNullEmployeeException(Throwable cause) {
        super(cause);
    }

    protected EmployeeStaffNullEmployeeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
