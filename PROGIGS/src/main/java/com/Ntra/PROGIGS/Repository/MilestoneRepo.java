package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilestoneRepo extends JpaRepository<Module, Integer> {
}
