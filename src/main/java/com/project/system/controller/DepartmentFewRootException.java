package com.project.system.controller;

public class DepartmentFewRootException extends RuntimeException {
    public DepartmentFewRootException() {
    }

    public DepartmentFewRootException(String message) {
        super(message);
    }

    public DepartmentFewRootException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentFewRootException(Throwable cause) {
        super(cause);
    }

    public DepartmentFewRootException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
