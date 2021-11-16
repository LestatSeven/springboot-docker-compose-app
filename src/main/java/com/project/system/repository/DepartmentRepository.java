package com.project.system.repository;

import com.project.system.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findDepartmentByParent(Integer id);
}
