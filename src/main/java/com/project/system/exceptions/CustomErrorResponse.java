package com.project.system.exceptions;

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
