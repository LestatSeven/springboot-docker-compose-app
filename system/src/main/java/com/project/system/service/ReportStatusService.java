package com.project.system.service;


import com.project.system.entity.ReportStatus;
import com.project.system.entity.ReportingConfig;

import java.util.List;

public interface ReportStatusService {
    List<ReportStatus> findAllByConfig(ReportingConfig config);
}
