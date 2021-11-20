package com.project.system.repository;

import com.project.system.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository  extends JpaRepository<Profession, Integer> {
}
