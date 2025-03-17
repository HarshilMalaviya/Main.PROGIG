package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Proposals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalsRepo extends JpaRepository<Proposals , Integer> {

    @Query("SELECT p FROM Proposals p WHERE p.jobs.id = :jobId")
    List<Proposals> findAllProposalsByJobs(@Param("jobId") int jobId);

}
