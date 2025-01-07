package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.ProfileDto;
import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Exception.NoContentException;
import com.Ntra.PROGIGS.Mapper.ProfileMapper;
import com.Ntra.PROGIGS.Repository.ProfileRepo;
import com.Ntra.PROGIGS.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service


public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired(required = true)
    private ProfileMapper profileMapper;
    @Override
    public Profile saveProfile(Profile profile) {
        ProfileDto profileDto1=this.profileMapper.MapptoProfileDto(Optional.ofNullable(profile));
        return this.profileMapper.MapptoProfile(profileDto1);
    }

    @Override
    public Profile getProfile(int profileId) {
       return null;

    }

    @Override
    public Profile editProfile(ProfileDto profile) {
        Optional<Profile> exsistingProfile= null;
        try {
            exsistingProfile = profileRepo.findById(profile.getProfileId());
        } catch (NoContentException e) {
            throw new NoContentException("NO_SUCH_JOB");
        }
        ProfileDto profileDto=this.profileMapper.MapptoProfileDto(exsistingProfile);
        profileDto.setProfileId(profileDto.getProfileId());
        profileDto.setAddress(profileDto.getAddress());
        profileDto.setZipCode(profileDto.getZipCode());
        profileDto.setCity(profileDto.getCity());
        profileDto.setState(profileDto.getState());
        profileDto.setCountry(profileDto.getCountry());
        profileDto.setCompanyName(profileDto.getCompanyName());
        profileDto.setLocation(profileDto.getLocation());
        profileDto.setExperience(profileDto.getExperience());
        profileDto.setEducation(profileDto.getEducation());
        profileDto.setArticles(profileDto.getArticles());
        profileDto.setCertification(profileDto.getCertification());
        profileDto.setReview(profileDto.getReview());
        profileDto.setPortfolio(profileDto.getPortfolio());
        return this.profileMapper.MapptoProfile(profileDto);
    }
}