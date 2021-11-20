package com.project.system.service;

import com.project.system.entity.ReportingConfig;
import com.project.system.repository.ReportingConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportingConfigServiceImpl implements ReportingConfigService {
    private final ReportingConfigRepository reportingConfigRepository;

    @Override
    public List<ReportingConfig> findAll() {
        return reportingConfigRepository.findAll();
    }
}
