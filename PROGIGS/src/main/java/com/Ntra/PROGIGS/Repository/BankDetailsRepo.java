package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailsRepo extends JpaRepository<BankDetails, Integer> {
}
