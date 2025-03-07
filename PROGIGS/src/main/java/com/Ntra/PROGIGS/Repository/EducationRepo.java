package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepo extends JpaRepository<Education, Integer> {
}
