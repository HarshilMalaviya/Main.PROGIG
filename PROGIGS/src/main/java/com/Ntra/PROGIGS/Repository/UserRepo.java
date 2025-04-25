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
    @Query(value = "SELECT DISTINCT u.* " +
            "FROM user u " +
            "JOIN demo1.profile p ON u.profile_id = p.id " +
            "LEFT JOIN demo1.profile_skills ps ON p.id = ps.profile_id " +
            "WHERE u.role = :role " +
            "AND ( " +
            "   LOWER(p.field_of_work) LIKE LOWER(CONCAT('%', :input, '%')) " +
            "   OR LOWER(ps.skills) LIKE LOWER(CONCAT('%', :input, '%')) " +
            "   OR ( :input = 'Graphic designers' AND ( " +
            "         LOWER(ps.skills) LIKE '%adobe photoshop%' " +
            "         OR LOWER(ps.skills) LIKE '%illustrator%' " +
            "         OR LOWER(ps.skills) LIKE '%branding%' " +
            "         OR LOWER(ps.skills) LIKE '%figma%' " +
            "         OR LOWER(p.field_of_work) LIKE '%graphic designer%' " +
            "   )) " +
            "   OR ( :input = 'Software developers' AND ( " +
            "         LOWER(ps.skills) LIKE '%java%' " +
            "         OR LOWER(ps.skills) LIKE '%spring boot%' " +
            "         OR LOWER(ps.skills) LIKE '%python%' " +
            "         OR LOWER(ps.skills) LIKE '%django%' " +
            "         OR LOWER(p.field_of_work) LIKE '%software developer%' " +
            "   )) " +
            "   OR ( :input = 'Website designers' AND ( " +
            "         LOWER(ps.skills) LIKE '%html%' " +
            "         OR LOWER(ps.skills) LIKE '%css%' " +
            "         OR LOWER(ps.skills) LIKE '%javascript%' " +
            "         OR LOWER(ps.skills) LIKE '%react%' " +
            "         OR LOWER(ps.skills) LIKE '%wordpress%' " +
            "         OR LOWER(p.field_of_work) LIKE '%website designer%' " +
            "   )) " +
            "   OR ( :input = '3D artists' AND ( " +
            "         LOWER(ps.skills) LIKE '%blender%' " +
            "         OR LOWER(ps.skills) LIKE '%3d modeling%' " +
            "         OR LOWER(ps.skills) LIKE '%texturing%' " +
            "         OR LOWER(p.field_of_work) LIKE '%3d artist%' " +
            "   )) " +
            "   OR ( :input = 'Mobile app developers' AND ( " +
            "         LOWER(ps.skills) LIKE '%flutter%' " +
            "         OR LOWER(ps.skills) LIKE '%react native%' " +
            "         OR LOWER(ps.skills) LIKE '%android%' " +
            "         OR LOWER(p.field_of_work) LIKE '%mobile app developer%' " +
            "   )) " +
            "   OR ( :input = 'Illustration' AND ( " +
            "         LOWER(ps.skills) LIKE '%illustration%' " +
            "         OR LOWER(ps.skills) LIKE '%digital illustration%' " +
            "         OR LOWER(p.field_of_work) LIKE '%illustrator%' " +
            "   )) " +
            "   OR ( :input = 'Web developers' AND ( " +
            "         LOWER(ps.skills) LIKE '%html%' " +
            "         OR LOWER(ps.skills) LIKE '%css%' " +
            "         OR LOWER(ps.skills) LIKE '%javascript%' " +
            "         OR LOWER(ps.skills) LIKE '%react%' " +
            "         OR LOWER(p.field_of_work) LIKE '%web developer%' " +
            "   )) " +
            "   OR ( :input = 'Java' AND ( " +
            "         LOWER(ps.skills) LIKE '%java%' " +
            "         OR LOWER(p.field_of_work) LIKE '%java developer%' " +
            "   )) " +
            "   OR ( :input = 'Spring boot' AND ( " +
            "         LOWER(ps.skills) LIKE '%spring boot%' " +
            "         OR LOWER(p.field_of_work) LIKE '%spring boot developer%' " +
            "   )) " +
            "   OR ( :input = 'SEO specialists' AND ( " +
            "         LOWER(ps.skills) LIKE '%seo%' " +
            "         OR LOWER(p.field_of_work) LIKE '%seo specialist%' " +
            "   )) " +
            "   OR ( :input = 'Data entry clerks' AND ( " +
            "         LOWER(p.field_of_work) LIKE '%data entry%' " +
            "   )) " +
            "   OR ( :input = 'Virtual assistants' AND ( " +
            "         LOWER(p.field_of_work) LIKE '%virtual assistant%' " +
            "   )) " +
            "   OR ( :input = 'Translators' AND ( " +
            "         LOWER(p.field_of_work) LIKE '%translator%' " +
            "   )) " +
            "   OR ( :input = 'Financial experts' AND ( " +
            "         LOWER(p.field_of_work) LIKE '%financial expert%' " +
            "   )) " +
            "   OR ( :input = 'Manufacturers' AND ( " +
            "         LOWER(p.field_of_work) LIKE '%manufacturer%' " +
            "   )) " +
            "   OR ( :input = 'Logistics experts' AND ( " +
            "         LOWER(p.field_of_work) LIKE '%logistics expert%' " +
            "   )) " +
            "   OR ( :input = 'Fashion designers' AND ( " +
            "         LOWER(p.field_of_work) LIKE '%fashion designer%' " +
            "   )) " +
            ")", nativeQuery = true)
    List<User> findBySkillOrFieldOfWork(@Param("input") String input, @Param("role") String role);


    @Query("SELECT u FROM User u JOIN u.profile p " +
            "WHERE LOWER(p.Location) LIKE LOWER(CONCAT('%', :country, '%')) " +
            "AND u.role = :role")
    List<User> findByCountry(@Param("country") String country, UserRole role);

    User findByProfile(Profile profile);



}
