package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.DTOs.ProfileDtoForGet;
import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Integer> {
    Optional<Profile> findByUser(User user);

    @Query("SELECT p FROM Profile p WHERE LOWER(p.Location) LIKE LOWER(CONCAT('%', :country, '%'))")
    List<Profile> findByCountry(String country);

    Profile findByEmail(String email);
}
