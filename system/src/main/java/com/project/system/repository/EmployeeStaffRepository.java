package com.project.system.repository;

import com.project.system.entity.Employee;
import com.project.system.entity.EmployeeStaff;
import com.project.system.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeStaffRepository extends JpaRepository<EmployeeStaff, Integer> {
    List<EmployeeStaff> findAllByStaff(Staff staff);
    Integer countAllByStaff(Staff staff);
    List<EmployeeStaff> findAllByEmployee(Employee employee);
}
