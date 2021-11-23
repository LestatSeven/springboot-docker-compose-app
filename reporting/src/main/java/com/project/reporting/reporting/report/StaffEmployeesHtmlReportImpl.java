package com.project.reporting.reporting.report;

import com.project.reporting.entity.ReportStatus;
import com.project.reporting.reporting.collector.DataCollector;
import com.project.reporting.reporting.model.StaffEmployee;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class StaffEmployeesHtmlReportImpl extends HtmlReportImpl {
    private final ReportStatus reportStatus;
    private final DataCollector<StaffEmployee> dataCollector;

    public StaffEmployeesHtmlReportImpl(ReportStatus reportStatus, DataCollector<StaffEmployee> dataCollector) {
        this.reportStatus = reportStatus;
        this.dataCollector = dataCollector;
    }

    public String generateBody() {
        StringBuilder html = new StringBuilder();
        html.append("""
                <table border=\"1\">
                    <tr>
                        <th>ID штатной строки</th>
                        <th>Подразделение</th>
                        <th>Должность</th>
                        <th>Текущее количество персонала</th>
                        <th>Максимальное количество персонала</th>
                    </tr>
                """);

        List<StaffEmployee> staffEmployees = dataCollector.getData();
        for (StaffEmployee staffEmployee : staffEmployees) {
            html.append(String.format("""
                    <tr>
                        <td>%d</td>
                        <td>%s</td>
                        <td>%s</td>
                        <td>%d</td>
                        <td>%d</td>
                    </tr>
                    """,
                    staffEmployee.id(),
                    staffEmployee.depName(),
                    staffEmployee.profName(),
                    staffEmployee.maxCount(),
                    staffEmployee.realCount()
                    )
            );
            System.out.println(staffEmployee);
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
