package com.project.system.repository;

import com.project.system.model.EmployeeStaff;
import com.project.system.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeStaffRepository extends JpaRepository<EmployeeStaff, Integer> {
    List<EmployeeStaff> findAllByStaff(Staff staff);
}
