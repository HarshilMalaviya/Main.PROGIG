package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.UserDto;
import com.Ntra.PROGIGS.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<UserDto> getAllFreelancer();

    List<UserDto> getAllFreelancerBySkill(String skill);

    List<UserDto> getAllFreelancerByLocation(String country);

    List<UserDto> getTopFreelancer();
}
