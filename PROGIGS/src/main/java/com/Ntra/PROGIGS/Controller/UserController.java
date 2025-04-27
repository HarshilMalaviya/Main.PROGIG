package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.UserDto;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Service.ServiceImpl.UserServiceImpl;
import com.Ntra.PROGIGS.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllFreelancer());
    }

    @GetMapping("/bySkill/{skill}")
    public ResponseEntity<List<UserDto>> getAllUsersBySkill(@PathVariable String skill){
        return ResponseEntity.ok(userService.getAllFreelancerBySkill(skill));
    }

    @GetMapping("/byCountry/{country}")
    public ResponseEntity<List<UserDto>> getAllUsersByLocation(@PathVariable String country){
        return ResponseEntity.ok(userService.getAllFreelancerByLocation(country));
    }

    @GetMapping("/top-Freelancer")
    public ResponseEntity<List<UserDto>> getTopFreelancer(){
        return ResponseEntity.ok(userService.getTopFreelancer());
    }


}
