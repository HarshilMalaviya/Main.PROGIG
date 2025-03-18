package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.EducationDto;
import com.Ntra.PROGIGS.Service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile/education")
public class EducationController {
    @Autowired
    private EducationService educationService;

    @PostMapping
    public void addEducation(@RequestBody EducationDto education) {
        educationService.addEducation(education);
    }

    @PutMapping("/{id}")
    public void editEducation(@RequestBody EducationDto education,@PathVariable int id){
        educationService.editEducation(education,id);
    }
}
