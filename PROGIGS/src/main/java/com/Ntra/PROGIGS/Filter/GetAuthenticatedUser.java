package com.Ntra.PROGIGS.Filter;

import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class GetAuthenticatedUser {
    @Autowired
    private UserRepo userRepo;


    public User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userRepo.findByUsername(username);
        }
        throw new RuntimeException("User is not authenticated");
    }
}
