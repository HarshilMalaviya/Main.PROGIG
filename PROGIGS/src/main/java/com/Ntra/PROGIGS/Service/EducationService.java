package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.EducationDto;

public interface EducationService {
    public EducationDto addEducation(EducationDto education);


    void deleteEducation(int id);
}
