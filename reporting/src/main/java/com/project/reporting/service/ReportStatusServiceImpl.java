package com.project.reporting.service;

import com.project.reporting.entity.ReportStatus;
import com.project.reporting.repository.ReportStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportStatusServiceImpl implements ReportStatusService{
    private final ReportStatusRepository reportStatusRepository;

    @Override
    public ReportStatus findById(Integer id) {
        return reportStatusRepository.findById(id).orElseThrow(() -> new IllegalStateException("Did not find report status with id - " + id));
    }

    @Override
    public void save(ReportStatus reportStatus) {
        reportStatusRepository.save(reportStatus);
    }
}
