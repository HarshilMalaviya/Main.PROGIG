package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Certificates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepo extends JpaRepository<Certificates, Integer> {
}
