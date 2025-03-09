package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.EducationDto;
import com.Ntra.PROGIGS.Entity.Education;

public interface EducationService {
    public void addEducation(EducationDto education);
    public void editeEducation(EducationDto education,int id);
}
