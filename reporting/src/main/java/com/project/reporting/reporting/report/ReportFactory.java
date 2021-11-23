package com.project.reporting.reporting.report;

import com.project.reporting.entity.ReportStatus;
import com.project.reporting.reporting.collector.EmployeesDatabaseDataCollectorImpl;
import com.project.reporting.reporting.collector.StaffEmployeesDataCollectorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportFactory {
    private final EmployeesDatabaseDataCollectorImpl employeesDatabaseDataCollector;
    private final StaffEmployeesDataCollectorImpl staffEmployeesDataCollector;

    public Report getReport(ReportStatus reportStatus) {
        switch (reportStatus.getConfig().getReportShort()) {
            case "employees_list":
                return new EmployeesHtmlReportImpl(reportStatus, employeesDatabaseDataCollector);
            case "staff_employees_list":
                return new StaffEmployeesHtmlReportImpl(reportStatus, staffEmployeesDataCollector);
            default:
                throw new IllegalArgumentException("Wrong type of report: " + reportStatus.getConfig().getReportShort());
        }
    }
}
