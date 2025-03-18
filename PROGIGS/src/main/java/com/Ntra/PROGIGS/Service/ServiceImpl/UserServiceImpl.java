package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.UserDto;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Exception.NoContentException;
import com.Ntra.PROGIGS.Mapper.UserMapper;
import com.Ntra.PROGIGS.Repository.UserRepo;
import com.Ntra.PROGIGS.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.Ntra.PROGIGS.Entity.UserRole.FREELANCER;

@Service

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo repo;
@Autowired
private UserMapper userMapper;
    @Override
    public List<UserDto> getAllFreelancer(){

        try {
            List<User> users =repo.findAllByRole(FREELANCER);
            return users.stream().map(userMapper::mapToUserDtoCard).toList();
        }catch (NoContentException e){
            throw new NoContentException("No_Content");
        }
    }
}
