package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Integer> {

}
