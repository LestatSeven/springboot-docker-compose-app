package com.project.system.controller;

import com.jayway.jsonpath.internal.JsonContext;
import com.project.system.exceptions.ReportingResponseFailedException;
import com.project.system.service.ReportingConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
@RequestMapping("/reporting")
@RequiredArgsConstructor
@Api("Reports controller methods")
public class ReportingConfigController {
    private final ReportingConfigService reportingConfigService;

    @Value("${service.rabbit.url}")
    private String rabbitUrl;

    @GetMapping("/list")
    @ApiOperation("Reports list page")
    public String list(Model model) {
        var reportingConfigs = reportingConfigService.findAll();
        model.addAttribute("reportingConfigs", reportingConfigs);

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
