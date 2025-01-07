package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.UserDto;
import com.Ntra.PROGIGS.Entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;
    public UserDto mapptoUserDto(User users) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        UserDto userDto = new UserDto();
        userDto = new ModelMapper().map(users, UserDto.class);
        return userDto;
    }
    public User mapptoUser(UserDto userDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        User user = new User();
        user = new ModelMapper().map(userDto, User.class);
        return user;
    }
}
