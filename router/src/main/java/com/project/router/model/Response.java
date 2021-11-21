package com.project.router.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {
    JsonReturnStatuses status;
    Integer requestedId;
    String text;
}
