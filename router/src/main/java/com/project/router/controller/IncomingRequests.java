package com.project.router.controller;

import com.project.router.config.RabbitConfig;
import com.project.router.entity.ReportStatus;
import com.project.router.entity.ReportingConfig;
import com.project.router.model.JsonReturnStatuses;
import com.project.router.model.ResponseDto;
import com.project.router.service.ReportStatusService;
import com.project.router.service.ReportingConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class IncomingRequests {
    private final AmqpTemplate template;
    private final ReportStatusService reportStatusService;
    private final ReportingConfigService reportingConfigService;

    @GetMapping("/send/{id}")
    public ResponseEntity<ResponseDto> getRequest(@PathVariable Integer id) {
        try {
            ReportingConfig reportingConfig = reportingConfigService.findById(id);
            ReportStatus reportStatus = ReportStatus.builder()
                    .config(reportingConfig)
                    .dateRequest(LocalDateTime.now())
                    .build();
            reportStatusService.save(reportStatus);

            template.convertAndSend(RabbitConfig.QUEUE_NAME, reportStatus.getId());

            ResponseDto responseDto = ResponseDto.builder()
                            .status(JsonReturnStatuses.SUCCESSED)
                            .requestedId(id)
                            .reportStatusId(reportStatus.getId())
                            .build();
            log.info(responseDto.toString());
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder()
                    .status(JsonReturnStatuses.FAILED)
                    .text(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}
