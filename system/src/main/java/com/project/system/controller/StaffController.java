package com.project.system.controller;

import com.project.system.entity.Staff;
import com.project.system.exceptions.StaffExistedException;
import com.project.system.model.StaffDto;
import com.project.system.service.DepartmentService;
import com.project.system.service.EmployeeStaffService;
import com.project.system.service.ProfessionService;
import com.project.system.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/staffs")
@RequiredArgsConstructor
@Api("Staff controller methods")
public class StaffController {
    private final StaffService staffService;
    private final EmployeeStaffService employeeStaffService;
    private final DepartmentService departmentService;
    private final ProfessionService professionService;

    @GetMapping("/list")
    @ApiOperation("Staffs list page")
    public String list(Model model) {
        var staffs = staffService.findAll();
        List<StaffDto> result = new ArrayList<>();
        for (Staff staff : staffs) {
            result.add(new StaffDto(staff, employeeStaffService.countAllByStaff(staff)));
        }

        model.addAttribute("staffDtos", result);

        return "staffs/list";
    }

    @PostMapping("/save")
    @ApiOperation("Saving new or edited staff object")
    public String save(@ModelAttribute("staff") Staff staff) {
        Staff searchStaff = staffService.findByDepartmentAndProfession(staff.getDepartment(), staff.getProfession());
        if (searchStaff != null) {
            throw new StaffExistedException(String.format("Attempt to change existed staff: %s", searchStaff.getId()));
        }

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
