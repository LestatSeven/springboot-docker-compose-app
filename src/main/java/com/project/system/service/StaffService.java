package com.project.system.service;

import com.project.system.model.Staff;
import java.util.List;

public interface StaffService {
    List<Staff> findAll();
    Staff findById(Integer id);
    void save(Staff staff);
    void deleteById(Integer id);
}
