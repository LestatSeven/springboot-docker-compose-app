package com.project.reporting.controller;

import com.project.reporting.entity.ReportStatus;
import com.project.reporting.reporting.saver.FileReader;
import com.project.reporting.service.ReportStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {
    private final ReportStatusService reportStatusService;
    private final FileReader fileReader;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getReport(@PathVariable Integer id) {
        log.info("getReport id: " + id);
        ReportStatus reportStatus = reportStatusService.findById(id);

        if (reportStatus == null || reportStatus.getDateEnd() == null) {
            throw new IllegalStateException(String.format("report with id=%d not found", id));
        }

        String fileName = URLEncoder.encode(reportStatus.getGeneratedName(), StandardCharsets.UTF_8).replace("+", "%20");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "text/html; charset=UTF-8")
                .body(fileReader.read(reportStatus));
    }
}
