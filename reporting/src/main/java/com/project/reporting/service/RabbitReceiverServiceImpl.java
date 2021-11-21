package com.project.reporting.service;

import com.project.reporting.config.RabbitConfig;
import com.project.reporting.entity.ReportStatus;
import com.project.reporting.reporting.producer.DataProducer;
import com.project.reporting.reporting.producer.DataProducerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class RabbitReceiverServiceImpl implements RabbitReceiverService {
    private final ReportStatusService reportStatusService;
    private final JdbcTemplate jdbcTemplate;

    @Override
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.printf("QUEUE \"%s\": %s%n", RabbitConfig.QUEUE_NAME, message);
        ReportStatus reportStatus = reportStatusService.findById(Integer.valueOf(message));
        reportStatus.setDateReceived(LocalDateTime.now());
        reportStatusService.save(reportStatus);

        DataProducer reportProducer = new DataProducerFactory(jdbcTemplate).getReportProducer(reportStatus);
        reportProducer.beforeStart(() -> {
            reportStatus.setDateStart(LocalDateTime.now());
            reportStatusService.save(reportStatus);
        });
        reportProducer.collect();
        reportProducer.generateHeader(reportStatus.getConfig().getReportName() + " - " + reportStatus.getDateRequest().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        reportProducer.produce();
        reportProducer.generateFooter();
        reportProducer.save();
        reportProducer.afterEnd(() -> {
            reportStatus.setDateEnd(LocalDateTime.now());
            reportStatusService.save(reportStatus);
        });
    }
}
