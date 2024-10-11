package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Repository.UserRepo;
import com.Ntra.PROGIGS.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        User createUser = this.userRepo.save(user);

        createUser.setFirstName(user.getFirstName());
        createUser.setLastName(user.getLastName());
        createUser.setEmail(user.getEmail());
        createUser.setUsername(user.getUsername());
        createUser.setPassword(user.getPassword());
        createUser.setDescription(user.getDescription());
        createUser.setRole(user.getRole());
        return createUser;
    }
}
