package com.project.system.service;

import com.project.system.entity.Employee;
import com.project.system.entity.EmployeeStaff;
import com.project.system.entity.Staff;
import com.project.system.repository.EmployeeStaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeStaffServiceImpl implements EmployeeStaffService {
    private final EmployeeStaffRepository employeeStaffRepository;

    @Override
    public List<EmployeeStaff> findAll() {
        return employeeStaffRepository.findAll();
    }

    @Override
    public EmployeeStaff findById(Integer id) {
        return employeeStaffRepository.findById(id).orElseThrow(() -> new IllegalStateException("Did not find employee staff with id - " + id));
    }

    @Override
    public void save(EmployeeStaff employeeStaff) {
        employeeStaffRepository.save(employeeStaff);
    }

    @Override
    public void deleteById(Integer id) {
        employeeStaffRepository.deleteById(id);
    }

    @Override
    public List<EmployeeStaff> findAllByStaff(Staff staff) {
        return employeeStaffRepository.findAllByStaff(staff);
    }

    @Override
    public Integer countAllByStaff(Staff staff) {
        return employeeStaffRepository.countAllByStaff(staff);
    }

    @Override
    public List<EmployeeStaff> findAllByEmployee(Employee employee) {
        return employeeStaffRepository.findAllByEmployee(employee);
    }
}
