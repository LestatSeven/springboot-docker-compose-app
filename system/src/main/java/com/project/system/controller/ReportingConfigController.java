package com.project.system.controller;

import com.project.system.entity.ReportingConfig;
import com.project.system.entity.Staff;
import com.project.system.exceptions.ReportingResponseFailedException;
import com.project.system.model.ReportingConfigDto;
import com.project.system.model.StaffDto;
import com.project.system.service.ReportStatusService;
import com.project.system.service.ReportingConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reporting")
@RequiredArgsConstructor
@Api("Reports controller methods")
public class ReportingConfigController {
    private final ReportingConfigService reportingConfigService;
    private final ReportStatusService reportStatusService;

    @Value("${service.rabbit.url}")
    private String rabbitUrl;

    @Value("${service.report.url}")
    private String reportUrl;

    @GetMapping("/list")
    @ApiOperation("Reports list page")
    public String list(Model model) {
        var reportingConfigs = reportingConfigService.findAll();
        List<ReportingConfigDto> result = new ArrayList<>();
        for (ReportingConfig config: reportingConfigs) {
            result.add(new ReportingConfigDto(config, reportStatusService.findAllByConfig(config)));
        }
        model.addAttribute("reportingConfigsDto", result);
        model.addAttribute("reportService", reportUrl);

        return "reports/list";
    }

    @GetMapping("/makeReport")
    @ApiOperation("Send request to make report")
    public String sendRequestToMakeReport(@RequestParam("id") Integer id, Model model) {
        final String uri = String.format("%s/requests/send/%d", rabbitUrl, id);
        RestTemplate template = new RestTemplate();
        String result = template.getForObject(uri, String.class);
        System.out.println(result);
        if(result.contains("\"status\":\"FAILED\"")) {
            throw new ReportingResponseFailedException(String.format("Getting an error to send task to creating report id=[%d]: %s", id, result));
        }

        return "redirect:/reporting/list";
    }
}
