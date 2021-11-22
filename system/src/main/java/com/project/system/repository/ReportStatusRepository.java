package com.project.system.repository;

import com.project.system.entity.ReportStatus;
import com.project.system.entity.ReportingConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportStatusRepository extends JpaRepository<ReportStatus, Integer> {
    List<ReportStatus> findAllByConfig(ReportingConfig config);
}
