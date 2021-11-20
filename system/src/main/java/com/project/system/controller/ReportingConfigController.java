package com.project.system.controller;

import com.project.system.service.ReportingConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reporting")
@RequiredArgsConstructor
@Api("Reports controller methods")
public class ReportingConfigController {
    private final ReportingConfigService reportingConfigService;

    @GetMapping("/list")
    @ApiOperation("Reports list page")
    public String list(Model model) {
        var reportingConfigs = reportingConfigService.findAll();
        model.addAttribute("reportingConfigs", reportingConfigs);

        return "reports/list";
    }

    @PostMapping("/makeReport")
    @ApiOperation("Send request to make report")
    public String sendRequestToMakeReport(@RequestParam("id") Integer id, Model model) {

        return "reports/list";
    }
}
