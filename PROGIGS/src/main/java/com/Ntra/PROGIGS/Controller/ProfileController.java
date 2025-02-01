package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.ProfileDto;
import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Service.ProfileService;
import com.Ntra.PROGIGS.Service.ServiceImpl.ProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private  final ProfileService profileService;


    @PostMapping
    public Profile saveProfile(@RequestBody ProfileDto profile) {
        return profileService.saveProfile(profile);
    }

    @GetMapping
    public List<ProfileDto> getProfile() {
        return profileService.getProfile();
    }
    @PutMapping
    public Profile editProfile(@RequestBody ProfileDto profileDto) {
        return profileService.editProfile(profileDto);
    }


}
