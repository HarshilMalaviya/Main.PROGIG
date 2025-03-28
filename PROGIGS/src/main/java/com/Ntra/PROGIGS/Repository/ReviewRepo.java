package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Review;
import com.Ntra.PROGIGS.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {


    List<Review> findByUser(User user);
}
