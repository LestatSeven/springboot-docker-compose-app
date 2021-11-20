package com.project.system.service;

import com.project.system.entity.Profession;
import com.project.system.repository.ProfessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionServiceImpl implements ProfessionService {
    private final ProfessionRepository professionRepository;

    @Override
    public List<Profession> findAll() {
        return professionRepository.findAll();
    }

    @Override
    public Profession findById(Integer id) {
        return professionRepository.findById(id).orElseThrow(() -> new IllegalStateException("Did not find profession with id - " + id));
    }

    @Override
    public void save(Profession profession) {
        professionRepository.save(profession);
    }

    @Override
    public void deleteById(Integer id) {
        professionRepository.deleteById(id);
    }
}
