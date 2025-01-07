package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.ProfileDto;
import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Exception.NoContentException;
import com.Ntra.PROGIGS.Mapper.ProfileMapper;
import com.Ntra.PROGIGS.Repository.ProfileRepo;
import com.Ntra.PROGIGS.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private ProfileMapper profileMapper;
    @Override
    public Profile saveProfile(ProfileDto profile) {
//        ProfileDto profileDto=this.profileMapper.MapptoProfileDto(profile);
        return profileRepo.save(profileMapper.MapptoProfile(profile));
    }

    @Override
    public List<ProfileDto> getProfile() {
        List<ProfileDto> profileList= this.profileRepo.findAll().stream().map(profileMapper::MapptoProfileDto).toList();
       return profileList;

    }

    @Override
    public Profile editProfile(ProfileDto profile) {
        Profile exsistingProfile= null;
        try {
            exsistingProfile = profileRepo.findById(profile.getId()).orElseThrow(()->new RuntimeException("NO_SUCH_Profile"));
        } catch (NoContentException e) {
            throw new NoContentException("NO_SUCH_JOB");
        }
        ProfileDto profileDto=this.profileMapper.MapptoProfileDto(exsistingProfile);
//        profileDto.setAddress(profileDto.getAddress());
        profileDto.setZipCode(profile.getZipCode());
        profileDto.setCity(profile.getCity());
        profileDto.setState(profile.getState());
        profileDto.setCountry(profile.getCountry());
        profileDto.setCompanyName(profile.getCompanyName());
        profileDto.setLocation(profile.getLocation());
        profileDto.setExperience(profile.getExperience());
        profileDto.setEducation(profile.getEducation());
        profileDto.setArticles(profile.getArticles());
        profileDto.setCertification(profile.getCertification());
//        profileDto.setReview(profileDto.getReview());
//        profileDto.setPortfolio(profileDto.getPortfolio());
        return profileRepo.save(profileMapper.MapptoProfile(profileDto));

    }

}