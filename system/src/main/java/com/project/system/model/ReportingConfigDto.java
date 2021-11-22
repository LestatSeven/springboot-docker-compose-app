package com.project.system.model;

import com.project.system.entity.ReportStatus;
import com.project.system.entity.ReportingConfig;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReportingConfigDto {
    private ReportingConfig reportingConfig;
    private List<ReportStatus> reportStatusList;
}
