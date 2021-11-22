package com.project.reporting.reporting.producer;

import com.project.reporting.reporting.collector.DataCollector;
import com.project.reporting.reporting.model.Employee;
import com.project.reporting.reporting.saver.HtmlFileSaver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeesHtmlReportProducerImpl<T> extends HtmlDataProducerImpl<Employee> {
    public EmployeesHtmlReportProducerImpl(DataCollector<Employee> dataCollector, HtmlFileSaver saver) {
        super(dataCollector, saver);
    }

    @Override
    public void produce() {
        super.produce();

        StringBuilder html = new StringBuilder();

        html.append("<table border=\"1\">");
        html.append("<tr>");
        html.append("<th>ID</th>");
        html.append("<th>Полное имя</th>");
        html.append("<th>Номер телефона</th>");
        html.append("<th>E-mail</th>");
        html.append("<th>Должность</th>");
        html.append("<th>Подразделение</th>");
        html.append("</tr>");

        List<Employee> employees = this.<Employee>getDataCollector().getData();
        for (Employee employee: employees) {
            html.append("<tr>");
            html.append("<td>");
            html.append(employee.id());
            html.append("</id>");
            html.append("<td>");
            html.append(employee.fullName());
            html.append("</id>");
            html.append("<td>");
            html.append(employee.phoneNumber());
            html.append("</id>");
            html.append("<td>");
            html.append(employee.email());
            html.append("</id>");
            html.append("<td>");
            html.append(employee.currentJob());
            html.append("</id>");
            html.append("<td>");
            html.append(employee.currentDep());
            html.append("</id>");
            html.append("</tr>");
        }

        html.append("</table>");

        this.appendResult(html);
    }
}
