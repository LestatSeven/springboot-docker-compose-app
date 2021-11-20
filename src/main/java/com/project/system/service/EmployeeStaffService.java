package com.project.system.service;

import com.project.system.entity.Employee;
import com.project.system.entity.EmployeeStaff;
import com.project.system.entity.Staff;

import java.util.List;

public interface EmployeeStaffService {
    List<EmployeeStaff> findAll();
    EmployeeStaff findById(Integer id);
    void save(EmployeeStaff employeeStaff);
    void deleteById(Integer id);
    List<EmployeeStaff> findAllByStaff(Staff staff);
    Integer countAllByStaff(Staff staff);
    List<EmployeeStaff> findAllByEmployee(Employee employee);
}