package com.project.system.controller;

import com.project.system.service.ProfessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/professions")
@Api("Profession controller methods")
public class ProfessionController {
    private ProfessionService professionService;

    @Autowired
    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @GetMapping("/list")
    @ApiOperation("Profession list page")
    public String listProfession(Model model) {
        var professions = professionService.findAll();

        model.addAttribute("professions", professions);
        return "professions/list";
    }
}
