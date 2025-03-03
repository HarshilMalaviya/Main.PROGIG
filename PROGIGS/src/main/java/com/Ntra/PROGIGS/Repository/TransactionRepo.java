package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
}
