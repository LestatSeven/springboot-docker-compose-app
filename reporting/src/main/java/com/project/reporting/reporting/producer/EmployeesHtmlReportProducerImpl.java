package com.project.reporting.reporting.producer;

import com.project.reporting.reporting.collector.DataCollector;
import com.project.reporting.reporting.model.Employee;

import java.util.List;

public class EmployeesHtmlReportProducerImpl extends HtmlDataProducerImpl {

    public EmployeesHtmlReportProducerImpl(DataCollector dataCollector) {
        super(dataCollector);
    }

    @Override
    public void produce() {
        super.produce();

        StringBuilder html = new StringBuilder();

        html.append("<table>");
        html.append("<tr>");
        html.append("<th>ID</th>");
        html.append("<th>Полное имя</th>");
        html.append("<th>Номер телефона</th>");
        html.append("<th>E-mail</th>");
        html.append("<th>Должность</th>");
        html.append("<th>Подразделение</th>");
        html.append("</tr>");

        List<Employee> employees = this.getDataCollector().getData();
        for (Employee employee: employees) {
            html.append("<tr>");
            html.append("<td>" + employee.fullName() + "</id>");
            html.append("<td>" + employee.phoneNumber() + "</id>");
            html.append("<td>" + employee.email() + "</id>");
            html.append("<td>" + employee.currentJob() + "</id>");
            html.append("<td>" + employee.currentDep() + "</id>");
            html.append("</tr>");
        }

        html.append("</table>");

        this.appendResult(html);
    }
}
