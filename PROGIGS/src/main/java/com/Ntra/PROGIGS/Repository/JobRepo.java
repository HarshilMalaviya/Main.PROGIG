package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Jobs,Long> {

}
