package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.ProfileDtoForCard;
import com.Ntra.PROGIGS.DTOs.UserDto;
import com.Ntra.PROGIGS.Entity.User;
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

    public UserDto mapToUserDtoCard(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setJoiningDate(user.getJoiningDate());
        dto.setRole(user.getRole());

        if (user.getProfile() != null) {
            ProfileDtoForCard profileDto = new ProfileDtoForCard();
            profileDto.setId(user.getProfile().getId());
            profileDto.setFirstName(user.getProfile().getFirstName());
            profileDto.setLastName(user.getProfile().getLastName());
            profileDto.setFieldOfWork(user.getProfile().getFieldOfWork());
            profileDto.setImageUrl(user.getProfile().getImageUrl());
            profileDto.setLocation(user.getProfile().getLocation());
            profileDto.setHourlyRate(user.getProfile().getHourlyRate());

            dto.setProfileDtoForCard(profileDto);
        }

        return dto;
    }
}
