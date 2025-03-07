package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.ProfileDto;
import com.Ntra.PROGIGS.DTOs.ProfileDtoForGet;
import com.Ntra.PROGIGS.Entity.LocalVariable;
import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private final ProfileService profileService;


    @PostMapping("/image/{profileId}")
    public Map uploadImage(@RequestParam("file")MultipartFile file,@PathVariable int profileId){
        Map data = this.profileService.uploadImage(file, profileId);
        return data;
    }

    @PutMapping("/edite")
    public Profile editeProfile(@RequestBody ProfileDto profile){
        Profile profile1 = this.profileService.editeProfile(profile);
        return profile1;
    }
    @GetMapping("/success-rate")
    public ResponseEntity<LocalVariable> getUserSuccessRate() {
        LocalVariable successData = profileService.updateUserSuccessRate();
        return ResponseEntity.ok(successData);
    }

    @GetMapping("/getProfile")
    public ProfileDtoForGet getProfile() {
        return profileService.getProfile();
    }

    @GetMapping("/by-country/{country}")
    public List<ProfileDtoForGet> getUsersByCountry(@PathVariable String country) {
        return profileService.getUsersByCountry(country);
    }
}
