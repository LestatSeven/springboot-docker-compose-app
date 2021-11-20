package com.project.system.service;

import com.project.system.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    Department findById(Integer id);
    void save(Department department);
    void deleteById(Integer id);
    Department findRoot();
}
