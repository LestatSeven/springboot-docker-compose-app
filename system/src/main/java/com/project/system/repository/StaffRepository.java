package com.project.system.repository;

import com.project.system.entity.Department;
import com.project.system.entity.Profession;
import com.project.system.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer>{
    Staff findByDepartmentAndProfession(Department department, Profession profession);
}