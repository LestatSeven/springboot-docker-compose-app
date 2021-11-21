package com.project.router.controller;

import com.project.router.entity.ReportStatus;
import com.project.router.entity.ReportingConfig;
import com.project.router.model.Response;
import com.project.router.model.JsonReturnStatuses;
import com.project.router.service.ReportStatusService;
import com.project.router.service.ReportingConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class IncomingRequests {
    private final AmqpTemplate template;
    private final ReportStatusService reportStatusService;
    private final ReportingConfigService reportingConfigService;

    @GetMapping("/send/{id}")
    public Response getRequest(@PathVariable Integer id) {
        try {
            this.template.convertAndSend("rabbit-queue", id);

            ReportingConfig reportingConfig = reportingConfigService.findById(id);
            ReportStatus reportStatus = ReportStatus.builder()
                    .config(reportingConfig)
                    .dateRequest(LocalDateTime.now())
                    .build();
            reportStatusService.save(reportStatus);

            Response response = Response.builder()
                            .status(JsonReturnStatuses.SUCCESSED)
                            .requestedId(id)
                            .build();
            System.out.println(response);
            return response;
        } catch (Exception e) {
            Response response = Response.builder()
                    .status(JsonReturnStatuses.FAILED)
                    .text(e.getMessage())
                    .build();
            return response;
        }
    }
}
