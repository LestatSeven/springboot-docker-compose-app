package com.project.system.controller;

import com.project.system.model.Staff;
import com.project.system.service.DepartmentService;
import com.project.system.service.ProfessionService;
import com.project.system.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staffs")
@Api("Staff controller methods")
public class StaffController {
    private StaffService staffService;
    private DepartmentService departmentService;
    private ProfessionService professionService;

    @Autowired
    public StaffController(StaffService staffService, DepartmentService departmentService, ProfessionService professionService) {
        this.staffService = staffService;
        this.departmentService = departmentService;
        this.professionService = professionService;
    }

    @GetMapping("/list")
    @ApiOperation("Staffs list page")
    public String list(Model model) {
        var staffs = staffService.findAll();
        model.addAttribute("staffs", staffs);

        return "staffs/list";
    }

    @PostMapping("/save")
    @ApiOperation("Saving new or edited staff object")
    public String save(@ModelAttribute("staff") Staff staff) {
        staffService.save(staff);

        return "redirect:/staffs/list";
    }

    @GetMapping("/delete")
    @ApiOperation("Delete staff by id")
    public String delete(@RequestParam("id") Integer id) {
        staffService.deleteById(id);

        return "redirect:/staffs/list";
    }

    @GetMapping("/showAddForm")
    @ApiOperation("Form for adding staff")
    public String showAddForm(Model model) {
        Staff staff = new Staff();
        var departments = departmentService.findAll();
        var professions = professionService.findAll();

        model.addAttribute("staff", staff);
        model.addAttribute("departments", departments);
        model.addAttribute("professions", professions);

        return "staffs/form";
    }

    @GetMapping("/showUpdateForm")
    @ApiOperation("Form for updating employee")
    public String showUpdateForm(@RequestParam("id") Integer id, Model model) {
        Staff staff = staffService.findById(id);
        var departments = departmentService.findAll();
        var professions = professionService.findAll();

        model.addAttribute("staff", staff);
        model.addAttribute("departments", departments);
        model.addAttribute("professions", professions);

        return "staffs/form";
    }
}
