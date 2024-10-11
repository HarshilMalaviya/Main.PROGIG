package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Portfolio;
import com.Ntra.PROGIGS.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepo extends JpaRepository<Portfolio, Integer> {
}
