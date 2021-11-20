package com.project.system.service;

import com.project.system.entity.Department;
import com.project.system.entity.Profession;
import com.project.system.entity.Staff;
import java.util.List;

public interface StaffService {
    List<Staff> findAll();
    Staff findById(Integer id);
    void save(Staff staff);
    void deleteById(Integer id);
    Staff findByDepartmentAndProfession(Department department, Profession profession);
}
