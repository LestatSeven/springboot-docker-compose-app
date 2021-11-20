package com.project.system.exceptions;

public class EmployeeStaffIntersectionedDateBeginException extends RuntimeException {
    public EmployeeStaffIntersectionedDateBeginException() {
        super();
    }

    public EmployeeStaffIntersectionedDateBeginException(String message) {
        super(message);
    }

    public EmployeeStaffIntersectionedDateBeginException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeStaffIntersectionedDateBeginException(Throwable cause) {
        super(cause);
    }

    protected EmployeeStaffIntersectionedDateBeginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
