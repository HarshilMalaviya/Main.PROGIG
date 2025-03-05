package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.Entity.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ProfileService {
    public Map uploadImage(MultipartFile file, int profileId);
    public Profile editeProfile(Profile profile,int id);
}
