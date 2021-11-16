package com.project.system.controller;

import com.project.system.model.Department;
import com.project.system.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/departments")
@Api("Department controller methods")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/list")
    @ApiOperation("Departments list page")
    public String listDepartments(Model model) {
        var departments = departmentService.findAll();

        model.addAttribute("departments", departments);
        return "departments/list";
    }
}
