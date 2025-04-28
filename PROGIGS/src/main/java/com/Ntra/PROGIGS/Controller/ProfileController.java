package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.ProfileDto;
import com.Ntra.PROGIGS.DTOs.ProfileDtoForGet;
import com.Ntra.PROGIGS.DTOs.ProfileDtoForViewCard;
import com.Ntra.PROGIGS.Entity.LocalVariable;
import com.Ntra.PROGIGS.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private final ProfileService profileService;


    @PostMapping("/image")
    public Map uploadImage(@RequestParam("file")MultipartFile file){
        Map data = this.profileService.uploadImage(file);
        return data;
    }

    @PutMapping("/edit")
    public ProfileDto editeProfile(@RequestBody ProfileDto profile){
        ProfileDto profile1 = this.profileService.editeProfile(profile);
        return profile1;
    }
    @GetMapping("/success-rate/{id}")
    public ResponseEntity<LocalVariable> getUserSuccessRate(@PathVariable int id) {
        LocalVariable successData = profileService.updateUserSuccessRateById(id);
        return ResponseEntity.ok(successData);
    }
    @GetMapping("/get-international-clients")
    public List<ProfileDtoForViewCard> getInternationalClients() {
        return profileService.getInternationalClients();
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



    @GetMapping("/by-id/{id}")
    public ProfileDtoForViewCard getProfileById(@PathVariable int id) {
        return profileService.getProfileById(id);
    }

    @GetMapping("/image")
    public ResponseEntity<Map<String, String>> getProfileImage() {
        String imageUrl = profileService.getProfileImage();

        Map<String, String> response = new HashMap<>();
        response.put("imageUrl", imageUrl);

        return ResponseEntity.ok(response);
    }
}
