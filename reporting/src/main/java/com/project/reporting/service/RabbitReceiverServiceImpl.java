package com.project.reporting.service;

import com.project.reporting.config.RabbitConfig;
import com.project.reporting.entity.ReportStatus;
import com.project.reporting.reporting.collector.DataCollector;
import com.project.reporting.reporting.collector.EmployeesDatabaseDataCollector;
import com.project.reporting.reporting.model.Employee;
import com.project.reporting.reporting.producer.HtmlReportProducerImpl;
import com.project.reporting.reporting.producer.ReportProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RabbitReceiverServiceImpl implements RabbitReceiverService {
    private final ReportStatusService reportStatusService;
    private final JdbcTemplate jdbcTemplate;

    @Override
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println(String.format("QUEUE \"%s\": %s", RabbitConfig.QUEUE_NAME, message));
        ReportStatus reportStatus = reportStatusService.findById(Integer.valueOf(message));
        reportStatus.setDateReceived(LocalDateTime.now());
        reportStatusService.save(reportStatus);

        System.out.println("Trying to collect");
        DataCollector<Employee> dataCollector = new EmployeesDatabaseDataCollector<>(jdbcTemplate);
        ReportProducer employeeReportProducer = new HtmlReportProducerImpl(dataCollector);
        employeeReportProducer.collect();
        employeeReportProducer.produce();
        employeeReportProducer.commit();
    }
}
