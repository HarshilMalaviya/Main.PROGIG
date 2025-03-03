package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepo extends JpaRepository<Contract,Integer> {
}
