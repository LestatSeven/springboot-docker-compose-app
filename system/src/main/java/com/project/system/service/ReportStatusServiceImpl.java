package com.project.system.service;


import com.project.system.entity.ReportStatus;
import com.project.system.entity.ReportingConfig;
import com.project.system.repository.ReportStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportStatusServiceImpl implements ReportStatusService {
    private final ReportStatusRepository reportStatusRepository;

    @Override
    public List<ReportStatus> findAllByConfig(ReportingConfig config) {
        return reportStatusRepository.findAllByConfig(config);
    }
}
