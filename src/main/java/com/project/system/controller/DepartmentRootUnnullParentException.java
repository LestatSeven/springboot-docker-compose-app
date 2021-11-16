package com.project.system.controller;

public class DepartmentRootUnnullParentException extends RuntimeException {
    public DepartmentRootUnnullParentException(String message) {
        super(message);
    }

    public DepartmentRootUnnullParentException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentRootUnnullParentException(Throwable cause) {
        super(cause);
    }

    public DepartmentRootUnnullParentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
