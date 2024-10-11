package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Service.ServiceImpl.UserServiceImpl;
import com.Ntra.PROGIGS.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public User saveUser(@RequestBody User user) {

        return userService.saveUser(user);
    }

}
