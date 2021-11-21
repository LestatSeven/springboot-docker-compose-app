package com.project.reporting.repository;

import com.project.reporting.entity.ReportingConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportingConfigRepository extends JpaRepository<ReportingConfig, Integer> {
}
