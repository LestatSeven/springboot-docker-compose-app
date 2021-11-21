package com.project.reporting.repository;

import com.project.reporting.entity.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportStatusRepository extends JpaRepository<ReportStatus, Integer> {
}
