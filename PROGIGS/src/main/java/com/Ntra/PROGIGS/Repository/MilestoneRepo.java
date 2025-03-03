package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilestoneRepo extends JpaRepository<Milestone, Integer> {
}
