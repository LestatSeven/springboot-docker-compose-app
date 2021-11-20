package com.project.system.controller;

import com.project.system.entity.Employee;
import com.project.system.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
@Api("Employee controller methods")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/list")
    @ApiOperation("Employees list page")
    public String list(Model model) {
        var employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        return "employees/list";
    }

    @PostMapping("/save")
    @ApiOperation("Saving new or edited employee object")
    public String save(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);

        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    @ApiOperation("Delete employee by id")
    public String delete(@RequestParam("id") Integer id) {
        employeeService.deleteById(id);

        return "redirect:/employees/list";
    }

    @GetMapping("/showAddForm")
    @ApiOperation("Form for adding employee")
    public String showAddForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employees/form";
    }

    @GetMapping("/showUpdateForm")
    @ApiOperation("Form for updating employee")
    public String showUpdateForm(@RequestParam("id") Integer id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);

        return "employees/form";
    }
}
