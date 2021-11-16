package com.project.system.controller;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CustomErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
    private StackTraceElement[] stacktrace;
}
