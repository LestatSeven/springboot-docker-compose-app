package com.project.system.exceptions;

public class EmployeeStaffNullDateBeginException extends RuntimeException {
    public EmployeeStaffNullDateBeginException() {
        super();
    }

    public EmployeeStaffNullDateBeginException(String message) {
        super(message);
    }

    public EmployeeStaffNullDateBeginException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeStaffNullDateBeginException(Throwable cause) {
        super(cause);
    }

    protected EmployeeStaffNullDateBeginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
