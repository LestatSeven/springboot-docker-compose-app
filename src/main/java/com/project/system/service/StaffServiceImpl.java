package com.project.system.service;

import com.project.system.model.Staff;
import com.project.system.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService{

    @Autowired
    private StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff findById(Integer id) {
        var result = staffRepository.findById(id);
        Staff staff = null;

        if (result.isPresent()) {
            staff = result.get();
        } else {
            throw new RuntimeException("Did not find staff with id - " + id);
        }

        return staff;
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
