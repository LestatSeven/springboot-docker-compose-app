package com.project.reporting.reporting.report;

import com.project.reporting.entity.ReportStatus;
import com.project.reporting.reporting.collector.DataCollector;
import com.project.reporting.reporting.model.Employee;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmployeesHtmlReportImpl extends HtmlReportImpl {
    private final ReportStatus reportStatus;
    private final DataCollector<Employee> dataCollector;

    public EmployeesHtmlReportImpl(ReportStatus reportStatus, DataCollector<Employee> dataCollector) {
        this.reportStatus = reportStatus;
        this.dataCollector = dataCollector;
    }

    public String generateBody() {
        StringBuilder html = new StringBuilder();
        html.append("""
                <table border=\"1\">
                    <tr>
                        <th>ID</th>
                        <th>Полное имя</th>
                        <th>Номер телефона</th>
                        <th>E-mail</th>
                        <th>Должность</th>
                        <th>Подразделение</th>
                    </tr>
                """);

        List<Employee> employees = dataCollector.getData();
        for (Employee employee : employees) {
            html.append(String.format("""
                    <tr>
                        <td>%d</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%s</td>
                    </tr>
                    """,
                    employee.id(),
                    employee.fullName(),
                    employee.phoneNumber(),
                    employee.email(),
                    employee.currentJob(),
                    employee.currentDep())
            );
        }
        html.append("</table>");

        return html.toString();
    }

    @Override
    public String getFullReportName() {
        System.out.println(reportStatus.getConfig());
        return reportStatus.getConfig().getReportName() + " - " + reportStatus.getDateRequest().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss")) + ".html";
    }

    @Override
    public ReportStatus getReportStatus() {
        return reportStatus;
    }
}