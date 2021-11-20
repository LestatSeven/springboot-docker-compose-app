package com.project.system.controller;

import com.project.system.model.EmployeeStaff;
import com.project.system.model.Staff;
import com.project.system.service.EmployeeService;
import com.project.system.service.EmployeeStaffService;
import com.project.system.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee_staffs")
@Api("Employee staff controller methods")
public class EmployeeStaffController {
    private EmployeeStaffService employeeStaffService;
    private EmployeeService employeeService;
    private StaffService staffService;

    @Autowired
    public EmployeeStaffController(EmployeeStaffService employeeStaffService, EmployeeService employeeService, StaffService staffService) {
        this.employeeStaffService = employeeStaffService;
        this.employeeService = employeeService;
        this.staffService = staffService;
    }

    @GetMapping("/list")
    @ApiOperation("Employee staffs list page")
    public String list(@RequestParam("staffId") Integer staffId, Model model) {
        Staff staff = staffService.findById(staffId);
        var employeeStaffs = employeeStaffService.findAllByStaff(staff);

        model.addAttribute("staff", staff);
        model.addAttribute("employeeStaffs", employeeStaffs);

        return "employee_staffs/list";
    }

    @PostMapping("/save")
    @ApiOperation("Saving new or edited employee staff object")
    public String save(@ModelAttribute("employeeStaff") EmployeeStaff employeeStaff) {
        employeeStaffService.save(employeeStaff);
        return String.format("redirect:/employee_staffs/list?staffId=%s", employeeStaff.getStaff().getId());
    }

    @GetMapping("/delete")
    @ApiOperation("Delete employee staff by id")
    public String delete(@RequestParam("id") Integer id) {
        employeeStaffService.deleteById(id);

        return "redirect:/employee_staffs/list";
    }

    @GetMapping("/showAddForm")
    @ApiOperation("Form for adding employee staff")
    public String showAddForm(Model model) {
        EmployeeStaff employeeStaff = new EmployeeStaff();
        var employees = employeeService.findAll();

        model.addAttribute("employeeStaff", employeeStaff);
        model.addAttribute("employees", employees);

        return "employee_staffs/form";
    }

    @GetMapping("/showUpdateForm")
    @ApiOperation("Form for updating employee staff")
    public String showUpdateForm(@RequestParam("id") Integer id, Model model) {
        EmployeeStaff employeeStaff = employeeStaffService.findById(id);
        var employees = employeeService.findAll();

        model.addAttribute("employeeStaff", employeeStaff);
        model.addAttribute("employees", employees);

        return "employee_staffs/form";
    }
}
