package com.project.reporting.service;

import com.project.reporting.config.RabbitConfig;
import com.project.reporting.entity.ReportStatus;
import com.project.reporting.reporting.report.Report;
import com.project.reporting.reporting.report.ReportFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class RabbitReceiverServiceImpl implements RabbitReceiverService {
    private final ReportStatusService reportStatusService;
    private final ReportFactory reportFactory;

    @Override
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(String message) throws IOException {
        System.out.printf("QUEUE \"%s\": %s%n", RabbitConfig.QUEUE_NAME, message);
        ReportStatus reportStatus = reportStatusService.findById(Integer.valueOf(message));
        reportStatus.setDateReceived(LocalDateTime.now());
        reportStatusService.save(reportStatus);

        Report report = reportFactory.getFactory(reportStatus);
        report.getProducer().beforeStart(() -> {
            reportStatus.setDateStart(LocalDateTime.now());
            reportStatusService.save(reportStatus);
        });
        report.getCollector().collect();
        report.getProducer().generateHeader(reportStatus.getConfig().getReportName() + " - " + reportStatus.getDateRequest().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss")));
        report.getProducer().produce();
        report.getProducer().generateFooter();
        try {
            report.getProducer().save();
        } catch (Exception e) {
            reportStatus.setError(e.getMessage());
            e.printStackTrace();
            reportStatusService.save(reportStatus);
        }
        report.getProducer().afterEnd(() -> {
            reportStatus.setDateEnd(LocalDateTime.now());
            reportStatusService.save(reportStatus);
        });
    }
}
