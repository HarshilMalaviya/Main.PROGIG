package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.EducationDto;

public interface EducationService {
    public void addEducation(EducationDto education);
    public void editEducation(EducationDto education, int id);
}
