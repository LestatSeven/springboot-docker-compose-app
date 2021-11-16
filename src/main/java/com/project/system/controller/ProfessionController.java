package com.project.system.controller;

import com.project.system.model.Profession;
import com.project.system.service.ProfessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/showFormForAdd")
    @ApiOperation("Form for adding profession")
    public String showFormForAdd(Model model) {
        Profession profession = new Profession();
        model.addAttribute("profession", profession);

        return "professions/form";
    }

    @PostMapping("/save")
    @ApiOperation("Saving new or edited profession object")
    public String saveProfession(@ModelAttribute("profession") Profession profession) {
        professionService.save(profession);

        return "redirect:/professions/list";
    }

    @GetMapping("/showFormForUpdate")
    @ApiOperation("Form for updating profession")
    public String showFormForUpdate(@RequestParam("id") Integer id, Model model) {
        Profession profession = professionService.findById(id);
        model.addAttribute("profession", profession);

        return "professions/form";
    }

    @GetMapping("/delete")
    @ApiOperation("Delete profession by id")
    public String deleteProfession(@RequestParam("id") Integer id) {
        professionService.deleteById(id);

        return "redirect:/professions/list";
    }
}
