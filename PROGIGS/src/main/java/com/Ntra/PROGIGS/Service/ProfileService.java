package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.ProfileDto;
import com.Ntra.PROGIGS.Entity.Profile;

public interface ProfileService {
    public Profile saveProfile(Profile profile);
    public Profile getProfile(int profileId);
    public Profile editProfile(ProfileDto profileDto);

}
