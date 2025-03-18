package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.ProfileDtoForCard;
import com.Ntra.PROGIGS.DTOs.UserDto;
import com.Ntra.PROGIGS.Entity.LocalVariable;
import com.Ntra.PROGIGS.Entity.Review;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Filter.GetAuthenticatedUser;
import com.Ntra.PROGIGS.Repository.ProfileRepo;
import com.Ntra.PROGIGS.Repository.ReviewRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReviewRepo reviewRepo;



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

//    public UserDto mapToUserDtoCard(User user) {
//        UserDto dto = new UserDto();
//        dto.setId(user.getId());
//        dto.setUsername(user.getUsername());
//        dto.setJoiningDate(user.getJoiningDate());
//        dto.setRole(user.getRole());
//
//        if (user.getProfile() != null) {
//            ProfileDtoForCard profileDto = new ProfileDtoForCard();
//            profileDto.setId(user.getProfile().getId());
//            profileDto.setFirstName(user.getProfile().getFirstName());
//            profileDto.setLastName(user.getProfile().getLastName());
//            profileDto.setFieldOfWork(user.getProfile().getFieldOfWork());
//            profileDto.setImageUrl(user.getProfile().getImageUrl());
//            profileDto.setLocation(user.getProfile().getLocation());
//            profileDto.setHourlyRate(user.getProfile().getHourlyRate());
//
//            dto.setProfileDtoForCard(profileDto);
//        }
//
//        return dto;
//    }
    public UserDto mapToUserDtoCard(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setJoiningDate(user.getJoiningDate());
        dto.setRole(user.getRole());

        if (user.getProfile() != null) {
            List<Review> reviews = reviewRepo.findByUser(user);
            double totalRating = reviews.stream().mapToDouble(Review::getReview).sum();
            int totalReviews = reviews.size();

            LocalVariable localVariable = new LocalVariable();
            if (reviews.isEmpty()) {
                totalRating = 0.0;
                totalReviews = 0;
            }
            ProfileDtoForCard profileDto = new ProfileDtoForCard();
            profileDto.setId(user.getProfile().getId());
            profileDto.setFullName(user.getProfile().getFirstName() + " " + user.getProfile().getLastName());
            profileDto.setFieldOfWork(user.getProfile().getFieldOfWork());
            profileDto.setImageUrl(user.getProfile().getImageUrl());
            profileDto.setLocation(user.getProfile().getLocation());
            profileDto.setHourlyRate(user.getProfile().getHourlyRate());
            profileDto.setSkills(user.getProfile().getSkills());
            profileDto.setRating(totalRating / totalReviews);
            profileDto.setReviewCount(totalReviews);
            dto.setProfileDtoForCard(profileDto);
        }

        return dto;
    }
}
