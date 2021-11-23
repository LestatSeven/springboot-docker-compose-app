package com.project.reporting.service;

import com.project.reporting.config.RabbitConfig;
import com.project.reporting.entity.ReportStatus;
import com.project.reporting.reporting.report.Report;
import com.project.reporting.reporting.report.ReportFactory;
import com.project.reporting.reporting.saver.Saver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitReceiverServiceImpl implements RabbitReceiverService {
    private final ReportStatusService reportStatusService;
    private final ReportFactory reportFactory;
    private final Saver saver;

    @Override
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        try {
            log.info("QUEUE \"{}\": {}", RabbitConfig.QUEUE_NAME, message);
            ReportStatus reportStatus = reportStatusService.findById(Integer.valueOf(message));
            reportStatus.setDateReceived(LocalDateTime.now());

            Report report = reportFactory.getReport(reportStatus);

            reportStatus.setDateStart(LocalDateTime.now());

            String reportResult = report.generate();

            reportStatus.setGeneratedName(report.getFullReportName());

            saver.save(reportStatus, reportResult.getBytes(StandardCharsets.UTF_8));

            reportStatus.setDateEnd(LocalDateTime.now());
            reportStatusService.save(reportStatus);
        } catch (Exception e) {
            log.error("FAILED MESSAGE", e);
        }
    }
}
