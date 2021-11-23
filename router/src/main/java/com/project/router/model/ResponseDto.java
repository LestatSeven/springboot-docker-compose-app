package com.project.router.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDto {
    JsonReturnStatuses status;
    Integer requestedId;
    Integer reportStatusId;
    String text;
}
