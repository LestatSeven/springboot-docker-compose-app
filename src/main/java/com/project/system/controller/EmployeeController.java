package com.project.system.controller;

import com.project.system.model.Employee;
import com.project.system.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/employees")
@Api("Employee controller methods")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    @ApiOperation("Employees list page")
    public String listEmployees(Model model) {
        var employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        return "employees/list";
    }

    @GetMapping("/showFormForAdd")
    @ApiOperation("Form for adding employee")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employees/form";
    }

    @PostMapping("/save")
    @ApiOperation("Saving new or edited employee object")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);

        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    @ApiOperation("Form for updating employee")
    public String showFormForUpdate(@RequestParam("id") Integer id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);

        return "employees/form";
    }

    @GetMapping("/delete")
    @ApiOperation("Delete employee by id")
    public String deleteEmployee(@RequestParam("id") Integer id) {
        employeeService.deleteById(id);

        return "redirect:/employees/list";
    }
}
