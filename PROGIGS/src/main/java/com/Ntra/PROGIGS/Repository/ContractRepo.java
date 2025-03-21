package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Contract;
import com.Ntra.PROGIGS.Entity.ContractStatus;
import com.Ntra.PROGIGS.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepo extends JpaRepository<Contract,Integer> {

    List<Contract> findAllByFreelancer(User user);

    List<Contract> findAllByClient(User user);
    List<Contract> findAllByClientAndStatus(@Param("user") User user, @Param("status") ContractStatus status);
    List<Contract> findAllByFreelancerAndStatus(@Param("user") User user, @Param("status") ContractStatus status);
}
