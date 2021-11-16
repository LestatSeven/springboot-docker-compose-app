package com.project.system.service;

import com.project.system.model.Profession;

import java.util.List;

public interface ProfessionService {
    List<Profession> findAll();
    Profession findById(Integer id);
    void save(Profession profession);
    void deleteById(Integer id);
}
