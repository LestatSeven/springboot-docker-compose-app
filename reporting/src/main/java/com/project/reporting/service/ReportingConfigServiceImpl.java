package com.project.reporting.service;

import com.project.reporting.entity.ReportingConfig;
import com.project.reporting.repository.ReportingConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportingConfigServiceImpl implements ReportingConfigService {
    private final ReportingConfigRepository reportingConfigRepository;

    @Override
    public ReportingConfig findById(Integer id) {
        return reportingConfigRepository.findById(id).orElseThrow(() -> new IllegalStateException("Did not find reporting config with id - " + id));
    }
}
