package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.ProfileDto;
import com.Ntra.PROGIGS.DTOs.ProfileDtoForGet;
import com.Ntra.PROGIGS.DTOs.ProfileDtoForViewCard;
import com.Ntra.PROGIGS.Entity.LocalVariable;
import com.Ntra.PROGIGS.Entity.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProfileService {
    public Map uploadImage(MultipartFile file);
    public ProfileDto editeProfile(ProfileDto profile);
    public LocalVariable updateUserSuccessRate();

    public ProfileDtoForGet getProfile();

    List<ProfileDtoForGet> getUsersByCountry(String country);

    ProfileDtoForViewCard getProfileById(int id);
}
