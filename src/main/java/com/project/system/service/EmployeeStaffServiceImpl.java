package com.project.system.service;

import com.project.system.model.EmployeeStaff;
import com.project.system.model.Staff;
import com.project.system.repository.EmployeeStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeStaffServiceImpl implements EmployeeStaffService {
    private EmployeeStaffRepository employeeStaffRepository;

    @Autowired
    public EmployeeStaffServiceImpl(EmployeeStaffRepository employeeStaffRepository) {
        this.employeeStaffRepository = employeeStaffRepository;
    }

    @Override
    public List<EmployeeStaff> findAll() {
        return employeeStaffRepository.findAll();
    }

    @Override
    public EmployeeStaff findById(Integer id) {
        var result = employeeStaffRepository.findById(id);
        EmployeeStaff employeeStaff = null;

        if (result.isPresent()) {
            employeeStaff = result.get();
        } else {
            throw new RuntimeException("Did not find employee staff with id - " + id);
        }

        return employeeStaff;
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
}
