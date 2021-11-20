package com.project.system.controller;

import com.project.system.exceptions.DepartmentFewRootException;
import com.project.system.exceptions.DepartmentRootUnnullParentException;
import com.project.system.model.Department;
import com.project.system.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
    public String list(Model model) {
        var departments = departmentService.findAll();

        model.addAttribute("departments", departments);
        return "departments/list";
    }

    @PostMapping("/save")
    @ApiOperation("Saving new or edited profession object")
    public String save(@ModelAttribute("department") Department department) {
        Department rootDepartment = departmentService.findRoot();
        if (rootDepartment != null && department.getParent() != null && rootDepartment.getId() == department.getId()) {
            throw new DepartmentRootUnnullParentException("Attempt to change parent id in root department: " + rootDepartment.getId());
        }

        if(rootDepartment != null && department.getParent() == null && rootDepartment.getId() != department.getId()) {
            throw new DepartmentFewRootException("Attempt to make few root departments: " + department.getId());
        }

        departmentService.save(department);

        return "redirect:/departments/list";
    }

    @GetMapping("/delete")
    @ApiOperation("Delete department by id")
    public String delete(@RequestParam("id") Integer id) {
        departmentService.deleteById(id);

        return "redirect:/departments/list";
    }

    @GetMapping("/showAddForm")
    @ApiOperation("Form for adding department")
    public String showAddForm(Model model) {
        Department department = new Department();
        var departments = departmentService.findAll();

        model.addAttribute("department", department);
        model.addAttribute("departmentsList", departments);

        return "departments/form";
    }

    @GetMapping("/showUpdateForm")
    @ApiOperation("Form for updating department")
    public String showUpdateForm(@RequestParam("id") Integer id, Model model) {
        Department department = departmentService.findById(id);
        var departments = departmentService.findAll();
        departments.removeIf(dep -> dep.getId() == department.getId());

        model.addAttribute("department", department);
        model.addAttribute("departmentsList", departments);

        return "departments/form";
    }


}
