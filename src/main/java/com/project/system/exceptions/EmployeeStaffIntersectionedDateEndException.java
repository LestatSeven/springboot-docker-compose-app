package com.project.system.exceptions;

public class EmployeeStaffIntersectionedDateEndException extends RuntimeException {
    public EmployeeStaffIntersectionedDateEndException() {
        super();
    }

    public EmployeeStaffIntersectionedDateEndException(String message) {
        super(message);
    }

    public EmployeeStaffIntersectionedDateEndException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeStaffIntersectionedDateEndException(Throwable cause) {
        super(cause);
    }

    protected EmployeeStaffIntersectionedDateEndException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
