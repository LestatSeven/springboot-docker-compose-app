package com.project.reporting.reporting.producer;

import com.project.reporting.entity.ReportStatus;
import com.project.reporting.reporting.collector.DataCollector;
import com.project.reporting.reporting.collector.EmployeesDatabaseDataCollectorImpl;
import com.project.reporting.reporting.model.Employee;
import com.project.reporting.reporting.producer.DataProducer;
import com.project.reporting.reporting.producer.EmployeesHtmlReportProducerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class DataProducerFactory {
    private final JdbcTemplate jdbcTemplate;

    public DataProducer getReportProducer(ReportStatus reportStatus) {
        DataProducer reportProducer = null;

        switch (reportStatus.getConfig().getReportShort()) {
            case "employees_list":
                DataCollector<Employee> dataCollector = new EmployeesDatabaseDataCollectorImpl<>(jdbcTemplate);
                reportProducer = new EmployeesHtmlReportProducerImpl(dataCollector);
                break;

            case "staff_employees_list":

                break;

            default:
                throw new IllegalArgumentException("Wrong type of report: " + reportStatus.getConfig().getReportShort());
        }

        return reportProducer;
    }
}
