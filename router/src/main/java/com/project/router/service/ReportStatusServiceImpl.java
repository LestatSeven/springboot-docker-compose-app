package com.project.router.service;

import com.project.router.entity.ReportStatus;
import com.project.router.repository.ReportStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportStatusServiceImpl implements ReportStatusService{
    private final ReportStatusRepository reportStatusRepository;

    @Override
    public void save(ReportStatus reportStatus) {
        reportStatusRepository.save(reportStatus);
    }
}
