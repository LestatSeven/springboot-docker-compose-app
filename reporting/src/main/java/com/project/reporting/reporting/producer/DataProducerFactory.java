package com.project.reporting.reporting.producer;

import com.project.reporting.entity.ReportStatus;
import com.project.reporting.reporting.collector.DataCollector;
import com.project.reporting.reporting.collector.EmployeesDatabaseDataCollectorImpl;
import com.project.reporting.reporting.model.Employee;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class DataProducerFactory<T> {
    private final JdbcTemplate jdbcTemplate;
    public DataProducer<T> getReportProducer(ReportStatus reportStatus) {
        switch (reportStatus.getConfig().getReportShort()) {
            case "employees_list":
                DataCollector<Employee> dataCollector = new EmployeesDatabaseDataCollectorImpl<Employee>(jdbcTemplate);

                @SuppressWarnings("unchecked")
                DataProducer<T> producer = (DataProducer<T>) new EmployeesHtmlReportProducerImpl<Employee>(dataCollector);

                return producer;

            case "staff_employees_list":

                break;

            default:
                throw new IllegalArgumentException("Wrong type of report: " + reportStatus.getConfig().getReportShort());
        }

        return null;
    }
}
