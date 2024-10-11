package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Integer> {
}
