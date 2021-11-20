package com.project.system.controller;

import com.project.system.entity.Profession;
import com.project.system.service.ProfessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/professions")
@Api("Profession controller methods")
public class ProfessionController {
    private final ProfessionService professionService;

    @GetMapping("/list")
    @ApiOperation("Profession list page")
    public String list(Model model) {
        var professions = professionService.findAll();

        model.addAttribute("professions", professions);
        return "professions/list";
    }

    @PostMapping("/save")
    @ApiOperation("Saving new or edited profession object")
    public String saveProfession(@ModelAttribute("profession") Profession profession) {
        professionService.save(profession);

        return "redirect:/professions/list";
    }
    @GetMapping("/delete")
    @ApiOperation("Delete profession by id")
    public String delete(@RequestParam("id") Integer id) {
        professionService.deleteById(id);

        return "redirect:/professions/list";
    }

    @GetMapping("/showAddForm")
    @ApiOperation("Form for adding profession")
    public String showAddForm(Model model) {
        Profession profession = new Profession();
        model.addAttribute("profession", profession);

        return "professions/form";
    }

    @GetMapping("/showUpdateForm")
    @ApiOperation("Form for updating profession")
    public String showUpdateForm(@RequestParam("id") Integer id, Model model) {
        Profession profession = professionService.findById(id);
        model.addAttribute("profession", profession);

        return "professions/form";
    }


}
