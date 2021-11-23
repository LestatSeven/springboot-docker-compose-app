package com.project.reporting.reporting.report;

import com.project.reporting.entity.ReportStatus;
import com.project.reporting.reporting.saver.Saver;

public interface Report {
    String generate();
    String getFullReportName();
    ReportStatus getReportStatus();
}