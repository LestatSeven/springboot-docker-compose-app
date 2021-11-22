package com.project.reporting.reporting.producer;

import com.project.reporting.entity.ReportStatus;
import com.project.reporting.reporting.collector.DataCollector;
import com.project.reporting.reporting.collector.EmployeesDatabaseDataCollectorImpl;
import com.project.reporting.reporting.model.Employee;
import com.project.reporting.reporting.report.EmployeesHtmlReport;
import com.project.reporting.reporting.saver.HtmlFileSaver;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class DataProducerFactory<T> {
    private final JdbcTemplate jdbcTemplate;
    private final EmployeesHtmlReport htmlReport;

    public DataProducer<T> getReportProducer(ReportStatus reportStatus) {
        switch (reportStatus.getConfig().getReportShort()) {
            case "employees_list":
                return (DataProducer<T>) htmlReport.getProducer();

            case "staff_employees_list":

                break;

            default:
                throw new IllegalArgumentException("Wrong type of report: " + reportStatus.getConfig().getReportShort());
        }

        return null;
    }
}
