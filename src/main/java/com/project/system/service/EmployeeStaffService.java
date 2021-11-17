package com.project.system.service;

import com.project.system.model.EmployeeStaff;
import com.project.system.model.Staff;

import java.util.List;

public interface EmployeeStaffService {
    List<EmployeeStaff> findAll();
    EmployeeStaff findById(Integer id);
    void save(EmployeeStaff employeeStaff);
    void deleteById(Integer id);
    List<EmployeeStaff> findAllByStaff(Staff staff);
}
