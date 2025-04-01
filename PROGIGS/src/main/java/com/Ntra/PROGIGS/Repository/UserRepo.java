package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    List<User> findAllByRole(UserRole role);
    @Query(value = "SELECT DISTINCT u.* FROM user u " +
            "JOIN profile p ON u.profile_id = p.id " +
            "LEFT JOIN profile_skills ps ON p.id = ps.profile_id " +
            "WHERE (LOWER(ps.skills) LIKE LOWER(CONCAT('%', :input, '%')) " +
            "OR LOWER(p.field_of_work) LIKE LOWER(CONCAT('%', :input, '%'))) " +
            "AND u.role = :role",  // Add filtering for user role
            nativeQuery = true)
    List<User> findBySkillOrFieldOfWork(@Param("input") String input,UserRole role);






    User findByProfile(Profile profile);
}
