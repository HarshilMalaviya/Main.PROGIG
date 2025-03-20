package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    List<User> findAllByRole(UserRole role);

    User findByProfile(Profile profile);
}
