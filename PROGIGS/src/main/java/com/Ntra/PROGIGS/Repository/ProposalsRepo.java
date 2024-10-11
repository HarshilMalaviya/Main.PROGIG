package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Proposals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalsRepo extends JpaRepository<Proposals , Integer> {
}
