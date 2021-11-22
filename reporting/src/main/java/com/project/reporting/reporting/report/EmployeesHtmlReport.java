package com.project.reporting.reporting.report;

import com.project.reporting.reporting.collector.DataCollector;
import com.project.reporting.reporting.collector.EmployeesDatabaseDataCollectorImpl;
import com.project.reporting.reporting.model.Employee;
import com.project.reporting.reporting.producer.DataProducer;
import com.project.reporting.reporting.producer.EmployeesHtmlReportProducerImpl;
import com.project.reporting.reporting.saver.HtmlFileSaver;
import com.project.reporting.reporting.saver.Saver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeesHtmlReport<T> implements Report<Employee>{
    @Autowired
    EmployeesDatabaseDataCollectorImpl<Employee> dataCollector;
    @Autowired
    EmployeesHtmlReportProducerImpl<Employee> producer;
    @Autowired
    HtmlFileSaver saver;

    @Override
    public DataCollector<Employee> getCollector() {
        return dataCollector;
    }

    @Override
    public DataProducer<Employee> getProducer() {
        return producer;
    }

    @Override
    public Saver getSaver() {
        return saver;
    }
}
