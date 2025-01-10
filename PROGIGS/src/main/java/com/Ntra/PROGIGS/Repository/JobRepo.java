package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Jobs, Integer> {

    Jobs findById(int id);

    List<Jobs> findBySkillsRequired(String skills);

    List<Jobs> findBySkillsRequiredIn(List<String> skills);
}
