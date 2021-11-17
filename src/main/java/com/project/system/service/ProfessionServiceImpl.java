package com.project.system.service;

import com.project.system.model.Profession;
import com.project.system.repository.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionServiceImpl implements ProfessionService {
    private ProfessionRepository professionRepository;

    @Autowired
    public ProfessionServiceImpl(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }

    @Override
    public List<Profession> findAll() {
        return professionRepository.findAll();
    }

    @Override
    public Profession findById(Integer id) {
        Optional<Profession> result = professionRepository.findById(id);
        Profession profession = null;

        if(result.isPresent()) {
            profession = result.get();
        } else {
            throw new RuntimeException("Did not find profession with id - " + id);
        }

        return profession;
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
