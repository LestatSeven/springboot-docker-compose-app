package com.project.system.controller;

import com.project.system.entity.EmployeeStaff;
import com.project.system.entity.Staff;
import com.project.system.exceptions.EmployeeStaffIntersectionedDateBeginException;
import com.project.system.exceptions.EmployeeStaffIntersectionedDateEndException;
import com.project.system.exceptions.EmployeeStaffNullDateBeginException;
import com.project.system.exceptions.EmployeeStaffNullEmployeeException;
import com.project.system.service.EmployeeService;
import com.project.system.service.EmployeeStaffService;
import com.project.system.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee_staffs")
@Api("Employee staff controller methods")
public class EmployeeStaffController {
    private final EmployeeStaffService employeeStaffService;
    private final EmployeeService employeeService;
    private final StaffService staffService;

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
        if (employeeStaff.getEmployee() == null) {
            throw new EmployeeStaffNullEmployeeException(String.format("Attempt to save employee staff with null employee: %s", employeeStaff.getId()));
        }

        if (employeeStaff.getDateBegin() == null) {
            throw new EmployeeStaffNullDateBeginException(String.format("Attempt to save employee staff with null date begin: %s", employeeStaff.getId()));
        }

        var searchedEmployeeStaffs = employeeStaffService.findAllByEmployee(employeeStaff.getEmployee());
        searchedEmployeeStaffs.removeIf(employeeStaff1 -> employeeStaff1.getId().equals(employeeStaff.getId()));

        for (EmployeeStaff searchedEmployeeStaff: searchedEmployeeStaffs) {
            LocalDate searchedDateEnd = Optional.ofNullable(searchedEmployeeStaff.getDateEnd()).orElse(LocalDate.MAX);
            LocalDate savingdDateEnd = Optional.ofNullable(employeeStaff.getDateEnd()).orElse(LocalDate.MAX);

            if (employeeStaff.getDateBegin().isAfter(searchedEmployeeStaff.getDateBegin()) && employeeStaff.getDateBegin().isBefore(searchedDateEnd)) {
                throw new EmployeeStaffIntersectionedDateBeginException(String.format("Attempt to save employee staff with intersectioned date begin: \"%s\",  found id=[%d]", searchedEmployeeStaff.getDateBegin().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), searchedEmployeeStaff.getId()));
            }

            if (savingdDateEnd.isAfter(searchedEmployeeStaff.getDateBegin()) && savingdDateEnd.isAfter(searchedDateEnd) && !savingdDateEnd.isEqual(searchedDateEnd)) {
                throw new EmployeeStaffIntersectionedDateEndException(String.format("Attempt to save employee staff with intersectioned date end: \"%s\" found id=[%d]", searchedEmployeeStaff.getDateEnd().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), searchedEmployeeStaff.getId()));
            }
        }

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
