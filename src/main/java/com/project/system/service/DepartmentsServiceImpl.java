package com.project.system.service;

import com.project.system.model.Department;
import com.project.system.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentsServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentsServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Integer id) {
        Optional<Department> result = departmentRepository.findById(id);
        Department department = null;

        if (result.isPresent()) {
            department = result.get();
        } else {
            throw new RuntimeException("Did not find department id - " + id);
        }
        return department;
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void deleteById(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department findRoot() {
        return departmentRepository.findDepartmentByParent(null);
    }
}
