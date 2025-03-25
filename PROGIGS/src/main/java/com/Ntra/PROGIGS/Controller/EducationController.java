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
    public EducationDto addEducation(@RequestBody EducationDto education) {
        educationService.addEducation(education);
        return education;
    }

    @DeleteMapping("/{id}")
    public void editEducation(@PathVariable int id){
        educationService.deleteEducation(id);
    }
}
