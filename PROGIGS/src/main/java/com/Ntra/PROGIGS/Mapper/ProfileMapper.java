package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.ProfileDto;
import com.Ntra.PROGIGS.Entity.Profile;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class ProfileMapper {
    @Autowired
    private ModelMapper modelMapper;
    public ProfileDto MapptoProfileDto(Optional<Profile> profile) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ProfileDto profileDto = new ProfileDto();
        profileDto = new ModelMapper().map(profile, ProfileDto.class);
        return profileDto;
    }
    public Profile MapptoProfile(ProfileDto profileDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Profile profile = new Profile();
        profile = new ModelMapper().map(profileDto, Profile.class);
        return profile;
    }
}
