package com.project.system.service;

import com.project.system.entity.Department;
import com.project.system.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentsServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Integer id) {
        return departmentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Did not find department with id - " + id));
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
