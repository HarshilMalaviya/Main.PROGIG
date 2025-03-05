package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    };
}
