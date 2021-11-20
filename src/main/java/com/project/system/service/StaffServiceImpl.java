package com.project.system.service;

import com.project.system.entity.Department;
import com.project.system.entity.Profession;
import com.project.system.entity.Staff;
import com.project.system.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{
    private final StaffRepository staffRepository;

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff findById(Integer id) {
        return staffRepository.findById(id).orElseThrow(() -> new IllegalStateException("Did not find staff with id - " + id));
    }

    @Override
    public Staff findByDepartmentAndProfession(Department department, Profession profession) {
        return staffRepository.findByDepartmentAndProfession(department, profession);
    }

    @Override
    public void save(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void deleteById(Integer id) {
        staffRepository.deleteById(id);
    }
}
