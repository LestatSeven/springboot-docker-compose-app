package com.project.system.controller;

import com.project.system.model.Employee;
import com.project.system.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        //employees.forEach(employee -> System.out.println(employee));

        model.addAttribute("employees", employees);
        return "employees/list";
    }
}
