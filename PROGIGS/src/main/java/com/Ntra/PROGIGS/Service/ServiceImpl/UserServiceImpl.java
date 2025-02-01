package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.UserDto;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Mapper.UserMapper;
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
@Autowired
private UserMapper userMapper;
    @Override
    public User saveUser(User user) {
        UserDto userDto = userMapper.mapptoUserDto(user);
        return this.userMapper.mapptoUser(userDto);
    }
}
