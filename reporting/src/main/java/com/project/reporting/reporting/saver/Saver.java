package com.project.reporting.reporting.saver;

import com.project.reporting.entity.ReportStatus;

public interface Saver {
    void save(ReportStatus reportStatus, byte[] result);
}
