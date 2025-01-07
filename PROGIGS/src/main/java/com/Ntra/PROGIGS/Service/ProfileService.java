package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.ProfileDto;
import com.Ntra.PROGIGS.Entity.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProfileService {
    public Profile saveProfile(ProfileDto profile);
    public List<ProfileDto> getProfile();
    public Profile editProfile(ProfileDto profileDto);

}
