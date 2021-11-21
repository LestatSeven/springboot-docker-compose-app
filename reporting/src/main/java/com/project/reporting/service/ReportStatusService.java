package com.project.reporting.service;

import com.project.reporting.entity.ReportStatus;

public interface ReportStatusService {
    ReportStatus findById(Integer id);
    void save(ReportStatus reportStatus);
}
