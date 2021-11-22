package com.project.reporting.reporting.report;

import com.project.reporting.entity.ReportStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportFactory<T> {
    @Autowired
    private EmployeesHtmlReport<T> employeesHtmlReport;

    public Report<T> getFactory(ReportStatus reportStatus) {
        switch (reportStatus.getConfig().getReportShort()) {
            case "employees_list":
                return (Report<T>) employeesHtmlReport;
            default:
                throw new IllegalArgumentException("Wrong type of report: " + reportStatus.getConfig().getReportShort());
        }

        //return null;
    }
}
